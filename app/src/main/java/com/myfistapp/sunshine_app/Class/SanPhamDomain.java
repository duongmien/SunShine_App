package com.myfistapp.sunshine_app.Class;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPhamDomain implements Serializable {



    private int idsp;

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

    private int iddm;
    @SerializedName("tensanpham")
    private String tensanpham;
    @SerializedName("motasanpham")
    private String thongtinsanpham;
    @SerializedName("anh")
    private String anhsanpham;
    @SerializedName("giasanpham")
    private String giasanpham;
    private String kcal;
    private String thoigian;
    private int soluongdathang;
    private String danhgiasanpham;


    public SanPhamDomain(String tensanpham, String anhsanpham, String thongtinsanpham, String thoigian, String kcal, String giasanpham, String danhgiasanpham) {
        this.tensanpham = tensanpham;
        this.anhsanpham = anhsanpham;
        this.thongtinsanpham = thongtinsanpham;
        this.thoigian = thoigian;
        this.kcal = kcal;
        this.giasanpham = giasanpham;
        this.danhgiasanpham = danhgiasanpham;
    }

    public SanPhamDomain(String tensanpham, String anhsanpham, String thongtinsanpham, String thoigian, String kcal, String giasanpham, int soluongsanpham, String danhgiasanpham) {
        this.tensanpham = tensanpham;
        this.anhsanpham = anhsanpham;
        this.thongtinsanpham = thongtinsanpham;
        this.thoigian = thoigian;
        this.kcal = kcal;
        this.giasanpham = giasanpham;
        this.soluongdathang = soluongsanpham;
        this.danhgiasanpham = danhgiasanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public String getThongtinsanpham() {
        return thongtinsanpham;
    }

    public void setThongtinsanpham(String thongtinsanpham) {
        this.thongtinsanpham = thongtinsanpham;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getDanhgiasanpham() {
        return danhgiasanpham;
    }

    public int getSoluongdathang() {
        return soluongdathang;
    }

    public void setSoluongdathang(int soluongdathang) {
        this.soluongdathang = soluongdathang;
    }

    public void setDanhgiasanpham(String danhgiasanpham) {
        this.danhgiasanpham = danhgiasanpham;
    }
}
