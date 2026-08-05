package entity;

import java.time.LocalDate;

/**
 * One item stored in the kitchen — pure data (an entity).
 * Carries no nutrition; that is looked up separately via NutritionSource.
 */
public record FoodItem(
        String name,
        double quantity,
        String unit,
        LocalDate expiryDate,
        Location location
) {
}
