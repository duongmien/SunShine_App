package com.myfistapp.sunshine_app.Class;

import java.io.Serializable;

public class DiaChi implements Serializable {
    private String ten, sdt, sonha, xa, huyen, tinh;

    public DiaChi(String ten, String sdt, String sonha, String xa, String huyen, String tinh) {
        this.ten = ten;
        this.sdt = sdt;
        this.sonha = sonha;
        this.xa = xa;
        this.huyen = huyen;
        this.tinh = tinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSonha() {
        return sonha;
    }

    public void setSonha(String sonha) {
        this.sonha = sonha;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }
}