package com.firstspringboot.dao;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("collegeBean")
public class College {

    @Value("${app.name}")
    private String collegeName;
    private List<String> collegeList;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void met(){
        System.out.println("hi this is college method");
    }

    @PostConstruct // when dispatcher initializes this init met will be loaded
    public void init(){
        collegeList=new ArrayList<>();
        collegeList.add("MDT");
        collegeList.add("xaviers");
        collegeList.add("msu");
    }

public void addCollege(String collegeName){
        collegeList.add(collegeName);
}
    public List<String> getAllCollege(){
  return collegeList;
    }

    public int NoOfCollege(){
        return collegeList.size();
    }

    @PreDestroy
    // when server stops running, the container will become empty and the
    // beans(component anno) inside the ioc will also be deleted at the time we use this predestry
   public void destroy(){
        collegeList.clear();
   }

}
