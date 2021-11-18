package com.myfistapp.sunshine_app.Model;

import com.google.gson.annotations.SerializedName;

public class Sanpham {


    private int IDSP;

    private int IDDM;

    private String TenSanPham;

    private String MoTaSanPham;

    private String Anh;

    private String GiaSanPham;

    private String Kcal;
    private String ThoiGianCheBien;

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public int getIDDM() {
        return IDDM;
    }

    public void setIDDM(int IDDM) {
        this.IDDM = IDDM;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getMoTaSanPham() {
        return MoTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        MoTaSanPham = moTaSanPham;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getKcal() {
        return Kcal;
    }

    public void setKcal(String kcal) {
        Kcal = kcal;
    }

    public String getThoiGianCheBien() {
        return ThoiGianCheBien;
    }

    public void setThoiGianCheBien(String thoiGianCheBien) {
        ThoiGianCheBien = thoiGianCheBien;
    }
}
