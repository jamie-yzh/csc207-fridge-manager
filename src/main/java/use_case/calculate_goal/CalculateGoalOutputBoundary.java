package use_case.calculate_goal;

/**
 * The way <b>out</b> of the calculate-goal use case — the presenter implements this.
 *
 * <p>When the interactor finishes it calls {@code present(...)} to report the
 * result. Talking to this interface, not to a presenter directly, keeps the
 * arithmetic unaware of Swing and lets a test drop in a fake. (role: output boundary)
 */
public interface CalculateGoalOutputBoundary {

    /** Report the goal that was just calculated. */
    void present(CalculateGoalOutputData outputData);
}
