package com.phoncus.app.alpha.start;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.etc.StartViewPagerAdapter;

//startActivity 는 추천 시작, 설명, 폰인식 화면,
// 액티비티에 fragment라는 껍질을 연결. 1,2,3
@SuppressLint("NewApi")
public class StartActivity extends FragmentActivity {

	private ImageView startbar = null;
    private ViewPager viewPager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		//레이아웃 연결 및 view 설정
        setContentView(R.layout.activity_start);
		startbar = (ImageView) findViewById(R.id.startactionbar);
		startbar.setImageResource(R.mipmap.start1_actionbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

		//fragment 방식 중하나인 viewpager를 쓰기위해, 해당 외부 클래스 객체선언 및 연결
        StartViewPagerAdapter adapter = new StartViewPagerAdapter(getSupportFragmentManager());

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			//현재 화면에 표시된 fragment에 따라 액션바 이미지 변경
			@Override
			public void onPageSelected(int position) {

				if (position==0) {
					startbar.setImageResource(R.mipmap.start1_actionbar);
				}else if (position == 1){
					startbar.setImageResource(R.mipmap.start2_actionbar);
				}else if (position == 2){
					startbar.setImageResource(R.mipmap.start3_actionbar);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		viewPager.setAdapter(adapter);

    }
}