package com.work.service;

import com.work.data.Person;
import com.work.data.PostNotes;
import com.work.entities.NotesEntity;
import com.work.entities.PersonEntity;
import com.work.repository.NotesRepository;
import com.work.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    NotesRepository notesRepository;

    public List<Person> getAllPersons() {
        List<PersonEntity> personList = personRepository.findAll();
        return personRepository.findAll().stream().map(x -> {
            Person person = new Person();
            person.setId(x.getId());
            person.setFirstName(x.getFirstName());
            person.setLastName(x.getLastName());
            person.setEmail(x.getEmail());
            person.setAge(x.getAge());
            return person;
        }).collect(Collectors.toUnmodifiableList());
    }

    public Person getPersonDetails(Long personId) {
        Person person = new Person();
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        if(personEntity.isPresent()) {
            PersonEntity x = personEntity.get();
            person.setId(x.getId());
            person.setFirstName(x.getFirstName());
            person.setLastName(x.getLastName());
            person.setEmail(x.getEmail());
            person.setAge(x.getAge());
            List<NotesEntity> notesList = notesRepository.findByPersonId(personId);
            person.setPostNotes(notesList.stream().map(y -> {
                com.work.data.PostNotes postNotes = new com.work.data.PostNotes();
                postNotes.setId(y.getId());
                postNotes.setTitle(y.getTitle());
                postNotes.setBody(y.getNotes());
                postNotes.setUrl(y.getUrl());
                return postNotes;
            }).collect(Collectors.toUnmodifiableList()));
        }
        return person;
    }

    public PostNotes getNotes(Long id) {
        PostNotes notes = new PostNotes();
        notesRepository.findById(id).ifPresent(y -> {
            notes.setId(y.getId());
            notes.setTitle(y.getTitle());
            notes.setBody(y.getNotes());
            notes.setUrl(y.getUrl());

            Person person = new Person();
            person.setId(y.getPerson().getId());
            person.setFirstName(y.getPerson().getFirstName());
            person.setLastName(y.getPerson().getLastName());
            person.setEmail(y.getPerson().getEmail());
            person.setAge(y.getPerson().getAge());
            notes.setPerson(person);

        });
        return notes;
    }
}
