package com.lego.geektask2.Utils;

/**
 * @author Lego on 08.08.2016.
 */

public class Person {

    public String title;
    public String labelOne;
    public String labelTwo;
    public String labelTree;
    public String labelFour;


    public Person(String title,String name, String lastName, String email, String phone) {
        this.title = title;
        this.labelOne = name;
        this.labelTwo = lastName;
        this.labelTree = email;
        this.labelFour = phone;
    }
}