package com.crud.singl.eyehealth.startup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buttonLogin_onClick(View view){
        try{
            Account_en accountEn = new Account_en();
            accountEn.setEmail(editTextEmail.getText().toString());
            accountEn.setPassword(editTextPassword.getText().toString());
            AccountService accountService = APIClient.getClient().create(AccountService.class);
            Call call = accountService.create(accountEn);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Boolean result = (Boolean) response.body();
                    if(result){
                        Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                        intent.putExtra("email", editTextEmail.getText());
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.failed), Toast.LENGTH_SHORT).show();
                    }
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
