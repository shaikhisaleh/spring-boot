package com.example.demo.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//REST Api
@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
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
    @GetMapping(path ="/hello-international")
    public String helloWorldInternational(){
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage("good.morning.message",null,"default message",locale);
    }
}
