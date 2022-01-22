package com.example.fimeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;


public class calendariolineasabatino extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calendariolineasabatino, container, false);


        DatabaseReference cals = FirebaseDatabase.getInstance().getReference().child("Calendarios");

        cals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imagen = snapshot.child("calendariolineasabatino").getValue().toString();
                ZoomageView sabcal = view.findViewById(R.id.calendariolineasab);
                Picasso.with(getContext()).load(imagen).into(sabcal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error getting the info",Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }
}