package com.netease.example.demo.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netease.example.demo.context.TenantContext;
import com.netease.example.demo.entity.User;
import com.netease.example.demo.service.UserService;

@RestController
public class HelloController {
	@Autowired
	UserService userService;
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }
	
	@RequestMapping(value="/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value="user_id",required=false) String userId) {
		if(dataSource==null) {
			System.out.println("dataSource==null");
		}
        return "{\"msg\":\"成功了\"}";
    }
	
	@RequestMapping(value="/add_user", produces = "application/json;charset=UTF-8")
    public String addUser(@RequestParam(value="user_name") String name) {
		TenantContext.setCurrentTenant("test");
		User u=new User();
		u.setName(name);
		userService.save(u);
		System.out.println(u.getId());
        return "{\"msg\":\"成功了\"}";
    }
	@RequestMapping(value="/add_user2", produces = "application/json;charset=UTF-8")
	public String addUser2(@RequestParam(value="user_name") String name) {
		TenantContext.setCurrentTenant("test2");
		User u=new User();
		u.setName(name);
		userService.save(u);
		System.out.println(u.getId());
		return "{\"msg\":\"成功了\"}";
	}
}
