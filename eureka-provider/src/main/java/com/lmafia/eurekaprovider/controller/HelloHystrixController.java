package com.lmafia.eurekaprovider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lmafia
 * @classname HelloController.java
 * @description TODO
 * @date 2021/8/10
 */
@RestController
@RequestMapping("/test/hystrix")
public class HelloHystrixController {
    @Value("${server.port}")
    private String port ;

    @GetMapping("/helloWord")
    @HystrixCommand(defaultFallback = "fallback")
    public String helloWord(@RequestParam("name") String name){
        if (name.contains("..")) {
            throw  new RuntimeException();
        }
        return "Hello " + name + new Date() + " " + port;
    }

    public String fallback(){
        return "provider is busy...";
    }
}
