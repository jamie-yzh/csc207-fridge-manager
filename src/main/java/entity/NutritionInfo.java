package entity;

/**
 * Nutrition facts: calories, protein, carbs and fat.
 * Immutable value object. The local table stores per-100 g values;
 * {@code perHundredGrams.scale(grams / 100.0)} converts one to a portion, and
 * {@link #plus(NutritionInfo)} sums portions (used by DailyLog and CookAndLog).
 */
public final class NutritionInfo {

    /** All-zero nutrition, the identity for {@link #plus(NutritionInfo)}. */
    public static final NutritionInfo ZERO = new NutritionInfo(0, 0, 0, 0);

    private final double calories;
    private final double protein;
    private final double carbs;
    private final double fat;

    /**
     * Constructs a NutritionInfo.
     * @param calories kilocalories
     * @param protein grams of protein
     * @param carbs grams of carbohydrate
     * @param fat grams of fat
     */
    public NutritionInfo(double calories, double protein, double carbs, double fat) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    /**
     * Component-wise sum of this and another NutritionInfo.
     * @param other the values to add
     * @return the sum
     */
    public NutritionInfo plus(NutritionInfo other) {
        return new NutritionInfo(calories + other.calories, protein + other.protein,
                carbs + other.carbs, fat + other.fat);
    }

    /**
     * Multiplies every value by a factor, e.g. {@code grams / 100.0} against a
     * per-100 g table entry.
     * @param factor the multiplier
     * @return the scaled values
     */
    public NutritionInfo scale(double factor) {
        return new NutritionInfo(calories * factor, protein * factor,
                carbs * factor, fat * factor);
    }

    @Override
    public String toString() {
        return calories + " kcal (P " + protein + " / C " + carbs + " / F " + fat + ")";
    }
}
