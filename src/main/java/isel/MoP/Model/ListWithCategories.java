package isel.MoP.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class ListWithCategories extends List {
    ArrayList<String> categoriesList = new ArrayList<>();

    //default categories
    private final String DAIRY = "dairy products";
    private final String BAKERY = "bakery";
    private final String FRUITS = "FRUITS";
    private final String VEGETABLES = "vegetables";
    private final String SNACKS = "snacks";
    private final String DRINKS = "drinks";

    public ListWithCategories(List list) {
        super(list.name);
        this.items = list.items;
        categoriesList.addAll(Arrays.asList(DAIRY, BAKERY, FRUITS, VEGETABLES, SNACKS, DRINKS));
    }

    public ListWithCategories(String name) {
        super(name);
        categoriesList.addAll(Arrays.asList(DAIRY, BAKERY, FRUITS, VEGETABLES, SNACKS, DRINKS));
    }

    public void addCategory(int index, String categoryName) {
        Item item = this.items.get(index);
        this.items.set(index, new Category(item, categoryName));
        categoriesList.add(categoryName);
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public String getCategory(int index) {
        return categoriesList.get(index);
    }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
