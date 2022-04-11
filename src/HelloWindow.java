import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class HelloWindow {
    JFrame frame = new JFrame("SportsApp");

    JComboBox<String> selectionListProfile = new JComboBox<>();

    JButton entryButton = new JButton("Вход");
    JButton addProfileButton = new JButton("Создать профиль");

    JLabel profileLabel = new JLabel("Выберите профиль");

    ArrayList<String> profiles;

    HelloWindow(){
        loadData();
        createWindow();
    }

    void createWindow(){
        int x = 45;

        frame.setBounds(630, 140, 300, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(profileLabel);
        frame.add(entryButton);
        frame.add(selectionListProfile);
        frame.add(addProfileButton);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
                System.exit(0);
            }
        });

        profileLabel.setBounds(x+25, 45, 200, 45);
        profileLabel.setFont(new Font("Verdana", Font.PLAIN, 15));

        for (String name : profiles)
            selectionListProfile.addItem(name);

        selectionListProfile.setBounds(x, 90, 200, 45);
        selectionListProfile.setEditable(false);
        selectionListProfile.setFont(new Font("Verdana Bold", Font.PLAIN, 12));
        selectionListProfile.setBackground(Color.WHITE);

        entryButton.setBounds(x, 160, 200, 55);
        entryButton.setContentAreaFilled(false);
        entryButton.setFocusPainted(false);


        entryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
                frame.dispose();
                new MainMenuWindow((String) selectionListProfile.getSelectedItem());
            }
        });

        addProfileButton.setBounds(x, 240, 200, 55);
        addProfileButton.setFocusPainted(false);
        addProfileButton.setContentAreaFilled(false);
        addProfileButton.setFocusPainted(false);

        addProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProfile();
            }
        });
    }

    void createProfile(){
        String name = JOptionPane.showInputDialog("Введите имя: ", "");

        if (profiles.contains(name)){
            JOptionPane.showMessageDialog(frame, "Профиль уже существует");
        }
        else{
            selectionListProfile.addItem(name);
            profiles.add(name);
        }

    }

    private void saveData() {
        try (FileOutputStream file = new FileOutputStream("./data/users.txt");
             ObjectOutputStream objOut = new ObjectOutputStream(file)) {
            objOut.writeObject(profiles);
            objOut.flush();
//            System.out.println("Сохранено!");
//            System.out.println(profiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("./data/users.txt"))) {
            profiles = (ArrayList<String>) objIn.readObject();
//            System.out.println("Загружено!");
        } catch (FileNotFoundException e) {
            profiles = new ArrayList<>();
        }
        catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}
