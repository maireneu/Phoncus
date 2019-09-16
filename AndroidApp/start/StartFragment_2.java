package com.phoncus.app.alpha.start;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoncus.app.alpha.R;


public class StartFragment_2 extends Fragment{

    //start의 두번째 설명 화면, xml연결
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_2, container, false);
    }
}
