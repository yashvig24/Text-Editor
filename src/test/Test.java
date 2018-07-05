package test;

import java.io.File;
import model.docs.*;
import model.markov.*;

public class Test {
    public static void main(String args[]) {
        testMarkov();
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

    public static void testMarkov() {
        TextGen gen = new MarkovTextGen();
        String path = "Users/legend/Documents/myTextEditor/data/lyrics.txt";
        Document lyricDoc = new BasicDocument(new File(path));
        String lyrics = lyricDoc.getText();
        gen.train(lyrics);
        System.out.println(gen.generate(5));
    }
}