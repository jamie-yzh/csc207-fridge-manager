package use_case.add_food;

/**
 * The way <b>out</b> of the add-food use case — the presenter implements this.
 *
 * <p>When the interactor finishes, it calls {@code present(...)} to report the
 * result. It talks to this interface, not to a presenter directly, so the logic
 * stays unaware of Swing or anything on the screen. (role: output boundary)
 */
public interface AddFoodOutputBoundary {

    /** Report the result after an item was added. */
    void present(AddFoodOutputData outputData);
}
