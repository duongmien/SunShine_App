package com.myfistapp.sunshine_app.Model;

import com.google.gson.annotations.SerializedName;

public class Sanpham {
    private int idsp;
    private int iddm;
    @SerializedName("tensp")
    private String tensanpham;
    @SerializedName("motasp")
    private String thongtinsanpham;
    @SerializedName("anhsp")
    private String anhsanpham;
    @SerializedName("gia")
    private String giasanpham;
    private String kcal;
    private String thoigian;

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getIddm() {
        return iddm;
    }

    public void setIddm(int iddm) {
        this.iddm = iddm;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getThongtinsanpham() {
        return thongtinsanpham;
    }

    public void setThongtinsanpham(String thongtinsanpham) {
        this.thongtinsanpham = thongtinsanpham;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
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
}
