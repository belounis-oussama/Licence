package com.example.epa51;

public class Shift_Modele {


    private int id;
    private String nom_shift;
    private String Debut;
    private String Fin;


    public Shift_Modele(int id,String nom_shift,String Debut,String Fin)
    {

        this.id=id;
        this.nom_shift=nom_shift;
        this.Debut=Debut;
        this.Fin=Fin;
    }

    public Shift_Modele(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_shift() {
        return nom_shift;
    }

    public void setNom_shift(String nom_shift) {
        this.nom_shift = nom_shift;
    }

    public String getDebut() {
        return Debut;
    }

    public void setDebut(String debut) {
        Debut = debut;
    }

    public String getFin() {
        return Fin;
    }

    public void setFin(String fin) {
        Fin = fin;
    }

    @Override
    public String toString() {
        return "Shift_Modele{" +
                "id=" + id +
                ", nom_shift='" + nom_shift + '\'' +
                ", Debut='" + Debut + '\'' +
                ", Fin='" + Fin + '\'' +
                '}';
    }
}
