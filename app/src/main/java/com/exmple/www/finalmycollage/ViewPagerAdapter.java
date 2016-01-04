package com.exmple.www.finalmycollage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    // Tab Titles
    private String tabtitles[] = new String[]{"الخميس", "الأربعاء", "الثلاثاء", "الإثنين", "الأحد", "السبت"};
    Context context;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 5:
                return new NativeFragment(6);
            case 4:
                return new NativeFragment(5);
            case 3:
                return new NativeFragment(4);
            case 2:
                return new NativeFragment(3);
            case 1:
                return new NativeFragment(2);
            case 0:
                return new NativeFragment(1);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}
