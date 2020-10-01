package com.ttolivet.usmolivet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("libequipe")
    private String teamWording;
    @JsonProperty("libdivision")
    private String divisionWording;
    @JsonProperty("liendivision")
    private String lienDivision;

    @JsonIgnore
    @OneToMany(mappedBy = "poule", cascade = CascadeType.REFRESH)
    private List<Team> teams;

    public Poule() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamWording() {
        return teamWording;
    }

    public void setTeamWording(String teamWording) {
        this.teamWording = teamWording;
    }

    public String getDivisionWording() {
        return divisionWording;
    }

    public void setDivisionWording(String divisionWording) {
        this.divisionWording = divisionWording;
    }

    public String getLienDivision() {
        return lienDivision;
    }

    public void setLienDivision(String lienDivision) {
        this.lienDivision = lienDivision;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
