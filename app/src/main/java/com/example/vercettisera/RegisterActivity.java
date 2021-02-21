package com.example.vercettisera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.makeText;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText regEmail;
    EditText phoneNumber;
    EditText resPass;
    FirebaseAuth auth;
    FirebaseFirestore db;
    ImageButton nextButton;
    ProgressBar progBar;
    Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        regEmail = findViewById(R.id.regEmail);
        phoneNumber = findViewById(R.id.phoneNumber);
        resPass = findViewById(R.id.regPassword);
        progBar = findViewById(R.id.reg_progress);
        loginButton = findViewById(R.id.login_button);
        progBar.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String email = regEmail.getText().toString();
                String phNum = phoneNumber.getText().toString();
                String pass = resPass.getText().toString();
                if(!(email.isEmpty()&&pass.isEmpty()))
                    loginClick(user,email,phNum,pass);
                else
                    Toast.makeText(RegisterActivity.this,"Fields can't be Empty",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createUser(String email, String password,User user){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid  = authResult.getUser().getUid();
                saveUser(user,uid);
            }
        });
    }

    public void saveUser(User user,String uid){
        Map<String,Object> customUser = new HashMap<>();
        customUser.put("name",user.name);
        customUser.put("phone",user.phone);
        customUser.put("email",user.email);
        db.collection("users").document(uid).set(customUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void loginClick(String username, String email, String phoneNumber, String pass){
        nextButton.setVisibility(View.GONE);
        progBar.setVisibility(View.VISIBLE);
        if (!username.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !pass.isEmpty()) {
            User user = new User(email,phoneNumber,username);
            createUser(email,pass,user);
        }
        else{
            nextButton.setVisibility(View.VISIBLE);
            progBar.setVisibility(View.GONE);
            Toast makeText;
            makeText(getApplicationContext(),"Fill all the details",Toast.LENGTH_SHORT).show();
        }
    }
}