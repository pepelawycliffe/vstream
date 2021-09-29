package com.webandcrafts.vstream.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.webandcrafts.vstream.fragments.AboutFragment;
import com.webandcrafts.vstream.fragments.ProgramScheduleFragment;
import com.webandcrafts.vstream.fragments.VideoPlayerFragment;



public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {

            case 0:
                return VideoPlayerFragment.newInstance("", "");

            case 1:
                return ProgramScheduleFragment.newInstance("", "");

            case 2:
                return AboutFragment.newInstance("", "");


            default:
                return VideoPlayerFragment.newInstance("", "");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}