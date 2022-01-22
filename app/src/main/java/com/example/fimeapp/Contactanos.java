package com.example.fimeapp;

//A&E
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Contactanos extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactanos, container, false);



        ImageButton face,twitter,insta,youtube;

        face = view.findViewById(R.id.btnface);
        twitter = view.findViewById(R.id.btntwitter);
        insta = view.findViewById(R.id.btninsta);
        youtube = view.findViewById(R.id.btnyoutube);

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentface = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/fime.oficial/"));
                startActivity(intentface);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttwit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/fime_oficial?lang=es"));
                startActivity(intenttwit);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentinsta = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/fime.oficial/?hl=es-la"));
                startActivity(intentinsta);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentyou = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCfmQiSfgZ5cMDe-kAYplmww/featured"));
                startActivity(intentyou);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaremail();
            }
        });


        return view;
    }

    protected void enviaremail() {
        Log.i("Enviar mail", "");

        String[] TO = {"contacto@fime.uanl.mx"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto: ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Nombre Completo: \nMatricula: \nCarrera: \nDescriba el problema o solucion que busca: \n");

        try {
            startActivity(Intent.createChooser(emailIntent, ""));
            Log.i("Terminando de enviar", "Email");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(),
                    "Gmail no esta instalado", Toast.LENGTH_SHORT).show();
        }
    }
}