package com.example.gymownerapp;

public class Profile_recycle {


    String dataImage;
    String gymName;
    String ownerName;
    String userAddr;
    String userEmail;
    String userName;
    String userPlan;
    String userphone;

    public Profile_recycle(String dataImage, String gymName, String ownerName, String userAddr, String userEmail, String userName, String userPlan, String userphone) {
        this.dataImage = dataImage;
        this.gymName = gymName;
        this.ownerName = ownerName;
        this.userAddr = userAddr;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPlan = userPlan;
        this.userphone = userphone;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getGymName() {
        return gymName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPlan() {
        return userPlan;
    }

    public String getUserphone() {
        return userphone;
    }

    public Profile_recycle() {
    }
}
