package com.example.appvol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.appvol.adapter.PostulantesAdapter;
import com.example.appvol.model.Oferta;
import com.example.appvol.model.Voluntario;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VerPostulantesActivity extends AppCompatActivity {


    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference ofertas = FireStore.collection("Ofertas");
    private CollectionReference voluntarios = FireStore.collection("Voluntarios");
    ArrayList<Voluntario> list;
    private RecyclerView rvPostulantes;
    private PostulantesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_postulantes);
        Intent intent = getIntent();
        Oferta oferta = new Oferta();

        if (intent.hasExtra("oferta")){
            oferta = intent.getParcelableExtra("oferta");
        }
        rvPostulantes = findViewById(R.id.rvPostulantes);
        rvPostulantes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Voluntario>();
        assert oferta != null;
        String idOferta = oferta.getId();
        getPostulantes(idOferta);
        adapter = new PostulantesAdapter(this);
        rvPostulantes.setAdapter(adapter);

    }

/*    public void getPostulantes(String idOferta){
        ofertas.document(idOferta).collection("Postulantes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    final String voluntarioId = documentSnapshot.getString("voluntario");
                    voluntarios.document(voluntarioId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String nombre = documentSnapshot.getString("nombre");
                            if (nombre != null) {
                                String apellidos = documentSnapshot.getString("apellidos");
                                Voluntario voluntario = new Voluntario();
                                voluntario.setNombres(nombre);
                                voluntario.setApellidos(apellidos);
                                list.add(voluntario);
                            }
                        }
                    });
                }
               // adapter.agregarDatos(list);
            }

        });
    }*/

    public void getPostulantes(String idOferta){
        ofertas.document(idOferta).collection("Postulantes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    String nombre = documentSnapshot.getString("nombres");
                    String intereses = documentSnapshot.getString("intereses");
                    String experiencia = documentSnapshot.getString("experiencia");
                    Voluntario voluntario = new Voluntario();
                    voluntario.setNombres(nombre);
                    voluntario.setIntereses(intereses);
                    voluntario.setExperiencia(experiencia);
                    list.add(voluntario);
                }
                adapter.agregarDatos(list);
            }
        });
    }
}