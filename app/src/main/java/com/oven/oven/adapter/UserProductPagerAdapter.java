package com.oven.oven.adapter;

/**
 * Created by sung9 on 2018-01-16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oven.oven.layout.LikeProductFragment;
import com.oven.oven.layout.RecentlyViewedFragment;
import com.oven.oven.layout.TodayArriveFragment;

public class UserProductPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    int pos;

    public UserProductPagerAdapter(FragmentManager fm, int numOfTabs, int _position) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.pos = _position;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args;
        switch (position) {
            case 0:
                args = new Bundle();
                args.putInt("position", pos);
                TodayArriveFragment tab1 = new TodayArriveFragment(); // Fragment 는 알아서 만들자
                tab1.setArguments(args);
                return tab1;
            case 1:
                args = new Bundle();
                args.putInt("position", pos);
                LikeProductFragment tab2 = new LikeProductFragment();
                tab2.setArguments(args);
                return tab2;
            case 2:
                args = new Bundle();
                args.putInt("position", pos);
                RecentlyViewedFragment tab3 = new RecentlyViewedFragment();
                tab3.setArguments(args);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
