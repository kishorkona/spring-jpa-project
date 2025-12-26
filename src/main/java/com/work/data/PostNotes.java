package com.work.data;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostNotes {
    private MultipartFile file;
    private String title;
    private String notes;
    private Long id;
    private String url;

    private Employee employee;
    private Person person;
}
