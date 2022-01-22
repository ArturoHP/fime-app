package com.example.fimeapp.ui.home;

//A&E

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fimeapp.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Inicio extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        //Array jej
        final int[] sampleImages = {R.drawable.carrusel1, R.drawable.carrusel2, R.drawable.carrusel3, R.drawable.carrusel4, R.drawable.carrusel5};
        final int[] aliados = {R.drawable.caterpillar,R.drawable.danfoss,R.drawable.yazaki,R.drawable.ternium,R.drawable.aguadrenaje,R.drawable.britishcouncil};

            CarouselView carouselView = view.findViewById(R.id.carouselView);
            carouselView.setPageCount(sampleImages.length);
            carouselView.setIndicatorGravity(Gravity.END|Gravity.BOTTOM);

            CarouselView carouselView1 = view.findViewById(R.id.carouselView4aliados);
            carouselView1.setPageCount(aliados.length);
            carouselView1.setIndicatorGravity(Gravity.CENTER|Gravity.BOTTOM);



        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };

        carouselView.setImageListener(imageListener);


        ImageListener imageListener1 = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(aliados[position]);
            }
        };

        carouselView1.setImageListener(imageListener1);


        return view;
    }
}