/*
 * This is a Book class which is a subclass of the abstract Item class
 * It adds the page number attribute to the object, as well as a custom toString and equals method
 */
package items;

public class Book extends Item {
    private int pageNumber;
    private static int globalBookCount=0;

    public Book(){
        super();
    }

    public Book(String itemName, String author, int yearPublished, int pageNumber){
        super(itemName, author, yearPublished);
        this.pageNumber = pageNumber;
        this.itemId = "B"+ ++globalBookCount;
    }

    public Book(Book book){
        this(book.itemName, book.author, book.yearPublished, book.pageNumber);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static int getGlobalBookCount() {
        return globalBookCount;
    }

    public static void setGlobalBookCount(int globalBookCount) {
        Book.globalBookCount = globalBookCount;
    }

    @Override
    public String toString(){
        String output = super.toString();

        output+="\nPage number: " + this.pageNumber;

        return output;
    }

    @Override
    public boolean equals(Object object){

        if(object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;
        
        Book book = (Book)object;

        return super.equals(object) && this.pageNumber==book.pageNumber;
    }

 

}

