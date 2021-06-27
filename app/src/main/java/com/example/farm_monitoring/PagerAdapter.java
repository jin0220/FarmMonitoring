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

    public PagerAdapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    public void addFrag(Fragment fragment){
        fragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position); //프래그먼트 교체
    }

    @Override
    public int getItemCount() { //페이지 개수
        return fragments.size();
    }
}
