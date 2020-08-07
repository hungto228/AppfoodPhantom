package com.example.appfood_phantom.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appfood_phantom.Controller.PlacesController;
import com.example.appfood_phantom.Model.RestauranModel;
import com.example.appfood_phantom.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesFragment extends Fragment {
PlacesController placesController;
RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.layout_fragment_places,container,false);
     recyclerView=view.findViewById(R.id.recycleview_places);
     return view;
    }

    @Override
    public void onStart() {
        super.onStart();
       placesController=new PlacesController(getContext());
       placesController.getListRestauranController(recyclerView);
    }
}
