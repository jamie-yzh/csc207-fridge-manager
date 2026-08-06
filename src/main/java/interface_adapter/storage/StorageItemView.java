package interface_adapter.storage;

/**
 * One row of the storage list, formatted for display — all strings, ready to
 * drop straight into a label. The presenter builds these; the view reads them
 * and never has to touch an entity or the use case. (role: view data)
 */
public record StorageItemView(
        String name,
        String quantity,
        String expiry,
        String location
) {
}
