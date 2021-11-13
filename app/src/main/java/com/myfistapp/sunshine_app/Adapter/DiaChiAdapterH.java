package com.myfistapp.sunshine_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfistapp.sunshine_app.Class.DiaChi;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class DiaChiAdapterH extends RecyclerView.Adapter<DiaChiAdapterH.DiaChiHolder>{

    private Context context;
    private ArrayList<DiaChi> mlist;
    private ItemClick itemClick;

    public DiaChiAdapterH(Context context, ItemClick itemClick) {

        this.context = context;
        this.itemClick = itemClick;
    }

    public void setData (ArrayList<DiaChi> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiaChiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diachi, parent, false);

        return new DiaChiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaChiHolder holder, @SuppressLint("RecyclerView") int position) {

        DiaChi diaChi = mlist.get(position);
        if(diaChi == null) {
            return;
        }

        holder.tv_ten.setText(diaChi.getTen());
        holder.tv_sdt.setText(diaChi.getSdt());
        holder.tv_sonha.setText(diaChi.getSonha());
        holder.tv_all.setText(diaChi.getXa() + ", " + diaChi.getHuyen() + ", " + diaChi.getTinh());
        holder.tv_stt.setText(position + 1 + "");

        holder.lo_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(mlist.get(position));
            }
        });

        holder.lo_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
    }

    public class DiaChiHolder extends RecyclerView.ViewHolder {

        private TextView tv_ten, tv_sdt, tv_sonha, tv_all, tv_stt;
        private LinearLayout lo_item;
        public DiaChiHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten = itemView.findViewById(R.id.tv_itdc_hoten);
            tv_sdt = itemView.findViewById(R.id.tv_itdc_sdt);
            tv_sonha = itemView.findViewById(R.id.tv_itdc_sonha);
            tv_all = itemView.findViewById(R.id.tv_itdc_all);
            tv_stt = itemView.findViewById(R.id.tv_itdc_stt);
            lo_item = itemView.findViewById(R.id.lo_itdc_item);
        }
    }

    //sự kiện chọn item
    public interface ItemClick {
        public void onItemClick(DiaChi diaChi);

    }
}
