package com.firstspringboot.controllers;

import com.firstspringboot.dao.Student;
import com.firstspringboot.autoconfig.StudentService;
import com.firstspringboot.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class StudentController{

    @Autowired
    StudentDao studentDao;

    Logger logger=Logger.getLogger(StudentController.class.getName());
    @Autowired
    StudentService stuSer;

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        List<Student> students = stuSer.getStudents();
        logger.info("this is logger "+students);

        return students;
    }

    @PostMapping("/setStudentsToDB")
    public void setStudentsToDB(){
        studentDao.setStudents();
        System.out.println("students set");
    }

    @GetMapping("/paginationMet")
    public Page<Student> paginationMet(){
        int page=2;
        int size=10;
        return stuSer.getStudentsPagination(page,size);
    }



    @GetMapping("/getStudentsFromDB")
    public List<Student> getStudentsFromDB(){
        List<Student> allStudentsfromDB = studentDao.getAllStudentsfromDB();
        return allStudentsfromDB;
    }



    // below we just specified explicitily add the type of the response as xml format(you need to add jackson xml dataformat dependencies),
    // in postman request header, change the ACCEPT row into application/json or application/xml or text/plain(u need to return string in method  if you get want to get response as string)
    // so below method wouldnt return any other format
    // will throw NotAcceptable exception(check in console request and response). u remove the produces below, in whatever format you are asking the response will be provided
    @GetMapping(value = "/getStudentById/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Student getStudentById(@PathVariable int id){
        Student student = stuSer.getStudentById(id);
        return student;
    }

    // below we explicitly define the below endpoint will accept only json type and return only as xml format as response, in postman in the request header
    // check content-type row. if we dont give any mediatype here it will accept any values from request depends upon the dependencies we have\\
    // in post main in request -->content-type is the format of sending request and accept is the format for accepting the response
    @RequestMapping(method = RequestMethod.POST,value="/addStudent",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public Student addStudent(@RequestBody Student student){
        boolean b = stuSer.addStudent(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addStudentToDB")
    public Student addStudentToDB(@RequestBody Student student){
        studentDao.addStudent(student);
        return student;
    }

    @RequestMapping(method=RequestMethod.PUT,value="/updateStudent")
    public void updateStudent(@RequestBody Student student){

        stuSer.updateStudent(student);
    }
    // belo patch method, we use to partially update some fields not all the fields-- here we just update the subject tab
    @RequestMapping(method=RequestMethod.PATCH,value="/partialSubjectUpdateStudent")
    public void partialSubjectUpdateStudent(@RequestBody Student student){
        logger.info("the value of student object inside the patch method "+student);
        stuSer.partialSubjectUpdateStudent(student);
    }

    // the below will return the headers alone not body, in post man click the headers tab
    @RequestMapping(method=RequestMethod.HEAD,value="/headStudent/{id}")
    public Student headStudent(@PathVariable int id){
        Student studentById = stuSer.getStudentById(id);
        logger.info("student by id "+studentById);
        return studentById;
    }


    @RequestMapping(method=RequestMethod.DELETE,value="/deleteStudent")
    public void deleteStudent(@RequestBody Student student){

        stuSer.deleteStudent(student);
    }


    //below options method is used to see which method are supported for a particular endpoint
    //ex client sends an options request to the below endpoint, in response he gets the below given values as supported methods
    //then client gets to know which is supported, then he sends the request with according method
    //you can check on the headers tab, on the allow row
    @RequestMapping(method = RequestMethod.OPTIONS, value = "/students")
    public ResponseEntity<Void> optionsForStudents() {
        // Return a response with the allowed methods
        return ResponseEntity.ok()
                .header("Allow", "GET, POST, PUT, DELETE")
                .build();
    }



}

