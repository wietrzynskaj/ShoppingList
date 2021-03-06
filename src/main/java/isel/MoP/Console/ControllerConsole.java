package isel.MoP.Console;

import isel.MoP.Model.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ControllerConsole {

    private static final Logger LOGGER = Logger.getLogger(isel.MoP.Console.ControllerConsole.class);

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
                list = new ListWithCategories();
                list.setName(name);
                view = new ListViewConsole();
                int editing = -1;
                while (editing != 8) {
                    editing = editingListMode();
                }
            } else if (choice != 2) {
                LOGGER.error("Wrong input! Try again.\n");
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
                deleteItem(number - 1);
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
                uncheckedItem(number - 1);
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
                System.out.print("Enter item's number: ");
                int number = -1;
                try {
                    number = scanner.nextInt();
                } catch (InputMismatchException e) {
                    number = -1;
                }
                addCategory(number);
                showList();
                break;
            }
            case 8:
                break;
            default: {
                LOGGER.error("Wrong input! Try again.\n");
                break;
            }
        }
        return choice;
    }

    void showList() {
        view.showList(list.getItems());
    }

    void addItem(String name) {
        if (list.addItem(name)) LOGGER.error(name + " is already in a list");
    }

    void addCategory(int index) {
        if (index != -1) {
            view.showAvailableCategories(((ListWithCategories) list).getCategoriesList());
            view.addingCategoryOptions();
            int option = getOptionInput();
            if (option == 1) {
                int choice = -1;
                while (choice == -1) {
                    int numberOfCategories = ((ListWithCategories) list).getCategoriesList().size();
                    System.out.print("Enter number of category: ");
                    choice = getChoice(numberOfCategories);
                }
                ((ListWithCategories) list).addCategory(index-1, ((ListWithCategories) list).getCategory(choice - 1));
            } else {
                System.out.print("Enter category's name: ");
                scanner = new Scanner(System.in);
                String categoryName = scanner.nextLine();
                ((ListWithCategories) list).addCategory(index-1, categoryName);
            }
        } else LOGGER.error("Item not found.");
    }

    void deleteItem(int index) {
        if (!list.deleteAt(index)) LOGGER.error("Item not found.");
    }

    void uncheckedItem(int index) {
        if (!list.uncheckItemAt(index)) LOGGER.error("Item not found.");
    }

    void sortList() {
        list.sortList();
    }

    void save() {
        String name = list.getFileName();
        if (list.validateFileName(name)) {
            System.out.println("File with this name already exist. Do you want to overwrite it?");
            System.out.println("1.yes");
            System.out.println("2.no");
            int option = getOptionInput();
            if (option == 2) {
                System.out.print("Enter new file name: ");
                scanner = new Scanner(System.in);
                String newName = scanner.nextLine();
                name = newName + ".txt";
            }
        }
        try {
            list.saveList(name);
        } catch (IOException e) {
            LOGGER.error("Saving to file was not successful");
        }

    }

    void loadFromFile() {
        int choice = -1;
        while (choice == -1) {
            view.showAvailableFiles(list.getAvailableFiles());
            int numberOfFiles = list.getAvailableFiles().size();
            System.out.print("Enter number of file: ");
            choice = getChoice(numberOfFiles);
        }
        try {
            list.loadListFromFile(choice);
        } catch (IOException e1) {
            LOGGER.error("Reading from file was not successful");
        } catch (ClassNotFoundException e2) {
            LOGGER.error("Class not found");
        }

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
        if (choice == -1) LOGGER.error("Wrong input. Try again");
        return choice;
    }

    private int getOptionInput() {
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
            if (option == -1) LOGGER.error("Wrong input. Try again");
        }
        return option;
    }

    private void printInitialOptions() {
        System.out.println("Welcome in Shopping List App!\n");
        System.out.println("Enter 1 to create new list");
        System.out.println("Enter 2 to close the app");
    }


}
