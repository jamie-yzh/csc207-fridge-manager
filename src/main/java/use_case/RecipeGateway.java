package use_case;

import java.util.List;
import java.util.Optional;

import entity.Recipe;
import entity.RecipeSummary;

/**
 * Where recipes come from. Defined in the use-case layer so interactors never
 * touch HTTP (Dependency Inversion): implemented by TheMealDbClient in
 * production and by MockRecipeGateway before the real client exists.
 */
public interface RecipeGateway {

    /**
     * The dishes containing an ingredient (the search step; TheMealDB
     * filter.php). Powers the recipe cards.
     * @param ingredient an ingredient name, e.g. "chicken breast"
     * @return matching dishes; an empty list when nothing matches, never null
     */
    List<RecipeSummary> searchByIngredient(String ingredient);

    /**
     * The full recipe for one dish (the detail step; TheMealDB lookup.php).
     * Powers the expanded card.
     * @param id the source id from a RecipeSummary
     * @return the recipe, or empty when the id is unknown
     */
    Optional<Recipe> getRecipeDetails(String id);
}
