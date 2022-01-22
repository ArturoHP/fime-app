package com.example.fimeapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagercalendarios extends FragmentStatePagerAdapter {
    int tabCount;

    //Constructor de la clase
    public Pagercalendarios(FragmentManager fm, int tabCount) {
        super(fm);
        //se inicializa el tabcount
        this.tabCount= tabCount;
    }

    //El switch jejeje
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                calendarioalumno tab0 = new calendarioalumno();
                return tab0;
            case 1:
                calenadariolinea tab1 = new calenadariolinea();
                return tab1;
            case 2:
                calendariolineasabatino tab2 = new calendariolineasabatino();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
