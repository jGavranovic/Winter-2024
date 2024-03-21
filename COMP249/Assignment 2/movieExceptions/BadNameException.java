package movieExceptions;

public class BadNameException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadNameException(String movieRecord, String fileName, int line){
        super("Semantic error: missing actor name ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadNameException(){
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
