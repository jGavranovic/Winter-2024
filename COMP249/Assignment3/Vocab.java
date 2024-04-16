/**
 * A Vocab class which is to act as the data portion of each Dlist node
 * contains two inner attributes: the topic of the list and the actual word list
 * @author Jovan Gavranovic
 */
public class Vocab {
    private String topic;
    private SinglyLinkedList wordList;
/**
 * Constructor for a new vocab object which sets the topic name but the  list is still empty
 * @param topic The topic of the new vocab object
 */
    public Vocab(String topic){
        this.topic = topic;
        this.wordList = new SinglyLinkedList();
    }
/**
 * 
 * @return Returns the topic of the vocab object
 */
    public String getTopic(){
        return topic;
    }
/**
 * 
 * @return Returns a reference to the word list in the given vocab object 
 */
    public SinglyLinkedList getWordList(){
        return wordList;
    }
/**
 * @return The topic and and words contained in the vocab object formatted for eventually writing to a file
 */
    public String toString(){
        String output = "#"+topic+"\n";
        output+=wordList.toString();

        return output;
    }
    /**
     * Replaces a word in the word list with a new word
     * @param oldWord The old word to be replaced
     * @param newWord The new word to be replaced with
     */
    public void replaceWord(String oldWord, String newWord){
        wordList.replaceWord(oldWord, newWord);
    }
    /**
     * Checks if a word already is contained in the word list
     * @param word The word to be checked
     * @return Boolean of whther the given word was found
     */
    public boolean alreadyExists(String word){
        return wordList.alreadyExists(word);
    }
    /**
     * Adds a given word to the word list
     * @param word The word to be added to the list
     */
    public void addWord(String word){
        wordList.insert(word);
    }
    /**
     * 
     * @param letter The first letter to be searched for
     * @return A string of every word match in the vocab object, sepreated by spaces
     */
    public String getLetterMatches(String letter){
        return wordList.getLetterMatches(letter);
    }
    /**
     * Removes a given word from the word list
     * @param word The word to be removed from the wordlist
     */
    public void removeWord(String word){
        this.wordList.removeWord(word);
    }
    
}
