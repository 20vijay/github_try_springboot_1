package com.firstspringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class StudentDao {

    @Autowired
   JdbcTemplate jdbcTemplate;
    Logger logger= java.util.logging.Logger.getLogger(StudentDao.class.getName());

    public List<Student> getAllStudentsfromDB(){
        String query="select * from springbootjdbc";
        List<Student> listOfStudents = jdbcTemplate.query(query, new BeanPropertyRowMapper<Student>(Student.class));
        logger.info("list of students "+listOfStudents);
        return listOfStudents;
    }

    public void addStudent(Student student) {
        String query="insert into springbootjdbc(id,name,subject) values(?,?,?)";

        int update = jdbcTemplate.update(query, student.getId(), student.getName(), student.getSubject());
        System.out.println("updated "+update);
    }
    public void setStudents(){
        String query="insert into springbootjdbc(id,name,subject) values(?,?,?)";
        for(int i=1;i<=100;i++){
            Object obj[]={};
            jdbcTemplate.update(query,i,"name "+i,"subject "+i);
        }
    }

    public List<Student> findStudentsUsingPagination(int page, int size){
        int offSet=(page-1)*size;

        System.out.println("offset " +offSet + "limit "+size);
        String query="select * from springbootjdbc order by id limit ? offset ?";

        Object[] obj={size,offSet};

        return jdbcTemplate.query(query,obj,new BeanPropertyRowMapper<>(Student.class));
    }
    public int countStudents(){
        String query="select count(*) from springbootjdbc";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
