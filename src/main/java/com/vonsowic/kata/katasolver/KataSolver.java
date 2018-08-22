package com.vonsowic.kata.katasolver;

import com.vonsowic.kata.exceptions.UnknownWordException;
import com.vonsowic.kata.exceptions.WordsLengthNotEqualException;

import java.util.Collection;
import java.util.HashMap;

public class KataSolver {

    private final HashMap<Integer, WordChainGraph> graphs = new HashMap<>();

    public Collection<String> findChain(String startWord, String endWord) {
        if (startWord == null || endWord == null) {
            throw new UnknownWordException("Arguments must not be null");
        }

        if(startWord.length() != endWord.length()) {
            throw new WordsLengthNotEqualException();
        }

        if ( !this.graphs.containsKey(startWord.length())) {
            throw new UnknownWordException();
        }


        return this.graphs.get(startWord.length())
                .findShortestSolution(startWord, endWord);
    }

    public void addWords(Collection<String> words) {
        words.forEach(this::addWord);
    }

    public void addWord(String word) {
        if ( !this.graphs.containsKey(word.length()) ) {
            this.graphs.put(word.length(), new WordChainGraph());
        }

        graphs.get(word.length()).addWord(word);
    }
}
