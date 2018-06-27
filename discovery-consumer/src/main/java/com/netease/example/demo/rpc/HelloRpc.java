package com.netease.example.demo.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="spring-cloud-producer")
public interface HelloRpc {
	@RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
	
	@RequestMapping(value="/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value="userId",required=false) String userId) ;
}
