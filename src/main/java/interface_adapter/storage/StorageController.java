package interface_adapter.storage;

import entity.Location;
import use_case.add_food.AddFoodInputBoundary;
import use_case.add_food.AddFoodInputData;
import use_case.remove_food.RemoveFoodInputBoundary;
import use_case.remove_food.RemoveFoodInputData;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads the raw strings from the Storage screen and turns them into clean
 * requests for the use cases. Both the Add button and a row's Remove button come
 * through here, and they share the same parsing ("500g" into a number + unit,
 * the location word, the date). (role: controller)
 */
public class StorageController {

    /** Leading number (with optional decimals); whatever follows is the unit. */
    private static final Pattern QUANTITY =
            Pattern.compile("^\\s*(\\d+(?:\\.\\d+)?)\\s*(.*)$");

    private final AddFoodInputBoundary addFoodInteractor;
    private final RemoveFoodInputBoundary removeFoodInteractor;

    public StorageController(
            AddFoodInputBoundary addFoodInteractor,
            RemoveFoodInputBoundary removeFoodInteractor
    ) {
        this.addFoodInteractor = addFoodInteractor;
        this.removeFoodInteractor = removeFoodInteractor;
    }

    /**
     * Called from the Add button. Takes exactly what the form holds.
     *
     * @throws IllegalArgumentException if the expiry is not written as yyyy-mm-dd
     */
    public void addFood(
            String name,
            String quantityText,
            String expiryText,
            String locationText
    ) {
        addFoodInteractor.execute(new AddFoodInputData(
                name.trim(),
                parseQuantity(quantityText),
                parseUnit(quantityText),
                parseExpiry(expiryText),
                mapLocation(locationText)
        ));
    }

    /**
     * Called from a row's Remove button. Takes the same four display fields the
     * row already shows, so we can rebuild and drop the matching item.
     *
     * @throws IllegalArgumentException if the expiry is not written as yyyy-mm-dd
     */
    public void removeFood(
            String name,
            String quantityText,
            String expiryText,
            String locationText
    ) {
        removeFoodInteractor.execute(new RemoveFoodInputData(
                name.trim(),
                parseQuantity(quantityText),
                parseUnit(quantityText),
                parseExpiry(expiryText),
                mapLocation(locationText)
        ));
    }

    private double parseQuantity(String text) {
        Matcher matcher = QUANTITY.matcher(text);
        return matcher.matches() ? Double.parseDouble(matcher.group(1)) : 1.0;
    }

    private String parseUnit(String text) {
        Matcher matcher = QUANTITY.matcher(text);
        return matcher.matches() ? matcher.group(2).trim() : text.trim();
    }

    private LocalDate parseExpiry(String text) {
        try {
            return LocalDate.parse(text.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Expiry must look like 2026-08-10", e);
        }
    }

    private Location mapLocation(String locationText) {
        return switch (locationText.trim().toLowerCase()) {
            case "freezer" -> Location.FREEZER;
            case "pantry" -> Location.PANTRY;
            default -> Location.FRIDGE;
        };
    }
}
