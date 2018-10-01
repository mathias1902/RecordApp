package com.example.opet.recordapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText editNome;
    EditText editEmail;
    EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
    }

    public void cadastrar(View v){

        final String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (task.isSuccessful()) {

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nome).build();
                            user.updateProfile(profileUpdates);

                            Toast.makeText(CadastroActivity.this, "Deu certo", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                            startActivity(intent);


                        } else {

                            Toast.makeText(CadastroActivity.this, "Deu errado", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}
