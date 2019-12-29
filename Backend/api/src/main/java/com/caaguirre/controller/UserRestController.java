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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
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

    @PostMapping(value = "/save")
    public ResponseEntity<User> save(@RequestBody User user){

        Person person = personService.get(user.getPerson().getId_person());
        if (person == null) {
            // Exception persona no registrada
            person = personService.save(user.getPerson());
        }
        Status status = statusService.get(user.getStatus().getId_status());
        if (status == null) {
            // Exception status no registrado
            status = statusService.save(user.getStatus());
        }
        Rol rol = rolService.get(user.getRol().getId_rol());
        if (rol == null) {
            //Exception rol no registrado
            rol = rolService.save(user.getRol());
        }

        if (user.getId_user() == null) {
            user.setId_user((long) 0);
        }
        user.setPerson(person);
        user.setStatus(status);
        user.setRol(rol);
        User obj = userService.save(user);
        return new ResponseEntity<User>(obj, HttpStatus.OK);
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
