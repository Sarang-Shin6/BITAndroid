package com.example.bit_android;

public class ClientJob {
    String StartDate, Firstname, LastName, JobDesc, JobCat, Status, JobID;

    public String getJobID() {
        return JobID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getJobCat() {
        return JobCat;
    }

    public String getJobDesc() {
        return JobDesc;
    }

    public String getLastName() {
        return LastName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setJobCat(String jobCat) {
        JobCat = jobCat;
    }

    public void setJobDesc(String jobDesc) {
        JobDesc = jobDesc;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
