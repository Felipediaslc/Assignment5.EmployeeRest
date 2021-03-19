package com.nagarro.training.repositories;


import com.nagarro.training.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
