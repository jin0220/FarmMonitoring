package com.example.farm_monitoring.ui.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farm_monitoring.data.PreferenceManager;
import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.User;
import com.example.farm_monitoring.ui.UserViewModel;

public class AccountActivity extends AppCompatActivity {
    EditText name, email, phone, farm_id, pw, pwc;
    TextView id, user;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("개인 정보 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = findViewById(R.id.user);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        farm_id = findViewById(R.id.farm_id);
        id = findViewById(R.id.id);

        pw = findViewById(R.id.pw);
        pwc = findViewById(R.id.pwc);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        
        // 오류 - 자동 로그인을 하지 않으면 PreferenceManager에 값이 저장 되어있지 않으므로 실행히 잘 되지 않음
        viewModel.login(PreferenceManager.getString(this,"id"),
                            PreferenceManager.getString(this,"pw"));
        viewModel.response.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User dbuser) {
                user.setText(dbuser.getUsername() + "님의 게정");
                name.setText(dbuser.getUsername());
                email.setText(dbuser.getEmail());
                phone.setText(dbuser.getPhone());
                farm_id.setText(dbuser.getFarm_id());
                id.setText(dbuser.getId());
            }
        });
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
                upDate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void upDate(){
        String name, email, phone, farm_id, pw, pwc, id;

        name = this.name.getText().toString();
        email = this.email.getText().toString();
        phone = this.phone.getText().toString();
        farm_id = this.farm_id.getText().toString();
        pw = this.pw.getText().toString();
        pwc = this.pwc.getText().toString();
        id = this.id.getText().toString();

        if(pw.isEmpty()){
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(pwc.isEmpty()){
            Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(!pw.equals(pwc)) {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            viewModel.account(id, farm_id, name, email, phone, pw);
            Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}