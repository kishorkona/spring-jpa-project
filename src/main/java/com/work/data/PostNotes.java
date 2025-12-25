package com.work.data;

import lombok.Data;

@Data
public class PostNotes {
    private Long id;
    private String title;
    private String body;
    private String url;

    private Person person;
}
