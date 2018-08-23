package com.vonsowic.kata.katasolver;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.vonsowic.kata.exceptions.NoSolutionFoundException;
import com.vonsowic.kata.exceptions.UnknownWordException;

import java.util.*;
import java.util.stream.Collectors;

class WordChainGraph {

    private final MutableGraph<String> graph = GraphBuilder
            .undirected()
            .build();


    void addWord(String newWord) {
        this.graph.addNode(newWord);

        this.graph.nodes()
                .stream()
                .filter(word -> shouldBeLinked(word, newWord))
                .forEach(word -> this.graph.putEdge(word, newWord));
    }


    private boolean shouldBeLinked(String word1, String word2) {
        if(word1.length() != word2.length()) {
            return false;
        }

        int numberOfDiffs = 0;
        for (int i=0; i<word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                numberOfDiffs++;
            }
        }

        return numberOfDiffs == 1;
    }


    Collection<String> findShortestSolution(String startWord, String endWord) {
        if ( !graph.nodes().contains(startWord) || !graph.nodes().contains(endWord)) {
            throw new UnknownWordException();
        }

        Set<String> visited = new HashSet<>();

        Queue<SolutionNode> wordQueue = new LinkedList<>();
        wordQueue.add(new SolutionNode(startWord));

        while (!wordQueue.isEmpty()) {
            SolutionNode processedNode = wordQueue.poll();

            for (String word: getUnvisitedNeighborsOf(processedNode, visited) ) {
                SolutionNode child = new SolutionNode(processedNode, word);
                if (child.valueEquals(endWord)) {
                    return child.getFullPath();
                }

                wordQueue.add(child);
                visited.add(word);
            }
        }


        throw new NoSolutionFoundException();
    }


    private Collection<String> getUnvisitedNeighborsOf(SolutionNode word, Collection<String> visited) {
        return getUnvisitedNeighborsOf(word.value, visited);
    }

    private Collection<String> getUnvisitedNeighborsOf(String word, Collection<String> visited) {
        return this.graph
                .adjacentNodes(word)
                .stream()
                .filter(w -> !visited.contains(w))
                .collect(Collectors.toList());
    }


    private class SolutionNode {
        final SolutionNode parent;
        final String value;

        SolutionNode(String value) {
            this(null, value);
        }

        SolutionNode(SolutionNode parent, String value) {
            this.value = value;
            this.parent = parent;
        }

        List<String> getFullPath() {
            List<String> result = new LinkedList<>();

            SolutionNode ptr = this;
            while (ptr != null) {
                result.add(0, ptr.value);
                ptr = ptr.parent;
            }

            return result;
        }

        boolean valueEquals(String anotherValue) {
            return this.value.equals(anotherValue);
        }
    }
}
