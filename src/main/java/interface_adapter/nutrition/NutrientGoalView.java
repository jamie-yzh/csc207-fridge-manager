package interface_adapter.nutrition;

/**
 * One progress row on the Nutrition screen, formatted for display — the numbers
 * the bar needs plus the label already written out. The presenter builds these;
 * the view reads them and never has to touch an entity or the use case.
 * (role: view data)
 *
 * <p>{@code current} and {@code target} stay {@code int} because that is what
 * {@code ProgressBar} draws with; {@code amount} is the caption beside it.
 */
public record NutrientGoalView(
        String name,
        int current,
        int target,
        String amount
) {

    /** The four row names, so the presenter and the panels agree on them. */
    public static final String CALORIES = "Calories";
    public static final String PROTEIN = "Protein";
    public static final String CARBS = "Carbs";
    public static final String FAT = "Fat";
}
