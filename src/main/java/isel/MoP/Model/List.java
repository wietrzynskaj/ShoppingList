package isel.MoP.Model;

import isel.MoP.Utils.ModelUtils;

import java.io.IOException;
import java.util.*;

public class List {
    String name;
    ArrayList<Item> items = new ArrayList<>();
    final Stockroom stockroom = new Stockroom();

    public List(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void sortList() {
        items.sort(Comparator.comparing(Item::getName));
    }

    public void saveList(String fileName) {
        try {
            stockroom.saveItems(items, fileName);
        } catch (IOException e) {
            System.out.println("Saving to file was not successful");
        }
    }

    public void loadListFromFile(int number) throws IOException, ClassNotFoundException {
        items = stockroom.readItemsFromFile(number);
    }

    public void loadListFromFile(String fileName) {
        try {
            items = stockroom.readItemsFromFile(fileName);
        } catch (IOException e1) {
            System.out.println("Reading from file was not successful");
        } catch (ClassNotFoundException e2) {
            System.out.println("Class not found");
        }
    }

    public String getFileName() {
        return name.replace(' ', '_') + ".txt";
    }

    public boolean validateFileName(String fileName) {
        return (!stockroom.checkIfFileNameAlreadyExist(fileName));
    }

    public boolean addItem(String name) {
        int sizeBeforeAdding = items.size();
        Item newItem = new Item(name);
        items.add(newItem);
        items = ModelUtils.removeDuplicates(items);
        return sizeBeforeAdding == items.size();
    }

    public boolean deleteAt(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public boolean uncheckItemAt(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setUnchecked(true);
            return true;
        }
        return false;
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public int getItemIndex(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Item getItem(String name) {
        int index = getItemIndex(name);
        return getItem(index);
    }

    public boolean changeName(int index, String newName) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setName(newName);
            return true;
        }
        return false;
    }

    public ArrayList<String> getAvailableFiles() {
        return stockroom.getAvailableFiles();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Stockroom getStockroom() {
        return stockroom;
    }

    @Override
    public String toString() {
        return name;
    }
}
