package clients;
import items.*;

public class Client {
    private String clientId;
    private String name;
    private String email;
    private String phoneNumber;
    private Item[] loans = {};
    private static int globalClientIdCount=0;

    
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getLoansLength() {
        return loans.length;
    }
    public Client(){

    }

    public Client(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.clientId = Integer.toString(++globalClientIdCount);
    }

    public Client(Client client){
        this(client.name, client.email, client.phoneNumber);
    }

    @Override
    public String toString(){
        String output="";
        output+="Id: "+clientId;
        output+="\nName: "+name;
        output+="\nEmail: "+email;
        output+="\nPhone number: "+phoneNumber;
        output+="\n\n"+this.loansToString();
        return output;
    }

    public String loansToString(){
        String output = "----Loans----\n";
        if (loans.length==0)
            output += "Client has no loans";
        else{
            for (Item item : loans) {
                output += item+"\n\n";
            }
        }

        return output;
    }

    @Override
    public boolean equals(Object object){
        if (object==null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        Client client = (Client)object;
        //Doesn't check id or loans
        return this.name.equals(client.name) && this.email.equals(client.email) && this.phoneNumber.equals(client.phoneNumber);
    }

    public void addItem(Item item){
        if(item.getLoaned() == true){
            System.out.println("The item is already loaned.");
            return;
        }

        Item[] newLoans = new Item[loans.length+1];
        for(int i=0;i<loans.length;i++){
            newLoans[i] = loans[i];
        }
        newLoans[newLoans.length-1] = item;
        item.setLoaned(true);
        loans = newLoans;
    }

    public void removeItem(Item item){
        // if(!this.checkLoaned(item)){
        //     System.out.println("This client is not loaning this item\n");
        //     return;
        // }

        Item[] newLoans = new Item[loans.length-1];
        int i;
        for (i=0;i<loans.length;i++){
            if(loans[i]==item)
                break;
            newLoans[i] = loans[i];
        }
        for (;i<newLoans.length;i++){
            newLoans[i] = loans[i+1];
        }
        loans = newLoans;
        item.setLoaned(false);
    }


    private boolean checkLoaned(Item item){
        for (Item loan : loans) {
            if (loan == item)
                return true;
        }
        return false;
    }
}
