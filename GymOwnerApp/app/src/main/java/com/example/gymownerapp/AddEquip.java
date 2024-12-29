package com.example.gymownerapp;

public class AddEquip {

    String equipname;
    String equipprice;
    String equipduration;

    public AddEquip(){}

    public AddEquip(String equipname, String equipprice, String equipduration) {
        this.equipname = equipname;
        this.equipprice = equipprice;
        this.equipduration = equipduration;
    }

    public String getEquipname() {
        return equipname;
    }

    public String getEquipprice() {
        return equipprice;
    }

    public String getEquipduration() {
        return equipduration;
    }

    public void setEquipname(String equipname) {
        this.equipname = equipname;
    }

    public void setEquipprice(String equipprice) {
        this.equipprice = equipprice;
    }

    public void setEquipduration(String equipduration) {
        this.equipduration = equipduration;
    }
}