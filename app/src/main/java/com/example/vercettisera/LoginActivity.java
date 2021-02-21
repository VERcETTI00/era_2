package com.example.vercettisera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
    ProgressBar loginProgressBar;
    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginProgressBar = findViewById(R.id.login_progress);
        loginProgressBar.setVisibility(View.GONE);
        emailTextInput = findViewById(R.id.email);
        passwordTextInput = findViewById(R.id.password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextInput.getText().toString();
                String pass = passwordTextInput.getText().toString();
                if(!(email.isEmpty()&&pass.isEmpty()))
                loginClick(email, pass);
                else
                    Toast.makeText(LoginActivity.this,"Fields can't be Empty",Toast.LENGTH_SHORT).show();
            }
        });

        Button regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginClick(String email, String pass){
        button.setVisibility(View.GONE);
        loginProgressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                button.setVisibility(View.VISIBLE);
                loginProgressBar.setVisibility(View.GONE);
                makeText(getApplicationContext(),"Authenticated Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                button.setVisibility(View.VISIBLE);
                loginProgressBar.setVisibility(View.GONE);
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