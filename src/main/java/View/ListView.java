package View;

import Model.Item;

import javax.swing.*;
import java.util.ArrayList;

public class ListView extends JFrame {
    JButton addItemButton;
    JButton deleteItemButton;

    void showList(String name, ArrayList<Item> items){

        this.setTitle(name);
        this.setSize(500,500);
        this.setVisible(true);

    }
}
