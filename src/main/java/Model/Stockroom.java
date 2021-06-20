package Model;

import java.io.*;
import java.util.ArrayList;

public class Stockroom {
    ArrayList<String> availableFiles;
    final String DEFAULT_FILE = "default_file.txt";

    public Stockroom() {
        availableFiles = new ArrayList<>();
        availableFiles.add(DEFAULT_FILE);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Item> readItemsFromFile(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Item> stockItems;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        stockItems = (ArrayList<Item>) objectInputStream.readObject();
        return stockItems;
    }

    public ArrayList<Item> readItemsFromFile(int number) throws IOException, ClassNotFoundException {
        String fileName = availableFiles.get(number - 1);
        return readItemsFromFile(fileName);
    }

    public void saveItems(ArrayList<Item> items, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(items);
        objectOutputStream.flush();
        objectOutputStream.close();
        if(!availableFiles.contains(fileName)) availableFiles.add(fileName);
    }

    public ArrayList<String> getAvailableFiles() {
        return availableFiles;
    }

}
