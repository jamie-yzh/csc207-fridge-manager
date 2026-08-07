package use_case.calculate_goal;

import entity.NutritionGoal;
import entity.NutritionInfo;
import entity.Sex;
import entity.UserProfile;
import use_case.gateway.ProfileRepository;

/**
 * The calculate-goal logic (role: interactor — the "cook" that does one job).
 *
 * <p>Steps: build the profile → run Mifflin–St Jeor → save → report the goal.
 * It reaches the outside world only through interfaces (the
 * {@link ProfileRepository} gateway and the {@link CalculateGoalOutputBoundary}),
 * so it never knows whether the profile lives in memory or a file, nor that a
 * Swing screen is on the other end.
 *
 * <p>The arithmetic:
 * <pre>
 *   BMR  = 10·kg + 6.25·cm − 5·age + (+5 male / −161 female)
 *   TDEE = BMR × activity multiplier
 *   goal = TDEE + objective adjustment
 * </pre>
 * then the calories are split into macro grams. Everything stays a {@code double};
 * rounding is the presenter's decision, not the logic's.
 */
public class CalculateGoalInteractor implements CalculateGoalInputBoundary {

    /** Mifflin–St Jeor weights each body measurement by these factors. */
    private static final double WEIGHT_FACTOR = 10.0;
    private static final double HEIGHT_FACTOR = 6.25;
    private static final double AGE_FACTOR = 5.0;

    /** The constant the formula ends with, which is what differs by sex. */
    private static final double MALE_CONSTANT = 5.0;
    private static final double FEMALE_CONSTANT = -161.0;

    /** How the day's calories are divided between the three macros. */
    private static final double PROTEIN_SHARE = 0.30;
    private static final double CARB_SHARE = 0.40;
    private static final double FAT_SHARE = 0.30;

    /** Energy per gram — protein and carbs carry 4 kcal, fat carries 9. */
    private static final double PROTEIN_CALORIES_PER_GRAM = 4.0;
    private static final double CARB_CALORIES_PER_GRAM = 4.0;
    private static final double FAT_CALORIES_PER_GRAM = 9.0;

    private final ProfileRepository profileRepository;
    private final CalculateGoalOutputBoundary presenter;

    public CalculateGoalInteractor(
            ProfileRepository profileRepository,
            CalculateGoalOutputBoundary presenter
    ) {
        this.profileRepository = profileRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(CalculateGoalInputData inputData) {
        // 1. Build the entity from the (already clean) request.
        UserProfile profile = new UserProfile(
                inputData.age(),
                inputData.sex(),
                inputData.weightKg(),
                inputData.heightCm(),
                inputData.activityLevel(),
                inputData.objective()
        );

        // 2. Run the numbers.
        NutritionGoal goal = new NutritionGoal(targetFor(profile));

        // 3. Save the profile together with the goal it produced.
        profileRepository.save(profile, goal);

        // 4. Report the goal so the screen can refresh.
        presenter.present(new CalculateGoalOutputData(profile, goal));
    }

    /** The four daily targets: calories, and the macro grams they buy. */
    private NutritionInfo targetFor(UserProfile profile) {
        double calories = dailyCalories(profile);
        return new NutritionInfo(
                calories,
                gramsFor(calories, PROTEIN_SHARE, PROTEIN_CALORIES_PER_GRAM),
                gramsFor(calories, CARB_SHARE, CARB_CALORIES_PER_GRAM),
                gramsFor(calories, FAT_SHARE, FAT_CALORIES_PER_GRAM)
        );
    }

    /** Maintenance calories (TDEE), shifted by what the user is aiming for. */
    private double dailyCalories(UserProfile profile) {
        double tdee = basalMetabolicRate(profile) * profile.activityLevel().multiplier();
        return tdee + profile.objective().calorieAdjustment();
    }

    /** Calories burned at complete rest — the Mifflin–St Jeor equation. */
    private double basalMetabolicRate(UserProfile profile) {
        return WEIGHT_FACTOR * profile.weightKg()
                + HEIGHT_FACTOR * profile.heightCm()
                - AGE_FACTOR * profile.age()
                + sexConstant(profile.sex());
    }

    private double sexConstant(Sex sex) {
        return sex == Sex.MALE ? MALE_CONSTANT : FEMALE_CONSTANT;
    }

    /** A share of the day's calories, converted into grams of one macro. */
    private double gramsFor(double calories, double share, double caloriesPerGram) {
        return calories * share / caloriesPerGram;
    }
}
