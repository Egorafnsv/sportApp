import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class StopwatchWindowTest {

    @Test
    void reset() {
        StopwatchWindow test = new StopwatchWindow(new JFrame(), new Statistics(), new TypeSport());
        test.elapsedTime = 10000;

        test.reset();

        String expectedString = "00:00:00";

        assertEquals(expectedString, test.timeLabel.getText());

    }

    @Test
    void start() {
        StopwatchWindow test = new StopwatchWindow(new JFrame(), new Statistics(), new TypeSport());
        test.createStopwatch();

        String expectedString = "PauseStartPauseStartPauseStartPause";
        String result = "";

        for (int i = 0; i <= 6; i++){
            test.startButton.doClick();
            result += test.startButton.getText();

        }

        assertEquals(expectedString, result);
    }
}