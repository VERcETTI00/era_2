package com.example.vercettisera;

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

import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity {

    EditText emailTextInput;
    EditText passwordTextInput;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        if (email.equals(currentUser.email) && pass.equals(currentUser.password)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            makeText(getApplicationContext(),"Authenticated Successfully",Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        DetailsProvider detailsProvider = new DetailsProvider();
        currentUser = detailsProvider.getDetails(this);
        if(currentUser.email.equals("0")){
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}