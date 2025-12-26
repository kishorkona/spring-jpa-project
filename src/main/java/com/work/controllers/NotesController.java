package com.work.controllers;

import com.google.gson.Gson;
import com.work.data.PostNotes;
import com.work.entities.NotesEntity;
import com.work.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class NotesController {
    Gson gson = new Gson();

    @Autowired
    S3Service s3Service;

    @GetMapping("/getAllNotes")
    public ResponseEntity<String> getAllNotes() {
        System.out.println("------------>> getNotes:"+Thread.currentThread().getName());
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @GetMapping("/getNotes")
    public ResponseEntity<String> getNotes() {
        PostNotes postNotes = new PostNotes();
        postNotes.setNotes("body");
        postNotes.setTitle("title");

        System.out.println("------------>> getNotes:"+gson.toJson(postNotes));

        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @PostMapping(path = "/notes", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<PostNotes> addNotes(
            @RequestParam("file") MultipartFile file,
            @RequestParam("postNotes") String jsonData) {
        PostNotes postNotes = gson.fromJson(jsonData, PostNotes.class);
        postNotes.setFile(file);
        try {
            PostNotes postNotesRslt = s3Service.uploadData(postNotes);
            return new ResponseEntity(postNotesRslt, HttpStatus.OK);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity(new PostNotes(), HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<PostNotes> getNotesById(@PathVariable("id") Long id) {
        PostNotes postNotes = s3Service.getNotesById(id);
        return new ResponseEntity(postNotes, HttpStatus.OK);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<PostNotes>> getNotesByQuery(
            @RequestParam(value="title") String title,
            @RequestParam(value="notes", required = false) String notes
    ) {
        List<PostNotes> postNotesList = null;
        if(!title.isEmpty()) {
            postNotesList = s3Service.getNotesByTitle(title);
        }
        if(notes != null) {
            postNotesList = s3Service.getNotesByTitleAndNotes(title, notes);
        }
        if(postNotesList.isEmpty()) {
            postNotesList = new ArrayList<>();
        }
        return new ResponseEntity(postNotesList, HttpStatus.OK);
    }
}
