package entity;

import java.util.Objects;

/**
 * One ingredient line of a recipe. The measure is free text from TheMealDB
 * ("1 1/2 cups", "Pinch") and is not parseable into grams, which is why
 * Cook &amp; log asks the user for gram amounts instead.
 */
public final class RecipeIngredient {

    private final String name;
    private final String measure;

    /**
     * Constructs a RecipeIngredient.
     * @param name the ingredient name
     * @param measure the free-text measure as the source gives it
     */
    public RecipeIngredient(String name, String measure) {
        this.name = Objects.requireNonNull(name, "name");
        this.measure = measure == null ? "" : measure;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return measure.isEmpty() ? name : name + " (" + measure + ")";
    }
}
