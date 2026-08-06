package data_access;

import entity.Fridge;
import use_case.gateway.FridgeRepository;

/**
 * Keeps the fridge in memory (just a field) — no files, no database.
 *
 * <p>It is an implementation (role: data access) of the {@link FridgeRepository}
 * gateway. Simple and fast: perfect for running the app now and for tests. The
 * trade-off is that the data resets every time the app closes; a file-backed
 * version can replace it later without any interactor changing.
 */
public class InMemoryFridgeRepository implements FridgeRepository {

    private Fridge fridge = new Fridge();

    @Override
    public Fridge load() {
        return fridge;
    }

    @Override
    public void save(Fridge fridge) {
        this.fridge = fridge;
    }
}
