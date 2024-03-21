package movieExceptions;

public class BadGenreException extends Exception{
    private String movieRecord;
    private String fileName;
    private int line;

    public BadGenreException(String movieRecord, String fileName, int line){
        super("Semantic error: invalid genre ");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.line = line;
    }
    public BadGenreException(){
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
