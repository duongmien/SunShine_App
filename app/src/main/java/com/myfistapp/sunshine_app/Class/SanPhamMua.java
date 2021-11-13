package com.myfistapp.sunshine_app.Class;

import java.io.Serializable;

public class SanPhamMua implements Serializable {
    private String idsp, ten, mota, gia, kcal, thoigian, iddm;
    private int image, sl;

    public SanPhamMua(String idsp, String ten, String mota, String gia, String kcal, String thoigian, String iddm, int image, int sl) {
        this.idsp = idsp;
        this.ten = ten;
        this.mota = mota;
        this.gia = gia;
        this.kcal = kcal;
        this.thoigian = thoigian;
        this.iddm = iddm;
        this.image = image;
        this.sl = sl;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getIddm() {
        return iddm;
    }

    public void setIddm(String iddm) {
        this.iddm = iddm;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}

