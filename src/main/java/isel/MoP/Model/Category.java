package isel.MoP.Model;

import isel.MoP.Utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Comparator;

public class Category extends ItemDecorator implements Comparable<Category> {

    final int DEFAULT_ICON_SIZE = 15;
    final String DEFAULT_ICON = "src/main/java/isel/MoP/Resources/supermarket/png/default.png";

    String categoryName;
    ImageIcon imageIcon;

    Item item;
    public Category(Item item, String categoryName) {
        super(item.getName());
        this.item = item;
        this.categoryName = categoryName;
    }

    public Category(Item item, String categoryName, String fileName) {
        super(item.getName());
        this.item = item;
        this.categoryName = categoryName;
        File file = new File(fileName);
        if(file.exists()) {
            imageIcon = ViewUtils.resizeIcon(new ImageIcon(fileName), DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
        } else imageIcon = ViewUtils.resizeIcon(new ImageIcon(DEFAULT_ICON), DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
    }

    public void setImageIcon(String fileName) {
        this.imageIcon =  ViewUtils.resizeIcon(new ImageIcon(fileName),
                DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE); }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static final Comparator<String> nullsComparator = Comparator.nullsLast(String::compareToIgnoreCase);

    private static final Comparator<Category> categoryComparator = Comparator.
            comparing(Category::getCategoryName, nullsComparator);

    @Override
    public int compareTo(Category other) {
        return categoryComparator.compare(this, other);
    }
}
