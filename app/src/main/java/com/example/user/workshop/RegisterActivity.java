package com.example.user.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUseR;
    private EditText etPassR;
    private EditText etPassRC;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUseR =(EditText)findViewById(R.id.etUserR);
        etPassR =(EditText)findViewById(R.id.etPassR);
        etPassRC =(EditText)findViewById(R.id.etPassRC);
        btnRegister =(Button)findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,Login.class);
                startActivity(i);
            }

        }
        );
    }
}
