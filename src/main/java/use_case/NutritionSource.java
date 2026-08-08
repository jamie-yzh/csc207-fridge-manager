package use_case;

import java.util.Optional;

import entity.NutritionInfo;

/**
 * Per-100 g nutrition lookup. One interface, two use cases: LogFood
 * (Nutrition tab) and CookAndLog (Meal tab). Implemented by the bundled local
 * JSON table now; a nutrition API could implement it later without touching
 * any use case (the team's Dependency-Inversion / extensibility example).
 */
public interface NutritionSource {

    /**
     * The per-100 g macros for a food, matched case-insensitively.
     * @param foodName the food to look up
     * @return its per-100 g nutrition, or empty when the table has no entry
     */
    Optional<NutritionInfo> per100g(String foodName);
}
