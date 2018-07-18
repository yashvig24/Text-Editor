package model.trie;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
    private static final int NUM_CHARS = 57;
    private static final int ASCII_ALPH_START = 65;
    private Node[] allChars; 
    private boolean isWord;
    private String sequence;

    TrieNode(String s) {
        this.sequence = s;
        allChars = new Node[NUM_CHARS];
        isWord = false;
    }

    boolean contains(char c) {
        int index = (int)(c) - ASCII_ALPH_START;
        boolean contains = this.allChars[index] != null;
        return contains;
    }

    boolean add(char c) {
        if(!contains(c)) {
            int index = (int)(c) - ASCII_ALPH_START;
            allChars[index] = new Node(c);
            allChars[index].child = new TrieNode(this.sequence + c);
            return true;
        }
        return false;
    }

    TrieNode getChildOf(char c) {
        int index = (int)(c) - ASCII_ALPH_START;
        return allChars[index].getChild();
    }

    void setWord() {
        this.isWord = true;
    }

    boolean isWord() {
        return isWord;
    }

    @Override
    public String toString() {
        return this.sequence;
    }

    List<TrieNode> getAllChildren() {
        List<TrieNode> children = new ArrayList<TrieNode>();
        for(int i = 0; i < allChars.length; i++) {
            if(allChars[i] != null)
                children.add(allChars[i].getChild());
        }
        return children;
    }
}