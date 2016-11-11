package com.example.user.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.user.workshop.R.id.btnLogin;
import static com.example.user.workshop.R.id.etUse;

public class Login extends AppCompatActivity {

    private EditText etUse;
    private EditText etPass;
    private Button btnLogin;
    private Button btnRe;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUse = (EditText) findViewById(R.id.etUse);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRe = (Button) findViewById(R.id.btnRe);

        setListener();
    }


    private boolean setValidate() {
        String username = etUse.getText().toString();
        String password = etPass.getText().toString();

        if (username.isEmpty()) return false;
        if (password.isEmpty()) return false;
        return true;
    }

    private void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setValidate()) {
                    new login(etUse.getText().toString(),
                            etPass.getText().toString());


                } else {
                    Toast.makeText(Login.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }



    private class login extends AsyncTask<Voice, Void, String> {
        private String username;
        private String password;

        public login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Voice... voices) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;
            //สร้างตัวส่งdata
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();

            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject rootOdj = new JSONObject(s);
                if (rootOdj.has("result")) {
                    JSONObject resultObj = rootOdj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1) {
                        Toast.makeText(Login.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent(Login.this, NewListActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Login.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException ex) {

            }

        }

    }
}


