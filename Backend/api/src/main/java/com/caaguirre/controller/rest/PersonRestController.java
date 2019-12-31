package com.caaguirre.controller.rest;

import com.caaguirre.model.Person;
import com.caaguirre.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/persons")
public class PersonRestController {

    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/all")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Person find(@PathVariable Long id) {
        return personService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Person> save(@RequestBody Person person){
        if (person.getId_person() == null) {
            person.setId_person((long) 0);
        }
        Person obj = personService.save(person);
        return new ResponseEntity<Person>(obj, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Person> update(@RequestBody Person person){
        if (person.getId_person() == null) {
            person.setId_person((long) 0);
        }
        Person obj = personService.save(person);
        return new ResponseEntity<Person>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id){
        Person person = personService.get(id);
        if(person != null) {
            personService.delete(id);
        }else {
            return new ResponseEntity<Person>(person, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Person>(person,HttpStatus.OK);
    }

}
