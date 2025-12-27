package com.work.controllers;

import com.google.gson.Gson;
import com.work.constants.MyConstants;
import com.work.data.ApiResponse;
import com.work.data.Employee;
import com.work.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PersonController {
    Gson gson = new Gson();

    @Autowired
    MyService personService;

    @GetMapping("/get-persons")
    public ResponseEntity<ApiResponse> getAllPerson() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(personService.getAllPersons());
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/get-person/{personId}")
    public ResponseEntity<ApiResponse> getPersonNotes(@PathVariable("personId") Long personId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getPersonDetails(personId));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/get-notes/{id}")
    public ResponseEntity<ApiResponse> getNotes(@PathVariable("id") Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getNotes(id));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/get-employees")
    public ResponseEntity<ApiResponse> getAllEmployees() {
        // Get all employees and their and their department names.
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getAllEmployees());
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/get-department/{deptName}")
    public ResponseEntity<ApiResponse> getAllEmployeesInDepartment(@PathVariable("deptName") String deptName) {
        // Get all employees and their and their department names.
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getAllEmployeesInDepartment(deptName.trim().toUpperCase()));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create-employee")
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody Employee employee) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.createEmployee(employee));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-employee/{firstName}")
    public ResponseEntity<ApiResponse> getAllEmployeesByName(@PathVariable("firstName") String firstName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getAllEmployeeByName(firstName));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/get-employee/{firstName}/{lastName}")
    public ResponseEntity<ApiResponse> getAllEmployeesByFirstNameLastName(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        Employee employee = personService.getAllEmployeeDetails(firstName, lastName);
        System.out.println("employee=" + gson.toJson(employee));
        apiResponse.getData().add(employee);
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/update-employee")
    public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.updateEmployee(employee));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-employee/{firstName}/{lastName}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        personService.deleteEmployee(firstName, lastName);
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }
}
