package com.myfistapp.sunshine_app.Model;

public class Hoadon {

    private int idhd;
    private int idkh;
    private String hinhthucthanhtoan;

    @Override
    public String toString() {
        return "Hoadon{" +
                "idhd=" + idhd +
                ", idkh=" + idkh +
                ", hinhthucthanhtoan='" + hinhthucthanhtoan + '\'' +
                '}';
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }
}
