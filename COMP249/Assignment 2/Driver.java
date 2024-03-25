// -------------------------------------------------
// Assignment 2
// Question 1
// Written by Jovan Gavranovic (40282175)
// -------------------------------------------------
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import movieExceptions.*;

public class Driver {
    private static int manifestPointer = 0;
    private static String[] fileNames = new String[10];
    private static Scanner readMovies = null;
    private static int currentLine = 0;
    private static String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama","crime","biography","horror","action","documentary","fantasy","mystery","sci-fi","family","romance","thriller","western"};
    private static Movie[][] all_movies = null;
    public static void main(String[] args){
        
        //part 1's manifest file
        String part1_manifest = "part1_manifest.txt"; 

        //part 2's manifest file
        String part2_manifest = do_part1(part1_manifest/*... */ ); //partition

        //part 3's manifest file
        String part3_manifest = do_part2(part2_manifest );

        do_part3(part3_manifest);

        System.out.println("-----------------------------");
        System.out.println("Welcome to the movie database");
        System.out.println("-----by: Jovan Gavranovic----");

        navigate();
        return;
    }

    private static String do_part1(String part1_manifest) {
        generateFileNames();
        PrintWriter errorWriter = null;
        PrintWriter[] genreWriters = new PrintWriter[17];
        for (int i=0; i<genreWriters.length;i++){
            try {
                genreWriters[i] = new PrintWriter(new FileOutputStream("Genres/"+validGenres[i]+".csv"));
            } catch (FileNotFoundException fnfe) {System.out.println("There was an error");}
        }
        try {
            errorWriter = new PrintWriter(new FileOutputStream("Genres/bad_movie_records.txt"));
        } catch (FileNotFoundException fnfe){System.out.println("There was an error");}
        for (int i=0; i<10; i++){
            currentLine = 0;
            generateReadMovies();
            while (readMovies.hasNextLine()){
                currentLine++;
                Movie movie = null;
                String line = readMovies.nextLine();
                try {
                    movie = parseEntry(line);
                    for (int j=0;j<validGenres.length;j++){
                        if (validGenres[j].equalsIgnoreCase(movie.getGenre())){
                            genreWriters[j].println(line);
                            break;
                        }
                     }
                    //System.out.println(movie);
                } catch (SyntaxException se) { errorWriter.println(se);}
                 catch (BadDirectorException bde){ errorWriter.println(new BadDirectorException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadYearException bde){ errorWriter.println(new BadYearException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadTitleException bde){ errorWriter.println(new BadTitleException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadGenreException bde){ errorWriter.println(new BadGenreException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadScoreException bde){ errorWriter.println(new BadScoreException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadDurationException bde){ errorWriter.println(new BadDurationException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadRatingException bde){ errorWriter.println(new BadRatingException(line, fileNames[manifestPointer-1],currentLine));}
                 catch (BadNameException bde){ errorWriter.println(new BadNameException(line, fileNames[manifestPointer-1],currentLine));}

                 
            }
        }
        
        errorWriter.close();
        for (PrintWriter printWriter : genreWriters) {
            printWriter.flush();
            printWriter.close();
        }
        
        PrintWriter manifestWriter = null;
        String part2_manifest = "Genres/part2_manifest.txt";
        try{
            manifestWriter = new PrintWriter(new FileOutputStream(part2_manifest));
        } catch (FileNotFoundException fnfe) {System.out.println("There was an error");}
        for (String genre : validGenres) {
            manifestWriter.println(genre+".csv");
        }
        manifestWriter.close();
        return part2_manifest;  
    }

    private static String do_part2(String part2_manifest){

        String part3_manifest = "Binary/part3_manifest.txt";
        PrintWriter manifestWriter = null;
        try {manifestWriter = new PrintWriter(new FileOutputStream(part3_manifest));}
        catch (Exception e){ System.out.println("There was an error");}

        for (String genre : validGenres) {
            manifestWriter.println(genre+".ser");
        }
        manifestWriter.close();

        Scanner[] genreScanners = new Scanner[validGenres.length];

        for (int i=0;i<genreScanners.length;i++){
            try {
                genreScanners[i] = new Scanner(new FileInputStream("Genres/"+validGenres[i]+".csv"));

            } catch (FileNotFoundException fnfe){System.out.println("Part2: file not found");}
        }

        ObjectOutputStream[] objectWriters = new ObjectOutputStream[validGenres.length];

        for (int i=0;i<objectWriters.length;i++){
            try{
                objectWriters[i] = new ObjectOutputStream(new FileOutputStream("Binary/"+validGenres[i]+".ser"));
            } catch (IOException ioe){ System.out.println("IOEXCEPTION Error");}
        }

        for (int i=0; i<genreScanners.length;i++){
            Movie [] movies = new Movie[0];
            while (genreScanners[i].hasNextLine()){
                Movie movie = null;
                try {
                    movie = parseEntry(genreScanners[i].nextLine());
                    // System.out.println("ADDING TO ARRAY:"+movie);
                } catch (Exception e){}//All entries at this point are valid
                movies = addMovie(movies, movie);

            }
            try {
                // for (Movie movie : movies){
                //     objectWriters[i].writeObject(movie);
                //     // System.out.println("PRINTING: "+movie);
                // }
                objectWriters[i].writeObject(movies);
                objectWriters[i].close();
            } catch (IOException ioe){System.out.println("IOEXCEPTION IN WRITING");}
            genreScanners[i].close();

        }






       

        return part3_manifest;
    }

    private static void do_part3(String part3_manifest){
        Scanner manifestReader = null;
        try {
            manifestReader = new Scanner(new FileInputStream(part3_manifest));
        } catch (FileNotFoundException fnfe) {System.out.println("There was an error - exiting.");}
        int genreCount=0;
        while (manifestReader.hasNextLine()){
            manifestReader.nextLine();
            genreCount++;
        } manifestReader.close();
        try {
            manifestReader = new Scanner(new FileInputStream(part3_manifest));
        } catch (FileNotFoundException fnfe) {System.out.println("There was an error - exiting.");}

        ObjectInputStream[] objectReaders = new ObjectInputStream[genreCount];
        for (int i=0;i<objectReaders.length;i++){
            try {
                objectReaders[i] = new ObjectInputStream(new FileInputStream("Binary/"+manifestReader.nextLine()));
            } catch (IOException ioe){System.out.println("IOEXCEPTION while reading files");}
        } manifestReader.close();

        all_movies = new Movie[genreCount][];

        for (int i=0; i<genreCount; i++){
            try {
                all_movies[i] = (Movie[])objectReaders[i].readObject();
                objectReaders[i].close();
            } catch (Exception ioe){System.out.println("Error reading object from binary file");}
        }

        // for (Movie[] movies : all_movies) {
        //     for (Movie movies2 : movies) {
        //         System.out.println(movies2);
        //     }
        // }




    }

    private static void generateFileNames(){
        Scanner readManifest = null;
 
        try {
            readManifest = new Scanner(new FileInputStream("Movies/part1_manifest.txt"));
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found, ending program");
            System.exit(0);
        }

        for (int i=0; i<10; i++){
            fileNames[i] = readManifest.nextLine();
        }

        readManifest.close();
        // System.out.println("Filenames: ");
        // for (String string : fileNames) {
        //     System.out.println(string);
        // }
    }

    private static void generateReadMovies(){
        //System.out.println("readMovies currently pointing to: "+fileNames[manifestPointer]);
        try {
            readMovies = new Scanner(new FileInputStream("Movies/"+fileNames[manifestPointer++]));
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found, exiting");
            System.exit(0);
        }


    }

    private static Movie parseEntry(String entry) throws SyntaxException, BadDirectorException,BadDurationException, BadNameException,BadYearException,BadRatingException,BadScoreException,BadTitleException,BadGenreException {
        String[] splitStringComma = entry.split(",");
        // for (String string : splitStringComma) {
        //     System.out.println(string);
        // }
        int quoteCounter = 0;
        boolean doubleQuoteFlag = false;
        String quotedEntry = "";
        int index = 0;
        String[] movieAttributes = new String[10];
        int i=0;

        for (i=0; i<splitStringComma.length; i++){
            //Stop when movieAttributes is filled
            if (index == 10)
                throw new ExcessFieldsException(entry, fileNames[manifestPointer-1], currentLine);
            //If no quotes
            if (splitStringComma[i].indexOf("\"")==-1 && quoteCounter == 0){
                movieAttributes[index++] = splitStringComma[i];
            //If two quotes
            } else if (splitStringComma[i].substring(splitStringComma[i].indexOf("\"")+1).indexOf("\"")!=-1){
                movieAttributes[index++] = splitStringComma[i].substring(1,splitStringComma[i].length()-1);
            //If starting quote only
            } else if (splitStringComma[i].indexOf("\"")==0){
                quotedEntry = splitStringComma[i].substring(1)+",";
                doubleQuoteFlag = true;
                quoteCounter++;
            }
            //If end quote only
            else if (splitStringComma[i].indexOf("\"")==splitStringComma[i].length()-1){
                quotedEntry += splitStringComma[i].substring(0,splitStringComma[i].length()-1);
                quoteCounter = 0;
                doubleQuoteFlag = false;
                movieAttributes[index++] = quotedEntry;
            }
            //If middle section in quotes between two commas
            else {
                quoteCounter++;
                quotedEntry += splitStringComma[i]+",";
            }
        }
        if (doubleQuoteFlag)
            throw new MissingQuotesException(entry, fileNames[manifestPointer-1], currentLine);
        if (index < 10){
            throw new MissingFieldsException(entry, fileNames[manifestPointer-1], currentLine);
        }
        
        return new Movie(movieAttributes);
    }

    private static Movie[] addMovie(Movie[] movies, Movie movie){

        Movie[] newMovies = new Movie[movies.length+1];
        int i=0;
        for (i=0; i<movies.length;i++){
            newMovies[i] = movies[i];
        }
        newMovies[i] = movie;
        return newMovies;
    }

    private static void navigate(){
        Scanner input = new Scanner(System.in);
        String choice;
        int choiceInt;
        int genrePointer = 0;
        int[] moviePointers = new int[17];
        for (int i=0;i<moviePointers.length;i++)
            moviePointers[i] = 0;
        
        while (true){
            System.out.println("-----------------------------");
            System.out.println("          Main Menu");
            System.out.println("-----------------------------");
            System.out.println("  s Select a movie array to navigate");
            System.out.println("  n Navigate "+validGenres[genrePointer]+" movies ("+all_movies[genrePointer].length+" records)");
            System.out.println("  x Exit");
            System.out.println("-----------------------------\n");
            System.out.print("Enter your choice: ");
            
            choice = input.nextLine().toLowerCase();

            switch (choice){
                case "s":
                    System.out.println("-----------------------------");
                    System.out.println("       Genre Sub-Menu");
                    System.out.println("-----------------------------");
                    int i=0;
                    for(; i<validGenres.length;i++){
                        System.out.printf("%-15s%15s",(i+1)+" "+validGenres[i],"("+all_movies[i].length+" movies)\n");
                    } System.out.println((i+1)+" Exit");

                    System.out.print(" Enter your choice: ");
                    choice = input.nextLine();
                    // if (choice.equals(i+1)) continue;
                    try {
                        choiceInt = Integer.parseInt(choice);
                    } catch (NumberFormatException nfe) {System.out.println("Invlaid input"); continue;}

                    if (choiceInt >0 && choiceInt<i+1) genrePointer = choiceInt-1; break;


                    case "n":
                        while (true) {
                                
                            System.out.println("Navigating "+validGenres[genrePointer]+" movies ("+all_movies[genrePointer].length+")");
                            System.out.print("Enter your choice: ");
                            choiceInt = Integer.parseInt(input.nextLine());

                            if (all_movies[genrePointer].length == 0){
                                break;
                            }
                            if (choiceInt == 0){
                                break;
                            } else if (choiceInt > 0){
                                boolean eof = moviePointers[genrePointer]+choiceInt>all_movies[genrePointer].length;
                                int max = (eof)?all_movies[genrePointer].length-moviePointers[genrePointer]:choiceInt;
                                for (i=0; i<max;i++)
                                    System.out.println(all_movies[genrePointer][moviePointers[genrePointer]+i]);
                                if (eof)
                                    System.out.println("EOF has been reached");
                                moviePointers[genrePointer] += max-1;
                            } else {
                                boolean bof = moviePointers[genrePointer]+choiceInt<-1;
                                int max = (bof)?moviePointers[genrePointer]+1:Math.abs(choiceInt);
                                
                                for (i=0; i<max;i++){
                                    System.out.println(all_movies[genrePointer][moviePointers[genrePointer]-i]);
                                }

                                if(bof)
                                    System.out.println("BOF has been reached");
                                moviePointers[genrePointer] -= max - 1;
                            }
                        }
                        break;
                    case "x":
                        input.close();
                        System.out.println("Exiting program - thank you");
                        System.exit(0);
            }

        }
    }
}
   