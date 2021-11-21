package com.myfistapp.sunshine_app.Model;

import java.io.Serializable;

public class Khachhang implements Serializable {
    private int idkh;
    private String tendangnhap;
    private String matkhau;
    private String hovaten;
    private String sdt;
    private String gioitinh;
    private String ngaysinh;
    private String email;

    public Khachhang( String tendangnhap, String matkhau, String hovaten, String email) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hovaten = hovaten;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Khachhang{" +
                "idkh=" + idkh +
                ", tendangnhap='" + tendangnhap + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", hovaten='" + hovaten + '\'' +
                ", sdt='" + sdt + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
