package com.example.farm_monitoring;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

    List<Fragment> fragments = new ArrayList<>();

    public PagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void addFrag(Fragment fragment){
        fragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MonitoringFragment();
            case 1:
                return new CommunityFragment();
            case 2:
                return new InformationFragment();
            default:
                return new MonitoringFragment();
        }
    }

    @Override
    public int getItemCount() { //페이지 개수
        return 3;
    }
}
