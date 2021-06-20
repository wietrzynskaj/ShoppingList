import org.junit.Before;
import org.junit.Test;

import Model.*;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class AppTests {
    List list;
    String DEFAULT_NAME = "test list";
    String TEST_FILE = "test_file.txt";
    String DEFAULT_FILE = "default_file.txt";
    String WRONG_PATH = "wrong_path.txt";
    String TEST_CATEGORY_NAME = "drinks";
    int INVALID_INDEX = 100;
    int FIRST_ITEM_INDEX = 0;
    int NUMBER_OF_DELETED_ADDED_FILES = 2;


    //initialize a default list for tests
    @Before
    public void init() {
        list = new List(DEFAULT_NAME);
        list.addItem("juice");
        list.addItem("milk");
        list.addItem("bread");
    }

    @Test
    public void readAndWriteToFileTest() throws IOException, ClassNotFoundException {
        list.loadListFromFile(DEFAULT_FILE);
        ObjectInputStream objectInputStreamAssert = new ObjectInputStream(new FileInputStream(DEFAULT_FILE));
        list.saveList(TEST_FILE);
        ObjectInputStream objectInputStreamTest = new ObjectInputStream(new FileInputStream(TEST_FILE));
        assertEquals(objectInputStreamAssert.readObject(), objectInputStreamTest.readObject());
    }

    @Test
    public void addingNewItemTest() {
        int numberOfItemsBeforeAdding = list.getItems().size();
        list.addItem("coffee");
        list.addItem("butter");
        int numberOfItemsAfterAdding = list.getItems().size();
        assertEquals(numberOfItemsBeforeAdding + NUMBER_OF_DELETED_ADDED_FILES, numberOfItemsAfterAdding);
    }

    @Test
    public void addingNewDuplicatedItemTest() {
        int numberOfItemsBeforeAdding = list.getItems().size();
        list.addItem("milk");
        int numberOfItemsAfterAdding = list.getItems().size();
        assertEquals(numberOfItemsBeforeAdding, numberOfItemsAfterAdding);
    }

    @Test
    public void removingItemTest() {
        int numberOfItemsBeforeDeleting = list.getItems().size();
        list.deleteAt(FIRST_ITEM_INDEX);
        list.deleteAt(FIRST_ITEM_INDEX);
        int numberOfItemsAfterDeleting = list.getItems().size();
        assertEquals(numberOfItemsBeforeDeleting, numberOfItemsAfterDeleting + NUMBER_OF_DELETED_ADDED_FILES);

    }

    @Test
    public void removingNotExistingItemTest() {
        int numberOfItemsBeforeDeleting = list.getItems().size();
        list.deleteAt(INVALID_INDEX);
        int numberOfItemsAfterDeleting = list.getItems().size();
        assertEquals(numberOfItemsBeforeDeleting, numberOfItemsAfterDeleting);
    }

    @Test
    public void sortingListTest() {
        Item firstItem = new Item("bread");
        list.sortList();
        assertEquals(firstItem, list.getItem(FIRST_ITEM_INDEX));
    }

    @Test
    public void addingCategoryTest() {
        ListWithCategories listWithCategories = new ListWithCategories(list);
        listWithCategories.addCategory(FIRST_ITEM_INDEX, TEST_CATEGORY_NAME);
        String name = ((Category)(listWithCategories.getItem(FIRST_ITEM_INDEX))).getCategoryName();
        assertEquals(TEST_CATEGORY_NAME, name);
    }

    @Test
    public void uncheckingItemTest() {
        list.uncheckItemAt(FIRST_ITEM_INDEX);
        assertEquals(true, list.getItem(FIRST_ITEM_INDEX).isUnchecked());
    }

    @Test
    public void uncheckingItemWrongInputTest() {
        list.uncheckItemAt(INVALID_INDEX);
        assertEquals(false, list.getItem(FIRST_ITEM_INDEX).isUnchecked());
    }

    @Test(expected = IOException.class)
    public void exceptionThrowableTest() throws IOException, ClassNotFoundException {
        list.getStockroom().readItemsFromFile(WRONG_PATH);
    }


}
