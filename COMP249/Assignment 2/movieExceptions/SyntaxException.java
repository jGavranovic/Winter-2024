package movieExceptions;

public abstract class SyntaxException extends Exception{
    String movieRecord, fileName;
    int lineNumber;

    public SyntaxException(String movieRecord, String fileName, int lineNumber){
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }
    public SyntaxException(String message){
        super(message);
    }
}
