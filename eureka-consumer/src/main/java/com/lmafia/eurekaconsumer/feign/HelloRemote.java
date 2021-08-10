package com.lmafia.eurekaconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lmafia
 * @classname HelloRemote.java
 * @description TODO
 * @date 2021/8/10
 */
@FeignClient(name = "eureka-provider")
public interface HelloRemote {

    @GetMapping("/test/helloWord/")
    public String helloWord3(@RequestParam("name") String name);

}
