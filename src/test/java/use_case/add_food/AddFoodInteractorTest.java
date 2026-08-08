package use_case.add_food;

import data_access.InMemoryFridgeRepository;
import entity.FoodItem;
import entity.Location;
import org.junit.jupiter.api.Test;
import use_case.gateway.FridgeRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddFoodInteractorTest {

    /** A fake output boundary: instead of drawing to a screen, it just remembers what it was handed. */
    private static class FakePresenter implements AddFoodOutputBoundary {
        private AddFoodOutputData captured;

        @Override
        public void present(AddFoodOutputData outputData) {
            this.captured = outputData;
        }
    }

    @Test
    void addingItem_putsItInTheFridge() {
        // Arrange — a real in-memory repo, a fake presenter, and the interactor under test
        FridgeRepository repository = new InMemoryFridgeRepository();
        FakePresenter presenter = new FakePresenter();
        AddFoodInteractor interactor = new AddFoodInteractor(repository, presenter);

        FoodItem broccoli = new FoodItem("broccoli", 500, "g", LocalDate.of(2026, 8, 10), Location.FRIDGE);
        AddFoodInputData request = new AddFoodInputData("broccoli", 500, "g", LocalDate.of(2026, 8, 10), Location.FRIDGE);

        // Act
        interactor.execute(request);

        // Assert — the presenter received the updated list, and it holds our item
        assertNotNull(presenter.captured, "the presenter should have been given a result");
        List<FoodItem> items = presenter.captured.allItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(broccoli));

        // ...and it was actually saved in the repository
        assertTrue(repository.load().items().contains(broccoli));
    }
}
