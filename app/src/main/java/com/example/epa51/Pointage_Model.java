package com.example.epa51;

public class Pointage_Model {

    private int id;
    private String nom_pointeur;
    private String nom_navire;
    private String date;
    private String mode_conditionnement;
    private String nature_marchandise;
    private String brigade;
    private String shift;
    private String quai;



    public Pointage_Model(int id,String nom_pointeur,String nom_navire,String date,String mode_conditionnement,String nature_marchandise,String brigade, String shift, String quai)
    {

        this.id=id;
        this.nom_pointeur=nom_pointeur;
        this.nom_navire=nom_navire;
        this.date=date;
        this.mode_conditionnement=mode_conditionnement;
        this.nature_marchandise=nature_marchandise;
        this.brigade=brigade;
        this.shift=shift;
        this.quai=quai;

    }
    public Pointage_Model()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_pointeur() {
        return nom_pointeur;
    }

    public void setNom_pointeur(String nom_pointeur) {
        this.nom_pointeur = nom_pointeur;
    }

    public String getNom_navire() {
        return nom_navire;
    }

    public void setNom_navire(String nom_navire) {
        this.nom_navire = nom_navire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMode_conditionnement() {
        return mode_conditionnement;
    }

    public void setMode_conditionnement(String mode_conditionnement) {
        this.mode_conditionnement = mode_conditionnement;
    }

    public String getNature_marchandise() {
        return nature_marchandise;
    }

    public void setNature_marchandise(String nature_marchandise) {
        this.nature_marchandise = nature_marchandise;
    }

    public String getBrigade() {
        return brigade;
    }

    public void setBrigade(String brigade) {
        this.brigade = brigade;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getQuai() {
        return quai;
    }

    public void setQuai(String quai) {
        this.quai = quai;
    }

    @Override
    public String toString() {
        return "Pointage_Model{" +
                "id=" + id +
                ", nom_pointeur='" + nom_pointeur + '\'' +
                ", nom_navire='" + nom_navire + '\'' +
                ", date='" + date + '\'' +
                ", mode_conditionnement='" + mode_conditionnement + '\'' +
                ", nature_marchandise='" + nature_marchandise + '\'' +
                ", brigade='" + brigade + '\'' +
                ", shift='" + shift + '\'' +
                ", quai='" + quai + '\'' +
                '}';
    }
}
