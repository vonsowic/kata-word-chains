package com.vonsowic.kata.katasolver;

import com.vonsowic.kata.exceptions.NoSolutionFoundException;
import com.vonsowic.kata.exceptions.UnknownWordException;
import com.vonsowic.kata.exceptions.WordsLengthNotEqualException;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordChainSolverTest {

    @Test
    public void shouldFindSolution() {
        // prepare data
        List<String> words = Arrays.asList("cat", "cot", "cog", "dog");

        WordChainSolver solver = new WordChainSolver();
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

        WordChainSolver solver = new WordChainSolver();
        solver.addWords(words);

        // run test
        Collection<String> result = solver.findChain("abcd", "wxyz");

        // validate result
        assertEquals(Arrays.asList("abcd", "wbcd", "wxcd", "wxyd", "wxyz"), result);
        assertEquals(5, result.size());
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenFirstWordIsNull() {
        WordChainSolver solver = new WordChainSolver();
        solver.findChain(null, "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenSecondWordIsNull() {
        WordChainSolver solver = new WordChainSolver();
        solver.findChain("dog", null);
    }

    @Test(expected = WordsLengthNotEqualException.class)
    public void shouldFailWhenWordsAreNotOfTheSameLength() {
        WordChainSolver solver = new WordChainSolver();

        // run test
        solver.findChain("cats", "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenFirstWordIsNotAvailableInDictionary() {
        // prepare data
        List<String> words = Arrays.asList("dog", "cot", "cog");

        WordChainSolver solver = new WordChainSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenLastWordIsNotAvailableInDictionary() {
        // prepare data
        List<String> words = Arrays.asList("cat", "cot", "cog");

        WordChainSolver solver = new WordChainSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }

    @Test(expected = UnknownWordException.class)
    public void shouldFailWhenThereIsNoWordWithRequiredLength() {
        // prepare data
        WordChainSolver solver = new WordChainSolver();
        solver.addWords(Arrays.asList("cat", "cot", "cog", "code", "ruby"));

        // run test
        solver.findChain("donkey", "monkey");
    }

    @Test(expected = NoSolutionFoundException.class)
    public void shouldFailWhenNoWordChainIsFound() {
        // prepare data
        List<String> words = Arrays.asList("cat", "dog", "cog");

        WordChainSolver solver = new WordChainSolver();
        solver.addWords(words);

        // run test
        solver.findChain("cat", "dog");
    }

    @Test
    public void shouldCreateSolverBasedOnFile() throws Exception {
        WordChainSolver solver = WordChainSolver.createFromFile(new File(
                getClass()
                        .getClassLoader()
                        .getResource("test_dictionary")
                        .getFile()));

        assertEquals(4, solver.findChain("donkey", "mongay").size());
    }
}