package com.caaguirre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="person", schema = "public")
public class Person {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_rol", unique = true, nullable = false)
    @Column(unique = true)
    private Long id_person;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String address;

    @Column
    private String phone;

    @JsonIgnore
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<User> userList;

    public Person() {
    }

    public Person(Long id_person) {
        this.id_person = id_person;
    }

    public Person(Long id_person, String name, String lastname, String address, String phone) {
        this.id_person = id_person;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getId_person() {
        return id_person;
    }

    public void setId_person(Long id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
