package com.myfistapp.sunshine_app.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPhamDomain implements Serializable {


    private int idsp;
    private int iddm;
    private String tensanpham;
    @SerializedName("motasanpham")
    private String thongtinsanpham;
    private String anhsanpham;
    private String giasanpham;
    private String kcal;
    @SerializedName("thoigianchebien")
    private String thoigian;
    private int soluongdathang;
    private String danhgiasanpham="5";


    public SanPhamDomain(String tensanpham, String anhsanpham, String thongtinsanpham, String thoigian, String kcal, String giasanpham, String danhgiasanpham) {
        this.tensanpham = tensanpham;
        this.anhsanpham = anhsanpham;
        this.thongtinsanpham = thongtinsanpham;
        this.thoigian = thoigian;
        this.kcal = kcal;
        this.giasanpham = giasanpham;
        this.danhgiasanpham = danhgiasanpham;
    }


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

    public int getSoluongdathang() {
        return soluongdathang;
    }

    public void setSoluongdathang(int soluongdathang) {
        this.soluongdathang = soluongdathang;
    }

    public String getDanhgiasanpham() {
        return danhgiasanpham;
    }

    public void setDanhgiasanpham(String danhgiasanpham) {
        this.danhgiasanpham = danhgiasanpham;
    }
}
