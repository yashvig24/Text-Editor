package model.trie;

public class Node {

    char letter;
    TrieNode child;

    public Node(Character letter, TrieNode child) {
        this.letter = letter;
        this.child = child;
    }

    public Node(Character letter) {
        this(letter, null);
    }

    public TrieNode getChild() {
        return this.child;
    }

    public char getLetter() {
        return this.letter;
    }
}