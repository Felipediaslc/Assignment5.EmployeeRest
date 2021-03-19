package com.nagarro.training.services;

import com.nagarro.training.dto.JsonResult;
import com.nagarro.training.models.Employee;
import com.nagarro.training.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public JsonResult validateEmployeeDetails(Employee employee) {
        JsonResult res = new JsonResult();

        if (!isValidEmployeeCode(employee.getEmployeeCode())) {
            res.setMessage("FAILURE! invalid employeeCode.");
            res.setStatus("failure");
        } else if(!isValidName(employee.getName())) {
            res.setMessage("FAILURE! invalid Name.");
            res.setStatus("failure");

        } else if(!isValidLocation(employee.getLocation())) {
            res.setMessage("FAILURE! invalid Location.");
            res.setStatus("failure");
        } else if(!isValidDob(employee.getDob())) {
            res.setMessage("FAILURE! invalid Date of Birth.");
            res.setStatus("failure");
        } else if(!isValidEmail(employee.getEmail())) {
            res.setMessage("FAILURE! invalid Email address.");
            res.setStatus("failure");
        } else {
            res.setMessage("SUCCESS! Employee details have been updated successfully.");
            res.setStatus("success");
        }

        return res;

    }

    private boolean isValidEmail(String email) {
        if (email == null || email.length() == 0 || email.length() > 100) {
            return false;
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidDob(Date dob) {
        return dob != null;
    }

    private boolean isValidLocation(String location) {
        return location != null && location.length() != 0 && location.length() <= 500;
    }

    private boolean isValidName(String name) {
        return name != null && name.length() != 0 && name.length() <= 100;
    }

    private boolean isValidEmployeeCode(Long id) {
        if (id == null || id < 1) {
            return false;
        }
        return employeeRepository.existsById(id);
    }
}
