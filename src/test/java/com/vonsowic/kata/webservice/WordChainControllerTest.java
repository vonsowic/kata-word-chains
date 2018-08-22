package com.vonsowic.kata.webservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WordChainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnListWithSolution4Words() throws Exception {
        mockMvc.perform(get("/api/chain")
                .param("start", "lead")
                .param("end", "gold"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]", is("lead")))
                .andExpect(jsonPath("$[1]", is("load")))
                .andExpect(jsonPath("$[2]", is("goad")))
                .andExpect(jsonPath("$[3]", is("gold")))
        ;
    }

    @Test
    public void shouldReturnListWithSolution5() throws Exception {
        mockMvc.perform(get("/api/chain")
                .param("start", "ruby")
                .param("end", "code"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0]", is("ruby")))
                .andExpect(jsonPath("$[1]", is("rube")))
                .andExpect(jsonPath("$[2]", is("rude")))
                .andExpect(jsonPath("$[3]", is("rode")))
                .andExpect(jsonPath("$[4]", is("code")))
        ;
    }

    @Test
    public void shouldReturnNotFoundResponseStatusWhenWordIsNotAvailableInDictionary() throws Exception {
        mockMvc.perform(get("/api/chain")
                .param("start", "AAA")
                .param("end", "AAQ"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnConflictStatusWhenSolutionIsNotFound() throws Exception {
        mockMvc.perform(get("/api/chain")
                .param("start", "Aliquippa's")
                .param("end", "Alexandrine"))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldReturnBadRequestStatusWhenGivenWordLengthIsNotEqual() throws Exception {
        mockMvc.perform(get("/api/chain")
                .param("start", "aaa")
                .param("end", "donkey"))
                .andExpect(status().isBadRequest());
    }
}