package com.example.fimeapp;

//A&E
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fimeapp.ui.splashFime;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 *
 */
public class Account extends Fragment {

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        String usrfrb = user.getUid();


        final TextView nombre, mat, carr;

        nombre = view.findViewById(R.id.nombrealumno);
        mat = view.findViewById(R.id.matriculaalumno);
        carr = view.findViewById(R.id.carreraalumno);

        DatabaseReference infoalumno = FirebaseDatabase.getInstance().getReference().child("Users").child(usrfrb).child("Infoalumno");


        infoalumno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String nombrestr,matriculastr,carrerastr;
                nombrestr = snapshot.child("nombre").getValue().toString();
                matriculastr = snapshot.child("matricula").getValue().toString();
                carrerastr = snapshot.child("carrera").getValue().toString();

                nombre.setText(nombrestr);
                mat.setText(matriculastr);
                carr.setText(carrerastr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error obteniendo datos", Toast.LENGTH_SHORT).show();
            }
        });


        Button lo = view.findViewById(R.id.logout);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), splashFime.class);
                Toast.makeText(getContext(),"Sesion cerrada exitosamente",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        return view;
    }
}