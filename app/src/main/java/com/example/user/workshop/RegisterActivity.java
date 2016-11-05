package com.example.user.workshop;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUserR;
    private EditText etPassR;
    private EditText etPassRC;
    private Button btnRegister;
    private EditText etDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDis =(EditText)findViewById(R.id.etDis);
        etUserR =(EditText)findViewById(R.id.etUserR);
        etPassR =(EditText)findViewById(R.id.etPassR);
        etPassRC =(EditText)findViewById(R.id.etPassRC);
        btnRegister =(Button)findViewById(R.id.btnRegister);

        setListener();
        vadidate();
    }




    private void setListener() {
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(vadidate()){

                    new Register(etUserR.getText().toString(),
                            etPassR.getText().toString(),
                            etPassRC.getText().toString(),
                            etDis.getText().toString())
                            .execute();
                }else{
                    Toast.makeText(RegisterActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }

                                           }

                                       }
        );
        }
    private boolean vadidate() {
        String username = etUserR.getText().toString();
        String password = etPassR.getText().toString();
        String passwordConfrim = etPassRC.getText().toString();
        String displayName = etDis.getText().toString();

        if (displayName.isEmpty())
            return  false;
        if (username.isEmpty())
            return  false;
        if (password.isEmpty())
            return false;
        if (passwordConfrim.isEmpty())
            return false;
        if (!password.equals(passwordConfrim))
            return false;
        return true;

    }

    private class Register extends AsyncTask<Void, Void ,String>{
        private String username;
        private String password;
        private String passwordconfrim;
        private String display;


        public Register(String username, String password, String passwordconfrim, String display) {
            this.username = username;
            this.password = password;
            this.passwordconfrim = passwordconfrim;
            this.display = display;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;
            //สร้างตัวส่งdata
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .add("password_con",passwordconfrim)
                    .add("display_name" , display)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/signup.php")
                    .post(requestBody)
                    .build();

            try{

                response = client.newCall(request).execute();
                if(response.isSuccessful()) {
                    return  response.body().string();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootOdj = new JSONObject(s);
                if(rootOdj.has("result")){
                    JSONObject resultObj = rootOdj.getJSONObject("result");
                    if (resultObj.getInt("result")==1){
                        Toast.makeText(RegisterActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException ex){

            }

        }


    }

    }

