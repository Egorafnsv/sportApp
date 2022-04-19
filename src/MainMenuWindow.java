import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenuWindow {
    JFrame frame = new JFrame();

    JButton pushUpButton = new JButton("Отжимания");
    JButton JumpButton = new JButton("Скакалка");
    JButton RunButton = new JButton("Бег");
    JButton resultsButton = new JButton("Результаты");
    JButton exitButton = new JButton("Выход");

    StopwatchWindow stopwatch;
    Statistics statistics;
    String profile;

    public MainMenuWindow(String profile) {
        this.profile = profile;
        loadData();
        createWindow();
    }

    private void createWindow() {
        frame.setBounds(630, 140, 300, 537);
        frame.setTitle(profile);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(pushUpButton);
        frame.add(JumpButton);
        frame.add(RunButton);
        frame.add(resultsButton);
        frame.add(exitButton);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
                System.exit(0);
            }
        });

        pushUpButton.setBounds(0, 0, 300, 100);
        pushUpButton.setFocusPainted(false);
        pushUpButton.setContentAreaFilled(false);
        pushUpButton.setFocusPainted(false);
        pushUpButton.setFont(new Font("Verdana Bold", Font.PLAIN, 12));

        pushUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                stopwatch = new StopwatchWindow(frame, statistics, new PushUp());
                stopwatch.createStopwatch();
            }
        });

        JumpButton.setBounds(0, 100, 300, 100);
        JumpButton.setFocusPainted(false);
        JumpButton.setContentAreaFilled(false);
        JumpButton.setFocusPainted(false);
        JumpButton.setFont(new Font("Verdana Bold", Font.PLAIN, 12));

        JumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                stopwatch = new StopwatchWindow(frame, statistics, new Jump());
                stopwatch.createStopwatch();
            }
        });

        RunButton.setBounds(0, 200, 300, 100);
        RunButton.setFocusPainted(false);
        RunButton.setContentAreaFilled(false);
        RunButton.setFocusPainted(false);
        RunButton.setFont(new Font("Verdana Bold", Font.PLAIN, 12));

        RunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                stopwatch = new StopwatchWindow(frame, statistics, new Run());
                stopwatch.createStopwatch();
            }
        });

        resultsButton.setBounds(0, 300, 300, 100);
        resultsButton.setFocusPainted(false);
        resultsButton.setContentAreaFilled(false);
        resultsButton.setFocusPainted(false);
        resultsButton.setFont(new Font("Verdana Bold", Font.PLAIN, 12));

        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, String.format("Суммарное количество калорий: %.2f", statistics.getSumCal()) +
                        "\n\n-- 5 последних упражнений --\n\n" + statistics.completedExercises(), "Общая статистика", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitButton.setBounds(0, 400, 300, 100);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setFont(new Font("Verdana Bold", Font.PLAIN, 12));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
                System.exit(0);
            }
        });
    }

    private void saveData() {
        try (FileOutputStream file = new FileOutputStream("./data/" + profile + ".txt");
            ObjectOutputStream objOut = new ObjectOutputStream(file)) {
            objOut.writeObject(statistics);
            objOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("./data/" + profile + ".txt"))) {
            statistics = (Statistics) objIn.readObject();


        } catch (FileNotFoundException e) {
            statistics = new Statistics();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


