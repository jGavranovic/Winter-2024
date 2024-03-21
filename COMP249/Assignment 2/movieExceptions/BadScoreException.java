package movieExceptions;

public class BadScoreException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadScoreException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid score ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadScoreException(){
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
