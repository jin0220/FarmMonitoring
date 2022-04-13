package com.example.farm_monitoring.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farm_monitoring.ui.home.MonitoringFragment;
import com.example.farm_monitoring.ui.home.community.CommunityFragment;
import com.example.farm_monitoring.ui.home.information.InformationFragment;

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
