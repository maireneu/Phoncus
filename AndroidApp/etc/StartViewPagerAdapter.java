package com.phoncus.app.alpha.etc;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phoncus.app.alpha.start.StartFragment_1;
import com.phoncus.app.alpha.start.StartFragment_2;
import com.phoncus.app.alpha.start.StartFragment_3;



//Viewpager를 쓰기 위함.
public class StartViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = new Fragment[3];


    public StartViewPagerAdapter(FragmentManager fm) {
        super(fm);

        //각 fragments 연결
        fragments[0] = new StartFragment_1();
        fragments[1] = new StartFragment_2();
        fragments[2] = new StartFragment_3();
    }

    //아래의 메서드들의 호출 주체는 ViewPager이다.
    //ListView와 원리가 같다.
	/*
	 * 여러 프레그먼트 중 어떤 프레그먼트를 보여줄지 결정
	 */
    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

	/*
	 * 보여질 프레그먼트가 몇개인지 결정
	 */
    public int getCount() {
        return fragments.length;
    }

}