package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import entity.Recipe;
import entity.RecipeIngredient;
import entity.RecipeSummary;
import use_case.RecipeGateway;

/**
 * Canned RecipeGateway so the Meal slice runs and is testable before
 * TheMealDbClient exists. It mirrors the real API's behaviour: ingredient
 * names use TheMealDB's fixed vocabulary (spaces become underscores, case is
 * ignored), a search with no matches yields an empty list (the real API
 * returns null there), and measures are free text. The Waterzooi entry
 * matches the live lookup.php?i=53466 sample in the architecture doc, so
 * swapping in the real client changes nothing downstream.
 */
public class MockRecipeGateway implements RecipeGateway {

    /** The id of the canned Belgian Waterzooi Chicken recipe. */
    public static final String WATERZOOI_ID = "53466";

    private static final String STIR_FRY_ID = "90001";

    @Override
    public List<RecipeSummary> searchByIngredient(String ingredient) {
        final List<RecipeSummary> results = new ArrayList<>();
        if ("chicken_breast".equals(normalize(ingredient))) {
            results.add(new RecipeSummary(WATERZOOI_ID, "Belgian Waterzooi Chicken",
                    "https://example.com/thumbs/waterzooi.jpg"));
            results.add(new RecipeSummary(STIR_FRY_ID, "Mock Chicken Stir-Fry",
                    "https://example.com/thumbs/stir-fry.jpg"));
        }
        return results;
    }

    @Override
    public Optional<Recipe> getRecipeDetails(String id) {
        if (WATERZOOI_ID.equals(id)) {
            final List<RecipeIngredient> ingredients = new ArrayList<>();
            ingredients.add(new RecipeIngredient("Chicken Breast", "1"));
            ingredients.add(new RecipeIngredient("Potatoes", "100g"));
            ingredients.add(new RecipeIngredient("Cream", "1 1/2 cups"));
            ingredients.add(new RecipeIngredient("Pepper", "Pinch"));
            return Optional.of(new Recipe(WATERZOOI_ID, "Belgian Waterzooi Chicken",
                    ingredients,
                    "Poach the chicken with the vegetables, finish the broth with "
                            + "cream, season with pepper and serve.",
                    "https://example.com/thumbs/waterzooi.jpg"));
        }
        if (STIR_FRY_ID.equals(id)) {
            final List<RecipeIngredient> ingredients = new ArrayList<>();
            ingredients.add(new RecipeIngredient("Chicken Breast", "2"));
            ingredients.add(new RecipeIngredient("Spinach", "1 bag"));
            return Optional.of(new Recipe(STIR_FRY_ID, "Mock Chicken Stir-Fry",
                    ingredients,
                    "Slice the chicken, fry until golden, wilt in the spinach and serve.",
                    "https://example.com/thumbs/stir-fry.jpg"));
        }
        return Optional.empty();
    }

    private String normalize(String ingredient) {
        if (ingredient == null) {
            return "";
        }
        return ingredient.trim().toLowerCase(Locale.ROOT).replace(' ', '_');
    }
}
