package com.vonsowic.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class AppTest {

    static {
        AppConfiguration.setWordlistFileName("wordlist-small.txt");
    }

    @Test
    public void applicationContextTest() {
        App.main(new String[] {});
    }
}