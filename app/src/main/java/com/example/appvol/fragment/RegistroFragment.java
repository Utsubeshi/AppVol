package com.example.appvol.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appvol.MainActivity;
import com.example.appvol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroFragment extends Fragment {

    private TextInputLayout etNOmbre, etApellido, etEmail, etPass, etPass2;
    private Button btnRegistro;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        etNOmbre = view.findViewById(R.id.ti_registro_nombre);
        etApellido = view.findViewById(R.id.ti_registro_apellidos);
        etEmail = view.findViewById(R.id.ti_registro_email);
        etPass = view.findViewById(R.id.ti_registro_pass);
        etPass2 = view.findViewById(R.id.ti_registro_repetir_pass);
        btnRegistro = view.findViewById(R.id.btn_registrarse);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUser();
            }
        });

    }

    public void registrarUser (){ //Data
        final String nombre = etNOmbre.getEditText().getText().toString();
        final String apellidos = etApellido.getEditText().getText().toString();
        String email = etEmail.getEditText().getText().toString();
        String pass = etPass.getEditText().getText().toString();
        String pass2 = etPass2.getEditText().getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                    Map<String, Object> voluntario = new HashMap<>();
                    voluntario.put("nombre",nombre);
                    voluntario.put("apellidos", apellidos);
                    String idVolutario = firebaseAuth.getCurrentUser().getUid();
                    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
                    fStore.collection("Usuarios")
                            .document(idVolutario)
                            .set(voluntario)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(),
                                            "Usuario registrado",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
            }
        });

//        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Map<String, Object> voluntario = new HashMap<>();
//                    voluntario.put("nombre",nombre);
//                    voluntario.put("apellidos", apellidos);
//                    Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
//                    String idVolutario = firebaseAuth.getCurrentUser().getUid();
//                    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
//                    CollectionReference voluntarios = fStore.collection("Voluntarios");
//                    voluntarios.document(idVolutario).set(voluntario).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//
//                        }
//                    });
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getActivity(), "Hubo un error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}