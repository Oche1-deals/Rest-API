package com.oche.rest.webservices.restful_web_service.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1","value2","value3");
    }
}