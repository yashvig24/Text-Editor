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
        this.tokens = getTokens("[!?.]+[a-zA-Z]+");
        for(String token : tokens) {
            if(isWord(token)) 
                this.numWords++;
            else 
                this.numSentences++;
            this.numSyllables += countSyllables(token);
        }
    }

    /** Returns whether a token is a word or an end of punctuation token
	 * 
	 * @param token to check if word
     * @return true if word, false if end of punctuation token
	 */
    private boolean isWord(String token) {
        return (!(token.indexOf('!') >= 0 || (token.indexOf('.') >= 0) || (token.indexOf('?') >= 0)));
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
    public List<String> getAllWords() {
        List<String> words = new List<String>(this.tokens);
        return words;
    }
}