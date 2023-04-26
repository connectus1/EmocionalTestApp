package com.example.testapp.TestPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TestPagerAdapter extends FragmentStateAdapter {
    private List<FragmentTestTarget> fragments;

    public TestPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<ItemQuestion> questions) {
        super(fragmentManager, lifecycle);
        fragments = new ArrayList<>();

        for (ItemQuestion item:
             questions) {
            fragments.add(new FragmentTestTarget(item));
        }

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
