package com.example.fimeapp;

//A&E

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAcercade extends FragmentStatePagerAdapter{

        //numero de tabs
        int tabCount;

        //Constructor de la clase
        public PagerAcercade(FragmentManager fm, int tabCount) {
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
                    Lafime tab1 = new Lafime();
                    return tab1;
                case 1:
                    Eldirectorpage tab2 = new Eldirectorpage();
                    return tab2;
                case 2:
                    Politicadecalidad tab4 = new Politicadecalidad();
                    return tab4;
                case 3:
                    Reconocimientos tab3 = new Reconocimientos();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
}
