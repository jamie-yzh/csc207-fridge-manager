package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A full suggested dish: ingredients and instructions, shown when a recipe
 * card is expanded. Comes from the detail step (TheMealDB lookup.php).
 */
public final class Recipe {

    private final String sourceId;
    private final String name;
    private final List<RecipeIngredient> ingredients;
    private final String instructions;
    private final String thumbnailUrl;

    /**
     * Constructs a Recipe.
     * @param sourceId the source's id for the dish
     * @param name the dish name
     * @param ingredients the ingredient lines, in order
     * @param instructions the cooking instructions
     * @param thumbnailUrl the image URL
     */
    public Recipe(String sourceId, String name, List<RecipeIngredient> ingredients,
                  String instructions, String thumbnailUrl) {
        this.sourceId = Objects.requireNonNull(sourceId, "sourceId");
        this.name = Objects.requireNonNull(name, "name");
        this.ingredients = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(ingredients, "ingredients")));
        this.instructions = instructions == null ? "" : instructions;
        this.thumbnailUrl = thumbnailUrl == null ? "" : thumbnailUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getName() {
        return name;
    }

    /**
     * The ingredient lines, in order.
     * @return an unmodifiable list of ingredients
     */
    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
