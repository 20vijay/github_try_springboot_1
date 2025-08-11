package com.firstspringboot.jpatry;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}

