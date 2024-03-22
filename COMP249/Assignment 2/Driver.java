import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import movieExceptions.*;

public class Driver {
    private static int manifestPointer = 0;
    private static String[] fileNames = new String[10];
    private static Scanner readMovies = null;
    private static int currentLine = 0;
    private static String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama","crime","biography","horror","action","documentary","fantasy","mystery","sci-fi","family","romance","thriller","western"};

    public static void main(String[] args){
        
        //part 1's manifest file
        String part1_manifest = "part1_manifest.txt"; 

        //part 2's manifest file
        String part2_manifest = do_part1(part1_manifest/*... */ ); //partition

        //part 3's manifest file
        String part3_manifest = do_part2(part2_manifest );

        //do_part3();

        //String line = "1990,Total Recall,113,Action,R,7.5,Paul Verhoeven,Ronny Cox,Rachel Ticotin,Marshall Bell,actor 4?";
        // System.out.println(parseEntry(line));
        // try {
        //     System.out.println(parseEntry("2004,\"I, Robot ,115,Action,PG-13,7.1,Alex Proyas,Will Smith,Bruce Greenwood,Chi McBride"));
        // } catch (SyntaxException e){ System.out.println(e);}
        // catch (BadDirectorException e){}
        // catch (BadDurationException e){}
        // catch (BadGenreException e){}
        // catch (BadNameException e){}
        // catch (BadRatingException e){}
        // catch (BadScoreException e){}
        // catch (BadTitleException e){}
        // catch (BadYearException e){}
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
        manifestPointer = 0;
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
                for (Movie movie : movies){
                    objectWriters[i].writeObject(movie);
                    // System.out.println("PRINTING: "+movie);
                }
                objectWriters[i].close();
            } catch (IOException ioe){System.out.println("IOEXCEPTION IN WRITING");}
            genreScanners[i].close();

        }






        String part3_manifest = "BinaryFiles/part3_manifest";

        return part3_manifest;
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
        System.out.println("Filenames: ");
        for (String string : fileNames) {
            System.out.println(string);
        }
    }

    private static void generateReadMovies(){
        System.out.println("readMovies currently pointing to: "+fileNames[manifestPointer]);
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
}
    