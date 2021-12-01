package com.myfistapp.sunshine_app.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.myfistapp.sunshine_app.Activity.SanPham;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class ReycyclerViewAdapterSearch extends RecyclerView.Adapter<ReycyclerViewAdapterSearch.ViewHolder> {

    ArrayList<SanPhamDomain> sanPhamDomains;
    Khachhang khachhang;
    public ReycyclerViewAdapterSearch(ArrayList<SanPhamDomain> SanPhamDomains, Khachhang mkhachhang) {
        this.sanPhamDomains =SanPhamDomains;
        this.khachhang=mkhachhang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_danhsach_sanpham_search, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttensanpham.setText(sanPhamDomains.get(position).getTensanpham());
        holder.txtgiasanpham.setText(sanPhamDomains.get(position).getGiasanpham());


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(sanPhamDomains.get(position).getAnhsanpham(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imgsanpham);

        holder.imgsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SanPham.class);
                intent.putExtra("object", sanPhamDomains.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamDomains.size();
    }

    public void filterList(ArrayList<SanPhamDomain> filterList, Khachhang khachhang1){
        sanPhamDomains = filterList;
        khachhang = khachhang1;
        notifyDataSetChanged();
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensanpham, txtgiasanpham;
        ImageView imgsanpham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensanpham = itemView.findViewById(R.id.txt_tensanpham);
            imgsanpham = itemView.findViewById(R.id.img_sanpham_giohang);
            txttensanpham = itemView.findViewById(R.id.txt_giasanpham);


        }
    }
}
