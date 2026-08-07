package use_case.calculate_goal;

import entity.NutritionGoal;
import entity.UserProfile;

/**
 * The result of calculating a goal — what the screen needs afterwards.
 *
 * <p>It carries the goal (the four targets the progress bars fill toward) and the
 * profile it came from, so the screen can show what the numbers were based on.
 * Values are unrounded; the presenter decides how they look.
 */
public record CalculateGoalOutputData(
        UserProfile profile,
        NutritionGoal goal
) {
}
