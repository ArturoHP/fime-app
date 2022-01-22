package com.example.fimeapp.ui.Acercade;

//A&E

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.fimeapp.PagerAcercade;
import com.example.fimeapp.R;
import com.google.android.material.tabs.TabLayout;

public class Acerca extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acercade, container, false);


        final TabLayout tabLayout = view.findViewById(R.id.tab4acerca);

        tabLayout.addTab(tabLayout.newTab().setText("La Fime"));
        tabLayout.addTab(tabLayout.newTab().setText("Director"));
        tabLayout.addTab(tabLayout.newTab().setText("Politica calidad"));
        tabLayout.addTab(tabLayout.newTab().setText("Reconocimientos"));

        final ViewPager viewPager =  view.findViewById(R.id.pager4acerca);

        PagerAcercade adapter = new PagerAcercade(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0){
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.fimecolor));
                } if (viewPager.getCurrentItem() == 1) {
                    tabLayout.selectTab(tabLayout.getTabAt(1));
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.fimecolor));
                }
                if (viewPager.getCurrentItem() == 2) {
                    tabLayout.selectTab(tabLayout.getTabAt(2));
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.fimecolor));
                }
                if (viewPager.getCurrentItem() == 3) {
                    tabLayout.selectTab(tabLayout.getTabAt(3));
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.fimecolor));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return view;
    }
}