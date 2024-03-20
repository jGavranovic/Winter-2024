import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Driver {
    private static int manifestPointer = 0;
    private static String[] fileNames = new String[10];
    private static Scanner readMovies = null;
    public static void main(String[] args){
        
        //part 1's manifest file
        String part1_manifest = "part1_manifest.txt"; 

        //part 2's manifest file
        //String part2_manifest = do_part1(part1_manifest/*... */ ); //partition

        //part 3's manifest file
        //String part3_manifest = do_part2(part2_manifest );

        //do_part3();

        // String line = "1991,\"Freddy's Dead, The, Final Nightmare\",93,Comedy,R,4.9,Rachel Talalay,Johnny Depp,Tom Arnold,Yaphet Kotto";
        // System.out.println(parseEntry(line));
        do_part1(part1_manifest);

        return;
    }

    private static String do_part1(String part1_manifest){
        generateFileNames();
        for (int i=0; i<10; i++){
            generateReadMovies();
            while (readMovies.hasNextLine()){
                String line = readMovies.nextLine();
                System.out.println(parseEntry(line)+"\n");
            }
        }




        return "part2_manifest";  
    }

    private static void generateFileNames(){
        Scanner readManifest = null;
 
        try {
            readManifest = new Scanner(new FileInputStream("COMP249/Assignment 2/Movies/part1_manifest.txt"));
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found, ending program");
            System.exit(0);
        }

        for (int i=0; i<10; i++){
            fileNames[i] = readManifest.nextLine();
        }

        readManifest.close();
    }

    private static void generateReadMovies(){
        try {
            readMovies = new Scanner(new FileInputStream("COMP249/Assignment 2/Movies/"+fileNames[manifestPointer++]));
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found, exiting");
            System.exit(0);
        }
    }

    private static Movie parseEntry(String entry){
        String[] splitStringComma = entry.split(",");
        // for (String string : splitStringComma) {
        //     System.out.println(string);
        // }
        int quoteCounter = 0;
        String quotedEntry = "";
        int index = 0;
        String[] movieAttributes = new String[10];

        for (int i=0; i<splitStringComma.length; i++){
            //Stop when movieAttributes is filled
            if (index == 10)
                break;
            //If no quotes
            if (splitStringComma[i].indexOf("\"")==-1 && quoteCounter == 0){
                movieAttributes[index++] = splitStringComma[i];
            //If two quotes
            } else if (splitStringComma[i].substring(splitStringComma[i].indexOf("\"")+1).indexOf("\"")!=-1){
                movieAttributes[index++] = splitStringComma[i].substring(1,splitStringComma[i].length()-1);
            //If starting quote only
            } else if (splitStringComma[i].indexOf("\"")==0){
                quotedEntry = splitStringComma[i].substring(1)+",";
                quoteCounter++;
            }
            //If end quote only
            else if (splitStringComma[i].indexOf("\"")==splitStringComma[i].length()-1){
                quotedEntry += splitStringComma[i].substring(0,splitStringComma[i].length()-1);
                quoteCounter = 0;
                movieAttributes[index++] = quotedEntry;
            }
            //If middle section in quotes between two commas
            else {
                quoteCounter++;
                quotedEntry += splitStringComma[i]+",";
            }
        }
        return new Movie(movieAttributes);
    }
}
