package com.example.module03_basicgui_db_interface;



public class Person {
    private int id;
    private String firstName;
    private String dept;
    private String major;

    public Person(int id, String firstName, String dept, String major) {
        this.id = id;
        this.firstName = firstName;
        this.dept = dept;
        this.major = major;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
