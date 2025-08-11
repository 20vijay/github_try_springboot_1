package com.firstspringboot.controllers;


import com.firstspringboot.dao.College;
import com.firstspringboot.dao.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@RestController
public class FirstController {

    @Autowired
    private College college;

    @GetMapping("/hello-world")
    public String met(){
        return "hi this is first controller";
    }

    @GetMapping("/jsonCol")
    public College jsonCollege(){
        // this endpoint returns  object in json format(Jackson)
        return college;
    }

    @GetMapping("/getAllCollege")
    public List<String> getAllCollegeNames(){

        List<String> allCollege = college.getAllCollege();

        return allCollege;
    }

    @GetMapping("/totalColleges")
    public int getNoOfColleges(){
        int i = college.NoOfCollege();
        return i;
    }

    @GetMapping("/addNewCollege/{collegeName}")
    public List<String> addNewCollege(@PathVariable String collegeName){
        college.addCollege(collegeName);
        List<String> allCollege = college.getAllCollege();
        System.out.println(allCollege);
        return allCollege;
    }


    // below method is how you read from the request check in postman
    @RequestMapping(method = RequestMethod.POST,value="/addNewCollegePost")
    public void addNewCollegePost(@RequestBody Map<String, Object> requestBody){
        String collegeName = (String) requestBody.get("name");  // Extract the "name" field from the request body
        System.out.println(collegeName);  // Prints the value of "name" from the JSON request body
        college.addCollege(collegeName);

        String address = (String) requestBody.get("address");

        String city = (String) requestBody.get("city");

        System.out.println("address "+address+" city "+city);
        college.addCollege(collegeName);
    }
}
@Controller
class SecondController{
    @Autowired
    MyBean myBean;

    @ResponseBody
    @GetMapping("/myBean")
    public String met(){

        System.out.println(myBean.getName());
        myBean.setName("thala");
        System.out.println(myBean.getName());

        System.out.println("second controller object "+myBean);

         return "hi second controller";
    }
}
@Controller
class ThirdController{

    @Autowired
    MyBean myBean;

    @ResponseBody
    @GetMapping("/myBean1")
    public String met(){
        System.out.println(myBean.getName());
        System.out.println("third controller object "+myBean);
        return "hi third controller";
    }
}
