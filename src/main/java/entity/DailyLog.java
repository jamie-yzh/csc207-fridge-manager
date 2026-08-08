package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * One day's intake: everything eaten and the running totals shown against the
 * daily goal.
 */
public class DailyLog {

    private final LocalDate date;
    private final List<LoggedFood> entries = new ArrayList<>();

    /**
     * Constructs an empty log for one day.
     * @param date the day this log covers
     */
    public DailyLog(LocalDate date) {
        this.date = Objects.requireNonNull(date, "date");
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Everything eaten so far, in the order it was logged.
     * @return an unmodifiable view of the entries
     */
    public List<LoggedFood> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    /**
     * Logs one more thing eaten.
     * @param entry the food to add
     */
    public void addEntry(LoggedFood entry) {
        entries.add(Objects.requireNonNull(entry, "entry"));
    }

    /**
     * The day's total nutrition across every entry.
     * @return the summed nutrition
     */
    public NutritionInfo totals() {
        NutritionInfo total = NutritionInfo.ZERO;
        for (LoggedFood entry : entries) {
            total = total.plus(entry.getNutrition());
        }
        return total;
    }
}
