package com.humanbooster.basededonnees;

/**
 * Created by Nea on 26/10/2016.
 */

public class Contact {

    private Integer id;
    private String name;
    private String firstname;
    private String phone;

    public Contact() {
    }

    public Contact(String name, String firstname, String phone) {
        this.firstname = firstname;
        this.name = name;
        this.phone = phone;
    }

    public Contact(Integer id, String name, String firstname,String phone) {
        this.phone = phone;
        this.firstname = firstname;
        this.id = id;
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstname='" + firstname + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
