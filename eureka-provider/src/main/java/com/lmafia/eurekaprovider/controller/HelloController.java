package com.lmafia.eurekaprovider.controller;

import com.thoughtworks.xstream.converters.time.LocalDateConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author lmafia
 * @classname HelloController.java
 * @description TODO
 * @date 2021/8/10
 */
@RestController
@RequestMapping("/test")
public class HelloController {
    @Value("${server.port}")
    private String port ;

    @GetMapping("/helloWord")
    public String helloWord(@RequestParam("name") String name){
        return "Hello " + name + new Date() + " " + port;
    }
}
