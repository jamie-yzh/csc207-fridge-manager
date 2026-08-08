package entity;

/**
 * The user's daily nutrition targets. Immutable: adjusting the goal means
 * saving a new NutritionGoal (auto-filled by CalculateGoal, then editable).
 */
public final class NutritionGoal {

    private final double calorieTarget;
    private final double proteinTarget;
    private final double carbTarget;
    private final double fatTarget;

    /**
     * Constructs a NutritionGoal.
     * @param calorieTarget daily kilocalories
     * @param proteinTarget daily grams of protein
     * @param carbTarget daily grams of carbohydrate
     * @param fatTarget daily grams of fat
     */
    public NutritionGoal(double calorieTarget, double proteinTarget,
                         double carbTarget, double fatTarget) {
        this.calorieTarget = calorieTarget;
        this.proteinTarget = proteinTarget;
        this.carbTarget = carbTarget;
        this.fatTarget = fatTarget;
    }

    public double getCalorieTarget() {
        return calorieTarget;
    }

    public double getProteinTarget() {
        return proteinTarget;
    }

    public double getCarbTarget() {
        return carbTarget;
    }

    public double getFatTarget() {
        return fatTarget;
    }
}
