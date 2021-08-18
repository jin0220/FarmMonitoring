package com.example.farm_monitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.LoginRequest;
import com.example.farm_monitoring.request.SignUpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    TextView id, name, password, password_c, email, phone, farm_id;
    Spinner category1, category2, category3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("회원가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.category1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        id = findViewById(R.id.userId);
        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password_c = findViewById(R.id.password_c);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        farm_id = findViewById(R.id.farm_id);

        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp(){
        String id, name, password, password_c, email, phone, farm_id, category1, category2, category3;

        id = this.id.getText().toString();
        name = this.name.getText().toString();
        password = this.password.getText().toString();
        password_c = this.password_c.getText().toString();
        email = this.email.getText().toString();
        phone = this.phone.getText().toString();
        farm_id = this.farm_id.getText().toString();

        category1 = this.category1.getSelectedItem().toString();
        category2 = this.category2.getSelectedItem().toString();
        category3 = this.category3.getSelectedItem().toString();

        if(id.isEmpty()) {

        }else if(!password.equals(password_c)){
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            Toast.makeText(getApplicationContext(), "회원가입을 축하드립니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show();

                            setResult(RESULT_OK);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입에 실패 하셨습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            SignUpRequest signUpRequest = new SignUpRequest(id, name, password, email, phone, farm_id, category1, category2, category3, responseListener);
            RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
            queue.add(signUpRequest);
        }
    }
}