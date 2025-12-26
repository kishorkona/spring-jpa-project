package com.work.service;

import com.work.data.Employee;
import com.work.data.Location;
import com.work.data.Person;
import com.work.data.PostNotes;
import com.work.entities.DepartmentEntity;
import com.work.entities.LocationEntity;
import com.work.entities.NotesEntity;
import com.work.entities.PersonEntity;
import com.work.repository.DepartmentRepository;
import com.work.repository.EmployeeRepository;
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

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

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
                postNotes.setNotes(y.getNotes());
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
            notes.setNotes(y.getNotes());
            notes.setUrl(y.getUrl());
        });
        return notes;
    }

    public List<Employee> getAllEmployeesInDepartment(String departmentName) {
        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentName(departmentName);
        if(departmentEntity == null) {
            return new ArrayList<>();
        }
        return departmentEntity.getEmployees().stream().map(x -> {
            Employee employee = new Employee();
            employee.setId(x.getId());
            employee.setFirstName(x.getFirstName());
            employee.setMiddleName(x.getMiddleName());
            employee.setLastName(x.getLastName());
            employee.setEmail(x.getEmail());
            employee.setPhoneNumber(x.getPhoneNumber());
            employee.setJobId(x.getJobId());
            employee.setSalary(x.getSalary());
            employee.setDepartmentName(departmentName);

            LocationEntity locationEntity  = departmentEntity.getLocation();
            if(locationEntity != null) {
                Location location = new Location();
                location.setId(locationEntity.getId());
                location.setStreetAddress(locationEntity.getStreetAddress());
                location.setPostalCode(locationEntity.getPostalCode());
                location.setCity(locationEntity.getCity());
                location.setStateProvince(locationEntity.getStateProvince());
                location.setCountryName(locationEntity.getCountryName());
                employee.setLocation(location);
            }
            return employee;
        }).collect(Collectors.toUnmodifiableList());
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().map(x -> {
            Employee employee = new Employee();
            employee.setId(x.getId());
            employee.setFirstName(x.getFirstName());
            employee.setMiddleName(x.getMiddleName());
            employee.setLastName(x.getLastName());
            employee.setEmail(x.getEmail());
            employee.setPhoneNumber(x.getPhoneNumber());
            employee.setJobId(x.getJobId());
            employee.setSalary(x.getSalary());

            DepartmentEntity dept = x.getDepartment();
            if(dept != null) {
                employee.setDepartmentName(dept.getDepartmentName());
                LocationEntity locationEntity  = dept.getLocation();
                if(locationEntity != null) {
                    Location location = new Location();
                    location.setId(locationEntity.getId());
                    location.setStreetAddress(locationEntity.getStreetAddress());
                    location.setPostalCode(locationEntity.getPostalCode());
                    location.setCity(locationEntity.getCity());
                    location.setStateProvince(locationEntity.getStateProvince());
                    location.setCountryName(locationEntity.getCountryName());
                    employee.setLocation(location);
                }
            }
            return employee;
        }).collect(Collectors.toUnmodifiableList());
    }

    public List<Employee> getAllEmployeeByName(String name) {
        return employeeRepository.findEmployeeByFirstName(name).stream().map(x -> {
            Employee employee = new Employee();
            employee.setId(x.getId());
            employee.setFirstName(x.getFirstName());
            employee.setMiddleName(x.getMiddleName());
            employee.setLastName(x.getLastName());
            employee.setEmail(x.getEmail());
            employee.setPhoneNumber(x.getPhoneNumber());
            employee.setJobId(x.getJobId());
            employee.setSalary(x.getSalary());

            DepartmentEntity dept = x.getDepartment();
            if(dept != null) {
                employee.setDepartmentName(dept.getDepartmentName());
                LocationEntity locationEntity  = dept.getLocation();
                if(locationEntity != null) {
                    Location location = new Location();
                    location.setId(locationEntity.getId());
                    location.setStreetAddress(locationEntity.getStreetAddress());
                    location.setPostalCode(locationEntity.getPostalCode());
                    location.setCity(locationEntity.getCity());
                    location.setStateProvince(locationEntity.getStateProvince());
                    location.setCountryName(locationEntity.getCountryName());
                    employee.setLocation(location);
                }
            }
            return employee;
        }).collect(Collectors.toUnmodifiableList());
    }
}
