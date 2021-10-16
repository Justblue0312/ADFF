package com.example.Model;

import java.io.Serializable;

public class Application implements Serializable {
    private String jobpost;
    private String applicant;
    private String is_checked;
    private boolean is_approved;
    private String description;

    public Application(String jobpost, String applicant, String is_checked, boolean is_approved, String description) {
        this.jobpost = jobpost;
        this.applicant = applicant;
        this.is_checked = is_checked;
        this.is_approved = is_approved;
        this.description = description;
    }

    public String getJobpost() {
        return jobpost;
    }

    public void setJobpost(String jobpost) {
        this.jobpost = jobpost;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
