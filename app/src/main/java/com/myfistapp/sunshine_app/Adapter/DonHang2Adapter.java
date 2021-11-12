package com.myfistapp.sunshine_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfistapp.sunshine_app.Class.DonHang2;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class DonHang2Adapter extends RecyclerView.Adapter<DonHang2Adapter.DonHang2Holder> {

    private Context context;
    private ArrayList<DonHang2> mList;
    private LSMHClickItem lsmhClickItem;

    public DonHang2Adapter(Context context, LSMHClickItem lsmhClickItem) {
        this.context = context;
        this.lsmhClickItem = lsmhClickItem;
    }

    public void setData(ArrayList<DonHang2> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DonHang2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsumuahang, parent, false);

        return new DonHang2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHang2Holder holder, int position) {
        DonHang2 donHang2 = mList.get(position);

        if(donHang2 == null) {
            return;
        }

        holder.tv_trangthaidonhang.setText(donHang2.getTrangThaiDonHang());
        holder.tv_tongtientatca.setText(donHang2.getTongTien(donHang2.getListSanPhamMua()) + "đ");
        holder.tv_tongsosanpham.setText(donHang2.getTongSoLuongSanPham(donHang2.getListSanPhamMua()) + "sản phẩm");
        holder.tv_soluong.setText("x" + donHang2.getFirtSanPhamMua(donHang2.getListSanPhamMua()).getSl() + "");
        holder.tv_tensp.setText(donHang2.getFirtSanPhamMua(donHang2.getListSanPhamMua()).getTen());
        holder.tv_dongia.setText(donHang2.getFirtSanPhamMua(donHang2.getListSanPhamMua()).getGia() + "đ");
        holder.img_image.setImageResource(donHang2.getFirtSanPhamMua(donHang2.getListSanPhamMua()).getImage());

        holder.lo_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsmhClickItem.onItemClick(donHang2);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class DonHang2Holder extends RecyclerView.ViewHolder{

        private LinearLayout lo_item;
        private ImageView img_image;
        private TextView tv_tensp, tv_soluong, tv_dongia, tv_tongsosanpham, tv_tongtientatca, tv_trangthaidonhang;
        public DonHang2Holder(@NonNull View itemView) {
            super(itemView);

            img_image = itemView.findViewById(R.id.img_lsmh_image);
            tv_tensp = itemView.findViewById(R.id.tv_lsmh_ten);
            tv_dongia = itemView.findViewById(R.id.tv_lsmh_gia);
            tv_soluong = itemView.findViewById(R.id.tv_lsmh_sl);
            tv_tongsosanpham = itemView.findViewById(R.id.tv_lsmh_tongsp);
            tv_tongtientatca = itemView.findViewById(R.id.tv_lsmh_tongtien);
            tv_trangthaidonhang = itemView.findViewById(R.id.tv_lsmh_trangthaidonhang);
            lo_item = itemView.findViewById(R.id.lo_lsmh_item);
        }
    }

    public interface LSMHClickItem {
        public void onItemClick (DonHang2 donHang2);
    }
}
