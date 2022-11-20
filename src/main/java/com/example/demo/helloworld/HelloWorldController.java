package com.example.demo.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST Api
@RestController
public class HelloWorldController {

    @GetMapping(path ="/hello")
    public String helloWorld(){

        return "hello world";
    }
    @GetMapping(path ="/hello-bean")
    public helloWorldBean helloWorldBean(){

        return new  helloWorldBean("hello world bean");
    }
    @GetMapping(path ="/hello-bean/{name}")
    public helloWorldBean helloWorldBeanPath(@PathVariable String name){

        return new  helloWorldBean("hello world bean "+ name);
    }
}
