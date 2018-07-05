package model.markov;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class WordNode {
    private String word; 
    private List<WordNode> nextWords;

    public WordNode(String word) {
        this.word = word;
        this.nextWords = new LinkedList<WordNode>();
    }

    public String getWord() {
        return this.word;
    }

    public void addNextWord(String word) {
        this.nextWords.add(new WordNode(word));
    }

    public String getRandomNextWord() {
        Random generator = new Random();
        int index = generator.nextInt(nextWords.size());
	    return nextWords.get(index).getWord();
    }
}