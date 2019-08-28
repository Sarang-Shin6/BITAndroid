package com.example.bit_android;

public class ConJob {
    private String JobID, DueDate, CompanyName, ClientFirst, ClientLast, Phone, Branch, Street, Suburb, State, Postcode, jobdesc, jobcat, status, loggedkm, duration;
    private String ClientName, Address;

    public void GetFullNameAddress(){
        setClientName(getClientFirst() + " " + getClientLast());
        setAddress(getStreet() + " " + getSuburb() + ", " + getState() + " " + getPostcode());
    }

    public String getStatus() {
        return status;
    }

    public String getBranch() {
        return Branch;
    }

    public String getClientFirst() {
        return ClientFirst;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getClientLast() {
        return ClientLast;
    }

    public String getDuration() {
        return duration;
    }

    public String getJobcat() {
        return jobcat;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public String getPhone() {
        return Phone;
    }

    public String getState() {
        return State;
    }

    public String getLoggedkm() {
        return loggedkm;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getStreet() {
        return Street;
    }

    public String getSuburb() {
        return Suburb;
    }

    public void setClientFirst(String clientFirst) {
        ClientFirst = clientFirst;
    }

    public void setClientLast(String clientLast) {
        ClientLast = clientLast;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setJobcat(String jobcat) {
        this.jobcat = jobcat;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public void setLoggedkm(String loggedkm) {
        this.loggedkm = loggedkm;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public void setState(String state) {
        State = state;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public void setSuburb(String suburb) {
        Suburb = suburb;
    }

    public String getAddress() {
        return Address;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getDueDate() {
        return DueDate;
    }

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }
}
