package com.example.user.kansaidialect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.kansaidialect.fragment.PlaceholderFragment;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 2018/08/11.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /** リスト. */
    private ArrayList<Map.Entry<String, ?>> mList;

    /**
     * リストにアイテムを追加する.
     * @param item アイテム
     */
    public void add(Map.Entry<String, ?> item) {
        mList.add(item);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 7;
    }
}