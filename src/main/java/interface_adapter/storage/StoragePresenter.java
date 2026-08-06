package interface_adapter.storage;

import entity.FoodItem;
import entity.Location;
import use_case.add_food.AddFoodOutputBoundary;
import use_case.add_food.AddFoodOutputData;
import use_case.remove_food.RemoveFoodOutputBoundary;
import use_case.remove_food.RemoveFoodOutputData;

import java.util.List;

/**
 * Turns a use-case result into display rows and pushes them to the view model.
 *
 * <p>It implements both storage output boundaries (add + remove), so each
 * interactor calls it without knowing it is a presenter. All the "make it pretty
 * for the screen" work — formatting the quantity, the date, the section name —
 * lives here. (role: presenter)
 */
public class StoragePresenter implements AddFoodOutputBoundary, RemoveFoodOutputBoundary {

    private final StorageViewModel viewModel;

    public StoragePresenter(StorageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(AddFoodOutputData outputData) {
        refresh(outputData.allItems());
    }

    @Override
    public void present(RemoveFoodOutputData outputData) {
        refresh(outputData.allItems());
    }

    /** Both use cases refresh the whole screen the same way: format every item. */
    private void refresh(List<FoodItem> items) {
        List<StorageItemView> rows = items.stream()
                .map(this::toRow)
                .toList();
        viewModel.setItems(rows);
    }

    private StorageItemView toRow(FoodItem item) {
        return new StorageItemView(
                item.name(),
                formatQuantity(item.quantity(), item.unit()),
                item.expiryDate().toString(),
                sectionName(item.location())
        );
    }

    /** 2.0 + "g" -> "2 g"; 1.5 + "kg" -> "1.5 kg". */
    private String formatQuantity(double quantity, String unit) {
        String number = quantity == Math.floor(quantity)
                ? String.valueOf((long) quantity)
                : String.valueOf(quantity);
        return unit.isBlank() ? number : number + " " + unit;
    }

    /** Match the section headers the StoragePanel uses. */
    private String sectionName(Location location) {
        return switch (location) {
            case FRIDGE -> "REFRIGERATOR";
            case FREEZER -> "FREEZER";
            case PANTRY -> "PANTRY";
        };
    }
}
