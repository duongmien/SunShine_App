package com.myfistapp.sunshine_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class ReycyclerViewAdapter extends RecyclerView.Adapter<ReycyclerViewAdapter.ViewHolder> {

    ArrayList<SanPhamDomain> sanPhamDomains;
    public ReycyclerViewAdapter(ArrayList<SanPhamDomain> SanPhamDomains) {
        this.sanPhamDomains =SanPhamDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_danhsach_sanpham, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttensanpham.setText(sanPhamDomains.get(position).getTensanpham());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(sanPhamDomains.get(position).getAnhsanpham(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imgsanpham);

        holder.imgsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SanPham.class);
                intent.putExtra("object", sanPhamDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensanpham;
        ImageView imgsanpham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensanpham = itemView.findViewById(R.id.txt_tensanpham);
            imgsanpham = itemView.findViewById(R.id.img_sanpham_giohang);

        }
    }
}
