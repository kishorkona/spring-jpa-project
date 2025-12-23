package com.work.controllers;

import com.google.gson.Gson;
import com.work.constants.MyConstants;
import com.work.data.ApiResponse;
import com.work.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PersonController {
    Gson gson = new Gson();

    @Autowired
    MyService personService;

    @GetMapping("/getPersons")
    public ResponseEntity<ApiResponse> getAllPerson() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(personService.getAllPersons());
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getPerson/{personId}")
    public ResponseEntity<ApiResponse> getPersonNotes(@PathVariable("personId") Long personId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getPersonDetails(personId));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getNotes/{id}")
    public ResponseEntity<ApiResponse> getNotes(@PathVariable("id") Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new ArrayList());
        apiResponse.getData().add(personService.getNotes(id));
        apiResponse.setCode(MyConstants.SUCCESS_CODE);
        apiResponse.setStatus(MyConstants.SUCCESS);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }


}
