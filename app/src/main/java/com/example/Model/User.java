package com.example.Model;

public class User {
    private String password;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private Boolean is_student;

    public User(String password, String username, String email, String first_name, String last_name, Boolean is_student) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_student = is_student;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Boolean getIs_student() {
        return is_student;
    }

    public void setIs_student(Boolean is_student) {
        this.is_student = is_student;
    }
}
