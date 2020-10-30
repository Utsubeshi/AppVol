package com.example.appvol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.appvol.adapter.OfertasAdapter;
import com.example.appvol.adapter.OrgOfertaAdapter;
import com.example.appvol.model.Oferta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OrganizacionActivity extends AppCompatActivity {

    private RecyclerView rvOfertas;
    private ArrayList<Oferta> listOferta;
    private OrgOfertaAdapter adapter;
    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference coleccion = FireStore.collection("Ofertas");
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizacion);
        rvOfertas = findViewById(R.id.rvOfertasOrg);
        rvOfertas.setLayoutManager(new LinearLayoutManager(this));
        listOferta = new ArrayList<Oferta>();
        adapter = new OrgOfertaAdapter(this);
        getOfertasPorOrg();
        rvOfertas.setAdapter(adapter);
    }

    public void getOfertasPorOrg () {
        coleccion.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null ){
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    String userID = auth.getCurrentUser().getUid();
                    String orgid = documentSnapshot.getString("idOrg");
                    if (userID.equals(orgid)) {
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
                        listOferta.add(oferta);
                    }
                }
                adapter.agregarOfertas(listOferta);
            }
        });
    }
}
