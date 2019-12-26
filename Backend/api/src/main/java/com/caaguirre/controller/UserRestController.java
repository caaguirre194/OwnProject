package com.caaguirre.controller;

import com.caaguirre.model.Person;
import com.caaguirre.model.Rol;
import com.caaguirre.model.Status;
import com.caaguirre.model.User;
import com.caaguirre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/")
public class UserRestController {

    @Autowired
    private IUserService personaService;

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return personaService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public User find(@PathVariable Long id) {
        return personaService.get(id);
    }

    public User saveUser(User user) {
        User newUser = new User();
        newUser.setId_user((long) 2);
        newUser.setEmail("new email");
        Rol rol = new Rol();
        rol.setId_rol((long) 1);
        rol.setName("ACTIVE");
        rol.setDescription("El usuario se encuentra actualmente habilitado.");
        newUser.setRol(rol);
        Person person = new Person();
        person.setId_person((long)1);
        person.setLastname("new lastname");
        person.setName("new name");
        person.setAddress("new address");
        person.setPhone("new phone");
        newUser.setPerson(person);
        newUser.setUsername("new username");
        newUser.setPassword("new password");
        Status status = new Status();
        status.setId_status((long) 1);
        status.setName("READONLY");
        status.setDescription("Rol de solo lectura.");
        newUser.setStatus(status);
        return newUser;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<User> save(@RequestBody User user){

        User obj = personaService.get(user.getId_user());

        if (obj == null) {
            obj = personaService.save(saveUser(user));
        }else{
            obj = personaService.save(user);
        }
        return new ResponseEntity<User>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        User user = personaService.get(id);
        if(user != null) {
            personaService.delete(id);
        }else {
            return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

}
