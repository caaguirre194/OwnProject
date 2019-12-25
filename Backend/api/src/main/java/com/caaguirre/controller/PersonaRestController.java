package com.caaguirre.controller;

import com.caaguirre.model.Persona;
import com.caaguirre.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/")
public class PersonaRestController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = "/all")
    public List<Persona> getAll(){
        return personaService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Persona find(@PathVariable Long id) {
        return personaService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Persona> save(@RequestBody Persona persona){
        Persona obj = personaService.save(persona);
        return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id){
        Persona persona = personaService.get(id);
        if(persona != null) {
            personaService.delete(id);
        }else{
            return new ResponseEntity<Persona>(persona,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Persona>(persona,HttpStatus.OK);
    }

}
