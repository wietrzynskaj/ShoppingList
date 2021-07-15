package isel.MoP.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class ListWithCategories extends List {
    ArrayList<String> categoriesList = new ArrayList<>();

    //default categories
    private final String DAIRY = "dairy products";
    private final String BAKERY = "bakery";
    private final String FRUITS = "fruits";
    private final String VEGETABLES = "vegetables";
    private final String SNACKS = "snacks";
    private final String DRINKS = "drinks";

    public ListWithCategories(List list) {
        this.items = list.items;
        categoriesList.addAll(Arrays.asList(DAIRY, BAKERY, FRUITS, VEGETABLES, SNACKS, DRINKS));
    }

    public ListWithCategories() {
        categoriesList.addAll(Arrays.asList(DAIRY, BAKERY, FRUITS, VEGETABLES, SNACKS, DRINKS));
    }

    public void addCategory(int index, String categoryName) {
        Item item = this.items.get(index);
        String DEFAULT_PATH = "src/main/java/isel/MoP/Resources/supermarket/png/";
        String fileName = DEFAULT_PATH + categoryName.replaceAll(" ", "") + ".png";
        this.items.set(index, new Category(item, categoryName, fileName));
    }

    public boolean ifCategoryAlreadyExist(String categoryName) {
        return categoriesList.contains(categoryName);
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public String getCategory(int index) {
        return categoriesList.get(index);
    }
}
