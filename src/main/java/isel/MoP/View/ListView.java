package isel.MoP.View;

import isel.MoP.Model.Category;
import isel.MoP.Model.Item;
import isel.MoP.Utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;


public class ListView extends JFrame {

    final static int DEFAULT_ICON_SIZE = 20;
    private final String DEFAULT_PATH = "src/main/java/isel/MoP/Resources/";

    private JPanel mainContentPane;
    private JPanel newItemPanel;
    private JPanel listControlPanel;
    private JTextField newItemField;
    private JButton addItemButton;
    private JScrollPane listScrollPane;
    private JButton deleteItemButton;
    private JButton saveListButton;
    private JButton loadListButton;
    private JButton sortListButton;
    private JButton addCategoryButton;
    private JButton checkItemButton;
    private final JList<Item> itemList;

    private final MouseListener addButtonListener;
    private final MouseListener deleteButtonListener;
    private final MouseListener saveListButtonListener;
    private final MouseListener loadListButtonListener;
    private final MouseListener sortListButtonListener;
    private final MouseListener addCategoryButtonListener;
    private final MouseListener checkItemButtonListener;

    public ListView(ListModel<Item> model, WindowAdapter listWindowListener, MouseListener addButtonListener,
                    MouseListener deleteButtonListener, MouseListener saveListButtonListener,
                    MouseListener loadListButtonListener, MouseListener sortListButtonListener,
                    MouseListener addCategoryButtonListener, MouseListener checkItemButtonListener) {
        this.addButtonListener = addButtonListener;
        this.deleteButtonListener = deleteButtonListener;
        this.saveListButtonListener = saveListButtonListener;
        this.loadListButtonListener = loadListButtonListener;
        this.sortListButtonListener = sortListButtonListener;
        this.addCategoryButtonListener = addCategoryButtonListener;
        this.checkItemButtonListener = checkItemButtonListener;
        itemList = new JList<>(model);
        itemList.setCellRenderer(new IconAndTextRenderer());

        this.setContentPane(this.getMainContentPane());

        this.addWindowListener(listWindowListener);
        this.setMinimumSize(new Dimension(500, 500));

        this.pack();
    }

    private Container getMainContentPane() {
        if (mainContentPane == null) {
            this.mainContentPane = new JPanel();
            this.mainContentPane.setLayout(new BorderLayout());

            this.mainContentPane.add(getNewItemPanel(), BorderLayout.NORTH);
            this.mainContentPane.add(getListScrollPane(), BorderLayout.CENTER);
            this.mainContentPane.add(getTasksListControls(), BorderLayout.EAST);

        }
        return this.mainContentPane;
    }

    public static class IconAndTextRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {


        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Item item = (Item) value;
            if (value instanceof Category && ((Category) value).getImageIcon() != null) {
                label.setIcon(((Category) value).getImageIcon());
                label.setToolTipText(((Category) value).getCategoryName());
            }
            label.setText(value.toString());
            if(item.isChecked()) label.setFont(ViewUtils.setStrikethrough(label.getFont()));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return label;
        }
    }

    private Component getNewItemPanel() {
        if (this.newItemPanel == null) {
            this.newItemPanel = new JPanel();

            BorderLayout layout = new BorderLayout();
            this.newItemPanel.setLayout(layout);
            layout.setHgap(5);
            this.newItemPanel.setBorder(createEmptyBorder(10, 0, 10, 10));

            this.newItemPanel.add(getNewItemField(), BorderLayout.CENTER);
            this.newItemPanel.add(getAddItemButton(), BorderLayout.EAST);
        }

        return this.newItemPanel;
    }

    public JTextField getNewItemField() {
        if (this.newItemField == null) {
            this.newItemField = new JTextField();
            newItemField.setToolTipText("Enter item name");
        }
        return this.newItemField;
    }

    private JButton getAddItemButton() {
        if (this.addItemButton == null) {
            this.addItemButton = new JButton("Add");
            ImageIcon addIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "addItem.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.addItemButton.setIcon(addIcon);
            this.addItemButton.addMouseListener(addButtonListener);
        }

        return this.addItemButton;
    }

    private JButton getDeleteButton() {
        if (this.deleteItemButton == null) {
            this.deleteItemButton = new JButton("Delete");
            ImageIcon deleteIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "delete.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.deleteItemButton.setIcon(deleteIcon);
            this.deleteItemButton.addMouseListener(deleteButtonListener);
        }

        return this.deleteItemButton;
    }

    private JButton getSaveListButton() {
        if (this.saveListButton == null) {
            this.saveListButton = new JButton("Save");
            ImageIcon saveIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "save.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.saveListButton.setIcon(saveIcon);
            this.saveListButton.addMouseListener(saveListButtonListener);
        }
        return this.saveListButton;
    }

    private JButton getLoadListButton() {
        if (this.loadListButton == null) {
            this.loadListButton = new JButton("Load");
            ImageIcon saveIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "loading.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.loadListButton.setIcon(saveIcon);
            this.loadListButton.addMouseListener(loadListButtonListener);
        }

        return this.loadListButton;
    }

    private JButton getSortListButton() {
        if (this.sortListButton == null) {
            this.sortListButton = new JButton("Sort");
            ImageIcon sortIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "sort.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.sortListButton.setIcon(sortIcon);
            this.sortListButton.addMouseListener(sortListButtonListener);
        }

        return this.sortListButton;
    }

    private JButton getAddCategoryButton() {
        if (this.addCategoryButton == null) {
            this.addCategoryButton = new JButton("Add Category");
            ImageIcon addCategoryIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "category.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.addCategoryButton.setIcon(addCategoryIcon);
            this.addCategoryButton.addMouseListener(addCategoryButtonListener);
        }

        return this.addCategoryButton;
    }
    private JButton getCheckItemButton() {
        if (this.checkItemButton == null) {
            this.checkItemButton = new JButton("Check");
            ImageIcon uncheckIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_PATH + "tick.png"),
                    DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
            this.checkItemButton.setIcon(uncheckIcon);
            this.checkItemButton.addMouseListener(checkItemButtonListener);
        }

        return this.checkItemButton;
    }


    private Component getListScrollPane() {
        if (this.listScrollPane == null) {
            this.listScrollPane = new JScrollPane(getList());
        }
        return this.listScrollPane;
    }

    public JList<Item> getList() {
        return itemList;
    }

    private Component getTasksListControls() {
        if (this.listControlPanel == null) {
            this.listControlPanel = new JPanel();

            BoxLayout layout = new BoxLayout(this.listControlPanel, BoxLayout.Y_AXIS);
            this.listControlPanel.setLayout(layout);
            this.listControlPanel.setBorder(createEmptyBorder(5, 5, 5, 5));

            this.listControlPanel.add(createVerticalStrut(10));
            JButton button = getSortListButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

            this.listControlPanel.add(createVerticalStrut(10));

            button = getSaveListButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

            this.listControlPanel.add(createVerticalStrut(10));

            button = getLoadListButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

            this.listControlPanel.add(createVerticalStrut(10));

            button = getAddCategoryButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

            this.listControlPanel.add(createVerticalStrut(10));

            button = getCheckItemButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

            this.listControlPanel.add(createVerticalStrut(10));

            button = getDeleteButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.listControlPanel.add(button);

        }

        return this.listControlPanel;
    }


}
