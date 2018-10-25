package com.zoro.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
@EnableSwagger2

public class Controller {

     
	@RequestMapping(value="/f",method=RequestMethod.GET)
    public String getThrough(@RequestParam  String id) {
        
        return "ok";
    }

 

}