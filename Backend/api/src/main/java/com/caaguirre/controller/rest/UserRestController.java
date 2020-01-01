package com.caaguirre.controller.rest;

import com.caaguirre.model.exception.ApiException;
import com.caaguirre.model.Person;
import com.caaguirre.model.Rol;
import com.caaguirre.model.Status;
import com.caaguirre.model.User;
import com.caaguirre.model.exception.UserConstantException;
import com.caaguirre.service.*;
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

    @Autowired
    IMessageManagerService messageManagerService;

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Object find(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody User user) throws ApiException {

        if (user.getUsername() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_USERNAME));
        if (user.getPassword() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_PASSWORD));
        if (user.getEmail() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_EMAIL));

        if (user.getPerson() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_PERSON));
        Person person  = personService.get(user.getPerson().getId_person());
        if (person == null) {
            // Exception persona no registrada
            person = personService.save(user.getPerson());
        }

        if (user.getStatus() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_STATUS));
        Status status = statusService.get(user.getStatus().getId_status());
        if (status == null) {
            // Exception status no registrado
            status = statusService.save(user.getStatus());
        }

        if (user.getRol() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_ROL));
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

        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@RequestBody User user) throws ApiException {

        if (user.getUsername() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_USERNAME));
        if (user.getPassword() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_PASSWORD));
        if (user.getEmail() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, messageManagerService.getValue(UserConstantException.KEY_USER_NULL_EMAIL));

        Person person  = personService.get(user.getPerson().getId_person());
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

        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        User user = userService.get(id);
        if(user != null) {
            userService.delete(id);
        }else {
            return new ResponseEntity<Object>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(user,HttpStatus.OK);
    }

   /* @GetMapping(value = "/login")
    public ResponseEntity<Object> login(@PathVariable Login login){
        return new ResponseEntity<Object>(null,HttpStatus.OK);
    }*/

    @GetMapping(value = "/updatePassword")
    public ResponseEntity<Object> updatePassword(@PathVariable String password){
        return new ResponseEntity<Object>(null,HttpStatus.OK);
    }

    @GetMapping(value = "/restorePassword")
    public ResponseEntity<Object> restorePassword(@PathVariable Long id){
        return new ResponseEntity<Object>(null,HttpStatus.OK);
    }

}
