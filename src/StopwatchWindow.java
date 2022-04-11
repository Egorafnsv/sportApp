import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.sound.sampled.*;

public class StopwatchWindow implements ActionListener{

    JFrame frame = new JFrame();

    JButton startButton = new JButton("Start");
    JButton finishButton = new JButton("Finish");
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel();

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime+=1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    JFrame main_frame;
    Statistics stats;
    TypeSport exercise;

    File sound = new File("avb.wav");

    StopwatchWindow(JFrame frame, Statistics stats, TypeSport exercise){
        main_frame = frame;
        this.stats = stats;
        this.exercise = exercise;
    }

    public void createStopwatch(){
        frame.setBounds(550, 140, 400, 400);
        frame.setTitle(exercise.getNameExercise());
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(startButton);
        frame.add(finishButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finish();
            }
        });

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100,100, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(50, 210, 100, 50);
        startButton.setFont(new Font("Verdana Bold", Font.PLAIN, 18));
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(this);

        finishButton.setBounds(150, 210, 100, 50);
        finishButton.setFont(new Font("Verdana Bold", Font.PLAIN, 18));
        finishButton.setFocusPainted(false);
        finishButton.setContentAreaFilled(false);
        finishButton.setFocusPainted(false);
        finishButton.addActionListener(this);

        resetButton.setBounds(250, 210, 100, 50);
        resetButton.setFont(new Font("Verdana Bold", Font.PLAIN, 18));
        resetButton.setFocusPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton){
            if(!started){
                started = true;
                startButton.setText("Pause");
                start();
            }
            else {
                started = false;
                startButton.setText("Start");
                stop();
            }
        }
        if (e.getSource()==finishButton){
            finish();
        }

        if (e.getSource()==resetButton){
            reset();
        }
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void reset(){
        started = false;
        startButton.setText("Start");
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    void finish() {
        timer.stop();

        if (!(elapsedTime == 0)){
            if (elapsedTime < 5000){
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound)){
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                }
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
                    e.printStackTrace();
                }
            }
            double resKcal = exercise.getKkalOnSec()*elapsedTime/1000;
            stats.putNewExercise(exercise.getNameExercise(), resKcal, timeLabel.getText());
            JOptionPane.showMessageDialog(frame, "Упражнение " + exercise.getNameExercise() + " завершено\n" +
                    "Калорий сожжено: " + resKcal + "\n" + "Общее время тренировки: " + hours_string + ":" + minutes_string + ":" + seconds_string);
        }

        main_frame.setVisible(true);
        frame.dispose();
    }
}
