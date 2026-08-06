package use_case.add_food;

import entity.Location;

import java.time.LocalDate;

/**
 * The data needed to add one item — a plain request object (a record).
 *
 * <p>The controller builds this from the user's raw form input (parsing the
 * quantity text, mapping the location word, parsing the date) and hands it to
 * the interactor. By the time it reaches here, everything is already clean and
 * typed.
 */
public record AddFoodInputData(
        String name,
        double quantity,
        String unit,
        LocalDate expiryDate,
        Location location
) {
}

