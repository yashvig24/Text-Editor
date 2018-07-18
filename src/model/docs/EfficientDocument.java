package model.docs;

import java.util.List;
import java.util.ArrayList;
import java.io.File;


public class EfficientDocument extends Document {

    private int numWords;
    private int numSentences; 
    private int numSyllables;
    private List<String> tokens;

    /** Create a new EfficientDocument object
	 * 
	 * @param text The full text of the Document.
	 */
    public EfficientDocument(String text) {
        super(text);
        processText();
    }

    public EfficientDocument(File file) {
        super(file);
        processText();
    }

    /** Processes the current text in this document 
     * to get the number of words, sentences and syllables in it
	 */
    private void processText() {
        this.numWords = 0;
        this.numSentences = 0;
        this.numSyllables = 0;
        this.tokens = getTokens("[a-zA-Z]+[.?!]*");
        for(String token : tokens) {
            this.numWords++;
            if(isSentenceEndingWord(token))
                this.numSentences++;
            this.numSyllables += countSyllables(token);
        }
    }

    /** Return the number of words in this document */
    public int getNumSentences() {
        return this.numSentences;
    }

     /** Return the number of sentences in this document */
    public int getNumWords() {
        return this.numWords;
    }

    /** Return the entire text of this document */
    public int getNumSyllables() {
        return this.numSyllables;
    }

    /** Return a list of words in this document */
    public List<String> getWords() {
        List<String> words = new ArrayList<String>(this.tokens);
        return words;
    }
}