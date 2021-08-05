package com.example.farm_monitoring;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.LoginRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        getSupportActionBar().setTitle("로그인");

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);

        Button sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
                login();
            }
        });
    }

    public void login(){
        String id = this.id.getText().toString();
        String password = this.password.getText().toString();
        Log.d("json", "111 : " + id + " " + password);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("users_table");
//                    Log.d("json", "123 : " + jsonArray);
//                    if(){
                    jsonObject = jsonArray.getJSONObject(0);
                        String user_id = jsonObject.getString("id");
                        String user_pw = jsonObject.getString("password");
                    Log.d("json", "222 : " + user_id + " " + user_pw);

                        Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", user_id), Toast.LENGTH_SHORT ).show();

                        Intent intent = new Intent( LoginActivity.this, MainActivity.class );

                        intent.putExtra("id", user_id);
                        intent.putExtra("password", user_pw);

                        startActivity(intent);
//                    }
//                    else{
//                        Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
//                        return;
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(id, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }
}