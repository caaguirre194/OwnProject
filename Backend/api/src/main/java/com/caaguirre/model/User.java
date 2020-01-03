package com.caaguirre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="user", schema = "public")
public class User {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_user")
    @SequenceGenerator(name="sec_user", sequenceName = "sec_user", allocationSize=1)
    @Column(unique = true)
    private Long id_user;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Basic(optional = false)
    @XmlTransient
    //@com.fasterxml.jackson.annotation.JsonIgnore
    private String password;

    //@ManyToOne(cascade = CascadeType.ALL,targetEntity = Status.class)
    //@JoinColumn(name = "status", insertable = false, updatable = false,nullable = false)
    @JoinColumn(name = "status", referencedColumnName = "id_status")
    @ManyToOne(optional = false,cascade=CascadeType.PERSIST)
    //@JsonIgnore
    @XmlTransient
    private Status status;

    //@ManyToOne( cascade = CascadeType.ALL,targetEntity = Rol.class)
    //@JoinColumn(name="rol", insertable = false, updatable = false,nullable = false)
    @JoinColumn(name = "rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false,cascade=CascadeType.PERSIST)
    //@JsonIgnore
    @XmlTransient
    private Rol rol;

    //@ManyToOne( cascade = CascadeType.ALL,targetEntity = Person.class)
    //@JoinColumn(name="person", insertable = false, updatable = false,nullable = false)
    @JoinColumn(name = "person", referencedColumnName = "id_person")
    @ManyToOne(optional = false,cascade=CascadeType.PERSIST)
   // @JsonIgnore
   // @XmlTransient
    private Person person;

    public User() {
    }

    public User(Long id_user) {
        this.id_user = id_user;
    }

    public User(Long id_user, String email, String lastname, String username) {
        this.id_user = id_user;
        this.email = email;
        this.password = lastname;
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
