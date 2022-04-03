package com.example.epa51;

import java.util.ArrayList;

public class Gear_Model {

    private int id;
    private String gear_type;
    private int gear_number;
    public static ArrayList<Gear_Model>gear_modelArrayList=new ArrayList<>();

    public Gear_Model(int id, String gear_type, int gear_number) {
        this.id = id;
        this.gear_type = gear_type;
        this.gear_number = gear_number;
    }

    public Gear_Model()
    {

    }

    public int getId() {
        return id;
    }

    public String getGear_type() {
        return gear_type;
    }

    public int getGear_number() {
        return gear_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGear_type(String gear_type) {
        this.gear_type = gear_type;
    }

    public void setGear_number(int gear_number) {
        this.gear_number = gear_number;
    }

    @Override
    public String toString() {
        return "Gear_Model{" +
                "id=" + id +
                ", gear_type='" + gear_type + '\'' +
                ", gear_number=" + gear_number +
                '}';
    }
}
