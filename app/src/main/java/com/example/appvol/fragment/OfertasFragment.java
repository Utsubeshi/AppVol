package com.example.appvol.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appvol.R;
import com.example.appvol.adapter.OfertasAdapter;
import com.example.appvol.model.Oferta;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OfertasFragment extends Fragment {

    RecyclerView rvOfertas;
    ArrayList<Oferta> listaOfertas;
    private OfertasAdapter adapter;
    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference coleccion = FireStore.collection("Ofertas");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ofertas, container, false);
        rvOfertas = view.findViewById(R.id.rv_listado_ofertas);
        rvOfertas.setLayoutManager(new LinearLayoutManager(getContext()));
        listaOfertas = new ArrayList<Oferta>();
        adapter = new OfertasAdapter(getContext());
        //adapter.agregarOfertas(listaOfertas);
        recuperarOfertas();
        rvOfertas.setAdapter(adapter);


        return view;
    }

     public void recuperarOfertas() {
        coleccion.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null ){
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    //Oferta oferta = documentSnapshot.toObject(Oferta.class);
                    Oferta oferta = new Oferta();
                    oferta.setTitulo(documentSnapshot.getString("titulo"));
                    oferta.setTipo(documentSnapshot.getString("tipo"));
                    oferta.setFecha(documentSnapshot.getString("fecha"));
                    oferta.setOrganizacion(documentSnapshot.getString("organizacion"));
                    double cantidad = documentSnapshot.getDouble("cantidadMin");
                    oferta.setCantidadMin((int) cantidad);
                    oferta.setDetalle(documentSnapshot.getString("detalle"));
                    oferta.setTiempoMin(documentSnapshot.getString("tiempoMin"));
                    oferta.setUbicacion(documentSnapshot.getString("ubicacion"));
                    oferta.setUrlImagen(documentSnapshot.getString("urlImagen"));
                    oferta.setEstado(documentSnapshot.getBoolean("estado"));
                    oferta.setId(documentSnapshot.getId());
                    listaOfertas.add(oferta);
                }
                adapter.agregarOfertas(listaOfertas);
            }
        });
    }
}