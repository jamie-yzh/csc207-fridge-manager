package entity;

/**
 * What the user wants their weight to do — and the calorie change that serves it.
 *
 * <p>Like {@link ActivityLevel}, each constant carries its own number: a deficit
 * to lose, nothing to maintain, a surplus to gain. The interactor adds
 * {@code objective.calorieAdjustment()} to TDEE.
 */
public enum Objective {

    /** Eat below maintenance. */
    LOSE(-500.0),

    /** Eat at maintenance. */
    MAINTAIN(0.0),

    /** Eat above maintenance. */
    GAIN(400.0);

    private final double calorieAdjustment;

    Objective(double calorieAdjustment) {
        this.calorieAdjustment = calorieAdjustment;
    }

    /** Calories added to (or taken off) TDEE to serve this objective. */
    public double calorieAdjustment() {
        return calorieAdjustment;
    }
}
