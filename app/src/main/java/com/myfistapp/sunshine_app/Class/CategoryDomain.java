package com.myfistapp.sunshine_app.Class;

public class CategoryDomain {
    private int iddm;
    private String tendm;
    private String anhdm;


    public int getIddm() {
        return iddm;
    }

    public void setIddm(int iddm) {
        this.iddm = iddm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public String getAnhdm() {
        return anhdm;
    }

    public void setAnhdm(String anhdm) {
        this.anhdm = anhdm;
    }

    public CategoryDomain(int iddm, String tendm, String anhdm) {
        this.iddm = iddm;
        this.tendm = tendm;
        this.anhdm = anhdm;
    }
}
