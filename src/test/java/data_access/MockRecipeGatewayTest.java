package data_access;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import entity.Recipe;
import entity.RecipeSummary;
import use_case.RecipeGateway;

import static org.junit.jupiter.api.Assertions.*;

class MockRecipeGatewayTest {

    private final RecipeGateway gateway = new MockRecipeGateway();

    @Test
    void searchByIngredientTest() {
        // Spacing and case are normalised onto TheMealDB's vocabulary.
        final List<RecipeSummary> results = gateway.searchByIngredient("Chicken Breast");

        assertFalse(results.isEmpty());
        assertEquals(MockRecipeGateway.WATERZOOI_ID, results.get(0).getId());
        assertEquals("Belgian Waterzooi Chicken", results.get(0).getName());
    }

    @Test
    void searchWithNoMatchesReturnsEmptyListTest() {
        // The real API returns null here; the gateway contract is an empty list.
        assertTrue(gateway.searchByIngredient("dragonfruit").isEmpty());
        assertTrue(gateway.searchByIngredient(null).isEmpty());
    }

    @Test
    void getRecipeDetailsTest() {
        final Optional<Recipe> recipe =
                gateway.getRecipeDetails(MockRecipeGateway.WATERZOOI_ID);

        assertTrue(recipe.isPresent());
        assertEquals("Belgian Waterzooi Chicken", recipe.get().getName());
        assertEquals(4, recipe.get().getIngredients().size());
        assertEquals("Chicken Breast", recipe.get().getIngredients().get(0).getName());
        assertEquals("1 1/2 cups", recipe.get().getIngredients().get(2).getMeasure());
    }

    @Test
    void unknownIdReturnsEmptyTest() {
        assertTrue(gateway.getRecipeDetails("00000").isEmpty());
    }
}
