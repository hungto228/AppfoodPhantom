package com.example.appfood_phantom.Adapter;

import com.example.appfood_phantom.View.Fragment.FoodFragment;
import com.example.appfood_phantom.View.Fragment.PlacesFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class AdapterViewPagerHome extends FragmentPagerAdapter {
    PlacesFragment placesFragment;
    FoodFragment foodFragment;



    public AdapterViewPagerHome(@NonNull FragmentManager fm) {
        super(fm);
        foodFragment=new FoodFragment();
        placesFragment=new PlacesFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return foodFragment;
            case 1:
                return placesFragment;
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
