package com.example.loginbasico;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginbasico.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;

public class SignUp extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private EditText editNome;
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editEmail  = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        editNome = findViewById(R.id.editNome);
        mAuth = FirebaseAuth.getInstance();
    }

    public void criar(View view){
        String sLogin = editEmail.getText().toString();
        String sSenha = editSenha.getText().toString();
        String sNome = editNome.getText().toString();
        mAuth.createUserWithEmailAndPassword(sLogin, sSenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Usuario criado com sucesso!", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
/*
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(sNome)
                                    .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.e("Nome e foto adicionado ", "User profile updated.");
                                            } else{

                                            }
                                        }
                                    });
                                    */

                            

                            Intent intent = new Intent(SignUp.this, Initial.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(SignUp.this, "Falha na criação de usuário.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void criarNovoUsuario(String userId, String nome, Uri uriFoto) {
        User user = new User(nome, uriFoto);

        mDatabase.child("users").child(userId).setValue(user);
    }

}
