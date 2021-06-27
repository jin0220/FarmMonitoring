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

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;

    private String[] titles = new String[]{"모니터링", "커뮤니티"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment monitoring = new MonitoringFragment().newInstance("1","1-1");
        Fragment community = new CommunityFragment().newInstance("2","2-1");

        viewPager2 = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);

        pagerAdapter = new PagerAdapter(this);
        pagerAdapter.addFrag(monitoring);
        pagerAdapter.addFrag(community);

        viewPager2.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> tab.setText(titles[position])).attach();
    }

}