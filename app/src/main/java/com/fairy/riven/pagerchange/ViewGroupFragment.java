package com.fairy.riven.pagerchange;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yanfa6 on 2017/11/21.
 */
public class ViewGroupFragment extends FragmentPagerAdapter {
    private List<Fragment> listFragment;

    public ViewGroupFragment(FragmentManager fm) {
        super(fm);
    }

    public ViewGroupFragment(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment=listFragment;
    }
    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

}
