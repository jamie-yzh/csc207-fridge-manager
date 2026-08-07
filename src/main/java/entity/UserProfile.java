package entity;

/**
 * The body facts the goal calculation needs — pure data (an entity).
 *
 * <p>It carries no nutrition and does no arithmetic; it is simply what the user
 * told us about themselves. {@code CalculateGoal} reads it and produces a
 * {@link NutritionGoal}.
 */
public record UserProfile(
        int age,
        Sex sex,
        double weightKg,
        double heightCm,
        ActivityLevel activityLevel,
        Objective objective
) {
}
