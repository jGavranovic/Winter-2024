import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This is the Driver class for the program
 * @author Jovan Gavranovic (40282175)
 * 
 */
public class Driver {

    private static Scanner scan = null;
    private static DoublyLinkedList vocab_list = new DoublyLinkedList();
    private static Scanner inputRead = new Scanner(System.in);
    /**
     * This is the main methods which executes during runtime
     * @param args 
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the Vocabulary list!");
        while (true) {
            displayMenu();
            String input = inputRead.nextLine();

            switch (input){
                case "1":
                    option1();break;
                case "2":
                    option2(); break;
                case "3":
                    option3(); break;                  
                case "4":
                    option4(); break;
                case "5":
                    option5();break;
                case "6":
                    option6(); break;
                case "7":
                    option7(); break;
                case "8":
                    option8(); break;
                case "9":
                    option9();break;
                case "0":
                    System.out.println("Thank you for using the program");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }


        }



        // System.out.println(vocab_list);
        // System.out.println(vocab_list.toString().substring(0,1));
    }
    
    /**
     * The method to execute when the user selects option 1
     */
    private static void option1(){
        boolean exit = false;
        while (!exit){
            int num = chooseTopic();
            if (num == 0) return;
            vocab_list.displayOne(num);
        }
    }
    /**
     * The method to execute when the user selects option 2
     */
    private static void option2(){
        if (vocab_list.size() == 0){
            int num = 1;
            System.out.print("Enter a new topic name: ");
            String newTopic = inputRead.nextLine();
            vocab_list.addTopicBefore(newTopic, num);
            System.out.println("Enter new word entries - (Enter to exit)");
            String input = inputRead.nextLine();
            while (!input.equals("")){
                if (vocab_list.alreadyExists(input, num)){
                    System.out.println("Word already in list");
                }else {
                vocab_list.addWordtoTopic(input, num);
                }
                input = inputRead.nextLine();
            }
        }else {
            int num = chooseTopic();
            if (num == 0) return;
            System.out.print("Enter a new topic name: ");
            String newTopic = inputRead.nextLine();
            vocab_list.addTopicBefore(newTopic, num);
            System.out.println("Enter new word entries - (Enter to exit)");
            String input = inputRead.nextLine();
            while (!input.equals("")){
                if (vocab_list.alreadyExists(input, num)){
                    System.out.println("Word already in list");
                }else {
                vocab_list.addWordtoTopic(input, num);
                }
                input = inputRead.nextLine();
            }
        }
    }
    /**
     * The method to execute when the user selects option 3
     */
    private static void option3(){
        if (vocab_list.size() == 0){
            int num = 0;
            System.out.print("Enter a new topic name: ");
            String newTopic = inputRead.nextLine();
            vocab_list.addTopicAfter(newTopic, num);
            num++;
            System.out.println("Enter new word entries - (Enter to exit)");
            String input = inputRead.nextLine();
            while (!input.equals("")){
                vocab_list.addWordtoTopic(input, num);
                input = inputRead.nextLine();
            }
        }else {
            int num = chooseTopic();
            if (num == 0) return;
            System.out.print("Enter a new topic name: ");
            String newTopic = inputRead.nextLine();
            vocab_list.addTopicAfter(newTopic, num);
            num++;
            System.out.println("Enter new word entries - (Enter to exit)");
            String input = inputRead.nextLine();
            while (!input.equals("")){
                vocab_list.addWordtoTopic(input, num);
                input = inputRead.nextLine();
            }
        }
    }
    /**
     * The method to execute when the user selects option 4
     */
    private static void option4(){
        int num = chooseTopic();
        if (num == 0) return;
        vocab_list.removeTopic(num);
    }
    
    /**
     * A generic method which prompts presents all topics in the vocab list and asks the user to select one.
     * Includes input validation.
     */
    private static int chooseTopic(){
        System.out.println("------------------------------");
        System.out.println("           Pick a topic       ");
        System.out.println("------------------------------");
        vocab_list.displayList();
        System.out.println("   0  Exit");
        System.out.println("------------------------------");
        System.out.print("Enter your choice: ");
        boolean valid = false;
        int num = 0;
        while (!valid){
            try {
                num = Integer.parseInt(inputRead.nextLine());
                if (num!= 0 && (num<1 || num>vocab_list.size())) throw new Exception();
                valid = true;
            } catch (Exception e) {System.out.println("Invalid, please try again");}
        }
        return num;
    }

