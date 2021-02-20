package com.example.vercettisera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView phone;
    User user;
    FirebaseAuth auth;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();
        name = findViewById(R.id.username_home);
        email = findViewById(R.id.email_home);
        phone = findViewById(R.id.phone_home);
        Button signOut = findViewById(R.id.signOut);
        getUser();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                auth.signOut();
                startActivity(intent);
                finish();
            }
        });
    }
    public void getUser(){
        FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String,Object> data = documentSnapshot.getData();
                name.setText(data.get("name").toString());
                email.setText(data.get("email").toString());
                phone.setText(data.get("phone").toString());
            }
        });
    }
}
