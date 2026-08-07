package entity;

/**
 * The user's daily target — what they are aiming to eat.
 *
 * <p>A thin wrapper that <b>has-a</b> {@link NutritionInfo} rather than repeating
 * its four numbers. The wrapper earns its place by naming the meaning: the same
 * four doubles are a "goal" here and a "total eaten" elsewhere, and the type keeps
 * the two from being mixed up.
 */
public record NutritionGoal(
        NutritionInfo target
) {
}
