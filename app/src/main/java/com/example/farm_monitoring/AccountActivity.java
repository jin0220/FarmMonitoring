package com.example.farm_monitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountActivity extends AppCompatActivity {
    EditText name, email, phone, farm_id;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("개인 정보 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        farm_id = findViewById(R.id.farm_id);
        id = findViewById(R.id.id);

        dateLoad();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.store:
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dateLoad(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        String user_id = jsonObject.getString("id");
                        String user_name = jsonObject.getString("username");
                        String user_email = jsonObject.getString("email");
                        String user_phone = jsonObject.getString("phone");
                        String user_farm_id = jsonObject.getString("farm_id");

                        name.setText(user_name);
                        email.setText(user_email);
                        phone.setText(user_phone);
                        farm_id.setText(user_farm_id);
                        id.setText(user_id);

                    } else {
                        Toast.makeText(getApplicationContext(), "등록되지 않은 아이디거나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(PreferenceManager.getString(this,"id"), PreferenceManager.getString(this,"pw"), responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(loginRequest);
    }
}