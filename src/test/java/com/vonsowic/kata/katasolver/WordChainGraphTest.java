package com.vonsowic.kata.katasolver;

import com.google.common.graph.Graph;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class WordChainGraphTest {

    private static Graph<String> getInnerGraphFrom(WordChainGraph graph) throws NoSuchFieldException, IllegalAccessException {
        Field field = WordChainGraph.class.getDeclaredField("graph");
        field.setAccessible(true);
        return (Graph<String>) field.get(graph);
    }

    private static boolean shouldBeLinked(String word1, String word2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method shouldBeLinked = WordChainGraph.class
                .getDeclaredMethod("shouldBeLinked", String.class, String.class);

        shouldBeLinked.setAccessible(true);

        return (boolean) shouldBeLinked.invoke(new WordChainGraph(), word1, word2);
    }

    @Test
    public void shouldAddWord() throws Exception {
        WordChainGraph graph = new WordChainGraph();
        graph.addWord("donkey");

        assertEquals("donkey", getInnerGraphFrom(graph).nodes().iterator().next());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAddNull() throws Exception {
        WordChainGraph graph = new WordChainGraph();
        graph.addWord(null);
    }

    @Test
    public void shouldBePossibleToLink() throws Exception {
        assertTrue("donkey", shouldBeLinked("donkey", "monkey"));
    }

    @Test
    public void shouldNotBePossibleToLink() throws Exception {
        assertFalse(shouldBeLinked("grizly", "monkey"));
    }

    @Test
    public void shouldNotBePossibleToLinkSiblingsWords() throws Exception {
        assertFalse(shouldBeLinked("monkey", "monkey"));
    }

    @Test
    public void shouldNotBePossibleToLinkWordsWithDifferentLength() throws Exception {
        assertFalse(shouldBeLinked("monkey", "a"));
    }

    @Test
    public void shouldAddTwoWordsAndLinkThem() throws Exception {
        WordChainGraph graph = new WordChainGraph();
        graph.addWord("donkey1");
        graph.addWord("donkey2");

        Graph<String> innerGraph = getInnerGraphFrom(graph);
        assertEquals(2, innerGraph.nodes().size());
        assertTrue(innerGraph.hasEdgeConnecting("donkey1", "donkey2"));
    }

    @Test
    public void shouldAddTwoWordsAndNotLinkThem() throws Exception {
        WordChainGraph graph = new WordChainGraph();
        graph.addWord("donkey");
        graph.addWord("zombie");

        Graph<String> innerGraph = getInnerGraphFrom(graph);
        assertEquals(2, innerGraph.nodes().size());
        assertFalse(innerGraph.hasEdgeConnecting("donkey", "zombie"));
    }
}