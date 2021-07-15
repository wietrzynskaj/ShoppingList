package isel.MoP.Controller;

import isel.MoP.InitFrame;
import isel.MoP.Model.ListWithCategories;
import isel.MoP.View.ListView;
import isel.MoP.Model.Item;
import org.apache.log4j.Logger;

import static javax.swing.Box.createVerticalStrut;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Controller extends AbstractListModel<Item> {

    private static final Logger LOGGER = Logger.getLogger(isel.MoP.Console.ControllerConsole.class);

    ListWithCategories myList;
    ListView listView;

    public Controller() {
        myList = new ListWithCategories();
        listView = new ListView(this, new ListWindowListener(), new AddButtonListener(),
                new DeleteButtonListener(), new SaveListButtonListener(), new LoadListButtonListener(),
                new SortListButtonListener(), new AddCategoryButtonListener(), new UncheckItemButtonListener());
    }

    public void createList(String listName) {
        myList.setName(listName);
        myList.clearList();
        listView.setTitle(listName);
        fireContentsChanged(listView, 0, myList.size() - 1);
        listView.setVisible(true);
        LOGGER.info("List window created");
    }

    public class AddButtonListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (listView.getNewItemField().getText().length() > 0) {
                String name = listView.getNewItemField().getText().trim();
                if (!add(name)) {
                    showMessageDialog(null, name + " is already in a list");
                    LOGGER.error("Adding not successful");
                }
                listView.getNewItemField().setText("");
                listView.getList().setSelectedIndex(myList.size() - 1);
            }
        }
    }

    public class DeleteButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            deleteAt(listView.getList().getSelectedIndex());
        }
    }

    public class SaveListButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            save();
        }
    }

    public class LoadListButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            load();
        }
    }

    public class SortListButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            sort();
        }
    }

    public class AddCategoryButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            addCategory(listView.getList().getSelectedIndex());
        }
    }

    public class UncheckItemButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            uncheckItem(listView.getList().getSelectedIndex());
        }
    }

    public class ListWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int option = JOptionPane.showConfirmDialog(listView,
                    "Do you want to save list before closing?", null, dialogButton);
            if (option == 0) save();
            listView.dispose();
            new InitFrame(getController());
        }
    }

    public void deleteAt(int i) {
        this.myList.deleteAt(i);
        this.fireContentsChanged(this, i, i);
    }

    public boolean add(String name) {
        boolean added = this.myList.addItem(name);
        this.fireContentsChanged(this,
                this.myList.size() - 1, this.myList.size() - 1);
        return added;
    }

    public void uncheckItem(int index) {
        if (!myList.uncheckItemAt(index)) LOGGER.error("Item not found.");
    }

    public void addCategory(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel defaultCategoriesLabel = new JLabel("Default categories");
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(myList.getCategoriesList());
        JComboBox<String> comboBox = new JComboBox<>(model);

        JTextField newCategoryField = new JTextField();
        JLabel newCategoryLabel = new JLabel("Add new category");
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name;
            if (newCategoryField.getText().length() > 0) {
                name = newCategoryField.getText().trim();
                newCategoryField.setText("");
                if (!myList.ifCategoryAlreadyExist(name)) {
                    myList.getCategoriesList().add(name);
                    model.addElement(name);
                }

            }
        });

        panel.add(newCategoryLabel);
        panel.add(newCategoryField);
        panel.add(createVerticalStrut(5));
        panel.add(submitButton);
        panel.add(createVerticalStrut(10));
        panel.add(defaultCategoriesLabel);
        panel.add(comboBox);
        panel.add(createVerticalStrut(5));
        String categoryName = null;
        int result = JOptionPane.showConfirmDialog(null, panel, "Choose category", JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.CLOSED_OPTION:
                return;
            case JOptionPane.OK_OPTION:
                categoryName = (String) comboBox.getSelectedItem();
                break;
            default:
                LOGGER.error("Unexpected value: " + result);
        }
        if ((index >= 0 && index < myList.size()) && categoryName != null) {
            myList.addCategory(index, categoryName);
            fireContentsChanged(this, index, index);
        } else LOGGER.error("Adding category was not successful");
    }

    public void save() {
        String name = myList.getFileName();
        while (myList.validateFileName(name)) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int option = JOptionPane.showConfirmDialog(listView,
                    "File with this name already exist. Do you want to overwrite it?", null, dialogButton);
            if (option == 1) {
                name = JOptionPane.showInputDialog(listView,
                        "Enter file name: ", null) + ".txt";
            }
        }
        try {
            myList.saveList(name);
            showMessageDialog(null, "New file " + name + " added");
        } catch (IOException ex) {
            LOGGER.error("Saving to file was not successful");
        }
    }

    public void load() {
        int index = -1;

        JPanel panel = new JPanel();
        panel.add(new JLabel("Available files"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(myList.getAvailableFiles());
        JComboBox<String> comboBox = new JComboBox<>(model);
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Choose file", JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.CLOSED_OPTION:
                return;
            case JOptionPane.OK_OPTION:
                index = comboBox.getSelectedIndex();
                break;
            default:
                LOGGER.error("Unexpected value: " + result);
        }
        try {
            myList.loadListFromFile(index);
        } catch (IOException e1) {
            LOGGER.error("Reading from file was not successful");
        } catch (ClassNotFoundException e2) {
            LOGGER.error("Class not found");
        } catch (IndexOutOfBoundsException e3) {
            LOGGER.error("Wrong index of file");
        }
        this.fireContentsChanged(this, 0, myList.size() - 1);
    }

    void sort() {
        myList.sortList();
        this.fireContentsChanged(this, 0, myList.size() - 1);
    }

    @Override
    public int getSize() {
        return this.myList.size();
    }

    @Override
    public Item getElementAt(int index) {
        return this.myList.getItem(index);
    }

    public Controller getController() {
        return Controller.this;
    }

}
