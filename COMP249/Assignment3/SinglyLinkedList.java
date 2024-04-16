/**
 * Single linked list class for strings to be used as the list of words
 */
public class SinglyLinkedList{

    private Node head;
/**
 * The default constructor which initializes the head to null
 */
    public SinglyLinkedList(){
        head = null;
    }
/**
 * Adds a new {@code Node} with the {@code word} parameter at the head of the list
 * @param word  the word to be added to the list head
 */
    public void addAtHead(String word){
        head = new Node(word, head);
    }
/**
 * Adds a new {@code Node} with the {@code word} parameter at the tail of the list
 * @param word  the word to be added to the list tail
 */
    public void addAtTail(String word){
        if (head == null){
            addAtHead(word);
            return;
        }
        Node position = head;
        while (position.next != null)
            position = position.next;
        
        position.next = new Node(word, null);
    }
/**
 * toString method to be used in the writing back to text file
 * formatted as one word per line
 */
    public String toString(){
        String output = "";
        Node position = head;
        while (position != null){
            output+=position.word+"\n";
            position = position.next;
        }
        output+="\n";
        return output;
    }
/**
 * Prints the word list in the format of one word per line
 */
    public void displayList(){
        if (head == null) return;

        int counter = 0;
        Node position = head;
        while (position != null){
            System.out.print(String.format("%-25s",(++counter)+"  "+position.word+"   ")+(counter%4==0?"\n":""));
            position = position.next;
        }
    }
/**
 * 
 * @param letter The letter to be checked for
 * @return A string of every word which starts with the letter parameter, seperated by a comma
 */
    public String getLetterMatches(String letter){
        String output = "";
        Node position = head;
        while (position!= null){
            if (position.word.substring(0, 1).equalsIgnoreCase(letter)){
                output+=position.word+",";
            }
            position = position.next;
        }
        return output;

    }
/**
 * 
 * @return The size of the linked list
 */
    public int size(){
        int size = 0;

        Node position = head;
        while (position != null){
            position=position.next;
            size++;
        }

        return size;
    }
/**
 * 
 * @param word The word to be checked for
 * @return Whether there is a match for the given word in a linked list
 */
    public boolean alreadyExists(String word){
        Node position = head;

        while (position!=null){
            if (position.word.equalsIgnoreCase(word)) return true;
            position = position.next;
        }
        return false;
    }
    /**
     * Inserts a new word into the linked list in alphabetical order
     * @param word The word to be added
     */
    public void insert(String word){
        Node position = head;
        if (position == null || word.compareToIgnoreCase(position.word)<=0){
            addAtHead(word);return;
        }
        while (position.next != null && word.compareToIgnoreCase(position.next.word)>0)
            position = position.next;
        if (position.next == null){
            addAtTail(word);return;
        }
        position.next = new Node(word,position.next);

    }

    /**
 * Removes the first match with the given parameter wrod
 * @param word The word you want to remove from the list, not case sensitive
 */
    public void removeWord(String word){
        if (head == null) return;
        if (head.word.equalsIgnoreCase(word)){
            head = head.next;
            return;
        }
        Node position = head;
        while (!position.next.word.equalsIgnoreCase(word))
            position = position.next;
        if (position.next.next == null){
            position.next = null; return;
        }
        position.next = position.next.next;
    }
/**
 * Replaces a word in the list with a new word
 * @param oldWord The old word to replace
 * @param newWord The new word to replace with
 */
    public void replaceWord(String oldWord, String newWord){
        removeWord(oldWord);
        insert(newWord);
        // if (alreadyExists(newWord)) {
        //     System.out.println("Word already exists");
        //     return;
        // }
        // if (head == null) return;
        // if (head.word.equalsIgnoreCase(oldWord)){
        //     head.word = newWord;
        //     return;
        // }
        // Node position = head;
        // while (position!= null && !position.word.equalsIgnoreCase(oldWord))
        //     position = position.next;
        // if (position == null){
        //     System.out.println("Word not found");
        //     return;
        // }

        // position.word = newWord;
        // return;
        
        
        
    }
/**
 * This is private inner Node class for the singly linked list which has attributes next and word
 * @author Jovan Gavranovic
 */
    private class Node {
        private String word;
        private Node next;
/**
 * 
 * @param word The word to be stored in the node
 * @param next The reference to the next node in the linked list
 */
        private Node(String word, Node next){
            this.word = word;
            this.next = next;
        }
    }
}