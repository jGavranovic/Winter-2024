package movieExceptions;

public class MissingFieldsException extends SyntaxException {

    public MissingFieldsException(String movieRecord, String fileName, int lineNumber){
        super("Syntax error: Missing field in the movie record");
        this.movieRecord = movieRecord;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString(){
        String output=this.getMessage();
        output+="\n"+this.movieRecord;
        output+="\nIn file: "+fileName;
        output+="\nAt line number: "+lineNumber+"\n";

        return output;
    }
}