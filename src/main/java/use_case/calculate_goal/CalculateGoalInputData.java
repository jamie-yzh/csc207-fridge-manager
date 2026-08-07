package use_case.calculate_goal;

import entity.ActivityLevel;
import entity.Objective;
import entity.Sex;

/**
 * The data needed to calculate a goal — a plain request object (a record).
 *
 * <p>The controller builds this from the raw form input (parsing the age, weight
 * and height text, mapping the dropdown words to enums) and hands it to the
 * interactor. By the time it arrives here everything is clean and typed.
 */
public record CalculateGoalInputData(
        int age,
        Sex sex,
        double weightKg,
        double heightCm,
        ActivityLevel activityLevel,
        Objective objective
) {
}
