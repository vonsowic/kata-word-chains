package com.vonsowic.kata.katasolver;

import com.vonsowic.kata.exceptions.NoSolutionFoundException;
import com.vonsowic.kata.exceptions.UnknownWordException;
import com.vonsowic.kata.exceptions.WordsLengthNotEqualException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class KataSolverTest {

    @Test
    public void shouldFindSolution() {
        // prepare data
        List<String> words = Arrays.asList("cat", "dog", "cot", "cog");

        KataSolver solver = new KataSolver();
        solver.addWords(words);

        // run test
        Collection<String> result = solver.findChain("cat", "dog");

        // validate result
        assertEquals(words, result);
    }

    @Test
    public void shouldFindShortestSolution() {
        // prepare data
        List<String> words = Arrays.asList(
                "abcd",
                "arcd", "arrd", "arrd", "axrd", "wxrd",
                "wbcd",
                "wxcd",
                "wxyd",
                "wxyz"
        );

        KataSolver solver = new KataSolver();
        solver.addWords(words);

        // run test
        Collection<String> result = solver.findChain("abcd", "wxyz");

        // validate result
        assertEquals(words, result);
        assertEquals(5, result.size());
    }

    @Test(expected = WordsLengthNotEqualException.class)
    public void shouldFailWhenWordsAreNotOfTheSameLength() {
        KataSolver solver = new KataSolver();

        // run test
        solver.findChain("cats", "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenFirstWordIsNotAvailableInDictionary() {
        // prepare data
        List<String> words = Arrays.asList("dog", "cot", "cog");

        KataSolver solver = new KataSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenLastWordIsNotAvailableInDictionary() {
        // prepare data
        List<String> words = Arrays.asList("cat", "cot", "cog");

        KataSolver solver = new KataSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }

    @Test(expected = NoSolutionFoundException.class)
    public void shouldFailWhenNoWordChainIsFound() {
        // prepare data
        List<String> words = Arrays.asList("cat", "dog", "cog");

        KataSolver solver = new KataSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }
}