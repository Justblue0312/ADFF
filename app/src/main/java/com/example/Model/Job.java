package com.example.Model;

import java.io.Serializable;

public class Job implements Serializable{
    private String title;
    private String job_image;
    private String author;
    private String company_name;
    private String company_location;
    private String company_phone;
    private String company_email;
    private String jobs_desc;
    private int jobs_required;
    private boolean archived;

    public Job(String title, String job_image, String author, String company_name, String company_location, String company_phone, String company_email, String jobs_desc, int jobs_required, boolean archived) {
        this.title = title;
        this.job_image = job_image;
        this.author = author;
        this.company_name = company_name;
        this.company_location = company_location;
        this.company_phone = company_phone;
        this.company_email = company_email;
        this.jobs_desc = jobs_desc;
        this.jobs_required = jobs_required;
        this.archived = archived;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob_image() {
        return job_image;
    }

    public void setJob_image(String job_image) {
        this.job_image = job_image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getJobs_desc() {
        return jobs_desc;
    }

    public void setJobs_desc(String jobs_desc) {
        this.jobs_desc = jobs_desc;
    }

    public int getJobs_required() {
        return jobs_required;
    }

    public void setJobs_required(int jobs_required) {
        this.jobs_required = jobs_required;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
