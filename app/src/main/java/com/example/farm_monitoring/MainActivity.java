package com.example.farm_monitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

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

    // 리스너 생성
    public interface onBackPressedListener{
        void onBackPressed();
    }

    // 리스너 객체 생성
    private onBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(onBackPressedListener listener) {
        mBackListener = listener;
    }

    @Override
    public void onBackPressed() {
//        if(mBackListener != null) {
//            mBackListener.onBack();
//            Log.e("!!!", "Listener is not null");
//            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
//            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
//        } else {
//            Log.e("!!!", "Listener is null");
//            if ( pressedTime == 0 ) {
//                Snackbar.make(findViewById(R.id.main_layout),
//                        " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
//                pressedTime = System.currentTimeMillis();
//            }
//            else {
//                int seconds = (int) (System.currentTimeMillis() - pressedTime);
//
//                if ( seconds > 2000 ) {
//                    Snackbar.make(findViewById(R.id.main_layout),
//                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
//                    pressedTime = 0 ;
//                }
//                else {
//                    super.onBackPressed();
//                    Log.e("!!!", "onBackPressed : finish, killProcess");
//                    finish();
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                }
//            }
//        }

        //프래그먼트 onBackPressedListener사용
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragmentList){
            if(fragment instanceof onBackPressedListener){
                ((onBackPressedListener)fragment).onBackPressed();
                return;
            }
        }

        //두 번 클릭시 어플 종료
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