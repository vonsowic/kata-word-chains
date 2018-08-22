package com.vonsowic.kata.webservice;

import com.vonsowic.kata.katasolver.WordChainSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WordChainService {

    private final WordChainSolver solver;

    @Autowired
    public WordChainService(WordChainSolver solver) {
        this.solver = solver;
    }

    public Collection<String> findChain(String startOfChain, String endOfChain) {
        return solver.findChain(startOfChain, endOfChain);
    }
}
