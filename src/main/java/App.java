import Controller.User;
import Utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class App {

    private static User user;
    final static int DEFAULT_FONT_SIZE = 15;
    final static int DEFAULT_ICON_SIZE = 30;
    final static int DEFAULT_PANEL_DIMENSIONS = 100;
    final static int DEFAULT_FRAME_DIMENSIONS = 500;

    public static void main(String[] args) {
        user = new User();
        App.init();
    }

    public static void init() {
        JFrame frame = new JFrame();

        frame.setTitle("Java Shopping Model.List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_FRAME_DIMENSIONS, DEFAULT_FRAME_DIMENSIONS);
        frame.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/main/java/GUI/logo.png");
        logoLabel.setIcon(imageIcon);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBackground(Color.white);

        JButton createListButton = new JButton();
        createListButton.setText("Create New Model.List");
        createListButton.setFont(new Font("Lato", Font.BOLD, DEFAULT_FONT_SIZE));
        createListButton.addActionListener(e -> {
            frame.dispose();
            String name = JOptionPane.showInputDialog(frame,
                    "Enter list name: ", null);
            user.createList(name);
        });

        ImageIcon createListIcon = ViewUtils.resizeIcon(new ImageIcon("src/main/java/GUI/add.png"),
                DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
        createListButton.setIcon(createListIcon);

        JPanel main = new JPanel();
        main.setPreferredSize(new Dimension(DEFAULT_PANEL_DIMENSIONS, DEFAULT_PANEL_DIMENSIONS));
        main.add(logoLabel);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(DEFAULT_PANEL_DIMENSIONS, DEFAULT_PANEL_DIMENSIONS));
        bottom.setBackground(Color.WHITE);
        bottom.add(createListButton);

        frame.add(main, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
