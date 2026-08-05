package use_case.gateway;

import entity.Fridge;

/**
 * Saves and loads the fridge.
 *
 * <p>This is a gateway: the use-case layer depends on this interface, and the
 * outer layer provides the real implementation (a file store, or a mock for tests).
 * The interactors call these methods without knowing which implementation is behind them.
 */
public interface FridgeRepository {

    /** Load the saved fridge (an empty one if nothing has been saved yet). */
    Fridge load();

    /** Save the fridge's current contents. */
    void save(Fridge fridge);
}
