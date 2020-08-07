package com.example.appfood_phantom.Controller;

import android.content.Context;

import com.example.appfood_phantom.Adapter.AdapterRecyclerPlaces;
import com.example.appfood_phantom.Controller.Interface.PlaceInterface;
import com.example.appfood_phantom.Model.RestauranModel;
import com.example.appfood_phantom.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesController {


    Context context;
    RestauranModel restauranModel;

    public PlacesController(Context context) {
        this.context = context;
        restauranModel = new RestauranModel();
    }


    public void getListRestauranController(RecyclerView recyclerViewPlace) {
        final List<RestauranModel> restauranModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewPlace.setLayoutManager(layoutManager);

        final AdapterRecyclerPlaces adapterRecyclerPlaces = new AdapterRecyclerPlaces(restauranModelList, R.layout.row_places_recycleview);
        recyclerViewPlace.setAdapter(adapterRecyclerPlaces);
        PlaceInterface placeInterface = new PlaceInterface() {
            @Override
            public void getListRestauran(RestauranModel restauranModel) {
                restauranModelList.add(restauranModel);
                adapterRecyclerPlaces.notifyDataSetChanged();
            }
        };
        restauranModel.getListRestauran(placeInterface);
    }

}
