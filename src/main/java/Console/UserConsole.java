package Console;

import Model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserConsole {
    private List list;
    private ListViewConsole view;
    private Scanner scanner;


    void run() {
        int choice = -1;
        while (choice != 2) {
            printInitialOptions();
            System.out.print("Your choice: ");
            try {
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 3;
            }

            if (choice == 1) {
                System.out.print("Enter name of your list: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                list = new ListWithCategories(name);
                view = new ListViewConsole();
                int editing = -1;
                while (editing != 8) {
                    editing = editingListMode();
                }
            } else if (choice != 2) {
                System.out.println("Wrong input! Try again.\n");
            }
        }


    }

    int editingListMode() {
        view.showListEditingOptions();
        int choice;
        System.out.print("Enter your choice: ");
        try {
            scanner = new Scanner(System.in);
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        switch (choice) {
            case 1: {
                System.out.print("Enter item's name: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                addItem(name);
                showList();
                break;
            }
            case 2: {
                System.out.print("Enter item's number: ");
                scanner = new Scanner(System.in);
                int number;
                try {
                    number = scanner.nextInt();
                } catch (InputMismatchException e) {
                    number = -1;
                }
                deleteItem(number-1);
                showList();
                break;
            }
            case 3: {
                System.out.print("Enter item's number: ");
                scanner = new Scanner(System.in);
                int number;
                try {
                    number = scanner.nextInt();
                } catch (InputMismatchException e) {
                    number = -1;
                }
                uncheckedItem(number-1);
                showList();
                break;
            }
            case 4: {
                sortList();
                showList();
                break;
            }
            case 5: {
                loadFromFile();
                showList();
                break;
            }
            case 6: {
                save();
                showList();
                break;
            }
            case 7: {
                System.out.print("Enter item's name: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                addCategory(name);
                showList();
                break;
            }
            case 8:
                break;
            default: {
                System.out.println("Wrong input! Try again.\n");
                break;
            }
        }
        return choice;
    }

    void showList() {
        view.showList(list.getItems());
    }

    void addItem(String name) {
        if (!list.addItem(name)) System.out.println(name + " is already in a list");
    }

    void addCategory(String name) {
        int index = list.getItemIndex(name);
        if (index != -1) {
            view.showAvailableCategories(((ListWithCategories) list).getCategoriesList());
            view.addingCategoryOptions();
            int option = -1;
            while (option == -1) {
                System.out.print("Enter option number: ");
                scanner = new Scanner(System.in);
                try {
                    option = scanner.nextInt();
                } catch (InputMismatchException e) {
                    option = -1;
                }
                if (option != 1 && option != 2) option = -1;
                if (option == -1) System.out.println("Wrong input. Try again");
            }

            if (option == 1) {
                int choice = -1;
                while (choice == -1) {
                    int numberOfCategories = ((ListWithCategories) list).getCategoriesList().size();
                    System.out.print("Enter number of category: ");
                    choice = getChoice(numberOfCategories);
                }
                ((ListWithCategories) list).addCategory(index, ((ListWithCategories) list).getCategory(choice - 1));
            } else {
                System.out.print("Enter category's name: ");
                scanner = new Scanner(System.in);
                String categoryName = scanner.nextLine();
                ((ListWithCategories) list).addCategory(index, categoryName);
            }
        } else System.out.println("Item not found.");
    }

    void deleteItem(int index) {
        if (!list.deleteAt(index)) System.out.println("Item not found.");
    }

    void uncheckedItem(int index) {
        if (!list.uncheckItemAt(index)) System.out.println("Item not found.");
    }

    void sortList() {
        list.sortList();
    }

    void save() {
        list.saveList();
    }

    void loadFromFile() {
        int choice = -1;
        while (choice == -1) {
            view.showAvailableFiles(list.getAvailableFiles());
            int numberOfFiles = list.getAvailableFiles().size();
            System.out.print("Enter number of file: ");
            choice = getChoice(numberOfFiles);
        }
        list.loadListFromFile(choice);
    }

    private int getChoice(int listSize) {
        int choice;
        scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        if (choice <= 0 || choice > listSize) choice = -1;
        if (choice == -1) System.out.println("Wrong input. Try again");
        return choice;
    }

    void printInitialOptions() {
        System.out.println("Welcome in Shopping List App!\n");
        System.out.println("Enter 1 to create new list");
        System.out.println("Enter 2 to close the app");
    }


}
