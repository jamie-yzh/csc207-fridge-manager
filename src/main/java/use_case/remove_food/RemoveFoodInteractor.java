package use_case.remove_food;

import entity.FoodItem;
import entity.Fridge;
import use_case.gateway.FridgeRepository;

/**
 * The remove-food logic (role: interactor) — mirrors {@code AddFoodInteractor}.
 *
 * <p>The one difference from adding: it rebuilds the target {@link FoodItem} from
 * the request and removes the matching one. Because {@code FoodItem} is a record,
 * "matching" means all five fields are equal.
 */
public class RemoveFoodInteractor implements RemoveFoodInputBoundary {

    private final FridgeRepository fridgeRepository;
    private final RemoveFoodOutputBoundary presenter;

    public RemoveFoodInteractor(
            FridgeRepository fridgeRepository,
            RemoveFoodOutputBoundary presenter
    ) {
        this.fridgeRepository = fridgeRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(RemoveFoodInputData inputData) {
        // Rebuild the exact item to drop (records compare by all fields).
        FoodItem target = new FoodItem(
                inputData.name(),
                inputData.quantity(),
                inputData.unit(),
                inputData.expiryDate(),
                inputData.location()
        );

        Fridge fridge = fridgeRepository.load();
        fridge.remove(target);
        fridgeRepository.save(fridge);

        presenter.present(new RemoveFoodOutputData(fridge.items()));
    }
}
