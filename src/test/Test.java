package test;

import java.io.File;
import model.docs.*;
import model.markov.*;
import java.io.IOException;
import model.bst.*;
import model.dict.TrieDictionary;
import model.trie.*;

public class Test {
    public static void main(String args[]) {
        testDictionary();
    }

    private static void testGetNumWords() {
        String doc1 = "This is a test class";
        String doc2 = "This has   many   spaces  ";
        String doc3 = "    ";
        String doc4 = "";
        String doc5 = "    This starts and ends with spaces    ";
        String doc6 = "!?";

        String[] tests = new String[] {doc1, doc2, doc3, doc4, doc5, doc6};
        int[] groundTruth = new int[]{5, 4, 0, 0, 6, 1};
        boolean passed = true;
        for (int i = 0; i < tests.length; i++) {
            Document d = new BasicDocument(tests[i]);
            int numWords = d.getNumWords();
            passed = passed && (numWords == groundTruth[i]);
        }
        System.out.println(passed);
    }

    private static void testGetNumSentences() {
        String doc1 = "This is a test class. It has two sentences.";
        String doc2 = "This has   many   spaces. It also    has three sentences.  ";
        String doc3 = "Hi.";
        String doc4 = "";
        String doc5 = "    This? starts! and... ends!!! with spaces    ";
        String doc6 = "Why!? Did?!";

        String[] tests = new String[] {doc1, doc2, doc3, doc4, doc5, doc6};
        int[] groundTruth = new int[]{2, 3, 1, 0, 5, 2};
        boolean passed = true;
        for (int i = 0; i < tests.length; i++) {
            Document d = new BasicDocument(tests[i]);
            int numWords = d.getNumSentences();
            passed = passed && (numWords == groundTruth[i]);
        }
        System.out.println(passed);
    }

    private static void testMarkov() throws IOException {
        TextGen gen = new MarkovTextGen();
        String path = "/Users/legend/Documents/myTextEditor/data/lyric.txt";
        Document lyricDoc = new BasicDocument(new File(path));
        String lyrics = lyricDoc.getText();
        gen.train(lyrics);
        System.out.println(gen.generate(50));
    }

    private static void testTrie() {
        Trie t = new Trie();
        t.add("a");
        t.add("at");
        t.add("ate");
        t.add("basket");
        t.add("yellow");
        System.out.println(t.contains("a"));
        System.out.println(t.contains("basket"));
        System.out.println(t.contains("at"));
        System.out.println(t.contains("ate"));
        System.out.println(t.contains("yellow"));
        System.out.println(t.contains("Yellow"));
    }

    private static void testDictionary() {
        TrieDictionary dict = new TrieDictionary();
        dict.addWord("a");
        dict.addWord("ate");
        dict.addWord("at");
        dict.addWord("as");
        dict.addWord("ass");
        dict.addWord("an");
        dict.addWord("ant");
        System.out.println(dict.autoCompleteSuggest("a", 4));
    }
}