package entity;

import java.util.Objects;

/**
 * A lightweight dish reference: what one recipe card shows. Comes from the
 * search step (TheMealDB filter.php); the full Recipe is fetched on demand.
 */
public final class RecipeSummary {

    private final String id;
    private final String name;
    private final String thumbnailUrl;

    /**
     * Constructs a RecipeSummary.
     * @param id the source's id for the dish
     * @param name the dish name
     * @param thumbnailUrl the card image URL
     */
    public RecipeSummary(String id, String name, String thumbnailUrl) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.thumbnailUrl = thumbnailUrl == null ? "" : thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