    /**
     * The method to execute when the user selects option 5
     */
    private static void option5(){
        int num = chooseTopic();
        if (vocab_list.size() == 0) return;
        while (true){
            System.out.println("------------------------------");
            System.out.println("       Modify Topics Menu     ");
            System.out.println("------------------------------");
            System.out.println("    a  add a word");
            System.out.println("    r  remove a word");
            System.out.println("    c  change a word");
            System.out.println("    0  exit");
            System.out.println("------------------------------");
            System.out.print("Enter your choice: ");
            String input = inputRead.nextLine();
            switch (input.toLowerCase()){
                case "a":
                    System.out.println("Enter a word and press enter, or just press enter to exit");
                    String word = inputRead.nextLine();
                    if (vocab_list.alreadyExists(word, num)){
                        System.out.println("Word \'"+word+"\' is already listed");
                        continue;
                    }
                    vocab_list.addWordtoTopic(word, num);
                    break;
                case "r":
                    System.out.print("Enter the word you would like to remove:");
                    word = inputRead.nextLine();
                    if (!vocab_list.alreadyExists(word, num)){
                        System.out.println("Word \'"+word+"\' was not found");
                        continue;
                    } 
                    vocab_list.removeWordfromTopic(word, num); break;
                case "c":
                    System.out.print("Enter the old word you would like to replace: ");
                    String oldWord = inputRead.nextLine();
                    System.out.print("Enter the new word: ");
                    String neWord = inputRead.nextLine();
                    vocab_list.replaceWord(oldWord, neWord, num);
                    continue;
                case "0":
                    return;
                default:
                    System.out.println("Please try again");
            }
        }

    }
    /**
     * The method to execute when the user selects option 6
     */
    private static void option6(){
        String input = "";
        do{
            System.out.print("Enter the word you would like to search for (enter to exit)");
            input = inputRead.nextLine();
            boolean match=false;
            for (int i = 1; i<=vocab_list.size(); i++){
                if (vocab_list.alreadyExists(input, i)){
                    System.out.println("Word "+input+" found in topic: "+vocab_list.getTopic(i));
                    match=true;
                }
            }
            if (!match) System.out.println("There are no matches");
        } while (!input.equals(""));

    }
    /**
     * The method to execute when the user selects option 8
     */
    private static void option8(){
        while (true){
            String input;
            do{
            System.out.print("Enter a letter to search for in the topics (enter to exit): ");
            input = inputRead.nextLine();
            if (input.equals("")) break;
            } while (input.length()!=1);
            if (input.equals("")) break;
            ArrayList<String> matches = new ArrayList<>(20);
            String[] rawMatches = vocab_list.getLetterMatches(input).split(",");

            for (String word : rawMatches) {
                matches.add(word);
            }
            Collections.sort(matches);

            for (String word : matches) {
                System.out.println(word);
            } System.out.println();
        }
    }

    /**
     * The method to execute when the user selects option 7
     */
    private static void option7(){
        System.out.println("Enter the name of the input file: ");
        try {
            initializeScanner(inputRead.nextLine());
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found");
            return;
        }
        generateVocabList();
        System.out.println("Done loading");
    }
    /**
     * Method to print the main menu to the user
     */
    private static void displayMenu(){
        System.out.println("------------------------------");
        System.out.println("    Vocabulary Control Center   ");
        System.out.println("------------------------------");
        System.out.println("    1 browse topic");
        System.out.println("    2 insert a new topic before another one");
        System.out.println("    3 insert a new topic after another one");
        System.out.println("    4 remove a topic");
        System.out.println("    5 modify a topic");
        System.out.println("    6 search topics for a word");
        System.out.println("    7 load from a file");
        System.out.println("    8 show all words starting with a given letter");
        System.out.println("    9 save to file");
        System.out.println("    0 exit");
        System.out.print("Enter your choice: ");
    }
    /**
     * Method to initialize a text file scanner from a file name.
     * @param filename The name of the file to be read (including .txt)
     * @throws FileNotFoundException If the file isn't found
     */
    private static void initializeScanner(String filename) throws FileNotFoundException{
        scan = new Scanner(new FileInputStream(filename));
    }
    /**
     * Method to generate the vocab list using an already initialized {@code Scanner} scan.
     */
    private static void generateVocabList(){
        vocab_list = new DoublyLinkedList();
        String currentTopic = null;
        String currentWord = null;
        while (scan.hasNextLine()){
            String line = scan.nextLine();

            if (line.equals("")) continue;

            if (line.charAt(0) == '#'){
                currentTopic = line.substring(1);
                vocab_list.addAtHead(currentTopic);
                continue;
            }
            
            currentWord = line;
            vocab_list.addWordtoTopic(currentWord, currentTopic);
            continue;
            
        }
    }
    /**
     * The method to execute when the user selects option 9
     */
    private static void option9(){
        System.out.print("Enter the name of the new file (.txt will be added): ");
        String input = inputRead.nextLine();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(input+".txt");
        } catch (Exception e){}

        writer.print(vocab_list);
        writer.close();
        System.out.println("File successfully writen");
    }

}
