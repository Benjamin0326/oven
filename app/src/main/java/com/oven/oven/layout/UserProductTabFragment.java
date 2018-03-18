package com.oven.oven.layout;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oven.oven.R;
import com.oven.oven.adapter.UserProductPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProductTabFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserProductPagerAdapter pagerAdapter;

    public UserProductTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_user_product_tab, container, false);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_user_product);
        tabLayout.addTab(tabLayout.newTab().setText("금일 배송"));
        tabLayout.addTab(tabLayout.newTab().setText("찜한 상품"));
        tabLayout.addTab(tabLayout.newTab().setText("최근 본 상품"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //int id = getArguments().getInt("position", 0);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager_user_product);
        pagerAdapter = new UserProductPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), 2);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        int id = getArguments().getInt("pageNumber", 0);

        viewPager.setCurrentItem(id);

        return rootView;
    }

}
