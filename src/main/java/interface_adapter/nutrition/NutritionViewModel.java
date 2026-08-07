package interface_adapter.nutrition;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Holds what the Nutrition screen currently shows (the four progress rows).
 *
 * <p>When the rows change it fires a property-change event so any listening view
 * refreshes itself — the observer pattern the course uses. Two panels listen: the
 * progress bars and the profile's fine-tune fields, and neither knows about the
 * other. The view reads {@link #getNutrients()}; it never touches entities or the
 * use case. (role: view model)
 */
public class NutritionViewModel {

    /** Event name fired when the goal changes. */
    public static final String NUTRIENTS_PROPERTY = "nutrients";

    private List<NutrientGoalView> nutrients = new ArrayList<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Replace the shown rows and notify listeners. */
    public void setNutrients(List<NutrientGoalView> nutrients) {
        List<NutrientGoalView> previous = this.nutrients;
        this.nutrients = nutrients;
        support.firePropertyChange(NUTRIENTS_PROPERTY, previous, nutrients);
    }

    public List<NutrientGoalView> getNutrients() {
        return nutrients;
    }

    /**
     * One row looked up by name, or empty before a goal has been calculated.
     * Matching by name rather than position keeps the panels from depending on
     * the order the presenter happens to build the list in.
     */
    public Optional<NutrientGoalView> getNutrient(String name) {
        return nutrients.stream()
                .filter(nutrient -> nutrient.name().equals(name))
                .findFirst();
    }

    /** The view registers here to be told when the goal changes. */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
