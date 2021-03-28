package com.sarmale.myleads.model;

public class Lead {


    private int id;
    private String name;
    private String lastName;
    private String phone;
    private String email;


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString()
    {
        return "Name: "+this.name +"\nLast Name: "+this.lastName+"\nPhone: "+this.phone+"\nEmail: "+this.phone;
    }



}
