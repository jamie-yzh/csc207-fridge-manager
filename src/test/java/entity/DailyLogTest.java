package entity;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DailyLogTest {

    @Test
    void totalsTest() {
        final DailyLog log = new DailyLog(LocalDate.of(2026, 8, 8));
        log.addEntry(new LoggedFood("Chicken Breast", 150,
                new NutritionInfo(247.5, 46.5, 0, 5.4)));
        log.addEntry(new LoggedFood("Spinach", 100,
                new NutritionInfo(23, 2.9, 3.6, 0.4)));

        final NutritionInfo totals = log.totals();
        assertEquals(2, log.getEntries().size());
        assertEquals(270.5, totals.getCalories(), 1e-9);
        assertEquals(49.4, totals.getProtein(), 1e-9);
        assertEquals(3.6, totals.getCarbs(), 1e-9);
        assertEquals(5.8, totals.getFat(), 1e-9);
    }

    @Test
    void emptyLogTotalsAreZeroTest() {
        final DailyLog log = new DailyLog(LocalDate.of(2026, 8, 8));
        assertEquals(0, log.totals().getCalories(), 1e-9);
    }
}
