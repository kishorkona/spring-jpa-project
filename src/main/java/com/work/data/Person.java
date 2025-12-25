package com.work.data;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private List<PostNotes> postNotes;
}
