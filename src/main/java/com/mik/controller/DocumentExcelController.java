package com.mik.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/documents/excel")
public class DocumentExcelController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }


}
