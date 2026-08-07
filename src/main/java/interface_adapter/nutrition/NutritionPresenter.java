package interface_adapter.nutrition;

import entity.NutritionInfo;
import use_case.calculate_goal.CalculateGoalOutputBoundary;
import use_case.calculate_goal.CalculateGoalOutputData;

import java.util.List;

/**
 * Turns a calculated goal into display rows and pushes them to the view model.
 *
 * <p>It implements the calculate-goal output boundary, so the interactor calls it
 * without knowing it is a presenter. All the "make it pretty for the screen" work
 * lives here: this is the only place the goal's doubles get rounded to whole
 * numbers, which keeps the arithmetic in the use case exact. (role: presenter)
 */
public class NutritionPresenter implements CalculateGoalOutputBoundary {

    /**
     * How much has been eaten so far today. Fixed at zero for now: the daily log
     * belongs to the log-a-food use case, which is not built yet. When it lands,
     * only this value has to come from somewhere real — the row shape, the bars
     * and the labels already handle it.
     */
    private static final int EATEN_SO_FAR = 0;

    private static final String CALORIE_UNIT = "Kcal";
    private static final String MACRO_UNIT = "g";

    private final NutritionViewModel viewModel;

    public NutritionPresenter(NutritionViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(CalculateGoalOutputData outputData) {
        NutritionInfo target = outputData.goal().target();

        List<NutrientGoalView> rows = List.of(
                toRow(NutrientGoalView.CALORIES, target.calories(), CALORIE_UNIT),
                toRow(NutrientGoalView.PROTEIN, target.protein(), MACRO_UNIT),
                toRow(NutrientGoalView.CARBS, target.carbs(), MACRO_UNIT),
                toRow(NutrientGoalView.FAT, target.fat(), MACRO_UNIT)
        );

        viewModel.setNutrients(rows);
    }

    /** 2758.99… kcal becomes a "0 / 2759 Kcal" row the screen can draw. */
    private NutrientGoalView toRow(String name, double target, String unit) {
        int rounded = (int) Math.round(target);
        return new NutrientGoalView(
                name,
                EATEN_SO_FAR,
                rounded,
                EATEN_SO_FAR + " / " + rounded + " " + unit
        );
    }
}
