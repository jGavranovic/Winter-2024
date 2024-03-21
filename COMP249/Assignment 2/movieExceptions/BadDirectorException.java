package movieExceptions;

public class BadDirectorException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadDirectorException(String movieRecord, String fileName, int line){
        super("Semantic error: missing director ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadDirectorException(){
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
