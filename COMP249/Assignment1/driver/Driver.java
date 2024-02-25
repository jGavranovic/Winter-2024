package driver;
import items.*;
import clients.*;
import java.util.Scanner;   

public class Driver {
    private static Item[] collection = new Item[0];
    private static Client[] clients = {new Client("john", "eqw@gmail.com", "3213214")};//new Client[0];
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        collection = new Item[4];
        collection[0] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        //list[1] = new Media("Oshi no Ko", "Aka Akasaka", 2020, "Manga");
        //list[1] = new Media("Death note", "Tsugomi Oba", 2006, "Manga");
        collection[1] = new Media((Media)collection[0]);
        collection[2] = new Book("Snowcrash", "Neil stephenson", 1994, 560);
        collection[3] = new Journal("Nature", "Et al.", 1990, 347);
        boolean wantMenu = true, escape;
        do {
        System.out.println("Select the behavior you would like");
        System.out.println("    1. Default blank start");
        System.out.println("    2. Predefined scenario");

        String input = scan.nextLine();
        switch (input){
            case "1":
                wantMenu = true;
                escape = true;
                break;
            case "2":
                wantMenu = false;
                escape = true;
                break;
            default:
                defaultOption();
                escape = false;
        }
        } while (!escape);
        while (wantMenu) {
            printMainMenu();
           // printAllClients();
            String input = scan.nextLine();
            switch (input){
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
                // case "7":
                //     chooseOption7(); break;
                case "8":
                    chooseOption8(); break;
                default:
                    defaultOption();
            }
        }

