package interface_adapter.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds what the Storage screen currently shows (the display rows).
 *
 * <p>When the rows change, it fires a property-change event so any listening
 * view refreshes itself — that is the observer pattern the course uses. The
 * view reads {@link #getItems()}; it never touches entities or the use case.
 * (role: view model)
 */
public class StorageViewModel {

    /** Event name fired when the item list changes. */
    public static final String ITEMS_PROPERTY = "items";

    private List<StorageItemView> items = new ArrayList<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Replace the shown rows and notify listeners. */
    public void setItems(List<StorageItemView> items) {
        List<StorageItemView> previous = this.items;
        this.items = items;
        support.firePropertyChange(ITEMS_PROPERTY, previous, items);
    }

    public List<StorageItemView> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    /** The view registers here to be told when the list changes. */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
