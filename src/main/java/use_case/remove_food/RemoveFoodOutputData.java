package use_case.remove_food;

import entity.FoodItem;

import java.util.List;

/**
 * The result of removing an item — the full updated list, so the presenter can
 * refresh the whole screen at once. Mirrors {@code AddFoodOutputData}.
 */
public record RemoveFoodOutputData(
        List<FoodItem> allItems
) {
}
