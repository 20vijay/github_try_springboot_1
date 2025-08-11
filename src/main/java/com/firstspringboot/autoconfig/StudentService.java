package com.firstspringboot.autoconfig;

import com.firstspringboot.dao.Student;
import com.firstspringboot.dao.StudentDao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


List<Student> studentList=new ArrayList<>();
@Autowired
StudentDao studentDao;

    @PostConstruct
    public void setStudent(){
        Student student1=new Student();
        student1.setId(1);
        student1.setName("vijay");
        student1.setSubject("computer");

        Student student2=new Student();
        student2.setId(2);
        student2.setName("Ajit");
        student2.setSubject("maths");

        Student student3=new Student();
        student3.setId(3);
        student3.setName("surya");
        student3.setSubject("science");

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
    }

    public List<Student> getStudents(){

        return studentList;
    }

    public Page<Student> getStudentsPagination(int page,int size){
        List<Student> students=studentDao.findStudentsUsingPagination(page,size);
        int i = studentDao.countStudents();
        return new PageImpl<>(students, PageRequest.of(page-1,size),i);
    }


    public Student getStudentById(int id){
       for(Student student:studentList){
           if(student.getId()==id){
               return student;
           }
       }
        return null;
    }

    public boolean addStudent(Student student){
        boolean add=false;
     add = studentList.add(student);
        return add;
    }

    public void updateStudent(Student s){
        for(Student student:studentList){
            if(student.getId()==s.getId()){

                student.setId(s.getId());
                student.setName(s.getName());
                student.setSubject(s.getSubject());
System.out.println("updated student value "+student);
            }
        }
    }
    public void partialSubjectUpdateStudent(Student s){
        for(Student student:studentList){
            if(student.getId()==s.getId()){

                student.setId(s.getId());
                student.setSubject(s.getSubject());
                System.out.println("partially subject updated student value "+student);
            }
        }
    }

    public void deleteStudent(Student s){
        for(Student student:studentList){
            if(student.getId()==s.getId()){
               studentList.remove(student);
                System.out.println("deleted student value ");
                break;// we put break,bcoz if it is last student, when it gets deleted, it will try to iterate the loop, it will be null at the time so null pointer exception will occur
            }
        }
    }
}
