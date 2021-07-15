package isel.MoP;

import isel.MoP.Controller.Controller;
import isel.MoP.Utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class InitFrame extends JFrame {

    final static int DEFAULT_FONT_SIZE = 15;
    final static int DEFAULT_ICON_SIZE = 30;
    final static int DEFAULT_PANEL_DIMENSIONS = 100;
    final static int DEFAULT_FRAME_DIMENSIONS = 500;


    public InitFrame(Controller controller) {
        this.setTitle("Java Shopping List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_FRAME_DIMENSIONS, DEFAULT_FRAME_DIMENSIONS);
        this.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/main/java/isel/MoP/Resources/logo.png");
        logoLabel.setIcon(imageIcon);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBackground(Color.white);

        JButton createListButton = new JButton();
        createListButton.setText("Create New List");
        createListButton.setFont(new Font("Lato", Font.BOLD, DEFAULT_FONT_SIZE));
        createListButton.addActionListener(e -> {
            this.dispose();
            String name = JOptionPane.showInputDialog(this,
                    "Enter list name: ", null);
            controller.createList(name);
        });

        ImageIcon createListIcon = ViewUtils.resizeIcon(new ImageIcon("src/main/java/isel/MoP/Resources/add.png"),
                DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
        createListButton.setIcon(createListIcon);

        JPanel main = new JPanel();
        main.setPreferredSize(new Dimension(DEFAULT_PANEL_DIMENSIONS, DEFAULT_PANEL_DIMENSIONS));
        main.add(logoLabel);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(DEFAULT_PANEL_DIMENSIONS, DEFAULT_PANEL_DIMENSIONS));
        bottom.setBackground(Color.WHITE);
        bottom.add(createListButton);

        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setVisible(true);
    }

}
