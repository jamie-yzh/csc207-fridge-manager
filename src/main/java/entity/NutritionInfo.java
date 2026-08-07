package entity;

/**
 * A bundle of macros — the shared value object the whole app measures food in.
 *
 * <p>Nothing extends this; things <b>hold</b> one (composition over inheritance).
 * A daily goal has one, a logged food has one, a day's total is the sum of many.
 * It is immutable: {@link #plus} and {@link #scaledTo} return a new bundle rather
 * than changing this one, so a value can be shared without anyone corrupting it.
 */
public record NutritionInfo(
        double calories,
        double protein,
        double carbs,
        double fat
) {

    /** An empty bundle — the starting point when summing a day up. */
    public static final NutritionInfo ZERO = new NutritionInfo(0.0, 0.0, 0.0, 0.0);

    /** This bundle plus another, field by field. */
    public NutritionInfo plus(NutritionInfo other) {
        return new NutritionInfo(
                calories + other.calories(),
                protein + other.protein(),
                carbs + other.carbs(),
                fat + other.fat()
        );
    }

    /** Table values are per 100 g; scale them to the grams actually eaten. */
    public NutritionInfo scaledTo(double grams) {
        double factor = grams / 100.0;
        return new NutritionInfo(
                calories * factor,
                protein * factor,
                carbs * factor,
                fat * factor
        );
    }
}
