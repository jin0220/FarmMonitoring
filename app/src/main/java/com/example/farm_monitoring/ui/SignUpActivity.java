package com.example.farm_monitoring.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farm_monitoring.R;
import com.example.farm_monitoring.ui.UserViewModel;

public class SignUpActivity extends AppCompatActivity {

    TextView id, name, password, password_c, email, phone, farm_id;
    Spinner category1, category2, category3;
    ArrayAdapter<CharSequence> adapter_category1, adapter_category2, adapter_category3;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("회원가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        spinner();

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void spinner(){
        adapter_category1 = ArrayAdapter.createFromResource(this, R.array.category1, android.R.layout.simple_spinner_item);
        adapter_category1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category1.setAdapter(adapter_category1);
        category1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    int p = 0;
                    switch (position){
                        case 1:
                            p = R.array.category2_1; break;
                        case 2:
                            p = R.array.category2_2; break;
                        case 3:
                            p = R.array.category2_3; break;
                        case 4:
                            p = R.array.category2_4; break;
                        case 5:
                            p = R.array.category2_5; break;
                    }
                    adapter_category2 = ArrayAdapter.createFromResource(getApplicationContext(),
                            p, android.R.layout.simple_spinner_item);
                    adapter_category2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    category2.setAdapter(adapter_category2);

                    int finalP = p;
                    category2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position > 0) {
                                int p2 = 0;
                                if (finalP == R.array.category2_1) {
                                    switch (position) {
                                        case 1:
                                            p2 = R.array.category3_2_1_1;
                                            break;
                                        case 2:
                                            p2 = R.array.category3_2_1_2;
                                            break;
                                        case 3:
                                            p2 = R.array.category3_2_1_3;
                                            break;
                                        case 4:
                                            p2 = R.array.category3_2_1_4;
                                            break;
                                        case 5:
                                            p2 = R.array.category3_2_1_5;
                                            break;
                                    }
                                } else if (finalP == R.array.category2_2) {
                                    switch (position) {
                                        case 1:
                                            p2 = R.array.category3_2_2_1;
                                            break;
                                        case 2:
                                            p2 = R.array.category3_2_2_2;
                                            break;
                                        case 3:
                                            p2 = R.array.category3_2_2_3;
                                            break;
                                    }
                                } else if (finalP == R.array.category2_3) {
                                    switch (position) {
                                        case 1:
                                            p2 = R.array.category3_2_3_1;
                                            break;
                                        case 2:
                                            p2 = R.array.category3_2_3_2;
                                            break;
                                        case 3:
                                            p2 = R.array.category3_2_3_3;
                                            break;
                                        case 4:
                                            p2 = R.array.category3_2_3_4;
                                            break;
                                    }
                                } else if (finalP == R.array.category2_4) {
                                    switch (position) {
                                        case 1:
                                            p2 = R.array.category3_2_4_1;
                                            break;
                                        case 2:
                                            p2 = R.array.category3_2_4_2;
                                            break;
                                        case 3:
                                            p2 = R.array.category3_2_4_3;
                                            break;
                                        case 4:
                                            p2 = R.array.category3_2_4_4;
                                            break;
                                    }
                                } else if (finalP == R.array.category2_5) {
                                    switch (position) {
                                        case 1:
                                            p2 = R.array.category3_2_5_1;
                                            break;
                                        case 2:
                                            p2 = R.array.category3_2_5_2;
                                            break;
                                        case 3:
                                            p2 = R.array.category3_2_5_3;
                                            break;
                                    }
                                }
                                adapter_category3 = ArrayAdapter.createFromResource(getApplicationContext(),
                                        p2, android.R.layout.simple_spinner_item);
                                adapter_category3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                category3.setAdapter(adapter_category3);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
            viewModel.register(id, farm_id, name, email, phone, password, category1, category2, category3);
            Toast.makeText(getApplicationContext(), "회원가입을 축하드립니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}