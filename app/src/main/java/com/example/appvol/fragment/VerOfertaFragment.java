package com.example.appvol.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appvol.OrganizacionActivity;
import com.example.appvol.R;
import com.example.appvol.model.Oferta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class VerOfertaFragment extends Fragment {

    private TextView tvTitulo, tvEstado, tvDetalle, tvcantidadMin, tvFecha, tvOrganizacion, tvtiempoMin, tvTipo, tvUbicacion;
    private ImageView ivOfertaImagen;
    private Button btnPostular;
    private Oferta oferta;
    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference ofertas = FireStore.collection("Ofertas");
    private CollectionReference voluntarios = FireStore.collection("Voluntarios");
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_oferta, container, false);
        tvFecha = view.findViewById(R.id.tv_detalle_oferta_fecha);
        tvTitulo = view.findViewById(R.id.tv_detalle_oferta_titulo);
        tvEstado = view.findViewById(R.id.tv_detalle_oferta_estado);
        tvDetalle = view.findViewById(R.id.tv_detalle_oferta_detalle);
        tvcantidadMin = view.findViewById(R.id.tv_detalle_oferta_cantidadMin);
        tvOrganizacion = view.findViewById(R.id.tv_detalle_oferta_organizacion);
        tvtiempoMin = view.findViewById(R.id.tv_detalle_oferta_tiempoMin);
        tvTipo = view.findViewById(R.id.tv_detalle_oferta_tipo);
        ivOfertaImagen = view.findViewById(R.id.tv_detalle_oferta_imagen);
        tvUbicacion = view.findViewById(R.id.tv_detalle_oferta_ubicacion);
        btnPostular = view.findViewById(R.id.btn_oferta_postular);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userid = user.getUid();
            VerOfertaFragmentArgs args = VerOfertaFragmentArgs.fromBundle(getArguments());
            oferta = args.getOferta();
            tvFecha.setText(oferta.getFecha());
            tvTitulo.setText(oferta.getTitulo());
            String estado = "Cerrado/No Disponible";
            if(oferta.isEstado()){
                estado = "Disponible";
            }
            tvEstado.setText(estado);
            tvDetalle.setText(oferta.getDetalle());
            tvcantidadMin.setText(String.valueOf(oferta.getCantidadMin()));
            tvOrganizacion.setText(oferta.getOrganizacion());
            tvtiempoMin.setText(oferta.getTiempoMin());
            tvTipo.setText(oferta.getTipo());
            tvUbicacion.setText(oferta.getUbicacion());
            //String url = "https://portal.andina.pe/EDPfotografia3/Thumbnail/2019/06/22/000595226W.jpg";
            Picasso.get().load(oferta.getUrlImagen()).into(ivOfertaImagen);
            validarPostulacion();
            btnPostular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    voluntarios.document(userid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String nombre = documentSnapshot.getString("nombre");
                            String apellidos = documentSnapshot.getString("apellidos");
                            String nombres = nombre + " " + apellidos;
                            String experiencia = documentSnapshot.getString("experiencia");
                            String intereses = documentSnapshot.getString("intereses");
                            Map<String, Object> postulacion = new HashMap<>();
                            postulacion.put("voluntario", userid);
                            postulacion.put("nombres", nombres);
                            postulacion.put("experiencia", experiencia);
                            postulacion.put("intereses", intereses);
                            ofertas.document(oferta.getId()).collection("Postulantes").document().set(postulacion);
                            Toast.makeText(getContext(), "Gracias por su postulacion", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.nav_ofertas_frag);
                        }
                    });
                }
            });
        }
    }

    //si ya esta registrado en esta oferta deshabilita el boton
    public void validarPostulacion(){
        Task<QuerySnapshot> documentReference = ofertas.document(oferta.getId())
                .collection("Postulantes")
                .whereEqualTo("voluntario", userid).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                           if (documentSnapshot != null){
                               btnPostular.setEnabled(false);
                               return;
                           }
                        }
                    }
                });

    }

}


