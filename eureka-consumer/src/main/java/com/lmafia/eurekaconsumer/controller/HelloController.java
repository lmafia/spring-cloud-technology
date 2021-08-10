package com.lmafia.eurekaconsumer.controller;

import com.lmafia.eurekaconsumer.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

/**
 * @author lmafia
 * @classname HelloController.java
 * @description TODO
 * @date 2021/8/10
 */
@RestController
@RequestMapping("/consumer")
public class HelloController {
    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplateLoadBalance;

    @Autowired
    private HelloRemote helloRemote;

    /**
     * 方式1
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String helloWord(@RequestParam("name") String name){
        ServiceInstance instance = client.choose("eureka-provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/test/helloWord/?name=" + name+instance.getPort();
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * 方式2
     * 直接在RestTemplate 加 @LoadBalance
     * @param name
     * @return
     */
    @GetMapping("/hello2")
    public String helloWord2(@RequestParam("name") String name){
        String url = "http://eureka-provider/test/helloWord/?name=" +name;
        return restTemplateLoadBalance.getForObject(url, String.class);
    }


    /**
     * 方式3
     * Feign 的 方式
     * @param name
     * @return
     */
    @GetMapping("/hello3")
    public String helloWord3(@RequestParam("name") String name){
        return helloRemote.helloWord3(name);
    }
}
