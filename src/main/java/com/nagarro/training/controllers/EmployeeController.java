package com.nagarro.training.controllers;

import com.nagarro.training.dto.JsonResult;
import com.nagarro.training.models.Employee;
import com.nagarro.training.repositories.EmployeeRepository;
import com.nagarro.training.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Employee get(@PathVariable Long id) {
        return employeeRepository.getOne(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee){
        return employeeRepository.saveAndFlush(employee);
    }


    @PostMapping(value = "{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody Employee employee) {
        JsonResult res;
        if (!(employee.getEmployeeCode() == null) && !employee.getEmployeeCode().equals(id)) {
            res = new JsonResult();
            res.setStatus("failure");
            res.setMessage("FAILURE! invalid employeeCode provided.");
            return res;
        }
        res = employeeService.validateEmployeeDetails(employee);
        if (res.getStatus().equals("failure")) {
            return res;
        }
        Employee existingEmployee = employeeRepository.getOne(id);
        BeanUtils.copyProperties(employee, existingEmployee, "employeeCode");
        employeeRepository.saveAndFlush(existingEmployee);
        return res;
    }

}
