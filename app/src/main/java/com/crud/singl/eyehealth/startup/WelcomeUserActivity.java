package com.crud.singl.eyehealth.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crud.singl.eyehealth.R;

import org.w3c.dom.Text;

public class WelcomeUserActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonLogout, buttonChangeProfile;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        textViewWelcome = findViewById(R.id.textVieWelcome);
        buttonLogout = findViewById(R.id.buttonlogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeUserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonChangeProfile = findViewById(R.id.buttonChangeProfile);
        buttonChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeUserActivity.this, ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData(){
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        textViewWelcome.setText(getString(R.string.welcome) + " " + email);
    }
}
