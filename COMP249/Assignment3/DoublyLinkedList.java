/**
 * A double linked list class to be used for the vocab list
 * @author Jovan Gavranovic (40292175)
 */
public class DoublyLinkedList {
    private Node head;
    /**
     * Contsuctor which initializes the head to null
     */
    public DoublyLinkedList(){
        head = null;
    }
    /**
     * 
     * @param topic Adds a new Node a the head of the Dlist with this topic
     */
    public void addAtHead(String topic){
        if (head != null){
            head = new Node(topic, head, null);
            head.next.previous = head;
        } else {
            head = new Node(topic, head, null);
        }

        System.out.println("Topic added");

    }
    /**
     * 
     * @param newTopic The new topic to be added
     * @param place The position which the new node will be created before
     */
    public void addTopicBefore(String newTopic, int place){
        if (head == null || place == 1){
            addAtHead(newTopic);
            System.out.println("Topic created");
            return;
        }
        Node position = head;
        for (int counter=1; counter<place-1;counter++)
            position=position.next;
        position.next = new Node(newTopic, position.next, position);
        if (position.next.next == null) return;
        position.next.next.previous = position.next;

        System.out.println("Topic created");
    }
/**
 * 
 * @param newTopic The new topic to be added
 * @param place The position which the new node will be created after
 */
    public void addTopicAfter(String newTopic, int place) {
        if (head == null){
            addAtHead(newTopic);
            System.out.println("Topic created");
            return;
        }
        if (place>size()||place<1){
            System.out.println("Invalid number");
            return;
        }
        if (place == size()){
            addAtTail(newTopic);
            System.out.println("Topic created");
            return;
        }
        Node position = head;
        for (int counter=1; counter<place;counter++)
            position = position.next;
        position.next = new Node(newTopic, position.next, position);
        position.next.next.previous = position.next;
        System.out.println("Topic created");
    }
/**
 * Function to check whether a given word already exists in a given topic
 * @param word The word who's existence is to be checked
 * @param place The position of the node/topic to be checked
 * @return  Whether there already is that word in a topic
 */
    public boolean alreadyExists(String word, int place){
        Node position = head;
        for (int counter = 1; counter<place;counter++)
            position = position.next;

        return position.vocab.alreadyExists(word);
    }
    /**
     * 
     * @param topic Adds a new Node at the list tail with this topic
     */
    public void addAtTail(String topic){
        if (head == null){
            addAtHead(topic);
            return;
        }
        Node position = head;
        while (position.next != null)
            position = position.next;
        
        position.next = new Node(topic, null, position);
    }
/**
 * Replaces a word with another inside a given topic
 * @param oldWord   The old word to be replaced
 * @param newWord   The new word to be replaced with
 * @param place The position of the topic which is of interest
 */
    public void replaceWord(String oldWord, String newWord, int place){
        Node position = head;
        for (int counter = 1; counter<place;counter++)
            position = position.next;
        position.vocab.replaceWord(oldWord, newWord);
    }
/**
 * Prints the entire Dlist topic list
 */
    public void displayList(){
        Node position = head;
        int counter = 1;
        while (position != null){
            System.out.println("   "+(counter++)+"  "+position.vocab.getTopic());
            position = position.next;
        }
    }
/**
 * Returns a string of every word which starts with a given letter in every topic, seperated by commas
 * @param letter the letter to be searched for
 * @return The string of matches sepereated by commas
 */
    public String getLetterMatches(String letter){
        String output = "";
        Node position = head;
        while (position!= null){
            output+=position.vocab.getLetterMatches(letter);
            position = position.next;
        }
        return output;
    }
/**
 * 
 * @param place The position of the node of interest
 * @return The topic of the node at that position
 */
    public String getTopic(int place){
        if (head == null || place<1 || place>size()) return "";
        Node position = head;
        for (int counter =1;counter<place;counter++){
            position=position.next;
        }
        return position.vocab.getTopic();
    }
    /**
     * 
     * @return The size of the Dlist
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
 * Adds a word to a given topic
 * @param word The word to be added
 * @param place The position of the topic to which the word should be added
 */
    public void addWordtoTopic(String word, int place){
        if (head == null || place>size() || place<1) return;

        Node position = head;
        for (int counter = 1; counter<place; counter++)
            position = position.next;
        position.vocab.addWord(word);
    }
/**
 * Adds a word to a given topic
 * @param word The word to be added
 * @param topic The topic to which the word should be added
 */
    public void addWordtoTopic(String word, String topic){
        if (head == null) return;
        if (head.vocab.getTopic().equalsIgnoreCase(topic)){
            head.vocab.addWord(word);
            return;
        }
        
        Node position = head;
        while (position!=null && !position.vocab.getTopic().equalsIgnoreCase(topic))
            position = position.next;
        if (position.vocab.getTopic().equalsIgnoreCase(topic))
            position.vocab.addWord(word);
    }
/**
 * @return The the list of every every word contained in the Dlist
 */
    public String toString(){
        String output = "";

        Node position = head;
        while (position != null){
            output+=position.vocab.toString();
            position = position.next;
        }

        return output;
    }
/**
 * Removes a word from a given topic
 * @param word The word to be removed   
 * @param place The position of the topic in which the word should be removed
 */
    public void removeWordfromTopic(String word, int place){
        Node position = head;
        for (int counter=1;counter<place;counter++) 
            position = position.next;
        position.vocab.removeWord(word);
        System.out.println(word+" was removed from the list");
    }
/**
 * Prints the topic and contained words of a given node
 * @param place The position of the topic to be printed
 */
    public void displayOne(int place){
        if (place>size()){
            System.out.println("Invalid input");
            return;
        }
        Node position = head;

        for (int i=1;i<place;i++){
            position = position.next;
        }

        System.out.println("Topic: "+position.vocab.getTopic());
        position.vocab.getWordList().displayList();
        System.out.println("\n");
    }
/**
 * Removes a topic from the dlist
 * @param place The position of the topic to be removed from the Dlist
 */
    public void removeTopic(int place){
        if (head == null|| place>size()||place<1){
            System.out.println("Invalid number");
            return;
        }

        if (place == 1){
            head = head.next;
            System.out.println("Topic removed");
            return;
        }
        
        Node position = head;
        for (int counter=1; counter<place-1;counter++)
            position=position.next;
        position.next = position.next.next;

        System.out.println("Topic removed");
        
    }

/**
 * A private Node class to be used by the Dlist class
 * Contains attributes for vocab, next, and previous
 * @author Jovan Gavranovic
 */
    private class Node {
        private Vocab vocab;
        private Node next;
        private Node previous;

/**
 * Parametrized constructor for a Node
 * @param topic The topic of the node
 * @param next  Reference to the next node in the list
 * @param previous Reference to the previous node in the list
 */
        private Node(String topic, Node next, Node previous){
            this.vocab = new Vocab(topic);
            this.next = next;
            this.previous = previous;
        }
    }


    
}
