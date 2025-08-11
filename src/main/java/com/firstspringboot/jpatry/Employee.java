package com.firstspringboot.jpatry;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "springbootjpaemployee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   //In the case of GenerationType.IDENTITY,
    // it means that the database itself will be responsible for generating the primary key,
    // typically DB using an auto-incrementing column.

    private Integer id;

    private String name;

    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
