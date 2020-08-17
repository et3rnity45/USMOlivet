package com.ttolivet.usmolivet.entities;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Veuillez entrer un titre !")
    @Size(max = 80, message = "Le titre est trop long (80 caractères max)")
    private String title;

    @NotBlank(message = "Veuillez entrer un label !")
    @Size(max = 30, message = "Le label est trop long (30 caractères max)")
    private String label;

    private String picturePath;

    private Date date;

    @NotBlank(message = "Veuillez entrer du contenu !")
    @Column(columnDefinition = "TEXT")
    private String content = "Rédigez-ici ...";

    public Article() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
