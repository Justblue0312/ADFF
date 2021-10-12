package com.example.Model;


import java.io.Serializable;

public class Student implements Serializable {
    private int user;
    private String email;
    private String username;
    private String location;
    private String short_intro;
    private String bio;
    private String profile_image;
    private String social_github;
    private String social_twitter;
    private String social_linkedin;
    private String social_youtube;
    private String social_website;
    private String id;
    private String token;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShort_intro() {
        return short_intro;
    }

    public void setShort_intro(String short_intro) {
        this.short_intro = short_intro;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getSocial_github() {
        return social_github;
    }

    public void setSocial_github(String social_github) {
        this.social_github = social_github;
    }

    public String getSocial_twitter() {
        return social_twitter;
    }

    public void setSocial_twitter(String social_twitter) {
        this.social_twitter = social_twitter;
    }

    public String getSocial_linkedin() {
        return social_linkedin;
    }

    public void setSocial_linkedin(String social_linkedin) {
        this.social_linkedin = social_linkedin;
    }

    public String getSocial_youtube() {
        return social_youtube;
    }

    public void setSocial_youtube(String social_youtube) {
        this.social_youtube = social_youtube;
    }

    public String getSocial_website() {
        return social_website;
    }

    public void setSocial_website(String social_website) {
        this.social_website = social_website;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user=" + user +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", short_intro='" + short_intro + '\'' +
                ", bio='" + bio + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", social_github='" + social_github + '\'' +
                ", social_twitter='" + social_twitter + '\'' +
                ", social_linkedin='" + social_linkedin + '\'' +
                ", social_youtube='" + social_youtube + '\'' +
                ", social_website='" + social_website + '\'' +
                ", id='" + id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
