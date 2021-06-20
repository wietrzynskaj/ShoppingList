import org.junit.Before;
import org.junit.Test;

import isel.MoP.Model.*;

import java.io.*;

import static org.junit.Assert.*;

public class AppTests {
    List list;
    String DEFAULT_NAME = "test list";
    String TEST_FILE = "test_file.txt";
    String DEFAULT_FILE = "default_file.txt";
    String WRONG_PATH = "wrong_path.txt";
    String TEST_CATEGORY_NAME = "drinks";
    String FIRST_ITEM_NAME = "juice";
    String SECOND_ITEM_NAME = "milk";
    String THIRD_ITEM_NAME = "bread";
    String TEST_NOT_EXISTING_NAME = "ice cream";
    String TEST_NEW_ITEM_NAME = "orange juice";
    int TEST_LIST_SIZE = 3;
    int INVALID_INDEX = 100;
    int FIRST_ITEM_INDEX = 0;
    int NOT_EXISTING_ITEM_INDEX = -1;
    int NUMBER_OF_DELETED_ADDED_FILES = 2;


    //initialize a default list for tests
    @Before
    public void init() {
        list = new List(DEFAULT_NAME);
        list.addItem(FIRST_ITEM_NAME);
        list.addItem(SECOND_ITEM_NAME);
        list.addItem(THIRD_ITEM_NAME);
    }


    @Test
    public void addingNewItemTest() {
        int numberOfItemsBeforeAdding = list.getItems().size();
        list.addItem("coffee");
        list.addItem("butter");
        int numberOfItemsAfterAdding = list.getItems().size();
        assertEquals(numberOfItemsBeforeAdding, numberOfItemsAfterAdding - NUMBER_OF_DELETED_ADDED_FILES);
    }

    @Test
    public void addingNewDuplicatedItemTest() {
        int numberOfItemsBeforeAdding = list.getItems().size();
        list.addItem(THIRD_ITEM_NAME);
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
    public void getListOfItemsTest() {
        int testSize = list.getItems().size();
        assertEquals(TEST_LIST_SIZE, testSize);
    }

    @Test
    public void sortingListTest() {
        Item firstItem = new Item(THIRD_ITEM_NAME);
        list.sortList();
        assertEquals(firstItem, list.getItem(FIRST_ITEM_INDEX));
    }

    @Test
    public void uncheckingItemTest() {
        list.uncheckItemAt(FIRST_ITEM_INDEX);
        assertTrue(list.getItem(FIRST_ITEM_INDEX).isUnchecked());
    }

    @Test
    public void uncheckingItemWrongInputTest() {
        list.uncheckItemAt(INVALID_INDEX);
        assertFalse(list.getItem(FIRST_ITEM_INDEX).isUnchecked());
    }

    @Test
    public void getIndexOfItemByNameTest() {
        int testIndex = list.getItemIndex(FIRST_ITEM_NAME);
        assertEquals(FIRST_ITEM_INDEX, testIndex);
    }

    @Test
    public void getIndexOfNotExistingItemByNameTest() {
        int testIndex = list.getItemIndex(TEST_NOT_EXISTING_NAME);
        assertEquals(NOT_EXISTING_ITEM_INDEX, testIndex);
    }

    @Test
    public void changeNameOfItemTest() {
        list.changeName(FIRST_ITEM_INDEX, TEST_NEW_ITEM_NAME);
        String testName = list.getItem(FIRST_ITEM_INDEX).getName();
        assertEquals(TEST_NEW_ITEM_NAME, testName);
    }

    @Test
    public void getNotItemByNameTest() {
        int testIndex = list.getItemIndex(FIRST_ITEM_NAME);
        assertEquals(FIRST_ITEM_INDEX, testIndex);
    }

    @Test
    public void readAndWriteToFileTest() throws IOException, ClassNotFoundException {
        list.loadListFromFile(DEFAULT_FILE);
        ObjectInputStream objectInputStreamAssert = new ObjectInputStream(new FileInputStream(DEFAULT_FILE));
        list.saveList(TEST_FILE);
        ObjectInputStream objectInputStreamTest = new ObjectInputStream(new FileInputStream(TEST_FILE));
        assertEquals(objectInputStreamAssert.readObject(), objectInputStreamTest.readObject());
    }

    @Test(expected = IOException.class)
    public void exceptionThrowableTest() throws IOException, ClassNotFoundException {
        list.getStockroom().readItemsFromFile(WRONG_PATH);
    }

    @Test
    public void addingCategoryTest() {
        ListWithCategories listWithCategories = new ListWithCategories(list);
        listWithCategories.addCategory(FIRST_ITEM_INDEX, TEST_CATEGORY_NAME);
        String name = ((Category)(listWithCategories.getItem(FIRST_ITEM_INDEX))).getCategoryName();
        assertEquals(TEST_CATEGORY_NAME, name);
    }


}
