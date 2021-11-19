package com.myfistapp.sunshine_app.Model;

import com.google.gson.annotations.SerializedName;

public class KhachHang {
    @SerializedName("IDKH")
    private int idkh;
    @SerializedName("TenDangNhap")
    private String tendangnhap;
    @SerializedName("MatKhau")
    private String matkhau;
    @SerializedName("HoVaTen")
    private String hovaten;
    @SerializedName("SDT")
    private String sdt;
    @SerializedName("GioiTinh")
    private String gioitinh;
    @SerializedName("NgaySinh")
    private String ngaysinh;
    @SerializedName("Email")
    private String email;

    public KhachHang( String tendangnhap, String matkhau, String hovaten, String email) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hovaten = hovaten;
        this.email = email;
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
