package movieExceptions;

public class MovieSyntaxException extends Exception {
    private String movieRecord, fileName;
    private int lineNumber;

    public MovieSyntaxException(String movieRecord, String fileName, int lineNumber){
        super("Syntax error in movie record");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString(){
        String output=this.getMessage();
        output+="\nIn file: "+fileName;
        output+="\nAt line number: "+lineNumber;

        return output;
    }
}