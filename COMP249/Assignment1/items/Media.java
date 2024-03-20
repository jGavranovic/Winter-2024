/*
 * This is a Media class which is a subclass of the abstract Item class
 * It adds the type attribute to the object, as well as a custom toString and equals method
 */
package items;

public class Media extends Item {
    private String type;
    private static int globalMediaCount=0;

    public Media(){
        super();
    }

    public Media(String itemName, String author, int yearPublished, String type){
        super(itemName, author, yearPublished);
        this.type = type;
        this.itemId = "M"+ ++globalMediaCount;
    }

    public Media(Media media){
        this(media.itemName, media.author, media.yearPublished, media.type);
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getGlobalMediaCount() {
        return globalMediaCount;
    }

    public static void setGlobalMediaCount(int globalMediaCount) {
        Media.globalMediaCount = globalMediaCount;
    }
    
    @Override
    public String toString(){
        String output = super.toString();

        output+="\nType: " + this.type;

        return output;
    }

    @Override
    public boolean equals(Object object){

        if(object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;
        
        Media media = (Media)object;

        return super.equals(object) && this.type.equals(media.type);
    }

 

}
