package entity;

/**
 * How active the user is. Each level carries the standard TDEE multiplier so
 * CalculateGoal can use it directly.
 */
public enum ActivityLevel {
    SEDENTARY(1.2),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    EXTRA_ACTIVE(1.9);

    private final double factor;

    ActivityLevel(double factor) {
        this.factor = factor;
    }

    /**
     * The standard total-daily-energy-expenditure multiplier for this level.
     * @return the multiplier
     */
    public double getFactor() {
        return factor;
    }
}
