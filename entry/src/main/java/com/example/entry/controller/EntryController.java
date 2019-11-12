package com.example.entry.controller;

import com.example.entry.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

    @Autowired
    EntryService entryService;


    @RequestMapping("/consumer")
    public String helloConsumer(){
        return entryService.hello();
    }
}
