/*
 * This is an abstract Item class which is will be extended for Book, Journal, and Media
 * It defines the itemId, itemName, author, and year published which are used as well as a custom toString and equals method
 */
package items;

public abstract class Item {
    protected String itemId;
    protected String itemName;
    protected String author;
    protected int yearPublished;
    protected static int globalItemIdCount=0;
    protected boolean loaned = false;

    protected Item(){

    }

    protected Item(String itemName, String author, int yearPublished){
        this.itemName = itemName;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    protected Item(Item item){
        this.itemId = item.itemId;
        this.itemName = item.itemName;
        this.yearPublished = item.yearPublished;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }
    public boolean getLoaned(){
        return loaned;
    }
    public void setLoaned(boolean bool){
        loaned = bool;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public static int getGlobalItemIdCount() {
        return globalItemIdCount;
    }

    public static void setGlobalItemIdCount(int globalItemIdCount) {
        Item.globalItemIdCount = globalItemIdCount;
    }

    @Override
    public boolean equals(Object object){
        if (object == null)
            return false;
        
        if (this.getClass() != object.getClass())
            return false;
        
        Item item = (Item)object;

        return this.itemName.equals(item.itemName) && this.author.equals(item.author) && this.yearPublished==item.yearPublished;
        
    }
    
    @Override
    public String toString(){
        String output = "";

        output+="Id: " + this.itemId;
        output+="\nOn loan: "+(loaned?"Yes":"No");
        output+="\nName: " + this.itemName;
        output+="\nAuthor: " + this.author;
        output+="\nYear published: " + this.yearPublished;

        return output;

    }
}
