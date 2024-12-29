package com.example.gymownerapp;

public class Attendance_recycle {
    String dataImage;
    String userName;
    String userPlan;
    String userphone;
    String  status;

    public Attendance_recycle(String dataImage, String userName, String userPlan, String userphone,String status) {
        this.dataImage = dataImage;
        this.userName = userName;
        this.userPlan = userPlan;
        this.userphone = userphone;
        this.status = status;
        //status="";
    }

    public String getDataImage() {  return dataImage;   }
    public String getUserName() {   return userName;    }
    public String getUserPlan() {   return userPlan;    }
    public String getUserphone() {  return userphone;   }
    public String getStatus()   {     return status != null ? status : "";      }
    public void setStatus(String status) {
        this.status = status;
    }

    public Attendance_recycle(){

    }

}
