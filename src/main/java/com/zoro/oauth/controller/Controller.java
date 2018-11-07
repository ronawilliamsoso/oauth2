package com.zoro.oauth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
public class Controller {

     
	@RequestMapping(value="/f",method=RequestMethod.GET)
    public Test getThrough() {
        
        return new Test("access the API");
    }
	
 
	@RequestMapping("/session")
    public String helloAdmin(HttpSession session) {
        return "hello,session id is "+ session.getId() +"\n";
    }
 

}

 