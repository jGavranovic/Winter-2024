package movieExceptions;

public class BadDurationException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadDurationException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid duration ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadDurationException(){
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
