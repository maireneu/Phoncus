package com.phoncus.app.alpha.intro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoncus.app.alpha.HomeActivity;
import com.phoncus.app.alpha.HomeActivity2;
import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.start.StartActivity;

public class IntroActivity extends Activity {


    //핸들러 변수 선언
    Handler handler_intro;

    //이미지와 뷰, 애니메이션 변수 선언
    private ImageView logo = null;
    private ImageView desktop = null;
    private ImageView monitor = null;
    private ImageView phone = null;
    private ImageView laptop = null;
    private ImageView homeimage = null;


    private TextView phoncus = null;
    private TextView text2 = null;
    private Animation anim_logoslide = null;
    private Animation anim_logoslide2 = null;
    private Animation anim_logoslide3 = null;
    private Animation anim_logoslide4 = null;
    private Animation anim_logoslide5 = null;
    private Animation anim_logoslide6 = null;
    private Animation anim_logoslide7 = null;
    private Animation anim_logoslide8 = null;
    private Animation anim_logoslide9 = null;
    private Animation anim_logoslide10 = null;
    private Animation anim_logoslide11 = null;


    //onCreate는 activity 생성시 실행되는 메소드. activity 생명주기 검색 참조
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //SharedPreferences :: 해당 어플리케이션 내 file로 data 저장, 객체선언부분.
        // 현재 intro 화면에서 각정보들을 초기화 시킨다. (안시키면 앱 종료해도 데이터 저장됨)
        SharedPreferences prefs = this.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();

        //현재 폰 정보
        editor.putString("NAME", "");
        editor.putString("SIZE","");
        editor.putString("PERFORMANCE", "");
        editor.putString("PPI", "");
        editor.putString("CAMERA", "");

        //유저가 선택한 정보
        editor.putString("SELECT_SIZE", "");
        editor.putString("SELECT_OS", "");
        editor.putString("SELECT_PERFORMANCE","");
        editor.putString("SELECT_PPI","");
        editor.putString("SELECT_CAMERA","");
        editor.putString("SELECT_MANUFACTURER","");
        editor.putString("SELECT_BATTRYSAPERATE","");
        editor.putString("SELECT_WIRELESSCHARGE","");
        editor.putString("SELECT_WATERPROOF","");
        editor.putString("SELECT_FINGERPRINT","");
        editor.putString("SELECT_STYLUS","");
        editor.putString("SELECT_KNOCKCODE","");
        editor.putString("SELECT_SAMSUNGPAY","");
        editor.putString("SELECT_BUTTON","");
        editor.putString("SELECT_ROM","");
        editor.putBoolean("CALL",false);
        editor.putString("MESSAGE","");
        editor.putString("KAKAOTALK","");
        editor.putString("INTERNET","");
        editor.putString("YOUTUBE","");
        editor.putString("VIDEO","");
        editor.putString("CASUAL","");
        editor.putString("LATEST","");
        editor.putString("BEST","");

        //유저 정보
        editor.putString("YEAR","");
        editor.putString("MONTH","");
        editor.putString("GENDER","");


        //추천설계 받은 폰 정보, rank 1,2,3
        for(int i=0; i<3; i++) {

            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PETNAME", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_SIZE", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_OS", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_OS_VERSION", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PERFORMANCE", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PPI", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_CAMERA", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_MANUFACTURER", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_BATTRYSAPERATE", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_WIRELESSCHARGE", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_WATERPROOF", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_FINGERPRINT", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_STYLUS", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_KNOCKCODE", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_SAMSUNGPAY", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_BUTTON", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_URL", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_URL1", "");
            editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_URL2", "");

            //editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_QCBC", "");

        }


        //입력완료
        editor.commit();


        //intro xml 연결 및 view 다루기 위한 연결 설정
        setContentView(R.layout.activity_intro);

        logo = (ImageView) findViewById(R.id.intro_logo);
        phoncus = (TextView) findViewById(R.id.intro_phoncus);
        text2 = (TextView) findViewById(R.id.intro_text2);
        desktop = (ImageView) findViewById(R.id.intro_desktop);
        phone = (ImageView) findViewById(R.id.intro_phone);
        monitor = (ImageView) findViewById(R.id.intro_monitor);
        laptop = (ImageView) findViewById(R.id.intro_laptop);
        homeimage = (ImageView) findViewById(R.id.intro_homeimage);


        //애니메이션 연결, 애니메이션들은 res-anim 폴더에 저장
        anim_logoslide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim1);
        anim_logoslide2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim2);
        anim_logoslide3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim3);
        anim_logoslide4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim4);
        anim_logoslide5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim5);
        anim_logoslide6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim6);
        anim_logoslide7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim7);
        anim_logoslide8 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim8);
        anim_logoslide9 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim9);
        anim_logoslide10 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim10);
        anim_logoslide11 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.intro_anim11);

        //ui 접근위한 핸들러 설정 및 실행
        handler_intro = new Handler();
        handler_intro.postDelayed(run_anim1, 100);






        //handler_intro.postDelayed(run_anim6, 1800);


    }

    @Override
    protected void onResume() {
        super.onResume();


        //2번째로 일어나야할 애니메이션들
        handler_intro = new Handler();
        handler_intro.postDelayed(run_anim2, 1700);
        handler_intro.postDelayed(run_intro, 2450);


    }


    //첫번째 애니메이션
    Runnable run_anim1 = new Runnable() {
        @Override
        public void run() {
            try {

                logo.startAnimation(anim_logoslide);
                phoncus.startAnimation(anim_logoslide);
                text2.startAnimation(anim_logoslide);
                phone.startAnimation(anim_logoslide2);
                laptop.startAnimation(anim_logoslide3);
                monitor.startAnimation(anim_logoslide4);
                desktop.startAnimation(anim_logoslide5);
            }catch (Exception e){
            }
        }
    };


    //두번째 애니메이션
    Runnable run_anim2 = new Runnable() {
        @Override
        public void run() {
            try {

                logo.startAnimation(anim_logoslide6);
                phoncus.startAnimation(anim_logoslide6);
                text2.startAnimation(anim_logoslide6);
                phone.startAnimation(anim_logoslide7);
                laptop.startAnimation(anim_logoslide8);
                monitor.startAnimation(anim_logoslide9);
                desktop.startAnimation(anim_logoslide10);
                homeimage.startAnimation(anim_logoslide11);
            }catch (Exception e){
            }
        }
    };


    //HomeActivity 로 이동
    Runnable run_intro = new Runnable() {
        @Override
        public void run() {
            //Intent intent = new Intent(IntroActivity.this, StartActivity.class);

            //넘어갈 액티비티 객체 생성 및 넘어가기
            Intent intent = new Intent(IntroActivity.this, HomeActivity2.class);
            startActivity(intent);

            //액티비티 전환 애니메이션 초기화 (이거 안하면 기본 애니메이션 적용됨)
            overridePendingTransition(0,0);

            //현재 액티비티 종료
            finish();
        }
    };

//뒤로가기 키 설정
/*    @Override
    public void onBackPressed() {

    }*/
}
