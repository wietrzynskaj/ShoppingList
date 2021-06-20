package isel.MoP.Controller;

import isel.MoP.View.ListView;
import isel.MoP.Model.Item;
import isel.MoP.Model.List;

import java.util.ArrayList;

public class User {

    final String DEFAULT_FILENAME = "fileToRead.txt";

    List myList;
    ListView listView;

    public void createList(String listName){
        myList = new List(listName);
        listView = new ListView();

    }

    ArrayList<Item> getItemList(){
        return myList.getItems();
    }

    void addItem() {

    }

}
