package movieExceptions;

public class BadRatingException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadRatingException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid rating ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadRatingException(){
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
