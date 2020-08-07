package com.example.appfood_phantom.Model;

import android.util.Log;

import com.example.appfood_phantom.Controller.Interface.PlaceInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;

public class RestauranModel {
    private boolean giaohang;
    String giodongcua, giomocua, tenquanan, videogioithieu  , idrestauran;
    List<String> tienich;
    int luotthich;
    DatabaseReference reference;


    public RestauranModel() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public int getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(int luotthich) {
        this.luotthich = luotthich;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getIdrestauran() {
        return idrestauran;
    }

    public void setIdrestauran(String idrestauran) {
        this.idrestauran = idrestauran;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }


    public void getListRestauran(final PlaceInterface placeInterface) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshotRestauran = snapshot.child("quanans");
                for (DataSnapshot ds : dataSnapshotRestauran.getChildren()) {
                    RestauranModel restauranModel = ds.getValue(RestauranModel.class);
                    DataSnapshot dataSnapshot_image=snapshot.child("hinhanhquanans").child(ds.getKey());
                    Log.d("ddddd", dataSnapshot_image+"");
                    placeInterface.getListRestauran(restauranModel);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(valueEventListener);

    }

}
