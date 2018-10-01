package com.example.opet.recordapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by opet on 01/10/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText editNome;
    EditText editEmail;
    EditText editSenha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        editNome = findViewById(R.id.editNome);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void loginUsuario(View v) {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "Boa", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Péssima", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

}
