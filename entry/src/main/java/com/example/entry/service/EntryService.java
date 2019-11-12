package com.example.entry.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient(value = "SERVICE-PERSON")
public interface  EntryService {

    @RequestMapping("/hi")
    String hello();
}
