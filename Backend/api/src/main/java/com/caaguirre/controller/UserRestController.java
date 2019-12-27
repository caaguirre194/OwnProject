package com.caaguirre.controller;

import com.caaguirre.model.Person;
import com.caaguirre.model.Rol;
import com.caaguirre.model.Status;
import com.caaguirre.model.User;
import com.caaguirre.service.IPersonService;
import com.caaguirre.service.IRolService;
import com.caaguirre.service.IStatusService;
import com.caaguirre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IStatusService statusService;


    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public User find(@PathVariable Long id) {
        return userService.get(id);
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

        Person person = personService.get(user.getPerson().getId_person());
        if (person == null) {
            person = personService.save(user.getPerson());
        }
        Status status = statusService.get(user.getStatus().getId_status());
        if (status == null) {
            status = statusService.save(user.getStatus());
        }
        Rol rol = rolService.get(user.getRol().getId_rol());
        if (rol == null) {
            rol = rolService.save(user.getRol());
        }
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);

       /* User obj = personaService.get(user.getId_user());

        if (obj == null) {
            obj = personaService.save(saveUser(user));
        }else{
            obj = personaService.save(user);
        }
        return new ResponseEntity<User>(obj, HttpStatus.OK);*/
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        User user = userService.get(id);
        if(user != null) {
            userService.delete(id);
        }else {
            return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

}
