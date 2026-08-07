package entity;

/**
 * How active the user is — and, with it, the number BMR gets multiplied by to
 * reach TDEE (total daily energy expenditure).
 *
 * <p>Each constant carries its own multiplier, so the interactor asks
 * {@code level.multiplier()} instead of running a switch. Adding a level later
 * (SEDENTARY at 1.2, say) is then a one-line change here and nothing else moves.
 */
public enum ActivityLevel {

    /** Light exercise, 1–3 days a week. */
    LIGHT(1.375),

    /** Moderate exercise, 3–5 days a week. */
    MODERATE(1.55),

    /** Hard exercise, 6–7 days a week. */
    ACTIVE(1.725);

    private final double multiplier;

    ActivityLevel(double multiplier) {
        this.multiplier = multiplier;
    }

    /** The factor BMR is multiplied by to get TDEE. */
    public double multiplier() {
        return multiplier;
    }
}
