package com.myfistapp.sunshine_app.Class;

import java.io.Serializable;
import java.util.ArrayList;

public class DonHang2 implements Serializable {
    private ArrayList<SanPhamMua> listSanPhamMua;
    private String pttt, trangThaiDonHang;
    private DiaChi diaChi;

    public DonHang2(ArrayList<SanPhamMua> listSanPhamMua, String pttt, String trangThaiDonHang, DiaChi diaChi) {
        this.listSanPhamMua = listSanPhamMua;
        this.pttt = pttt;
        this.trangThaiDonHang = trangThaiDonHang;
        this.diaChi = diaChi;
    }


    //lấy tổng tiền sản phẩm đầu tiên
    public String getFirtTongTien(ArrayList<SanPhamMua> listSanPhamMua) {
        float dongia = Float.parseFloat(listSanPhamMua.get(0).getGia());
        int soluong = listSanPhamMua.get(0).getSl();

        float tongtien = dongia*soluong;

        return String.valueOf(tongtien);
    }

    //lấy sản phẩm đầu tiên trong đơn hàng
    public SanPhamMua getFirtSanPhamMua(ArrayList<SanPhamMua> listSanPhamMua) {
        return listSanPhamMua.get(0);
    }

    //lấy tổng số lượng sản phẩm trong đơn hàng
    public int getTongSoLuongSanPham(ArrayList<SanPhamMua> listSanPhamMua) {
        return listSanPhamMua.size();
    }

    //lấy tổng tiền tất cả các sản phẩm
    public String getTongTien(ArrayList<SanPhamMua> listSanPhamMua) {
        float tongtien = 0;
        for (int i=0; i<listSanPhamMua.size(); i++) {
            float dongia = Float.parseFloat(listSanPhamMua.get(i).getGia());
            int soluong = listSanPhamMua.get(i).getSl();

            tongtien += dongia*soluong;
        }
        return String.valueOf(tongtien);
    }



    //get & set
    public ArrayList<SanPhamMua> getListSanPhamMua() {
        return listSanPhamMua;
    }

    public void setListSanPhamMua(ArrayList<SanPhamMua> listSanPhamMua) {
        this.listSanPhamMua = listSanPhamMua;
    }

    public String getPttt() {
        return pttt;
    }

    public void setPttt(String pttt) {
        this.pttt = pttt;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }
}
