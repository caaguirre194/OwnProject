package com.caaguirre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="rol", schema = "public")
public class Rol {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_rol", unique = true, nullable = false)
    @Column(unique = true)
    private Long id_rol;

    @Column
    private String name;

    @Column
    private String description;


    @JsonIgnore
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<User> userList;

    public Rol() {
    }

    public Rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public Rol(Long id_rol, String name, String description) {
        this.id_rol = id_rol;
        this.name = name;
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
