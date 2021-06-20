package Console;

import java.util.ArrayList;

import Model.*;

public class ListViewConsole {

    void showList(ArrayList<Item> items) {
        System.out.println("\nCurrent list: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    void showAvailableCategories(ArrayList<String> categories) {
        System.out.println("\nAvailable categories: ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }

    public void showAvailableFiles(ArrayList<String> files) {
        System.out.println("\nAvailable files: ");
        int k = 1;
        for (String fileName : files) {
            System.out.println((k++) + ". " + fileName);
        }
    }

    void addingCategoryOptions() {
        System.out.println("\nOptions: ");
        System.out.println("1.Choose from available ");
        System.out.println("2.Create new ");

    }

    void showListEditingOptions() {
        System.out.println("\nYou have now following options: ");
        System.out.println("1.Add new item");
        System.out.println("2.Delete item");
        System.out.println("3.Uncheck the item");
        System.out.println("4.Sort list");
        System.out.println("5.Load list from the file");
        System.out.println("6.Save list to the file");
        System.out.println("7.Add/Change category to the item");
        System.out.println("8.Exit");
    }
}
