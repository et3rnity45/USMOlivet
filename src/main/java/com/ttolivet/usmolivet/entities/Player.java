package com.ttolivet.usmolivet.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licence;
    @JsonProperty("prenom")
    private String firstname;
    @JsonProperty("nom")
    private String lastname;
    private String club;
    @JsonProperty("nclub")
    private int numeroClub;
    @JsonProperty("clast")
    private String classementOfficiel;
    @JsonProperty("natio")
    private String nationalite;
    @JsonProperty("clglob")
    private int classementGlobal;
    @JsonProperty("point")
    private double points;
    @JsonProperty("aclglob")
    private int ancienClassementGlobal;
    @JsonProperty("apoint")
    private double ancienPoints;
    @JsonProperty("categ")
    private String categorie;
    @JsonProperty ("clnat")
    private int rangNational;
    @JsonProperty("rangreg")
    private int rangRegional;
    @JsonProperty("rangdep")
    private int rangDepartemental;
    @JsonProperty("valcla")
    private int pointsOfficiels;
    @JsonProperty("valinit")
    private double pointsDebutSaison;

    public Player() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
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

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getNumeroClub() {
        return numeroClub;
    }

    public void setNumeroClub(int numeroClub) {
        this.numeroClub = numeroClub;
    }

    public String getClassementOfficiel() {
        return classementOfficiel;
    }

    public void setClassementOfficiel(String classementOfficiel) {
        this.classementOfficiel = classementOfficiel;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getClassementGlobal() {
        return classementGlobal;
    }

    public void setClassementGlobal(int classementGlobal) {
        this.classementGlobal = classementGlobal;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getAncienClassementGlobal() {
        return ancienClassementGlobal;
    }

    public void setAncienClassementGlobal(int ancienClassementGlobal) {
        this.ancienClassementGlobal = ancienClassementGlobal;
    }

    public double getAncienPoints() {
        return ancienPoints;
    }

    public void setAncienPoints(double ancienPoints) {
        this.ancienPoints = ancienPoints;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getRangNational() {
        return rangNational;
    }

    public void setRangNational(int rangNational) {
        this.rangNational = rangNational;
    }

    public int getRangRegional() {
        return rangRegional;
    }

    public void setRangRegional(int rangRegional) {
        this.rangRegional = rangRegional;
    }

    public int getRangDepartemental() {
        return rangDepartemental;
    }

    public void setRangDepartemental(int rangDepartemental) {
        this.rangDepartemental = rangDepartemental;
    }

    public int getPointsOfficiels() {
        return pointsOfficiels;
    }

    public void setPointsOfficiels(int pointsOfficiels) {
        this.pointsOfficiels = pointsOfficiels;
    }

    public double getPointsDebutSaison() {
        return pointsDebutSaison;
    }

    public void setPointsDebutSaison(double pointsDebutSaison) {
        this.pointsDebutSaison = pointsDebutSaison;
    }
}
