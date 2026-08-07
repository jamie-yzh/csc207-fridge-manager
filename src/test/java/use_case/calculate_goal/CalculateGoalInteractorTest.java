package use_case.calculate_goal;

import data_access.InMemoryProfileRepository;
import entity.ActivityLevel;
import entity.NutritionGoal;
import entity.NutritionInfo;
import entity.Objective;
import entity.Sex;
import entity.UserProfile;
import org.junit.jupiter.api.Test;
import use_case.gateway.ProfileRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the calculate-goal interactor.
 *
 * <p>The interactor talks to the outside world only through two interfaces, so a
 * test can stand in for both: a <b>fake presenter</b> that just remembers what it
 * was handed, and the in-memory repository. No Swing, no files, no network — the
 * arithmetic is checked directly.
 */
class CalculateGoalInteractorTest {

    /** Doubles never compare exactly; allow a rounding-sized wobble. */
    private static final double DELTA = 1e-9;

    /**
     * Stands in for the real presenter: records the result instead of drawing it.
     * (This is the "fake" — a hand-written stub, no mocking library needed.)
     */
    private static class FakePresenter implements CalculateGoalOutputBoundary {

        private CalculateGoalOutputData received;

        @Override
        public void present(CalculateGoalOutputData outputData) {
            this.received = outputData;
        }
    }

    @Test
    void maleProfileGetsMifflinStJeorCalories() {
        // Arrange — BMR = 10(80) + 6.25(180) − 5(30) + 5 = 1780; × 1.55 moderate.
        FakePresenter presenter = new FakePresenter();
        ProfileRepository repository = new InMemoryProfileRepository();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(repository, presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.MODERATE, Objective.MAINTAIN));

        // Assert
        assertEquals(2759.0, presenter.received.goal().target().calories(), DELTA);
    }

    @Test
    void femaleProfileUsesTheMinus161Constant() {
        // Arrange — BMR = 10(60) + 6.25(165) − 5(25) − 161 = 1345.25; × 1.375 light.
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                25, Sex.FEMALE, 60.0, 165.0, ActivityLevel.LIGHT, Objective.MAINTAIN));

        // Assert
        assertEquals(1849.71875, presenter.received.goal().target().calories(), DELTA);
    }

    @Test
    void activityLevelScalesTheCalories() {
        // Arrange — same body (BMR 1780), the active multiplier 1.725.
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.ACTIVE, Objective.MAINTAIN));

        // Assert
        assertEquals(1780.0 * 1.725, presenter.received.goal().target().calories(), DELTA);
    }

    @Test
    void losingSubtractsFiveHundredCalories() {
        // Arrange
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.MODERATE, Objective.LOSE));

        // Assert — 2759 maintenance − 500.
        assertEquals(2259.0, presenter.received.goal().target().calories(), DELTA);
    }

    @Test
    void gainingAddsFourHundredCalories() {
        // Arrange
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.MODERATE, Objective.GAIN));

        // Assert — 2759 maintenance + 400.
        assertEquals(3159.0, presenter.received.goal().target().calories(), DELTA);
    }

    @Test
    void macrosSplitThirtyFortyThirtyByCalories() {
        // Arrange — 2759 kcal split 30% protein / 40% carbs / 30% fat.
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.MODERATE, Objective.MAINTAIN));

        // Assert — protein and carbs are 4 kcal per gram, fat is 9.
        NutritionInfo target = presenter.received.goal().target();
        assertEquals(0.30 * 2759.0 / 4.0, target.protein(), DELTA);
        assertEquals(0.40 * 2759.0 / 4.0, target.carbs(), DELTA);
        assertEquals(0.30 * 2759.0 / 9.0, target.fat(), DELTA);
    }

    @Test
    void macroCaloriesAddBackUpToTheTarget() {
        // Arrange — a sanity check that the split loses nothing.
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                25, Sex.FEMALE, 60.0, 165.0, ActivityLevel.LIGHT, Objective.LOSE));

        // Assert
        NutritionInfo target = presenter.received.goal().target();
        double fromMacros = target.protein() * 4.0 + target.carbs() * 4.0 + target.fat() * 9.0;
        assertEquals(target.calories(), fromMacros, DELTA);
    }

    @Test
    void theProfileAndGoalAreSaved() {
        // Arrange
        FakePresenter presenter = new FakePresenter();
        ProfileRepository repository = new InMemoryProfileRepository();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(repository, presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                30, Sex.MALE, 80.0, 180.0, ActivityLevel.MODERATE, Objective.MAINTAIN));

        // Assert — both halves round-trip through the gateway.
        Optional<UserProfile> savedProfile = repository.load();
        Optional<NutritionGoal> savedGoal = repository.loadGoal();
        assertTrue(savedProfile.isPresent());
        assertTrue(savedGoal.isPresent());
        assertEquals(80.0, savedProfile.get().weightKg(), DELTA);
        assertEquals(Sex.MALE, savedProfile.get().sex());
        assertEquals(2759.0, savedGoal.get().target().calories(), DELTA);
    }

    @Test
    void theProfileIsReportedBackToTheScreen() {
        // Arrange
        FakePresenter presenter = new FakePresenter();
        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(new InMemoryProfileRepository(), presenter);

        // Act
        interactor.execute(new CalculateGoalInputData(
                42, Sex.FEMALE, 70.5, 172.0, ActivityLevel.ACTIVE, Objective.GAIN));

        // Assert — the screen gets back what the calculation was based on.
        assertNotNull(presenter.received);
        UserProfile profile = presenter.received.profile();
        assertEquals(42, profile.age());
        assertEquals(Sex.FEMALE, profile.sex());
        assertEquals(70.5, profile.weightKg(), DELTA);
        assertEquals(172.0, profile.heightCm(), DELTA);
        assertEquals(ActivityLevel.ACTIVE, profile.activityLevel());
        assertEquals(Objective.GAIN, profile.objective());
    }

    @Test
    void anEmptyRepositoryReportsNoProfileYet() {
        // Arrange — nothing calculated yet.
        ProfileRepository repository = new InMemoryProfileRepository();

        // Act / Assert — "no profile" is an empty Optional, never a zeroed profile.
        assertTrue(repository.load().isEmpty());
        assertTrue(repository.loadGoal().isEmpty());
    }
}
