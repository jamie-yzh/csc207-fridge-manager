package use_case.remove_food;

/**
 * The way <b>in</b> to the remove-food use case — the controller calls this.
 * The interactor implements it. (role: input boundary)
 */
public interface RemoveFoodInputBoundary {

    /** Remove one item from the fridge. */
    void execute(RemoveFoodInputData inputData);
}
