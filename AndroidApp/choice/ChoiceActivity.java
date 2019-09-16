package com.phoncus.app.alpha.choice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.phoncus.app.alpha.R;


@SuppressLint("NewApi")
public class ChoiceActivity extends FragmentActivity {

    //현재 activity에 올라와있는 fragment가 무엇인지 저장변수
    private static int mCurrentFragmentIndex = -1;

    //fragment에 번호를 매긴다
    private final static int FRAGMENT_ONE = 0;
    private final static int FRAGMENT_TWO = 1;
    private final static int FRAGMENT_THREE = 2;
    private final static int FRAGMENT_FOUR = 3;
    private final static int FRAGMENT_FIVE = 4;
    private final static int FRAGMENT_SIX = 5;
    private final static int FRAGMENT_SEVEN = 6;

    //fragment 객체 선언
    private final static Fragment SizeFragment = new SizeFragment();
    private final static Fragment OSFragment = new OSFragment();
    private final static Fragment PerformanceFragment = new PerformanceFragment();
    private final static Fragment DisplayFragment = new DisplayFragment();
    private final static Fragment CameraFragment = new CameraFragment();
    private final static Fragment etcFragment = new etcFragment();
    private final static Fragment SurveyFragment = new SurveyFragment();

    //액션바 이미지 뷰
    private ImageView choicebar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_choice);
        setContentView(R.layout.activity_choice2);

        choicebar = (ImageView) findViewById(R.id.choiceactionbar);
        choicebar.setImageResource(R.mipmap.choice1_actionbar);

        mCurrentFragmentIndex = FRAGMENT_ONE;
        Fragment newFragment = getFragment(mCurrentFragmentIndex);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.body, newFragment);
        transaction.commit();
    }

    public void nextFragmentReplace(){
        mCurrentFragmentIndex++;
        Fragment newFragment = getFragment(mCurrentFragmentIndex);
        //Fragment newFragment = getFragment(a);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.body, newFragment);
        transaction.commit();

    }

    public void pastFragment(){
        mCurrentFragmentIndex--;
        Fragment newFragment = getFragment(mCurrentFragmentIndex);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.body, newFragment);
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_ONE:
                newFragment = SizeFragment;
                choicebar.setImageResource(R.mipmap.choice1_actionbar);
                break;

            case FRAGMENT_TWO:
                newFragment = OSFragment;
                choicebar.setImageResource(R.mipmap.choice2_actionbar);
                break;

            case FRAGMENT_THREE:
                newFragment = PerformanceFragment;
                choicebar.setImageResource(R.mipmap.choice3_actionbar);
                break;

            case FRAGMENT_FOUR:
                newFragment = DisplayFragment;
                choicebar.setImageResource(R.mipmap.choice3_actionbar);
                break;

            case FRAGMENT_FIVE:
                newFragment = CameraFragment;
                choicebar.setImageResource(R.mipmap.choice5_actionbar);
                break;

            case FRAGMENT_SIX:
                newFragment = etcFragment;
                choicebar.setImageResource(R.mipmap.choice6_actionbar);
                break;

            case FRAGMENT_SEVEN:
                newFragment = SurveyFragment;
                choicebar.setImageResource(0);
                break;

            default:
                break;
        }

        return newFragment;
    }

    public interface onKeyBackPressedListener {
        public void onBack();
    }
    private onKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener){
        mOnKeyBackPressedListener=listener;
    } // In MyActivity




    @Override
    public void onBackPressed() {
        if(mCurrentFragmentIndex>0) {
            //this.pastFragment();
            mOnKeyBackPressedListener.onBack();
        }else {
            super.onBackPressed();
        }
    }




}
