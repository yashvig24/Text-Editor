package model.dict;

import model.trie.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import model.docs.*;
import java.io.File;

public class TrieDictionary implements Dictionary {

    public Trie dict;

    public TrieDictionary(List<String> words) {
        this.dict = new Trie();
        for(String word : words) {
            dict.add(word);
        }
    }

    public TrieDictionary(File file) {
        this(new BasicDocument(file).getWords());
    }

    public TrieDictionary() {
        this(new ArrayList<String>());
    }

    public boolean addWord(String word) {
        return dict.add(word);
    }

    public boolean isWord(String word) {
        return dict.isWord(word);
    }

    public int size() {
        return dict.size();
    }

    public List<String> autoCompleteSuggest(String prefix, int numWords) {
        try {
            return dict.findNearbyWords(prefix, numWords);
        }
        catch(IllegalArgumentException e) {
            return new ArrayList<String>();
        }
    }

    public List<String> spellingCorrectSuggestion(String wrongWord, int numWords) {
        return dict.findCorrectWord(wrongWord, numWords);
    }
}