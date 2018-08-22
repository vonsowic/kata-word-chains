package com.vonsowic.kata;

import com.vonsowic.kata.katasolver.WordChainSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    private static String wordlistFileName = "wordlist.txt";

    @Bean
    public WordChainSolver solver() throws IOException {
        logger.info("Start of loading wordlist from file: " + wordlistFileName + ". Please be patient.");
        return WordChainSolver
                .createFromFile(new File(getClass()
                        .getClassLoader()
                        .getResource(getWordlistFileName())
                        .getFile()));
    }

    public static String getWordlistFileName() {
        return wordlistFileName;
    }

    public static void setWordlistFileName(String wordlistFileName) {
        AppConfiguration.wordlistFileName = wordlistFileName;
    }
}
