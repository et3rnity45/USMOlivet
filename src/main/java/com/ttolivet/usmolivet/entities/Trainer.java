package com.ttolivet.usmolivet.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Veuillez entre un prénom !")
    private String firstname;
    @NotBlank(message = "Veuillez entrer un nom !")
    private String lastname;

    private String picturePath;

    @NotBlank(message = "Veuillez entrer du contenu !")
    @Column(columnDefinition = "TEXT")
    private String informations;

    public Trainer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }
}
