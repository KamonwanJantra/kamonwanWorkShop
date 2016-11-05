package com.example.user.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.user.workshop.R.id.btnLogin;
import static com.example.user.workshop.R.id.etUse;

public class Login extends AppCompatActivity {

    private EditText etUse;
    private EditText etPass;
    private Button btnLogin;
    private Button btnRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       etUse =(EditText)findViewById(R.id.etUse);
        etPass =(EditText)findViewById(R.id.etPass);
        btnLogin =(Button)findViewById(R.id.btnLogin);
        btnRe =(Button)findViewById(R.id.btnRe);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this,NewListActivity.class);
                startActivity(i);
            }
        });
        btnRe.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        }
    }

