package com.phoncus.app.alpha.etc;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phoncus.app.alpha.result.ResultFragment_1;
import com.phoncus.app.alpha.result.ResultFragment_2;
import com.phoncus.app.alpha.result.ResultFragment_3;



public class ResultViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = new Fragment[3];


    public ResultViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new ResultFragment_1();
        fragments[1] = new ResultFragment_2();
        fragments[2] = new ResultFragment_3();
    }

    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    public int getCount() {
        return fragments.length;
    }

}