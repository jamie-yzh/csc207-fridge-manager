package app;

import app.gui.MenuTab;
import data_access.InMemoryProfileRepository;
import interface_adapter.nutrition.NutritionController;
import interface_adapter.nutrition.NutritionPresenter;
import interface_adapter.nutrition.NutritionViewModel;
import use_case.calculate_goal.CalculateGoalInputBoundary;
import use_case.calculate_goal.CalculateGoalInteractor;
import use_case.calculate_goal.CalculateGoalOutputBoundary;
import use_case.gateway.ProfileRepository;

/**
 * Builds the object graph and hands finished screens to the window.
 *
 * <p>This is the one place allowed to know every layer at once. Everything else
 * only ever sees the interface it was handed, which is what keeps the dependency
 * rule intact: the chain below is assembled outside-in, and each piece is given
 * the next as an interface rather than reaching for it.
 *
 * <pre>
 *   repository ─┐
 *               ├─→ interactor ─→ controller ─→ the screen
 *   presenter ──┘        ↑                          │
 *      │                 └──────── boundaries ──────┘
 *      └─→ view model ─→ the screen listens for results
 * </pre>
 */
public class Config {

    /**
     * Assemble the Nutrition tab: repository → presenter → interactor →
     * controller, then the panels that talk to the last two.
     */
    public MenuTab createMenuTab() {
        ProfileRepository profileRepository = new InMemoryProfileRepository();

        NutritionViewModel viewModel = new NutritionViewModel();

        CalculateGoalOutputBoundary presenter = new NutritionPresenter(viewModel);

        CalculateGoalInputBoundary interactor =
                new CalculateGoalInteractor(profileRepository, presenter);

        NutritionController controller = new NutritionController(interactor);

        return new MenuTab(controller, viewModel);
    }
}