        //Hard coded scenario
        
        

    }

    private static void printMainMenu(){
        System.out.println("\nPlease select an option: ");
        System.out.println("    1. Modify/view items");
        System.out.println("    2. Edit/view clients");
        System.out.println("    3. Lease/return item");
        System.out.println("    4. Display all loans from a client");
        System.out.println("    5. Show all leased items");
        System.out.println("    6. Display the biggest book");
        System.out.println("    7. Make a copy of the books array");
        System.out.println("    8. Quit");
    }

    private static void chooseOption1(){
        printOption1();
        switch (scan.nextLine()){
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
        String input = scan.nextLine();
        switch(input){
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
        String author = scan.nextLine(); //scan.next();
        System.out.print("Year published: ");
        int yearPublised = scan.nextInt();
        System.out.print("Type: "); scan.nextLine();
        String type = scan.nextLine();

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
        removeItem(scan.nextLine());
    }

    private static void removeItem(String itemId){
        if (!checkItemIdExists(itemId)){
            System.out.println("This item does not exist!");
            return;
        }
        Item[] newCollection = new Item[collection.length-1];
        int i;
        for (i=0; i < newCollection.length; i++) {
            if (collection[i].getItemId().equalsIgnoreCase(itemId)) 
                break;
            newCollection[i] = collection[i];
        }
        for (; i < newCollection.length; i++) {
            newCollection[i] = collection[i+1];
        }
        collection = newCollection;
        System.out.println("The item has been removed");
    }

    private static void editItemOption(){
        System.out.print("Enter the id of the item you would like to edit: ");
        String itemId = scan.nextLine();
        if (!checkItemIdExists(itemId)){
            System.out.println("This item does not exist!");
            return;
        }
        while (true){
            System.out.println("Which attribute would you like to edit?");
            System.out.println("    1. Name");
            System.out.println("    2. Author");
            System.out.println("    3. Year published");
            if (itemId.substring(0,1).equalsIgnoreCase("B"))
                System.out.println("    4. Page number");
            else if (itemId.substring(0,1).equalsIgnoreCase("J"))
                System.out.println("    4. Volume number");
            else if (itemId.substring(0,1).equalsIgnoreCase("M"))
                System.out.println("    4. Type");
            System.out.println("    5. Quit");
            switch (scan.nextLine()){
                case "1":
                    editItemName(itemId); break;
                case "2":
                    editItemAuthor(itemId); break;
                case "3":
                    editItemYear(itemId); break;
                case "4":

                if (itemId.substring(0,1).equalsIgnoreCase("B"))
                    editItemPage(itemId);
                else if (itemId.substring(0,1).equalsIgnoreCase("J"))
                    editItemVol(itemId);
                else if (itemId.substring(0,1).equalsIgnoreCase("M"))
                    editItemType(itemId);
                break;
                case "5":
                    return;
                default:
                    defaultOption();
            }
        }
    }

    private static void editItemName(String itemId){
        System.out.print("Enter the new name: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                collection[i].setItemName(scan.nextLine());//scan.next();
                System.out.println("The item's name was changed");
                break;
            }
        }
    }
    private static void editItemAuthor(String itemId){
        System.out.print("Enter the new author: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                collection[i].setAuthor(scan.nextLine()); //scan.next();
                System.out.println("The item's author was changed");
                break;
            }
        }
    }
    private static void editItemYear(String itemId){
        System.out.print("Enter the new author: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                collection[i].setYearPublished(scan.nextInt());
                System.out.println("The item's year was changed");
                break;
            }
        }
    }
    private static void editItemPage(String itemId){
        System.out.print("Enter the new page count: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                ((Book)collection[i]).setPageNumber(scan.nextInt());
                System.out.println("The item's page count was changed");
                break;
            }
        }
    }
    private static void editItemVol(String itemId){
        System.out.print("Enter the new volume number: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                ((Journal)collection[i]).setVolNumber(scan.nextInt());
                System.out.println("The item's volume number was changed");
                break;
            }
        }
    }
    private static void editItemType(String itemId){
        System.out.print("Enter the new type: ");
        for (int i=0; ; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                ((Media)collection[i]).setType(scan.nextLine());
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
                //scan.next();
                printBooks(); break;
            case "2":
                //scan.next();
                printJournals(); break;
            case "3":
                //scan.next();
                printMedia(); break;
            default:
                //scan.next();
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
        System.out.println("-----------COLLECTION-----------");
        for (Item item : collection) {
            System.out.println(item+"\n");
        }
    }

    private static void chooseOption2(){
        printOption2();
        switch (scan.nextLine()){
            case "1":
                //scan.next(); 
                addClientOption(); break;
            case "2":
                //scan.next(); 
                editClientOption(); break;
            case "3":
                //scan.next(); 
                removeClientOption(); break;
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
        String name = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scan.nextLine();

        addClient(new Client(name, email, phoneNumber));
        System.out.println("New client was added");
    }

    private static void addClient(Client client){
        Client[] newClients = new Client[clients.length+1];
        int i;
        for (i=0;i<clients.length; i++ ){
            newClients[i] = clients[i];
        }
        newClients[i] = client;
        clients = newClients;
    }

    private static void editClientOption(){
        printAllClients();
        System.out.print("Enter the id of the client you would like to edit: ");
        String clientId = scan.nextLine();
        if (!checkClientIdExist(clientId)){
            System.out.println("No such client exists");
            return;
        }

        System.out.println("Enter the field you would like to edit:");
        System.out.println("    1. Name");
        System.out.println("    2. Email");
        System.out.println("    3. Phone number");
        String input = scan.nextLine(); //scan.next();
        switch (input){
            case "1":
                editClientNameOption(clientId); break;
            case "2":
                editClientEmailOption(clientId); break;
            case "3":
                editClientPhoneOption(clientId); break;
            default:
                defaultOption();
        }
    }

    private static void editClientNameOption(String clientId){
        System.out.print("Enter the new client name: ");
        String name = scan.nextLine(); //scan.next();
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)){
                client.setName(name);
                System.out.println("Client ID was changed");
                return;
            }
        }

    }

    private static void editClientEmailOption(String clientId){
        System.out.print("Enter the new client email: ");
        String email = scan.nextLine(); //scan.next();
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)){
                client.setEmail(email);
                System.out.println("Client email was changed");
                return;
            }
        }

    }

    private static void editClientPhoneOption(String clientId){
        System.out.print("Enter the new client phone number: ");
        String phone = scan.nextLine();
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)){
                client.setPhoneNumber(phone);
                System.out.println("Client phone number was changed");
                return;
            }
        }

    }

    private static void removeClientOption(){
        System.out.print("Enter the id of the clients you would like to remove: ");
        String clientId = scan.nextLine();
        if (!checkClientIdExist(clientId)){
            System.out.println("The client does not exist");
            return;
        }
        Client[] newClients = new Client[clients.length-1];
        int i=0;
        for (i=0; i<newClients.length;i++){
            if ((clients[i].getClientId().equals(clientId)))
                break;
            newClients[i] = clients[i];
        }

        for (;i<newClients.length;i++){
            newClients[i] = clients[i+1];
        }
        clients = newClients;
        System.out.println("The client was successfully removed");
    }

    private static void chooseOption3(){
        System.out.println("Please enter which action you would like to do: ");
        System.out.println("    1. Lease an item");
        System.out.println("    2. Return an item");
        String input = scan.nextLine();
        switch(input){
            case "1":
                addItemLeaseOption(); break;
            case "2":
                returnItemLeaseOption(); break;
            default:
                defaultOption();
        }
    }
    private static void addItemLeaseOption(){
        System.out.print("Please enter the ID of the item you would like to lease: ");
        String itemId = scan.nextLine();

        if (!checkItemIdExists(itemId)){
            System.out.println("The item with this ID does not exist");
            return;
        }

        System.out.print("Please enter the ID of the client leasing the item: ");
        String clientId = scan.nextLine();

        if(!checkClientIdExist(clientId)){
            System.out.println("The client with this ID does not exist");
            return;
        }

        addItemClient(clientId, itemId);
    }
    private static void addItemClient(String clientId, String itemId){
        int i,j;
        for (i=0; i<collection.length; i++){
            if (collection[i].getItemId().equalsIgnoreCase(itemId)){
                break;
            }
        }
        for (j=0; j<clients.length;j++){
            if (clients[j].getClientId().equals(clientId)){
                break;
            }
        }
        //System.out.println("i: "+i+"   j: "+j);
        clients[j].addItem(collection[i]);
        System.out.println("The item has been leased");
    }
    private static void returnItemLeaseOption(){
        System.out.print("Please enter the ID of the item you would like to return: ");
        String itemId = scan.nextLine();

        if (!checkItemIdExists(itemId)){
            System.out.println("The item with this ID does not exist");
            return;
        }

        returnItemClient(itemId);
    }
    private static void returnItemClient(String itemId){
        if (!checkLeased(itemId)){
            System.out.println("The item is not being leased");
            return;
        }
        int i,j;
        for (i= 0; i < collection.length; i++) {
            if (collection[i].getItemId().equalsIgnoreCase(itemId))
                break;
        }
        for (j=0; j<clients.length;j++){
            clients[j].removeItem(collection[i]);
        }
        System.out.println("The item has been removed");
    }

    private static void chooseOption4(){
        System.out.print("Enter the id of the client you would like to view: ");
        String clientId = scan.nextLine();

        if(!checkClientIdExist(clientId)){
            System.out.println("This client does not exist");
            return;
        }

        for (Client client : clients) {
            if (client.getClientId().equalsIgnoreCase(clientId)){
                System.out.println(client);
                return;
            }
        }
    }

    private static void chooseOption5(){
        System.out.println("--------ALL LEASED ITEMS---------");
        for (Item item : collection) {
            if (item.getLoaned())
                System.out.println(item);
        }
    }
    private static void chooseOption6(){
        System.out.println("----BIGGEST BOOK----");
        System.out.println(getBiggestBook());
    }
    private static Book getBiggestBook(){
        int max = 0;
        int index = 0;
        for (int i=0; i<collection.length; i++) {
            if(collection[i] instanceof Book){
                if (((Book)collection[i]).getPageNumber() > max){
                    max = ((Book)collection[i]).getPageNumber();
                    index = i;
                }
            }
        }
        return (Book)collection[index];
    }

    private static void chooseOption7(){

    }
    
    private static Book[] copyBooks(Book[] books){
        Book[] copy = new Book[books.length];

        for (int i=0; i<books.length; i++){
            copy[i] = new Book(books[i]);
        }

        return copy;
    }
    
    private static void chooseOption8(){
        System.out.println("Thank you for using the program!");
        System.exit(0);
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
            if (item.getItemId().equalsIgnoreCase(itemId))
                return true;
        }
        return false;
    }
    private static boolean checkClientIdExist(String clientId){
        for (Client client : clients) {
            if (client.getClientId().equals(clientId))
                return true;
        }
        return false;
    }
    private static void printAllClients(){
        System.out.println("---List of all clients:");
        for (Client client : clients) {
            System.out.println(client+"\n");
        }
    }

    private static boolean checkLeased(String itemId){
        for (Item item : collection) {
            if (item.getItemId().equalsIgnoreCase(itemId)){
                if (item.getLoaned())
                    return true;
            }
        }
        return false;
    }

    private static void defaultOption(){
        System.out.println("The input you provided was invalid, please try again!");
    }
}