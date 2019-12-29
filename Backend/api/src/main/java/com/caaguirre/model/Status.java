package com.caaguirre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name="status", schema = "public")
public class Status {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "status", unique = true, nullable = false)
    @Column(unique = true)
    private Long id_status;

    @Column
    private String name;

    @Column
    private String description;

    @JsonIgnore
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<User> userList;

    public Status() {
    }

    public Status(Long id_status) {
        this.id_status = id_status;
    }

    public Status(Long id_status, String name, String description) {
        this.id_status = id_status;
        this.name = name;
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getId_status() {
        return id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
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
