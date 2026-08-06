package use_case.add_food;

/**
 * The way <b>in</b> to the add-food use case — the controller calls this.
 *
 * <p>A contract, just like a gateway: the controller depends on this interface,
 * and the interactor is what implements it. Because the controller only sees the
 * interface, it never knows (or cares) how adding actually works. (role: input boundary)
 */
public interface AddFoodInputBoundary {

    /** Add one item to the fridge. */
    void execute(AddFoodInputData inputData);
}
