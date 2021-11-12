package com.myfistapp.sunshine_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfistapp.sunshine_app.Class.SanPhamMua;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ChiTietDonHangHolder>{

    private Context context;
    private ArrayList<SanPhamMua> mList;

    public ChiTietDonHangAdapter(Context context) {
        this.context = context;
    }

    public void setData (ArrayList<SanPhamMua> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChiTietDonHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitietdonhang, parent, false);

        return new ChiTietDonHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangHolder holder, int position) {
        SanPhamMua sanPhamMua = mList.get(position);
        if(sanPhamMua == null) {
            return;
        }

        holder.img_image.setImageResource(sanPhamMua.getImage());
        holder.tv_ten.setText(sanPhamMua.getTen());
        holder.tv_sl.setText("x" + sanPhamMua.getSl() + "");
        holder.tv_gia.setText(sanPhamMua.getGia());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class ChiTietDonHangHolder extends RecyclerView.ViewHolder {

        private ImageView img_image;
        private TextView tv_ten, tv_sl, tv_gia;
        public ChiTietDonHangHolder(@NonNull View itemView) {
            super(itemView);

            img_image = itemView.findViewById(R.id.img_ctdh_image);
            tv_gia = itemView.findViewById(R.id.tv_ctdh_gia);
            tv_sl = itemView.findViewById(R.id.tv_ctdh_sl);
            tv_ten = itemView.findViewById(R.id.tv_ctdh_ten);
        }
    }
}
