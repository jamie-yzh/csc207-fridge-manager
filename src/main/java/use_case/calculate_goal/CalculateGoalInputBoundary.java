package use_case.calculate_goal;

/**
 * The way <b>in</b> to the calculate-goal use case — the controller calls this.
 *
 * <p>A contract, like a gateway: the controller depends on this interface and the
 * interactor implements it, so the controller never learns how the arithmetic
 * works. (role: input boundary)
 */
public interface CalculateGoalInputBoundary {

    /** Work out the daily goal for one profile. */
    void execute(CalculateGoalInputData inputData);
}
