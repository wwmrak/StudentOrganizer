package com.wwmrak.adapter;

/**
 * Created by wwmrak on 7/3/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wwmrak.main.fragment.ProfileFragment;
import com.wwmrak.main.fragment.ScheduleFragment;
import com.wwmrak.main.fragment.SpaceFragment;
import com.wwmrak.main.fragment.LibraryFragment;
import com.wwmrak.main.fragment.LocationFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new LibraryFragment();
            case 2:
                return new ScheduleFragment();
            case 3:
                return new SpaceFragment();
            case 4:
                return new LocationFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}

