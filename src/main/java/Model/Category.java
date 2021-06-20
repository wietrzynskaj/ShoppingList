package Model;

import java.util.Comparator;

public class Category extends ItemDecorator implements Comparable<Category> {

    String categoryName;
    String imageIcon;
    Item item;

    public Category(Item item, String categoryName) {
        super(item.getName());
        this.item = item;
        this.categoryName = categoryName;
    }

    public Category(Item item, String name, String imageIcon) {
        super(name);
        this.imageIcon = imageIcon;
    }

    public void setImageIcon(String imageIconName) {
        this.imageIcon = imageIconName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return super.toString() + ", category = " + categoryName;
    }

    private static final Comparator<String> nullsComparator = Comparator.nullsLast(String::compareToIgnoreCase);

    private static final Comparator<Category> categoryComparator = Comparator.
            comparing(Category::getCategoryName, nullsComparator);

    @Override
    public int compareTo(Category other) {
        return categoryComparator.compare(this, other);
    }
}
