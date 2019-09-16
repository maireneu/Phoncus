package com.phoncus.app.alpha.result;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoncus.app.alpha.R;


public class ResultFragment_1 extends Fragment {


    private ImageView imageview = null;
    private ImageView imageview2 = null;
    private int rank = 1;
    private TextView text_title=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        imageview = (ImageView) view.findViewById(R.id.imageview);
        imageview2 = (ImageView) view.findViewById(R.id.imageview2);
        text_title = (TextView) view.findViewById(R.id.result_text_title);

        text_title.setText("추천모델 " + rank);

        ResultMethod rm = new ResultMethod();
        rm.Imageset(getActivity(), view,rank);
        rm.startview(getActivity(),view,rank);
        rm.performance(getActivity(),view,rank);
        rm.performance(getActivity(),view,rank);
        rm.display(getActivity(),view,rank);
        rm.camera(getActivity(),view,rank);
        rm.addition(getActivity(),view,rank);

        ResultDrawOption rdo = new ResultDrawOption(getActivity(), rank);
        Bitmap bitmap = Bitmap.createBitmap(800,540,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        rdo.draw(canvas);
        imageview.setImageBitmap(bitmap);

        ResultDrawUseroption rdu = new ResultDrawUseroption(getActivity(), rank);
        Bitmap bitmap2 = Bitmap.createBitmap(800,540,Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap2);
        rdu.draw(canvas2);
        imageview2.setImageBitmap(bitmap2);


        return view;

    }
}