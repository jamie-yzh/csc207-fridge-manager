package data_access;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import entity.NutritionInfo;
import use_case.NutritionSource;

/**
 * Small hard-coded per-100 g table so LogFood and CookAndLog run before the
 * bundled JSON table (JsonNutritionTable) is wired up. Names are matched
 * case-insensitively.
 */
public class MockNutritionSource implements NutritionSource {

    private final Map<String, NutritionInfo> table = new HashMap<>();

    public MockNutritionSource() {
        table.put("chicken breast", new NutritionInfo(165, 31, 0, 3.6));
        table.put("potatoes", new NutritionInfo(77, 2.0, 17, 0.1));
        table.put("cream", new NutritionInfo(340, 2.1, 2.8, 36));
        table.put("spinach", new NutritionInfo(23, 2.9, 3.6, 0.4));
        table.put("egg", new NutritionInfo(155, 13, 1.1, 11));
        table.put("pepper", new NutritionInfo(251, 10, 64, 3.3));
    }

    @Override
    public Optional<NutritionInfo> per100g(String foodName) {
        if (foodName == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(table.get(foodName.trim().toLowerCase(Locale.ROOT)));
    }
}
