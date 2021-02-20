package com.example.vercettisera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity {

    EditText emailTextInput;
    EditText passwordTextInput;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        emailTextInput = findViewById(R.id.email);
        passwordTextInput = findViewById(R.id.password);

        ImageButton button = findViewById(R.id.button);


            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String email = emailTextInput.getText().toString();
                    String pass = passwordTextInput.getText().toString();
                    loginClick(email,pass);
                }
            });

        Button regButton = (Button)findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
    public void loginClick(String email, String pass){
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                makeText(getApplicationContext(),"Authenticated Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!(auth.getCurrentUser()==null))
            startActivity(new Intent(this,HomeActivity.class));
    }
}