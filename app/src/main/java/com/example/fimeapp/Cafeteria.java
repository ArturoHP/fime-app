package com.example.fimeapp;

//A&E

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicMarkableReference;



public class Cafeteria extends Fragment {

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private int Hamburgesas = 0;
    private int Burrito = 0;
    private int FDP = 0;
    private int Chillidogs = 0;
    private int Paps = 0;

    private int Hamburgesasstatecart = 0;
    private int Burritostatecart = 0;
    private int FDPstatecart = 0;
    private int Chillidogsstatecart = 0;
    private int Papsstatecart = 0;


    private RecyclerView recycler4comidas;
    private FirebaseRecyclerAdapter<ComidasFB, ComidaViewHolder> comidasrecycleradapter;
    private RecyclerView recycler4carrito;
    private FirebaseRecyclerAdapter<CarritoFB, CarritoitemViewHolder> carritoFbAdaptyer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafeteria, container, false);


        FloatingActionButton fab = view.findViewById(R.id.fab4cart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);
                dialog.setContentView(R.layout.dialog_layout_cart);

                ImageButton btnclosecarrito = dialog.findViewById(R.id.closecarrito);
                btnclosecarrito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



                recycler4carrito = dialog.findViewById(R.id.carrito);

                LinearLayoutManager layoutManagerhoriz
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                recycler4carrito.hasFixedSize();
                recycler4carrito.setLayoutManager(layoutManagerhoriz);

                final String usrfrb = user.getUid();
                DatabaseReference Carritop = FirebaseDatabase.getInstance().getReference().child("Users").child(usrfrb).child("Carrito").child("listaproductos");
                Query newCarritop = Carritop.orderByKey();

                final FirebaseRecyclerOptions carrito = new FirebaseRecyclerOptions.Builder<CarritoFB>().setQuery(newCarritop, CarritoFB.class).build();


                carritoFbAdaptyer = new FirebaseRecyclerAdapter<CarritoFB, CarritoitemViewHolder>(carrito) {
                    @Override
                    protected void onBindViewHolder(@NonNull final CarritoitemViewHolder carritoitemViewHolder, final int i, @NonNull final CarritoFB carritoFB) {
                        carritoitemViewHolder.setNombre(getActivity(),carritoFB.getNombreproducto());
                        carritoitemViewHolder.setCantidad(getActivity(),carritoFB.getCantidadproducto());

                        TextView carritoname = carritoitemViewHolder.mView.findViewById(R.id.nombrecomidacaarrito);
                        final TextView carritocant = carritoitemViewHolder.mView.findViewById(R.id.cantidadcarritocomida);
                        TextView cantidadtv = carritoitemViewHolder.mView.findViewById(R.id.cantidadshowtv);

                        carritoitemViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int cantprodint = Integer.parseInt(  carritocant.getText().toString());
                                Toast.makeText(getContext(),"Cantidad de productos: " + cantprodint,Toast.LENGTH_SHORT).show();
                            }
                        });

                        Button quitarcarritoitem = carritoitemViewHolder.mView.findViewById(R.id.quitaritemcarrito);
                        quitarcarritoitem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String usrfrb = user.getUid();
                                DatabaseReference itemborrar = FirebaseDatabase.getInstance().getReference().child("Users").child(usrfrb).child("Carrito").child("listaproductos").child(carritoFB.getNombreproducto());
                                itemborrar.removeValue();
                            }
                        });


                        //Esto hace que nunca se quede solo el recycler view
                        if (carritoname.getText().toString().equalsIgnoreCase("null")){
                            carritoname.setVisibility(View.GONE);
                            carritocant.setVisibility(View.GONE);
                            quitarcarritoitem.setVisibility(View.GONE);
                            cantidadtv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public Cafeteria.CarritoitemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.layout_item_carrito, parent, false);

                        return new Cafeteria.CarritoitemViewHolder(view);
                    }
                };
                carritoFbAdaptyer.startListening();
                recycler4carrito.setAdapter(carritoFbAdaptyer);

                Button comprar = dialog.findViewById(R.id.comprarcarrito);
                final ImageView qrcode = dialog.findViewById(R.id.qrcodepagar);

                comprar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            //Mande a traer el user id para asi en la otra app rellenarlo y hacer que lea la otra informacion equis de y rellene la orde
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            String usr = usrfrb;
                            BitMatrix bitMatrix = multiFormatWriter.encode(usr, BarcodeFormat.QR_CODE,200,200);
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            Bitmap bitqr = barcodeEncoder.createBitmap(bitMatrix);
                            qrcode.setImageBitmap(bitqr);
                        }catch (WriterException e){
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
        });



        recycler4comidas = view.findViewById(R.id.recycler4comidas);

        LinearLayoutManager layoutManagerhoriz
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycler4comidas.hasFixedSize();
        recycler4comidas.setLayoutManager(layoutManagerhoriz);

        DatabaseReference Comidass = FirebaseDatabase.getInstance().getReference().child("comidas");
        Query newComidass = Comidass.orderByKey();

        final FirebaseRecyclerOptions comidas = new FirebaseRecyclerOptions.Builder<ComidasFB>().setQuery(newComidass, ComidasFB.class).build();


        comidasrecycleradapter = new FirebaseRecyclerAdapter<ComidasFB, ComidaViewHolder>(comidas) {
            @Override
            protected void onBindViewHolder(@NonNull final ComidaViewHolder comidaViewHolder, final int i, @NonNull final ComidasFB comidasFB) {
                comidaViewHolder.setImagen(getActivity(),comidasFB.getImagencomida());
                comidaViewHolder.setNombre(getActivity(),comidasFB.getNombrecomida());
                comidaViewHolder.setPrecio(getActivity(),comidasFB.getPreciocomida());
                comidaViewHolder.setDescripcion(getActivity(),comidasFB.getDescripcioncomida());


                comidaViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Toast.makeText(getContext(),comidasFB.nombrecomida,Toast.LENGTH_SHORT).show();
                    }
                });

                Button masitem = comidaViewHolder.mView.findViewById(R.id.sumacantidad);
                Button menositem = comidaViewHolder.mView.findViewById(R.id.restacantidad);

                masitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (comidasFB.getNombrecomida().equals("Hamburgesa")){
                            Hamburgesas++;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Hamburgesas)));
                        }
                        if (comidasFB.getNombrecomida().equals("Burrito")){
                            Burrito++;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Burrito)));
                        }
                        if (comidasFB.getNombrecomida().equals("Fajitas de pollo")){
                            FDP++;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(FDP)));
                        }
                        if (comidasFB.getNombrecomida().equals("Chillidogs")){
                            Chillidogs++;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Chillidogs)));
                        }
                        if (comidasFB.getNombrecomida().equals("Papas a la francesa")){
                            Paps++;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Paps)));
                        }
                    }

                });

                menositem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (comidasFB.getNombrecomida().equals("Hamburgesa") && Hamburgesas > 0){
                            Hamburgesas--;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Hamburgesas)));
                        }
                        if (comidasFB.getNombrecomida().equals("Burrito") && Burrito > 0){
                            Burrito--;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Burrito)));
                        }
                        if (comidasFB.getNombrecomida().equals("Fajitas de pollo") && FDP > 0){
                            FDP--;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(FDP)));
                        }
                        if (comidasFB.getNombrecomida().equals("Chillidogs") && Chillidogs > 0){
                            Chillidogs--;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Chillidogs)));
                        }
                        if (comidasFB.getNombrecomida().equals("Papas a la francesa") && Paps > 0){
                            Paps--;
                            comidaViewHolder.setcantidad(getActivity(),(String.valueOf(Paps)));
                        }
                    }

                });

                Button btnagregar = comidaViewHolder.mView.findViewById(R.id.agregarcomidacarrito);
                btnagregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                        String usrfrb = user.getUid();
                        DatabaseReference reference = rootNode.getReference().child("Users").child(usrfrb).child("Carrito").child("listaproductos");
                        TextView nombrecomida = comidaViewHolder.mView.findViewById(R.id.nombrecomida);
                        TextView cantidad = comidaViewHolder.mView.findViewById(R.id.cantidadtvx);

                        if (Integer.parseInt(cantidad.getText().toString()) < 1){
                            Toast.makeText(getContext(),"Aumente la cantidad para continuar al carrito",Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                int costoenint = comidasFB.getPrecioenint();
                                Writer4carrito writer = new Writer4carrito(nombrecomida.getText().toString(), cantidad.getText().toString(), costoenint);
                                reference.child(nombrecomida.getText().toString()).setValue(writer);
                                Toast.makeText(getContext(),"Agreagado al carrito",Toast.LENGTH_SHORT).show();
                            }finally {
                                cantidad.setText("0");
                                Hamburgesas = 0;
                                Burrito = 0;
                                FDP = 0;
                                Chillidogs = 0;
                                Paps = 0;
                                comidaViewHolder.setcantidad(getActivity(),"0");
                            }
                        }
                    }
                });

            }

            @Override
            public Cafeteria.ComidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_item_comida, parent, false);

                return new Cafeteria.ComidaViewHolder(view);
            }

        };
        comidasrecycleradapter.startListening();
        recycler4comidas.setAdapter(comidasrecycleradapter);


        return view;
    }



    public static class ComidaViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ComidaViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setImagen(Context ctx, String image){
            ImageView avisoimg = mView.findViewById(R.id.imagencomida);
            Picasso.with(ctx).load(image).into(avisoimg);
        }

        public void setNombre(Context ctx, String nombrecomida){
            TextView tvnombre = mView.findViewById(R.id.nombrecomida);
            tvnombre.setText(nombrecomida);
        }

        public void setDescripcion(Context ctx, String desc){
            TextView tvdesc = mView.findViewById(R.id.descripcioncomida);
            tvdesc.setText(desc);
        }
        public void setPrecio(Context ctx, String costo){
            TextView tvcosto = mView.findViewById(R.id.costocomida);
            tvcosto.setText(costo);
        }
        public void setcantidad(Context ctx, String cantidad){
            TextView cantidadtv = mView.findViewById(R.id.cantidadtvx);
            cantidadtv.setText(cantidad);
        }

    }

    public static class CarritoitemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public CarritoitemViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNombre(Context ctx, String nombrecomida) {
            TextView tvnombre = mView.findViewById(R.id.nombrecomidacaarrito);
            tvnombre.setText(nombrecomida);
        }

        public void setCantidad(Context ctx, String cant) {
            TextView tvcant = mView.findViewById(R.id.cantidadcarritocomida);
            tvcant.setText(cant);
        }
    }
}