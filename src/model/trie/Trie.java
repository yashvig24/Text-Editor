package model.trie;

import java.lang.IllegalArgumentException;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Trie {
    private TrieNode root;
    private int size;

    public Trie() {
        this.root = new TrieNode("");
        this.size = 0;
    }

    public boolean add(String s) {
        if(!contains(s)) {
            add(s, root);
            size++;
            return true;
        }
        return false;
    }

    private void add(String s, TrieNode node) {
        if(s.length() > 0) {
            char firstChar = s.charAt(0);
            if(node.contains(firstChar)) {
                add(s.substring(1), node.getChildOf(firstChar));
            }
            else {
                node.add(firstChar);
                add(s.substring(1), node.getChildOf(firstChar));
            }
        }
        else {
            node.setWord();
        }
    }

    public boolean contains(String s) {
        try {
            return traverseTrie(s).isWord();
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isWord(String s) {
        return traverseTrie(s).isWord();
    }

    public int size() {
        return this.size;
    }

    public List<String> findNearbyWords(String prefix, int n) throws IllegalArgumentException {
        List<String> nearby = new ArrayList<String>();
        // Breadth First Search to find most nearby words
        Queue<TrieNode> q = new LinkedList<TrieNode>();
        q.add(traverseTrie(prefix));
        TrieNode currNode = null;
        while(nearby.size() != n && !q.isEmpty()) {
            currNode = q.remove();
            if(currNode.isWord()) {
                nearby.add(currNode.toString());
            }
            for(TrieNode child : currNode.getAllChildren()) {
                q.add(child);
            }
        }
        return nearby;
    }

    private TrieNode traverseTrie(String prefix) {
        TrieNode currNode = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(currNode.contains(c))
                currNode = currNode.getChildOf(c);
            else
                throw new IllegalArgumentException();
        }
        return currNode;
    }

    public List<String> findCorrectWord(String wrongWord, int num) {
        
        return null;
    }
}