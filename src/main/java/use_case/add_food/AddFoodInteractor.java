package use_case.add_food;

import entity.FoodItem;
import entity.Fridge;
import use_case.gateway.FridgeRepository;

/**
 * The add-food logic (role: interactor — the "cook" that does one job).
 *
 * <p>Steps: load the fridge → add the new item → save it → report the new state.
 * It reaches the outside world only through interfaces (the {@link FridgeRepository}
 * gateway and the {@link AddFoodOutputBoundary}), so it never knows whether the
 * data lives in memory or a file, nor that a Swing screen is on the other end.
 */
public class AddFoodInteractor implements AddFoodInputBoundary {

    private final FridgeRepository fridgeRepository;
    private final AddFoodOutputBoundary presenter;

    public AddFoodInteractor(
            FridgeRepository fridgeRepository,
            AddFoodOutputBoundary presenter
    ) {
        this.fridgeRepository = fridgeRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddFoodInputData inputData) {
        // 1. Build the entity from the (already clean) request.
        FoodItem newItem = new FoodItem(
                inputData.name(),
                inputData.quantity(),
                inputData.unit(),
                inputData.expiryDate(),
                inputData.location()
        );

        // 2. Load the fridge, add the item, save it back.
        Fridge fridge = fridgeRepository.load();
        fridge.add(newItem);
        fridgeRepository.save(fridge);

        // 3. Report the updated list so the screen can refresh.
        presenter.present(new AddFoodOutputData(fridge.items()));
    }
}
