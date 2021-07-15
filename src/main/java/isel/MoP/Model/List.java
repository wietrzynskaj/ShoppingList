package isel.MoP.Model;

import isel.MoP.Utils.ModelUtils;

import java.io.IOException;
import java.util.*;

public abstract class List implements Iterable<Item> {
    String name;
    ArrayList<Item> items = new ArrayList<>();
    final Stockroom stockroom = new Stockroom();

    public ArrayList<Item> getItems() {
        return items;
    }

    public void sortList() {
        items.sort(Comparator.comparing(Item::getName));
    }

    public void saveList(String fileName) throws IOException {
        stockroom.saveItems(items, fileName);
    }

    public void loadListFromFile(int number) throws IOException, ClassNotFoundException {
        items = stockroom.readItemsFromFile(number);
    }

    public void loadListFromFile(String fileName) throws IOException, ClassNotFoundException {
        items = stockroom.readItemsFromFile(fileName);
    }

    public String getFileName() {
        return name.replace(' ', '_') + ".txt";
    }

    public boolean validateFileName(String fileName) {
        return (fileName != null && stockroom.checkIfFileNameAlreadyExist(fileName));
    }

    public boolean addItem(String name) {
        int sizeBeforeAdding = items.size();
        Item newItem = new Item(name);
        items.add(newItem);
        items = ModelUtils.removeDuplicates(items);
        return (sizeBeforeAdding + 1) == items.size();
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
            items.get(index).setCheck(true);
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

    public void clearList() {
        items = new ArrayList<>();
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

    public void changeName(int index, String newName) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setName(newName);
        }
    }

    public ArrayList<String> getAvailableFiles() {
        return stockroom.getAvailableFiles();
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Stockroom getStockroom() {
        return stockroom;
    }

    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
