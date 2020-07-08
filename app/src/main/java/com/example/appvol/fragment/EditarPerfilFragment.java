package com.example.appvol.fragment;

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
import android.widget.Toast;

import com.example.appvol.R;
import com.example.appvol.model.Voluntario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditarPerfilFragment extends Fragment {

    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference voluntarios = FireStore.collection("Voluntarios");
    String userid, useremail;
    private TextInputLayout etNombre, etApellidos, etEmail, etCelular, etIntereses, etOcupacion, etExperiencia, etDocumento;
    private Button btnActualizarPerfil;
    private NavController navegador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        etNombre = view.findViewById(R.id.ti_perfil_nombre);
        etApellidos = view.findViewById(R.id.ti_perfil_apellidos);
        etEmail = view.findViewById(R.id.ti_perfil_email);
        etCelular = view.findViewById(R.id.ti_perfil_telefono);
        etIntereses = view.findViewById(R.id.ti_perfil_intereses);
        etOcupacion = view.findViewById(R.id.ti_perfil_ocupacion);
        etExperiencia = view.findViewById(R.id.ti_perfil_experiencia);
        etDocumento = view.findViewById(R.id.ti_perfil_documento);
        btnActualizarPerfil = view.findViewById(R.id.btn_editar_perfil);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navegador = Navigation.findNavController(view);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();
        useremail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        recuperarInfoVoluntario();
        btnActualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarPerfil();
            }
        });
    }

    private void actualizarPerfil() {
        Voluntario voluntario = new Voluntario();
        voluntario.setNombres(etNombre.getEditText().getText().toString());
        voluntario.setApellidos(etApellidos.getEditText().getText().toString());
        voluntario.setTelefono(etCelular.getEditText().getText().toString());
        voluntario.setDocumento(etDocumento.getEditText().getText().toString());
        voluntario.setExperiencia(etExperiencia.getEditText().getText().toString());
        voluntario.setIntereses(etIntereses.getEditText().getText().toString());
        voluntario.setOcupacion(etOcupacion.getEditText().getText().toString());

        DocumentReference documentReference = voluntarios.document(userid);
        documentReference.update("apellidos", voluntario.getApellidos());
        documentReference.update("documento", voluntario.getDocumento());
        documentReference.update("experiencia", voluntario.getExperiencia());
        documentReference.update("intereses", voluntario.getIntereses());
        documentReference.update("nombre", voluntario.getNombres());
        documentReference.update("ocupacion", voluntario.getOcupacion());
        documentReference.update("telefono", voluntario.getTelefono()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Se guardaron los cambios", Toast.LENGTH_SHORT).show();
                navegador.navigate(R.id.action_nav_editar_perfil_frag_to_nav_ofertas_frag);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Se produjo un error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recuperarInfoVoluntario() {
        final DocumentReference document = voluntarios.document(userid);
        document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Voluntario voluntario = new Voluntario();
                voluntario.setNombres(documentSnapshot.getString("nombre"));
                voluntario.setApellidos(documentSnapshot.getString("apellidos"));
                voluntario.setTelefono(documentSnapshot.getString("telefono"));
                voluntario.setIntereses(documentSnapshot.getString("intereses"));
                voluntario.setOcupacion(documentSnapshot.getString("ocupacion"));
                voluntario.setExperiencia(documentSnapshot.getString("experiencia"));
                voluntario.setDocumento(documentSnapshot.getString("documento"));
                mostrarDatos(voluntario);
            }
        });
    }

    private void mostrarDatos(Voluntario voluntario) {
        etNombre.getEditText().setText(voluntario.getNombres());
        etApellidos.getEditText().setText(voluntario.getApellidos());
        etEmail.getEditText().setText(useremail);
        etEmail.getEditText().setEnabled(false);
        etCelular.getEditText().setText(voluntario.getTelefono());
        etDocumento.getEditText().setText(voluntario.getDocumento());
        etIntereses.getEditText().setText(voluntario.getIntereses());
        etOcupacion.getEditText().setText(voluntario.getOcupacion());
        etExperiencia.getEditText().setText(voluntario.getExperiencia());
    }
}