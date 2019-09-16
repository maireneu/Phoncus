package com.phoncus.app.alpha.result;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.etc.ResultViewPagerAdapter;
import com.phoncus.app.alpha.etc.StartViewPagerAdapter;

@SuppressLint("NewApi")
public class ResultActivity extends FragmentActivity {

    private ViewPager viewPager = null;
    Handler handler = null;
    int p = 0;
    int v = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ResultViewPagerAdapter adapter = new ResultViewPagerAdapter(getSupportFragmentManager());

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		viewPager.setAdapter(adapter);

    }
}