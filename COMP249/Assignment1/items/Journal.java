package items;

public class Journal extends Item {
    private int volNumber;
    private static int globalJournalCount=0;

    public Journal(){
        super();
    }

    public Journal(String itemName, String author, int yearPublished, int volNumber){
        super(itemName, author, yearPublished);
        this.volNumber = volNumber;
        this.itemId = "J"+ ++globalJournalCount;
    }

    public Journal(Journal journal){
        this(journal.itemName, journal.author, journal.yearPublished, journal.volNumber);
    }

    public int getVolNumber() {
        return volNumber;
    }

    public void setVolNumber(int volNumber) {
        this.volNumber = volNumber;
    }

    public static int getGlobalJournalCount() {
        return globalJournalCount;
    }

    public static void setGlobalJournalCount(int globalJournalCount) {
        Journal.globalJournalCount = globalJournalCount;
    }

    @Override
    public String toString(){
        String output = super.toString();

        output+="\nVolume number: " + this.volNumber;

        return output;
    }

    @Override
    public boolean equals(Object object){

        if(object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;
        
        Journal journal = (Journal)object;

        return super.equals(object) && this.volNumber==journal.volNumber;
    }

 

}

