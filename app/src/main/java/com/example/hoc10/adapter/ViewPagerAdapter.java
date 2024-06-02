package com.example.hoc10.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hoc10.fragment.FragmentAccount;
import com.example.hoc10.fragment.FragmentHistory;
import com.example.hoc10.fragment.FragmentHome;
import com.example.hoc10.fragment.FragmentNotice;
import com.example.hoc10.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:return new FragmentHome();
            case 1:return new FragmentHistory();
            case 2:return new FragmentSearch();
            case 3:return new FragmentNotice();
            case 4:return new FragmentAccount();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
