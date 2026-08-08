package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionInfoTest {

    @Test
    void plusTest() {
        final NutritionInfo total = new NutritionInfo(100, 10, 5, 2)
                .plus(new NutritionInfo(50, 5, 2.5, 1));

        assertEquals(150, total.getCalories(), 1e-9);
        assertEquals(15, total.getProtein(), 1e-9);
        assertEquals(7.5, total.getCarbs(), 1e-9);
        assertEquals(3, total.getFat(), 1e-9);
    }

    @Test
    void scaleTest() {
        // 150 g of a per-100 g table entry (chicken breast).
        final NutritionInfo portion = new NutritionInfo(165, 31, 0, 3.6).scale(150 / 100.0);

        assertEquals(247.5, portion.getCalories(), 1e-9);
        assertEquals(46.5, portion.getProtein(), 1e-9);
        assertEquals(5.4, portion.getFat(), 1e-9);
    }
}
