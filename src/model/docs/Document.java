package model.docs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.util.Scanner;

/*
* A class that represents a text document
* @ specfield text : the current text in the document
*/
public abstract class Document {

    private String text; 

    /** 
     * Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
    public Document(String text) {
        this.text = text;
    }

    public Document(File file) {
        this.text = "";
        Scanner s = new Scanner(file);
        while(s.hasNextLine()) {
            this.text += s.nextLine();
        }
    }

    /** 
     * Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
    public List<String> getTokens(String pattern) {
        List<String> matches = new ArrayList<String>();
        // create matcher object that searches for pattern in text
        Matcher m = Pattern.compile(pattern).matcher(this.text);
        // while the matcher finds an token matching the pattern
        while(m.find()) {
            matches.add(m.group());
        }
        return matches;
    }

    /** 
     * This is a helper function that returns the number of syllables
	 * in a word.
     * 
     * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 */
    protected static int countSyllables(String word) {
        int numSyllables = 0;
        for(int i = 0; i < word.length(); i++) {
            if(isVowel(word.charAt(i))) {
                while(!isVowel(word.charAt(i))) {
                    i++;
                }
                numSyllables++;
            }
        }
        if(numSyllables > 1 && word.charAt(word.length() - 1) == 'e')
            numSyllables--;
        return numSyllables;
    }

    /**
     * Helper method that determines if a charcter is a vowel
     * 
     * 'y' is considered as vowel
     * 
     * @param c : character to be determined if vowel or not
     * @return : true if c is vowel, false otherwise
     */
    private static boolean isVowel(char c) {
        Set<Character> vowels = new TreeSet<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        return vowels.contains(c);
    }

    /** Return the number of words in this document */
    public abstract int getNumWords();

    /** Return the number of sentences in this document */
    public abstract int getNumSentences();

    /** Return the number of syllables in this document */
    public abstract int getNumSyllables();

    public abstract List<String> getWords();

    /** Return the entire text of this document */
    public String getText() {
        return this.text;
    }

    /** 
     * Gets the Flesch Score this document 
     * Flesch Score determines how hard it is to read the document
     * @return flesch score of this document
    */
    public double getFleschScore() {
        double score = 206.835 - 1.015 * (getNumWords()/(1.0*(getNumSentences()))) - 
                       84.6 * (getNumSyllables()/(1.0*(getNumWords())));
        return score;
    }
}