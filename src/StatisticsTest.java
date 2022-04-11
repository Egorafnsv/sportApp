import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    @Test
    void completedExercises1() {
        Statistics statistics = new Statistics();
        statistics.putNewExercise("Бег", 10.1, "5-00-00");
        statistics.putNewExercise("Что-то другое", 100, "2-20-00");

        String expectedString = "Упражнение: Бег\nКалории: 10.1\nВремя: 5-00-00\n\n" +
                "Упражнение: Что-то другое\nКалории: 100.0\nВремя: 2-20-00";

        assertEquals(expectedString, statistics.completedExercises());
    }

    @Test
    void completedExercises2() {
        Statistics statistics = new Statistics();

        String expectedString = "";

        assertEquals(expectedString, statistics.completedExercises());
    }

    @Test
    void getSumCal() {
        Statistics statistics = new Statistics();
        statistics.putNewExercise("Бег", 100.01, "5-00-00");
        statistics.putNewExercise("Что-то другое", 123, "2-20-00");

        assertEquals(223.01, statistics.getSumCal());
    }
}