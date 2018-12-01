package com.example.jennifer.yhackstesting;

public class Patient {

    public String name;
    public String age;
    public String gfr;
    public String gender;
    public String[] meds;
    public String[] conditions;
    public String[] allergies;

    public Patient(String name, String age, String gfr, String gender, String[] meds, String[] conditions, String[] allergies) {
        this.name = name;
        this.age = age;
        this.gfr = gfr;
        this.gender = gender;
        this.meds = meds;
        this.conditions = conditions;
        this.allergies = allergies;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String[] getMeds() {
        return meds;
    }

    public String[] getConditions() {
        return conditions;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public String getGfr() {
        return gfr;
    }
}
