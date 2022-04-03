package com.example.epa51;


import java.util.ArrayList;

public class User_Model {
    public static ArrayList<User_Model> user_modelArrayList=new ArrayList<>();
    private int id;
    private String Complete_name;
    private String Password;

    //constructor
    public User_Model(int id, String complete_name, String password) {
        this.id = id;
        this.Complete_name = complete_name;
        this.Password = password;
    }
    //empty constructor
    public User_Model() {
    }


    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplete_name() {
        return Complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.Complete_name = complete_name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    //toString :basically it help when we need to print all data of one user

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", Complete_name='" + Complete_name + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
