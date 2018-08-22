package com.vonsowic.kata;

import com.vonsowic.kata.katasolver.WordChainSolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConfiguration {

    @Bean
    public WordChainSolver solver() throws IOException {
        return WordChainSolver
                .createFromFile(new File(getClass()
                        .getClassLoader()
                        .getResource("wordlist.txt")
                        .getFile()));
    }
}
