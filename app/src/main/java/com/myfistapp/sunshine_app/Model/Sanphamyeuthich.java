package com.myfistapp.sunshine_app.Model;

public class Sanphamyeuthich {
    private int idkh;
    private int idsp;

    public Sanphamyeuthich(int idkh, int idsp) {
        this.idkh = idkh;
        this.idsp = idsp;
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }
}
