package use_case.remove_food;

/**
 * The way <b>out</b> of the remove-food use case — the presenter implements this.
 * (role: output boundary)
 */
public interface RemoveFoodOutputBoundary {

    /** Report the result after an item was removed. */
    void present(RemoveFoodOutputData outputData);
}
