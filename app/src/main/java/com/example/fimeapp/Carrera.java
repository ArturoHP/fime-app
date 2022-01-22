package com.example.fimeapp;

import android.content.SyncInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Carrera extends Fragment {

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_carrera, container, false);

        String usrfrb = user.getUid();

        final DatabaseReference getcarrera = FirebaseDatabase.getInstance().getReference().child("Users").child(usrfrb).child("Infoalumno");

        getcarrera.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot1) {
                Log.i("Firebase","Consultar nombre de carrera");
                final String carrerastr = snapshot1.child("carrera").getValue().toString();
                TextView namecarrera = view.findViewById(R.id.inicialescarrera);
                namecarrera.setText(carrerastr);

                final DatabaseReference getinfocarrera = FirebaseDatabase.getInstance().getReference().child("Infocarreras").child(carrerastr);

                getinfocarrera.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        String jefecarr, correojefecarr, textperfiling, textreqacademicos,textoperfilegre,textoreqlegales,textoreqdeselec,textoreqespecprog;

                        jefecarr = snapshot2.child("Jefecarrera").getValue().toString();
                        correojefecarr = snapshot2.child("correojefecarrera").getValue().toString();
                        textperfiling = snapshot2.child("perfiling").getValue().toString();
                        textoperfilegre = snapshot2.child("perfilegre").getValue().toString();
                        textreqacademicos = snapshot2.child("reqacademico").getValue().toString();
                        textoreqlegales = snapshot2.child("reqlegal").getValue().toString();
                        textoreqdeselec = snapshot2.child("reqdeselec").getValue().toString();
                        textoreqespecprog = snapshot2.child("reqespecprog").getValue().toString();


                        TextView jefecarrera = view.findViewById(R.id.jefecarrera);
                        jefecarrera.setText(jefecarr);
                        TextView correojefecarrera = view.findViewById(R.id.correojefecarrera);
                        correojefecarrera.setText(correojefecarr);
                        TextView textperfilinge = view.findViewById(R.id.textoperfilingreso);
                        textperfilinge.setText(textperfiling);

                        TextView textreqaca = view.findViewById(R.id.textoreqacademicos);
                        textreqaca.setText(textreqacademicos);
                        TextView textreqlegales = view.findViewById(R.id.textoreqlegales);
                        textreqlegales.setText(textoreqlegales);
                        TextView textreqdeselecc = view.findViewById(R.id.textoreqdeselec);
                        textreqdeselecc.setText(textoreqdeselec);
                        TextView textreqspecprog = view.findViewById(R.id.textoreqespecificos);
                        textreqspecprog.setText(textoreqespecprog);


                        TextView textperfilegres = view.findViewById(R.id.textoperfilegreso);
                        textperfilegres.setText(textoperfilegre);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Error getting info",Toast.LENGTH_SHORT).show();
                    }

                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error getting info",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}