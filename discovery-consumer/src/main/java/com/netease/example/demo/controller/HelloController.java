package com.netease.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netease.example.demo.rpc.HelloRpc;
@RestController
public class HelloController {
	@Autowired
	private HelloRpc helloRpc;
	@RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRpc.hello(name);
    }
	
	@RequestMapping(value="/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value="userId",required=false) String userId) {
		helloRpc.login("nothing");
        return "{\"msg\":\"成功了\"}";
    }
}
