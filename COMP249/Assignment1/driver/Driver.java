package driver;
import items.*;
import clients.*;
import java.util.Scanner;   

public class Driver {
    private static Item[] collection = new Item[0];
    private static Client[] clients = new Client[0];
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        collection = new Item[4];
        collection[0] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        //list[1] = new Media("Oshi no Ko", "Aka Akasaka", 2020, "Manga");
        //list[1] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        collection[1] = new Media((Media)collection[0]);
        collection[2] = new Book("Snowcrash", "Neil stephenson", 1994, 560);
        collection[3] = new Journal("Nature", "Et al.", 1990, 347);
        while (true) {
            printMainMenu();
            switch (scan.next()){
                case "1":
                    chooseOption1(); break;
                case "2":
                    chooseOption2(); break;
                case "3":
                    chooseOption3(); break;
                case "4":
                    chooseOption4(); break;
                case "5":
                    chooseOption5(); break;
                case "6":
                    chooseOption6(); break;
                case "7":
                    chooseOption7(); break;
                case "8":
                    chooseOption8(); break;
                default:
                    defaultOption();
            }
        }
        

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

    private static void chooseOption1(){
        printOption1();
        switch (scan.next()){
            case "1":
                addItemOption(); break;
            case "2":
                deleteItemOption(); break;
            case "3":
                editItemOption(); break;
            case "4":
                listCategoryOption(); break;
            case "5":
                listAllItemOption(); break;
            default:
                defaultOption();
        }
    }
    private static void printOption1(){
        System.out.println("Please select an option: ");
        System.out.println("    1. Add an item");
        System.out.println("    2. Delete an item");
        System.out.println("    3. Edit an existing item");
        System.out.println("    4. List all items in a given category");
        System.out.println("    5. List all items");
    }

    private static void addItemOption(){
        System.out.println("What type is this new item?");
        System.out.println("    1. Book");
        System.out.println("    2. Journal");
        System.out.println("    3. Media");
        switch(scan.next()){
            case "1":
                addBookOption(); break;
            case "2":
                addJournalOption(); break;
            case "3":
                addMediaOption(); break;
            default:
                defaultOption();
        }
    }

    private static void addBookOption(){
        System.out.println("Please enter the information of the new book:");

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine();
        System.out.print("Year published: ");
        int yearPublised = scan.nextInt();
        System.out.print("Page number: ");
        int pageNumber = scan.nextInt();

        addItemToCollection(new Book(name, author, yearPublised, pageNumber));
        System.out.println("The book has been added to the colection");
    }

    private static void addJournalOption(){
        System.out.println("Please enter the information of the new journal:");

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine();
        System.out.print("Year published: ");
        int yearPublised = scan.nextInt();
        System.out.print("Volume number: ");
        int volNumber = scan.nextInt();

        addItemToCollection(new Journal(name, author, yearPublised, volNumber));
        System.out.println("The journal has been added to the colection");
    }

    private static void addMediaOption(){
        System.out.println("Please enter the information of the new media:");

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine(); scan.next();
        System.out.print("Year published: ");
        int yearPublised = scan.nextInt();
        System.out.print("Type: ");
        String type = scan.next();

        addItemToCollection(new Media(name, author, yearPublised, type));
        System.out.println("The media has been added to the colection");
    }

    private static void addItemToCollection(Item item){
        Item[] newCollection = new Item[collection.length+1];
        for(int i=0;i<collection.length;i++){
            newCollection[i] = collection[i];
        }
        newCollection[newCollection.length-1] = item;
        collection = newCollection;
    }

    private static void deleteItemOption(){
        System.out.print("Enter the id of the item your would like to remove: ");
        removeItem(scan.next());
    }

    private static void removeItem(String itemId){
        if (!checkItemIdExists(itemId)){
            System.out.println("This item does not exist!");
            return;
        }
        Item[] newCollection = new Item[collection.length-1];
        int i=0;
        for (; i < newCollection.length; i++) {
            if (collection[i].getItemId() == itemId) 
                break;
            newCollection[i] = collection[i];
        }
        for (; i < newCollection.length; i++) {
            newCollection[i] = collection[i+1];
        }
        System.out.println("The item has been removed");
    }

    private static void editItemOption(){
        System.out.print("Enter the id of the item you would like to edit: ");
        String itemId = scan.next();
        if (!checkItemIdExists(itemId)){
            System.out.println("This item does not exist!");
            return;
        }
        System.out.println("Which attribute would you like to edit?");
        System.out.println("    1. Name");
        System.out.println("    2. Author");
        System.out.println("    3. Year published");
        if (itemId.substring(0,1).equals("B"))
            System.out.println("    4. Page number");
        else if (itemId.substring(0,1).equals("J"))
            System.out.println("    4. Volume number");
        else if (itemId.substring(0,1).equals("M"))
            System.out.println("    4. Type");
        switch (scan.next()){
            case "1":
                editItemName(itemId); break;
            case "2":
                editItemAuthor(itemId); break;
            case "3":
                editItemYear(itemId); break;
            case "4":
            if (itemId.substring(0,1).equals("B"))
                editItemPage(itemId);
            else if (itemId.substring(0,1).equals("J"))
                editItemVol(itemId);
            else if (itemId.substring(0,1).equals("M"))
                editItemType(itemId);
            break;
            default:
                defaultOption();
        }
    }

