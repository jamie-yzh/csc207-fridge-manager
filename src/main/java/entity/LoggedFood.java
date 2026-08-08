package entity;

import java.util.Objects;

/**
 * One thing eaten: a name, how much of it, and the nutrition of that portion.
 */
public final class LoggedFood {

    private final String name;
    private final double servingSize;
    private final NutritionInfo nutrition;

    /**
     * Constructs a LoggedFood.
     * @param name what was eaten
     * @param servingSize the portion size in grams
     * @param nutrition the nutrition of that whole portion (already scaled)
     */
    public LoggedFood(String name, double servingSize, NutritionInfo nutrition) {
        this.name = Objects.requireNonNull(name, "name");
        this.servingSize = servingSize;
        this.nutrition = Objects.requireNonNull(nutrition, "nutrition");
    }

    public String getName() {
        return name;
    }

    public double getServingSize() {
        return servingSize;
    }

    public NutritionInfo getNutrition() {
        return nutrition;
    }
}
