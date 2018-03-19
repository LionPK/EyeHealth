package com.crud.singl.eyehealth.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.entities.Account_en;
import com.crud.singl.eyehealth.service.APIClient;
import com.crud.singl.eyehealth.service.AccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSave, buttonCancel;
    private String email;
    private Account_en accountEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.change_password);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, WelcomeUserActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        loadData();
    }

    private void buttonSave_onClick(View view){
        try{
            String password = editTextPassword.getText().toString();
            if (password.isEmpty()){
                accountEn.setPassword(password);
            }
            AccountService accountService = APIClient.getClient().create(AccountService.class);
            Call call = accountService.update(accountEn);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Intent intent = new Intent(ProfileActivity.this, WelcomeUserActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(), getString(R.string.failed), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        try{
            Intent intent = getIntent();
            email = intent.getStringExtra("email");
            AccountService accountService = APIClient.getClient().create(AccountService.class);
            Call call = accountService.find(email);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    accountEn = (Account_en) response.body();
                    editTextEmail.setText(accountEn.getEmail());

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(), getString(R.string.failed), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}
