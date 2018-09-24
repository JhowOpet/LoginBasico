package com.example.loginbasico;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        editSenha = findViewById(R.id.editSenha);
        editEmail = findViewById(R.id.editEmail);
    }

    public void logar(View view){
        String sEmail = editEmail.getText().toString();
        String sSenha = editSenha.getText().toString();
        mAuth.signInWithEmailAndPassword(sEmail, sSenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MainActivity.this, Initial.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(MainActivity.this, "Falha no login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void telaSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

}
