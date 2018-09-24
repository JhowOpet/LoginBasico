package com.example.loginbasico;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginbasico.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Initial extends AppCompatActivity {

    private TextView textNome;
    private TextView textLogin;
    private ImageView imgPerfil;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        textNome = findViewById(R.id.textNome);
        textLogin = findViewById(R.id.textLogin);
        imgPerfil = findViewById(R.id.imgPerfil);

        if (currentUser != null) {
            String email = currentUser.getEmail();
            String uid = currentUser.getUid();

            // recuperarDadosUsuario();

            textLogin.setText("Email: " + email);
        }
    }

    public void sair(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
