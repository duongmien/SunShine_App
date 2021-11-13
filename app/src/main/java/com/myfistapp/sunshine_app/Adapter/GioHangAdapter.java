package com.myfistapp.sunshine_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.myfistapp.sunshine_app.Interface.ChangeNumberItemsListener;
import com.myfistapp.sunshine_app.Helper.ManagementCart;
import com.myfistapp.sunshine_app.R;
import com.myfistapp.sunshine_app.Class.SanPhamDomain;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private ArrayList<SanPhamDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public GioHangAdapter(ArrayList<SanPhamDomain> FoodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {

        this.foodDomains = FoodDomains;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(Double.parseDouble(amount));
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_gio_hang, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTensanpham());
        holder.feeEachItem.setText(String.valueOf( currencyFormat(foodDomains.get(position).getGiasanpham()) + " VNĐ" ));
       // holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getSoluongdathang() * Integer.parseInt(foodDomains.get(position).getGiasanpham()) * 100.0) / 100.0)));
        holder.num.setText(String.valueOf(foodDomains.get(position).getSoluongdathang()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getAnhsanpham(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.MinusNumerFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_tensanpham_giohang);
            feeEachItem = itemView.findViewById(R.id.txt_giatien);
            pic = itemView.findViewById(R.id.img_sanpham_giohang);
//            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.txt_soluongsanpham);
            plusItem = itemView.findViewById(R.id.btn_cong);
            minusItem = itemView.findViewById(R.id.btn_tru);
        }
    }
}
