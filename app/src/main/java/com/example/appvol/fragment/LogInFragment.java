package com.example.appvol.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appvol.LoginActivity;
import com.example.appvol.MainActivity;
import com.example.appvol.OrganizacionActivity;
import com.example.appvol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInFragment extends Fragment {

    private EditText inputEmail, inputPassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    NavController navController;
    private CheckBox checkBoxOrg;
    private FirebaseFirestore FireStore = FirebaseFirestore.getInstance();
    private CollectionReference org = FireStore.collection("Organizaciones");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        //Obtener instancia de autenticación de Firebase

        // Si la instancia es distinta de null
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }
        // Obtener la referencia de los controles
        inputEmail = view.findViewById(R.id.email);
        inputPassword = view.findViewById(R.id.password);
        progressBar = view.findViewById(R.id.progressBar);
        btnSignup = view.findViewById(R.id.btn_signup);
        btnLogin = view.findViewById(R.id.btn_login);
        btnReset = view.findViewById(R.id.btn_reset_password);
        checkBoxOrg = view.findViewById(R.id.chkBoxOrg);
        // Obtener instancia de autenticación de Firebase
        auth = FirebaseAuth.getInstance();
        // Clic del botón registrar en la aplicación
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(LogInFragmentDirections.actionLogInFragmentToRegistroFragment());
            }
        });
        // Clic en el boton para resetear la contraseña del usuario
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));*/
                inputEmail.setText("");
                inputPassword.setText("");
            }
        });
        // Clic para acceder
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los editText
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                // Validar si el logín a sido ingresado
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "¡Introducir la dirección de correo electrónico!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Validar si se ingreso la constraseña
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "¡Introducir la contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // ProgressBar visible
                progressBar.setVisibility(View.VISIBLE);
                // Autenticar si el usuario existe
                autenticarUser(email,password);
                }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public void autenticarUser(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
//                            if (checkBoxOrg.isChecked()){
//                                String orgID = auth.getCurrentUser().getUid();
//                                org.document(orgID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                    @Override
//                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                        //validar si es una org
//                                        if (documentSnapshot.contains("rol")) {
//                                            Intent intent = new Intent(getContext(), OrganizacionActivity.class);
//                                            startActivity(intent);
//                                        } else {
//                                            //si no es org pero igual esta registrado
//                                            Toast.makeText(getContext(), "No es una organizacion", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            startActivity(intent);
                                            progressBar.setVisibility(View.GONE);
                                        //}
 //                                   }
 //                               });
//                            } else {
//                                //Usuario voluntario
//                                Intent intent = new Intent(getContext(), MainActivity.class);
//                                startActivity(intent);
//                            }
                        } else {
                            Toast.makeText(getContext(), "No esta registrado", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}