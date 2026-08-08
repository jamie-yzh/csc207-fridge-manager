package use_case.remove_food;

import data_access.InMemoryFridgeRepository;
import entity.FoodItem;
import entity.Fridge;
import entity.Location;
import org.junit.jupiter.api.Test;
import use_case.gateway.FridgeRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveFoodInteractorTest {

    /** A fake output boundary that just captures the result. */
    private static class FakePresenter implements RemoveFoodOutputBoundary {
        private RemoveFoodOutputData captured;

        @Override
        public void present(RemoveFoodOutputData outputData) {
            this.captured = outputData;
        }
    }

    @Test
    void removingItem_takesItOut_andLeavesTheRest() {
        // Arrange — a fridge that already holds two items
        FoodItem broccoli = new FoodItem("broccoli", 500, "g", LocalDate.of(2026, 8, 10), Location.FRIDGE);
        FoodItem rice = new FoodItem("rice", 1, "kg", LocalDate.of(2027, 1, 1), Location.PANTRY);

        FridgeRepository repository = new InMemoryFridgeRepository();
        Fridge fridge = repository.load();
        fridge.add(broccoli);
        fridge.add(rice);
        repository.save(fridge);

        FakePresenter presenter = new FakePresenter();
        RemoveFoodInteractor interactor = new RemoveFoodInteractor(repository, presenter);

        RemoveFoodInputData request = new RemoveFoodInputData("broccoli", 500, "g", LocalDate.of(2026, 8, 10), Location.FRIDGE);

        // Act
        interactor.execute(request);

        // Assert — broccoli is gone, rice remains
        List<FoodItem> items = presenter.captured.allItems();
        assertEquals(1, items.size());
        assertFalse(items.contains(broccoli));
        assertTrue(items.contains(rice));
    }
}
