package com.example.gymownerapp;

public class UserPayments_recycle {
    private String dataImage;
    private String userName;
    private String userPhone;
    private String userDate;
    public UserPayments_recycle() {
        // Default constructor required for Firebase database
    }
    public UserPayments_recycle(String dataImage, String userName, String userPhone, String userDate) {
        this.dataImage = dataImage;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userDate = userDate;
    }
    public String getDataImage() {
        return dataImage;
    }
    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getUserDate() {
        return userDate;
    }
    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }
}
