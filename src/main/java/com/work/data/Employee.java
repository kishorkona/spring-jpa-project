package com.work.data;

import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer jobId;
    private Double salary;
    private String departmentName;
    private Location location;

}
