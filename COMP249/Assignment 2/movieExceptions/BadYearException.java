package movieExceptions;

public class BadYearException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadYearException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid year ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }

    public BadYearException(){
        super();
    }
    public String toString(){
        String output= this.getMessage();
        output+="\n"+movieRecord;
        output+="\nFile: "+fileName;
        output+="\nLine: "+line+"\n";

        return output;
    }
}   
