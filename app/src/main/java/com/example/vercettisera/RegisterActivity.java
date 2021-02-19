package com.example.vercettisera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.makeText;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText regEmail;
    EditText phoneNumber;
    EditText resPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        regEmail = findViewById(R.id.regEmail);
        phoneNumber = findViewById(R.id.phoneNumber);
        resPass = findViewById(R.id.regPassword);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String email = regEmail.getText().toString();
                String phNum = phoneNumber.getText().toString();
                String pass = resPass.getText().toString();
                loginClick(user,email,phNum,pass);
            }
        });
    }

    public void loginClick(String username, String email, String phoneNumber, String pass){
        if (!username.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !pass.isEmpty()) {

            User user = new User(email,pass,phoneNumber,username);
            DetailsProvider detailsProvider = new DetailsProvider();
            detailsProvider.saveDetails(user,this);
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
            makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast makeText;
            makeText(getApplicationContext(),"Fill all the details",Toast.LENGTH_SHORT).show();
        }
    }
}
