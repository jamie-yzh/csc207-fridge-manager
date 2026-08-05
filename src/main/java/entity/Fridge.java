package entity;

import java.util.ArrayList;
import java.util.List;

/** The user's kitchen inventory — holds the food items. */
public class Fridge {

    private final List<FoodItem> items;

    /** Start with an empty fridge. */
    public Fridge() {
        this.items = new ArrayList<>();
    }

    /** Build a fridge from already-saved items (used when loading from storage). */
    public Fridge(List<FoodItem> initialItems) {
        this.items = new ArrayList<>(initialItems);
    }

    /** Put an item in the fridge. */
    public void add(FoodItem item) {
        items.add(item);
    }

    /** Take an item out of the fridge. */
    public void remove(FoodItem item) {
        items.remove(item);
    }

    /** All items, as a read-only list. */
    public List<FoodItem> items() {
        return List.copyOf(items);
    }

    /** Just the items stored in one location (fridge / freezer / pantry). */
    public List<FoodItem> itemsAt(Location location) {
        return items.stream()
                .filter(item -> item.location() == location)
                .toList();
    }
}
