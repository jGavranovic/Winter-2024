package driver;
import items.*;
import clients.*;

public class Driver {
    private static Item[] collection;
    private static Client[] clients;
    public static void main(String[] args){
        collection = new Item[4];
        collection[0] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        //list[1] = new Media("Oshi no Ko", "Aka Akasaka", 2020, "Manga");
        //list[1] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        collection[1] = new Media((Media)collection[0]);
        collection[2] = new Book("Snowcrash", "Neil stephenson", 1994, 560);
        collection[3] = new Journal("Nature", "Et al.", 1990, 347);

    }

    private static void printMainMenu(){
        System.out.println("Please select an option: ");
        System.out.println("    1. Modify/view items");
        System.out.println("    2. Edit clients");
        System.out.println("    3. Lease/return item");
        System.out.println("    4. Display all loans from a client");
        System.out.println("    5. Show all leased items");
        System.out.println("    6. Display the biggest book");
        System.out.println("    7. Make a copy of the books array");
        System.out.println("    8. Quit");
    }

    private static void printOption1(){
        System.out.println("Please select an option: ");
        System.out.println("    1. Add an item");
        System.out.println("    2. Delete an item");
        System.out.println("    3. Edit an existing item");
        System.out.println("    4. List all items in a given category");
        System.out.println("    5. List all items");
    }

    private static void addItem(Item item){
        Item[] newCollection = new Item[collection.length+1];
        for(int i=0;i<collection.length;i++){
            newCollection[i] = collection[i];
        }
        newCollection[newCollection.length-1] = item;
        collection = newCollection;
    }

    private static void removeItem(Item item){
        if (!checkItemExist(item)){
            System.out.println("This item is not in the collection\n");
            return;
        }

        Item[] newCollection = new Item[collection.length-1];
        int i;
        for (i=0;i<collection.length;i++){
            if(collection[i]==item)
                break;
            newCollection[i] = collection[i];
        }
        for (;i<newCollection.length;i++){
            newCollection[i] = collection[i+1];
        }
        collection = newCollection;
        item.setLoaned(false);
    }

    private static boolean checkItemExist(Item item){
        for (Item collection : collection) {
            if (collection==item)
                return true;
        }
        return false;
    }

    // private static void editItem(Item item, String attribute){
    //     switch (attribute.toLowerCase()){
    //         "name":

    //     }
    // }
}
