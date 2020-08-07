package com.example.appfood_phantom.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appfood_phantom.Model.RestauranModel;
import com.example.appfood_phantom.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdapterRecyclerPlaces extends RecyclerView.Adapter<AdapterRecyclerPlaces.myHolder> {
    List<RestauranModel> restauranModelList;
    int resource;

    public AdapterRecyclerPlaces(List<RestauranModel> restauranModelList, int resource) {
        this.restauranModelList = restauranModelList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        RestauranModel restauranModel = restauranModelList.get(position);
        holder.mNameTV.setText(restauranModel.getTenquanan());
        if(restauranModel.isGiaohang()){
            holder.btnOrder.setText("DDa dat");
            holder.btnOrder.setBackgroundColor(Color.parseColor("#123243"));
        }
    }

    @Override
    public int getItemCount() {
        return restauranModelList.size();
    }

    class myHolder extends RecyclerView.ViewHolder {
        TextView mNameTV;
        Button btnOrder;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            mNameTV = itemView.findViewById(R.id.tv_nameOfrestauran);
            btnOrder=itemView.findViewById(R.id.btn_order);
        }
    }


}
