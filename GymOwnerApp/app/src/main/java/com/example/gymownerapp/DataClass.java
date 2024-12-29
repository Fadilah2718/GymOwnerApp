package com.example.gymownerapp;

public class DataClass {
        private String dataImage;
        private String ownerName;
        private String gymName;
        private String userName;
        private String userEmail;

        private String userphone;
        private String userAddr;
        private String userPlan;
        private String userDate;

    public String getUserDate() {
        return userDate;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getGymName() {
        return gymName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserphone() {
        return userphone;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public String getUserPlan() {
        return userPlan;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public void setUserPlan(String userPlan) {
        this.userPlan = userPlan;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public DataClass(String dataImage, String ownerName, String gymName, String userName, String userEmail, String userphone, String userAddr, String userPlan , String userdate) {
        this.dataImage = dataImage;
        this.ownerName = ownerName;
        this.gymName = gymName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userphone = userphone;
        this.userAddr = userAddr;
        this.userPlan = userPlan;
        this.userDate=userdate;
    }

    public DataClass(){
    }
}

