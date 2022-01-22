package com.example.fimeapp;

//A&E

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class calendarioalumno extends Fragment {

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private RecyclerView recycler4evento;
    private FirebaseRecyclerAdapter<CalendareventoFB, calendarioalumno.Eventoitemviewholder> eventoFBadapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendarioalumno, container, false);

        recycler4evento = view.findViewById(R.id.recycler4eventosper);
        final String usrfrb = user.getUid();
        DatabaseReference eventospersonales = FirebaseDatabase.getInstance().getReference().child("Users").child(usrfrb).child("Calendar").child("EventosPer");
        Query neweventosper = eventospersonales.orderByPriority();

        LinearLayoutManager layoutManagerhoriz
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycler4evento.setLayoutManager(layoutManagerhoriz);

        final FirebaseRecyclerOptions events = new FirebaseRecyclerOptions.Builder<CalendareventoFB>().setQuery(neweventosper, CalendareventoFB.class).build();

        eventoFBadapter = new FirebaseRecyclerAdapter<CalendareventoFB, Eventoitemviewholder>(events) {
            @Override
            protected void onBindViewHolder(@NonNull Eventoitemviewholder eventoitemviewholder, int i, @NonNull CalendareventoFB calendareventoFB) {
                eventoitemviewholder.setFecha(getActivity(),calendareventoFB.getDate());
                eventoitemviewholder.setNombre(getActivity(),calendareventoFB.getNombre());
                eventoitemviewholder.setDesc(getActivity(),calendareventoFB.getDesc());

                LinearLayout ll = eventoitemviewholder.mView.findViewById(R.id.linearlay4event);
                if(calendareventoFB.getColor().equals("0")){
                    ll.setBackgroundColor(getResources().getColor(R.color.fimecolor));
                }else{
                    ll.setBackgroundColor(getResources().getColor(R.color.Red));
                }

            }


            @Override
            public calendarioalumno.Eventoitemviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_item_eventopersonal, parent, false);

                return new calendarioalumno.Eventoitemviewholder(view);
            }
        };



        eventoFBadapter.startListening();
        recycler4evento.setAdapter(eventoFBadapter);




        CalendarView calalumno = view.findViewById(R.id.calendalum);
        final DatabaseReference usercalendar = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Calendar").child("EventosPer");
        final DatabaseReference calnothere = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Calendar");

        calalumno.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,final int año, final int mes, final int dia) {
                final Dialog dialognewevent = new Dialog(getContext());
                dialognewevent.setContentView(R.layout.layout_dialog_newevent);
                Button dialogsubmit = dialognewevent.findViewById(R.id.submitevent);
                ImageButton close = dialognewevent.findViewById(R.id.closeevent);
                final TextView titulotv,desctv;
                titulotv =  dialognewevent.findViewById(R.id.tvtituloevento);
                desctv = dialognewevent.findViewById(R.id.tvdescevento);
                Log.i("@@","Dialog abierto");
                final CheckBox nul,max;


                nul = dialognewevent.findViewById(R.id.impnula);
                max = dialognewevent.findViewById(R.id.impmax);


                dialogsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!titulotv.getText().toString().isEmpty() && !desctv.getText().toString().isEmpty()){

                            int cual;

                            if (nul.isChecked() && max.isChecked()){
                                Toast.makeText(getContext(),"Seleccione solo un tipo de importancia",Toast.LENGTH_SHORT).show();
                            }
                            if (nul.isChecked() && !max.isChecked()){

                                cual = 0;

                                try {
                                    String fecha = (dia + "/"+ mes + "/" + año);
                                    Writer4eventoscalendario writer = new Writer4eventoscalendario(titulotv.getText().toString(),desctv.getText().toString(),fecha,String.valueOf(cual));
                                    long currentTime = Calendar.getInstance().getTimeInMillis();
                                    usercalendar.child(String.valueOf(currentTime)).setValue(writer);
                                }finally {
                                    Toast.makeText(getContext(),"Guardado exitosamente!",Toast.LENGTH_SHORT).show();
                                    titulotv.setText("");
                                    desctv.setText("");
                                }
                            }
                            if (max.isChecked() && !nul.isChecked()){
                                cual = 1;
                                try {

                                    String fecha = (dia + "/"+ mes + "/" + año);
                                    Writer4eventoscalendario writer = new Writer4eventoscalendario(titulotv.getText().toString(),desctv.getText().toString(),fecha,String.valueOf(cual));
                                    long currentTime = Calendar.getInstance().getTimeInMillis();
                                    usercalendar.child(String.valueOf(currentTime)).setValue(writer);

                                }finally {
                                    Toast.makeText(getContext(),"Guardado exitosamente!",Toast.LENGTH_SHORT).show();
                                    titulotv.setText("");
                                    desctv.setText("");
                                }
                            }
                        }else{
                            Toast.makeText(getContext(),"Rellene los dos campos necesarios!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialognewevent.dismiss();
                    }
                });
                dialognewevent.show();
            }
        });
        usercalendar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("@@","Firebase change");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static class Eventoitemviewholder extends RecyclerView.ViewHolder {
        View mView;

        public Eventoitemviewholder(final View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setFecha(Context ctx, String fecha) {
            TextView tvfecha = mView.findViewById(R.id.fechaevento);
            tvfecha.setText(fecha);
        }

        public void setNombre(Context ctx, String titulo) {
            TextView tvtitulo = mView.findViewById(R.id.tituloevento);
            tvtitulo.setText(titulo);
        }

        public void setDesc(Context ctx, String desc) {
            TextView tvdesc = mView.findViewById(R.id.descevento);
            tvdesc.setText(desc);
        }
    }
}