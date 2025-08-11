package com.firstspringboot.dao;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component

//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//above we gave the scope as request means every request comes as a new request and new obj will be created everytime for each request.
//we can say within the particular request the object will remain as single ton
//the above line is same as the below line
//the @RequestScope annotation is not lazy by default. This means that a bean annotated with
//@RequestScope will be initialized at the beginning of an HTTP request and will be destroyed at the end of the request.
//@RequestScope

//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//above we gave the scope as session means every session(try different browser) comes as a new session and new obj will be created everytime for each session(try different browser).
//we can say within the particular session the object will remain as single ton. it is also no lazy by default
//the above line is same as the below line
//@SessionScope

@ApplicationScope
// the above is application scope which means, it does not matter how many containers u create, how many, request or sessions or dispatcher servlets you create
//there will be only one object will be created through out the entire application. and it is by default Lazy Initialization
public class MyBean {
    private String name="thalapathy";

    public MyBean(){
        System.out.println("this is my bean constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
