package use_case.add_food;

import entity.FoodItem;

import java.util.List;

/**
 * The result of adding an item — what the screen needs afterwards.
 *
 * <p>It carries the full, updated item list so the presenter can hand the view
 * everything it needs to re-render all three sections at once.
 */
public record AddFoodOutputData(
        List<FoodItem> allItems
) {
}
