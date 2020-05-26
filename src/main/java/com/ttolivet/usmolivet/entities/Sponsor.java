package com.ttolivet.usmolivet.entities;

import javax.persistence.*;

@Entity
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70)
    private String name;

    @Column(length = 100)
    private String picturePath;

    @Column(length = 100)
    private String picturePathBis;

    @Column(length = 100)
    private String siteUrl;

    public Sponsor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPicturePathBis() {
        return picturePathBis;
    }

    public void setPicturePathBis(String picturePathBis) {
        this.picturePathBis = picturePathBis;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
