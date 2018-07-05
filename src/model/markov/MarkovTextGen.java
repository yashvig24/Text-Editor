package model.markov;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import model.docs.*;

/*
* A class that represents a textGenerator using markov's decision process
*/

public class MarkovTextGen implements TextGen {

    // a map storing a string and a a list of all words that followed it 
    // repeatable words are allowed
    // the more a word repeated, the higher the chance of it being the next word 
    // while generating text
    private Map<String, List<String>> nextWords; 

    // keeps track of the starting of a sentence words
    private List<String> startWords;

    /** 
     * Create a new MarkovTextGen from the given text.
	 */
    public MarkovTextGen() {
        this.nextWords = new HashMap<String, List<String>>();
        this.startWords = new LinkedList<String>();
    }

    /** Train the generator by adding the sourceText */
    public void train(String input) {
        Document doc = new EfficientDocument(input);
        List<String> allWords = doc.getAllWords();
        // add first word into start words
        this.startWords.add(allWords.get(0));

        for(int i = 0; i < allWords.size() - 1; i++) {
            String word = allWords.get(i);
            String nextWord = allWords.get(i+1);
            // check if it is a sentence ending word
            if(!doc.isWord(word)) {
                // if yes, next word should be a sentence starting word
                this.startWords.add(nextWord);
            }
            if(nextWords.containsKey(word)) {
                nextWords.get(word).add(nextWord);
            }
            else {
                List<String> list = new LinkedList<String>();
                list.add(nextWord);
                nextWords.put(word, list);
            }
        }
    }

    /** Retrain the generator from scratch on the source text */
    public void retrain(String input) {
        nextWords.clear();
        train(input);
    }

    /** Generate the text with the specified number of words */
    public String generate(int numWords) {
        String result;
        Random r = new Random();

        // get a random starting word form list of starting words
        int index = r.nextInt(this.startWords.size());
        String current = this.startWords.get(index);

        result += current + " ";

        for(int i = 0; i < numWords - 1; i++) {
            // get a probablt next word through current word
            List<String> next = this.nextWords.get(current);
            index = r.nextInt(next.size());
            current = next.get(index);
            result += current + " ";
        }
        return result.trim();
    }
}