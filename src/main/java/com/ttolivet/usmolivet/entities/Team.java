package com.ttolivet.usmolivet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("clt")
    private String classement;
    @JsonProperty("equipe")
    private String name;
    @JsonProperty("joue")
    private int rencontres;
    @JsonProperty("pts")
    private int points;
    @JsonProperty("numero")
    private int clubNumber;
    @JsonProperty("vic")
    private int win;
    @JsonProperty("def")
    private int lose;
    @JsonProperty("nul")
    private int draw;
    @JsonProperty("pf")
    private int penalities;
    @JsonProperty("pg")
    private int winEncounter;
    @JsonProperty("pp")
    private int loseEncounter;
    @JsonProperty("poule")
    private String pouleName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "poule_id")
    private Poule poule;

    public Team() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRencontres() {
        return rencontres;
    }

    public void setRencontres(int rencontres) {
        this.rencontres = rencontres;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getClubNumber() {
        return clubNumber;
    }

    public void setClubNumber(int clubNumber) {
        this.clubNumber = clubNumber;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPenalities() {
        return penalities;
    }

    public void setPenalities(int penalities) {
        this.penalities = penalities;
    }

    public int getWinEncounter() {
        return winEncounter;
    }

    public void setWinEncounter(int winEncounter) {
        this.winEncounter = winEncounter;
    }

    public int getLoseEncounter() {
        return loseEncounter;
    }

    public void setLoseEncounter(int loseEncounter) {
        this.loseEncounter = loseEncounter;
    }

    public Poule getPoule() {
        return poule;
    }

    public void setPoule(Poule poule) {
        this.poule = poule;
    }

    public String getPouleName() {
        return pouleName;
    }

    public void setPouleName(String pouleName) {
        this.pouleName = pouleName;
    }
}
