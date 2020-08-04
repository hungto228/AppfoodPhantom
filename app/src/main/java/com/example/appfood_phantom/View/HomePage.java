package com.example.appfood_phantom.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.widget.RadioButton;

import com.example.appfood_phantom.Adapter.AdapterViewPagerHome;
import com.example.appfood_phantom.R;

public class HomePage extends AppCompatActivity {
ViewPager viewPagerHome;
RadioButton mRbtnFood,mRbtnPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        viewPagerHome=findViewById(R.id.viewpager_home);
        mRbtnFood=findViewById(R.id.rbtn_food);
        mRbtnPlace=findViewById(R.id.rbtn_places);
        AdapterViewPagerHome adapterViewPagerHome=new AdapterViewPagerHome(getSupportFragmentManager());
        viewPagerHome.setAdapter(adapterViewPagerHome);
viewPagerHome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                mRbtnFood.setChecked(true);
                break;
            case 1:
                mRbtnPlace.setChecked(true);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
    }
}
