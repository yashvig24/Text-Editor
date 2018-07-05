package model.markov;

/**
 *  The interface for the any TextGen
 */
public interface TextGen {

    /** Train the generator by adding the sourceText */
    public void train(String input);

    /** Generate the text with the specified number of words */
    public String generate(int numWords);
    
    /** Retrain the generator from scratch on the source text */
    public void retrain(String input);
}