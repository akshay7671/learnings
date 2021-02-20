package com.samples.test.junit_mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/arrays")
class ArraysDataStructure {

    @GetMapping("/hello")
    public String hello() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/sort/{s}")
    public String sort(@PathVariable  String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}
