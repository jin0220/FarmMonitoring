package com.example.farm_monitoring.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.PreferenceManager;
import com.example.farm_monitoring.data.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText id, password;
    CheckBox check;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        getSupportActionBar().setTitle("로그인");

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        check = findViewById(R.id.login_check);

        if(!PreferenceManager.getString(getApplicationContext(), "id").isEmpty()) {
            Toast.makeText(getApplicationContext(), String.format("%s님 환영합니다.",
                    PreferenceManager.getString(getApplicationContext(), "id")), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Button sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String id = this.id.getText().toString();
        String password = this.password.getText().toString();

        if(id.isEmpty()){
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }else {
            viewModel.login(id, password);

            // 오류 - onChanged가 여러번 호출됨.
            viewModel.response.observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if(user.isSuccess()){
                        Toast.makeText(getApplicationContext(), String.format("%s님 환영합니다.", user.getId()), Toast.LENGTH_SHORT).show();

                        //자동 로그인 여부
                        if(check.isChecked()) {
                            loginSave(user.getId(), user.getPassword());
                        }
                        else{
                            loginRemove();
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        intent.putExtra("id", user.getId());
                        intent.putExtra("password", user.getPassword());

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "등록되지 않은 아이디거나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
    }
    //자동 로그인 - 데이터 저장
    public void loginSave(String id, String pw){
        PreferenceManager.setString(this, "id", id);
        PreferenceManager.setString(this, "pw", pw);
        PreferenceManager.setBoolean(this, "loginCheck", true);
    }

    //자동 로그인 - 데이터 삭제
    public void loginRemove(){
        PreferenceManager.removeKey(getApplicationContext(),"id");
        PreferenceManager.removeKey(getApplicationContext(),"pw");
        PreferenceManager.removeKey(getApplicationContext(),"loginCheck");
    }
}