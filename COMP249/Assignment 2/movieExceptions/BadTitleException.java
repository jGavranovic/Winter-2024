package movieExceptions;

public class BadTitleException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadTitleException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid title ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadTitleException(){
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
