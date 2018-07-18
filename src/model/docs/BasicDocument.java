package model.docs;

import java.util.List;
import java.io.File;

public class BasicDocument extends Document {

    /** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
    public BasicDocument(String text) {
        super(text);
    }

    public BasicDocument(File file) {
        super(file);
    }

    /** Return the number of words in this document */
    public int getNumWords() {
        return this.getWords().size();
    }

    /** Returns a list of all tokens in the document 
	 * 
	 * @return List of all tokens in document
	 */
    public List<String> getWords() {
        String pattern = "[a-zA-Z]+[.?!]*";
        return getTokens(pattern);
    }

    /** Return the number of sentences in this document */
    public int getNumSentences() {
        String pattern = "[^.!?]+";
        List<String> list = getTokens(pattern);
        return list.size();
    }

    /** Return the entire text of this document */
    public int getNumSyllables() {
        int count = 0;
        for(String word : this.getWords()) {
            count += countSyllables(word);
        }
        return count;
    }
}