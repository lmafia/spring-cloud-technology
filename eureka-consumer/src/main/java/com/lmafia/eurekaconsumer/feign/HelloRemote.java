package com.lmafia.eurekaconsumer.feign;

import com.lmafia.eurekaconsumer.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lmafia
 * @classname HelloRemote.java
 * @description TODO
 * @date 2021/8/10
 */
@FeignClient(value = "eureka-provider", fallback = HelloRemoteHystrix.class)
public interface HelloRemote {

    @GetMapping("/test/helloWord/")
    public String helloWord3(@RequestParam("name") String name);

}
