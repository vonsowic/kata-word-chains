package com.vonsowic.kata.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class WordChainController {

    private final WordChainService service;

    @Autowired
    public WordChainController(WordChainService service) {
        this.service = service;
    }

    @GetMapping("/chain")
    public Collection<String> findChain(
            @RequestParam("start") String startOfChain,
            @RequestParam("end") String endOfChain) {

        return service.findChain(startOfChain, endOfChain);
    }
}
