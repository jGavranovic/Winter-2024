import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Part1 {
    Scanner read = null;
 
        try {
            read = new Scanner(new FileInputStream("COMP249/Movies/part1_manifest.txt"));
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found, ending program");
            System.exit(0);
        }
    
        String[] fileNames = new String[10];

        for (int i=0; i<10; i++){
            fileNames[i] = readManifest.nextLine();
        }

}
