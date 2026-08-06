package use_case.remove_food;

import entity.Location;

import java.time.LocalDate;

/**
 * The data needed to remove one item — mirrors {@code AddFoodInputData}.
 *
 * <p>It carries all five fields because that is how we identify which item to
 * remove: the interactor rebuilds the matching {@link entity.FoodItem} and drops it.
 */
public record RemoveFoodInputData(
        String name,
        double quantity,
        String unit,
        LocalDate expiryDate,
        Location location
) {
}
