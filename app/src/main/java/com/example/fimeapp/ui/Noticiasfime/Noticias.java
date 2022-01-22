package com.example.fimeapp.ui.Noticiasfime;

//A&E

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fimeapp.Cafeteria;
import com.example.fimeapp.ComidasFB;
import com.example.fimeapp.NoticiasFB;
import com.example.fimeapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Noticias extends Fragment {

    private RecyclerView recycler4noticias;
    private FirebaseRecyclerAdapter<NoticiasFB, Noticias.NoticiasViewHolder> noticiasrecycleradapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        recycler4noticias = view.findViewById(R.id.recycler4noticias);

        LinearLayoutManager layoutManagerhoriz
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycler4noticias.hasFixedSize();
        recycler4noticias.setLayoutManager(layoutManagerhoriz);

        DatabaseReference Noticiass = FirebaseDatabase.getInstance().getReference().child("noticias");
        Query newNoticiass = Noticiass.orderByKey();

        final FirebaseRecyclerOptions noticias = new FirebaseRecyclerOptions.Builder<NoticiasFB>().setQuery(newNoticiass, NoticiasFB.class).build();


        noticiasrecycleradapter = new FirebaseRecyclerAdapter<NoticiasFB, Noticias.NoticiasViewHolder>(noticias) {
            @Override
            protected void onBindViewHolder(@NonNull final Noticias.NoticiasViewHolder noticiasViewHolder, final int i, @NonNull final NoticiasFB noticiasFB) {
                noticiasViewHolder.setFecha(getActivity(),noticiasFB.getFechanoticia());
                noticiasViewHolder.setEncabezado(getActivity(),noticiasFB.getEncabezadonoticia());
                noticiasViewHolder.setDescripcion(getActivity(),noticiasFB.getDescnoticia());
                noticiasViewHolder.setImagen(getActivity(),noticiasFB.getImagennoticia());


                Button leermas = noticiasViewHolder.mView.findViewById(R.id.leermasnoticia);
                leermas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (noticiasFB.getFechanoticia() != null) {
                            Intent intentredirect = new Intent(Intent.ACTION_VIEW, Uri.parse(noticiasFB.getLeermasnoticia()));
                            startActivity(intentredirect);
                        }else{
                            Toast.makeText(getContext(),"This doesnt contain any  redirection",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            @Override
            public Noticias.NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_noticia, parent, false);

                return new Noticias.NoticiasViewHolder(view);
            }

        };
        noticiasrecycleradapter.startListening();
        recycler4noticias.setAdapter(noticiasrecycleradapter);

        return view;
    }
    public static class NoticiasViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NoticiasViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setImagen(Context ctx, String image){
            ImageView img = mView.findViewById(R.id.imagennoticia);
            Picasso.with(ctx).load(image).into(img);
        }
        public void setEncabezado(Context ctx, String enc){
            TextView tvenc = mView.findViewById(R.id.encabezadonoticia);
            tvenc.setText(enc);
        }
        public void setDescripcion(Context ctx, String desc){
            TextView tvdesc = mView.findViewById(R.id.descripcioncorta);
            tvdesc.setText(desc);
        }
        public void setFecha(Context ctx, String fecha){
            TextView tvfecha = mView.findViewById(R.id.fechanoticia);
            tvfecha.setText(fecha);
        }

    }
}