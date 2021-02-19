package com.example.vercettisera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.username_home);
        email = findViewById(R.id.email_home);
        phone = findViewById(R.id.phone_home);
        Button signOut = findViewById(R.id.signOut);

        DetailsProvider detailsProvider = new DetailsProvider();
        User currentUser = detailsProvider.getDetails(this);

        name.setText(currentUser.name);
        email.setText(currentUser.email);
        phone.setText(currentUser.phone);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
