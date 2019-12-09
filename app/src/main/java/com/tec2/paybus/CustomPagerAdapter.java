package com.tec2.paybus;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class CustomPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_PAGES = 1;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return OpcionesPagoFragment.newInstance();
            default:
                return null;
        }
    }
}