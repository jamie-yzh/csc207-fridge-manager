package interface_adapter.nutrition;

import entity.ActivityLevel;
import entity.Objective;
import entity.Sex;
import use_case.calculate_goal.CalculateGoalInputBoundary;
import use_case.calculate_goal.CalculateGoalInputData;

import java.util.Locale;

/**
 * Reads the raw strings from the Profile form and turns them into a clean request
 * for the calculate-goal use case. The Calculate Goal button comes through here,
 * and all the parsing — the number fields, the dropdown words — lives in one
 * place. (role: controller)
 */
public class NutritionController {

    private final CalculateGoalInputBoundary calculateGoalInteractor;

    public NutritionController(CalculateGoalInputBoundary calculateGoalInteractor) {
        this.calculateGoalInteractor = calculateGoalInteractor;
    }

    /**
     * Called from the Calculate Goal button. Takes exactly what the form holds.
     *
     * @throws IllegalArgumentException if age, weight or height is not a positive
     *         number — the panel catches this and shows the message to the user
     */
    public void calculateGoal(
            String ageText,
            String sexText,
            String weightText,
            String heightText,
            String activityText,
            String objectiveText
    ) {
        calculateGoalInteractor.execute(new CalculateGoalInputData(
                parseWholeNumber(ageText, "Age"),
                mapSex(sexText),
                parseNumber(weightText, "Weight"),
                parseNumber(heightText, "Height"),
                mapActivity(activityText),
                mapObjective(objectiveText)
        ));
    }

    private double parseNumber(String text, String fieldName) {
        double value;
        try {
            value = Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a number", e);
        }
        requirePositive(value, fieldName);
        return value;
    }

    private int parseWholeNumber(String text, String fieldName) {
        int value;
        try {
            value = Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a whole number", e);
        }
        requirePositive(value, fieldName);
        return value;
    }

    private void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than 0");
        }
    }

    private Sex mapSex(String sexText) {
        return switch (sexText.trim().toLowerCase(Locale.ROOT)) {
            case "female" -> Sex.FEMALE;
            default -> Sex.MALE;
        };
    }

    /** Match the words the Activity dropdown offers. */
    private ActivityLevel mapActivity(String activityText) {
        return switch (activityText.trim().toLowerCase(Locale.ROOT)) {
            case "light" -> ActivityLevel.LIGHT;
            case "active" -> ActivityLevel.ACTIVE;
            default -> ActivityLevel.MODERATE;
        };
    }

    /** Match the words the Objective dropdown offers. */
    private Objective mapObjective(String objectiveText) {
        return switch (objectiveText.trim().toLowerCase(Locale.ROOT)) {
            case "lose weight" -> Objective.LOSE;
            case "gain weight" -> Objective.GAIN;
            default -> Objective.MAINTAIN;
        };
    }
}
