package com.example.gymownerapp;

public class AddPlan {

    String planname;
    String planprice;
    String planduration;

    public AddPlan(){}
    public AddPlan(String planname, String planprice, String planduration) {
        this.planname = planname;
        this.planprice = planprice;
        this.planduration = planduration;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getPlanprice() {
        return planprice;
    }

    public void setPlanprice(String planprice) {
        this.planprice = planprice;
    }

    public String getPlanduration() {
        return planduration;
    }

    public void setPlanduration(String planduration) {
        this.planduration = planduration;
    }
}
