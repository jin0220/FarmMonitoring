package com.example.farm_monitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    Fragment home, plant, mypage;
    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new HomeFragment().newInstance("home","home");
        plant = new PlantStateFragment().newInstance("plant","plant");
        mypage = new MypageFragment().newInstance("mypage", "mypage");
        getSupportFragmentManager().beginTransaction().add(R.id.fram_fragment, home).commit();

        //하단 메뉴
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_fragment, home).commit();
                        return true;
                    case R.id.plant:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_fragment, plant).commit();
                        return true;
                    case R.id.my:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_fragment, mypage).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && gapTime <= 2000) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}