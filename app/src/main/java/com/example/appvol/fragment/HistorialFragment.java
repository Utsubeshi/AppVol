package com.example.appvol.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appvol.R;
import com.example.appvol.adapter.PostulacionesAdapter;
import com.example.appvol.model.Oferta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistorialFragment extends Fragment {

    RecyclerView rvPostulaciones;
    private ArrayList<Oferta> listaPostulaciones;
    PostulacionesAdapter adapter;
    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference ofertas = FireStore.collection("Ofertas");
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);
        rvPostulaciones = view.findViewById(R.id.rvPostulaciones);
        rvPostulaciones.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostulacionesAdapter(getContext());
        listaPostulaciones = new ArrayList<Oferta>();
        getPostulaciones();
        rvPostulaciones.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void getPostulaciones(){
        final String userId = fAuth.getCurrentUser().getUid();
        ofertas.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots){
                    queryDocumentSnapshot.contains("Postulantes");
                    queryDocumentSnapshot.contains("Postulantes");
                }
            }
        });
        adapter.agregarDatos(listaPostulaciones);
    }
}