package com.myfistapp.sunshine_app.Model;

public class Chitiethoadon {
    private int idhd;
    private int idsp;
    private int soluong;

    @Override
    public String toString() {
        return "Chitiethoadon{" +
                "idhd=" + idhd +
                ", idsp=" + idsp +
                ", soluong=" + soluong +
                '}';
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
