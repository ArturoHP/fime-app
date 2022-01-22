package com.example.fimeapp;

//A&E

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Eldirectorpage extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_eldirectorpage, container, false);
        final ImageButton directorbttn = view.findViewById(R.id.imagendirector);

        final DatabaseReference director = FirebaseDatabase.getInstance().getReference().child("Director");

        director.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                ImageButton directorsImage = view.findViewById(R.id.imagendirector);
                if (snapshot.child("nombre").exists()){
                    TextView nombretw = view.findViewById(R.id.nombredirector);
                    final String nombre = snapshot.child("nombre").getValue().toString();
                    nombretw.setText(nombre);
                    directorbttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(),nombre,Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Error getting name",Toast.LENGTH_SHORT).show();
                }
                if (snapshot.child("imagendire").exists()){
                    final String imagen = snapshot.child("imagendire").getValue().toString();
                    Picasso.with(getContext()).load(imagen).into(directorsImage);
                }else{
                    Toast.makeText(getContext(),"Error getting image", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error getting info", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}