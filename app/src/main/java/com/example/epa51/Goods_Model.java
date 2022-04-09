package com.example.epa51;

public class Goods_Model implements java.io.Serializable{

    private String reference;
    private int quantite;
    private int poid;

    public Goods_Model(String reference,int quantite,int poid)
    {
        this.reference=reference;
        this.quantite=quantite;
        this.poid=poid;
    }


    public Goods_Model()
    {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    @Override
    public String toString() {
        return "Goods_Model{" +
                "reference='" + reference + '\'' +
                ", quantite=" + quantite +
                ", poid=" + poid +
                '}';
    }
}