    private static void editItemName(String itemId){
        System.out.print("Enter the new name: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                collection[i].setItemName(scan.nextLine());scan.next();
                System.out.println("The item's name was changed");
                break;
            }
        }
    }
    private static void editItemAuthor(String itemId){
        System.out.print("Enter the new author: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                collection[i].setAuthor(scan.nextLine()); scan.next();
                System.out.println("The item's author was changed");
                break;
            }
        }
    }
    private static void editItemYear(String itemId){
        System.out.print("Enter the new author: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                collection[i].setYearPublished(scan.nextInt());
                System.out.println("The item's year was changed");
                break;
            }
        }
    }
    private static void editItemPage(String itemId){
        System.out.print("Enter the new page count: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                ((Book)collection[i]).setPageNumber(scan.nextInt());
                System.out.println("The item's page count was changed");
                break;
            }
        }
    }
    private static void editItemVol(String itemId){
        System.out.print("Enter the new volume number: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                ((Journal)collection[i]).setVolNumber(scan.nextInt());
                System.out.println("The item's volume number was changed");
                break;
            }
        }
    }
    private static void editItemType(String itemId){
        System.out.print("Enter the new type: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equals(itemId)){
                ((Media)collection[i]).setType(scan.next());
                System.out.println("The item's type was changed");
                break;
            }
        }
    }
    private static void listCategoryOption(){
        System.out.println("Enter the category of items you would like to list");
        System.out.println("    1. Book");
        System.out.println("    2. Journal");
        System.out.println("    3. Media");
        switch (scan.nextLine()){
            case "1":
                scan.next();
                printBooks(); break;
            case "2":
                scan.next();
                printJournals(); break;
            case "3":
                scan.next();
                printMedia(); break;
            default:
                scan.next();
                defaultOption();
        }
    }

    private static void printBooks(){
        for (Item item : collection) {
            if (item.getItemId().substring(0,1).equals("B"))
            System.out.println(item+"\n");
        }
    }
    private static void printJournals(){
        for (Item item : collection) {
            if (item.getItemId().substring(0,1).equals("J"))
            System.out.println(item+"\n");
        }
    }
    private static void printMedia(){
        for (Item item : collection) {
            if (item.getItemId().substring(0,1).equals("M"))
            System.out.println(item+"\n");
        }
    }
    private static void listAllItemOption(){
        for (Item item : collection) {
            System.out.println(item+"\n");
        }
    }

    private static void chooseOption2(){
        printOption2();
        switch (scan.nextLine()){
            case "1":
                scan.next(); addClientOption(); break;
            case "2":
                scan.next(); editClientOption(); break;
            case "3":
                scan.next(); removeClientOption(); break;
            default:
                defaultOption();
        }
    }


    private static void printOption2(){
        System.out.println("Choose one of the following options");
        System.out.println("    1. Add a client");
        System.out.println("    2. Edit a client");
        System.out.println("    3. Remove a client");
    }

    private static void addClientOption(){
        System.out.println("Please enter the information of the new client");

        System.out.print("Name: ");
        String name = scan.next();
        System.out.print("Email: ");
        String email = scan.next();
        System.out.print("Phone number: ");
        long phoneNumber = scan.nextLong();

        addClient(new Client(name, email, phoneNumber));
        System.out.println("New client was added");
    }

    private static void addClient(Client client){
        Client[] newClients = new Client[clients.length+1];
        int i=0;
        for (i=0;i<clients.length; i++ ){
            newClients[i] = clients[i];
        }
        newClients[i] = client;
    }

    private static void editClientOption(){
        System.out.print("Enter the id of the client you would like to edit: ");
        long clientId = scan.nextLong();
        if (!checkClientIdExist(clientId)){
            System.out.println("No such client exists");
            return;
        }
        

    }

    private static boolean checkItemExist(Item item){
        for (Item collection : collection) {
            if (collection==item)
                return true;
        }
        return false;
    }

    private static boolean checkItemIdExists(String itemId){
        for (Item item : collection) {
            if (item.getItemId().equals(itemId))
                return true;
        }
        return false;
    }

    private static boolean checkClientIdExist(long clientId){
        for (Client client : clients) {
            if (client.getClientId()==clientId)
                return true;
        }
        return false;
    }



    private static void defaultOption(){
        System.out.println("The option you provided was invalid, please try again!");
    }
}