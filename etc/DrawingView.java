package com.phoncus.app.alpha.etc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.start.StartActivity;

//seekbar를 그릴 외부 클래스
public class DrawingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    float w;
    float h;

    float seekbarh;
    float seekbarw;

    float buttonh;
    float buttonw;

    float touch=1;

    int n = 5; // seekbar의 갯수 변수

    Bitmap img1;
    Bitmap img2;

    Canvas canvas = null;

    float myX = 10;
    boolean first = true;

    float[] abc = new float[n];




    private SurfaceHolder holder = null;
    private boolean isRunning = false;
    private Thread thread = null;
    private Bitmap img = null;

    public DrawingView(Context context) {
            super(context);
            this.getHolder().addCallback(this);
            img1 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_button);
            img2 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_1);
            seekbarw = img2.getWidth();
            seekbarh = img2.getHeight();
            buttonh = img1.getWidth();
            buttonw = img1.getHeight();
    }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_MOVE:
                    Log.d("aa", "m" + event.getX() + "");
                    myX = event.getX();
                    draw(this.canvas);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("aa", "u" + event.getX() + "");
                    touch=0;
                    break;
                case MotionEvent.ACTION_DOWN:
                    Log.d("aa", "d" + event.getX() + "");
                    myX = event.getX();
                    touch=1;
                    break;
            }
        return true;// super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.canvas = canvas;

        Log.d("aa", "onDraw");

        canvas.drawBitmap(img2, (w-seekbarw)/2, (h - seekbarh)/2, null);

        if(touch==1) {
            if ((w - seekbarw) / 2 > myX) {
                myX = (w - seekbarw) / 2;
            } else if ((w + seekbarw) / 2 < myX) {
                myX = (w + seekbarw) / 2;
            }
            canvas.drawBitmap(img1, myX - buttonw / 2, h / 2 - buttonh / 2, null);
        }else {

            float tmp = 999999;
            for(int i=0; i<n; i++){
                if(Math.abs(tmp-myX) > Math.abs(myX-abc[i])){
                    tmp=abc[i];
                }
            }

            Log.d("bb", tmp+"");
            if(tmp > myX+w/400){
                myX = myX + w/200;
            }else if(tmp<myX-w/400){
                myX = myX - w/200;
            }
            canvas.drawBitmap(img1, myX - buttonw / 2, h / 2 - buttonh / 2, null);
        }



    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void run() {
        Canvas canvas = null;
        while (isRunning) {
            canvas = holder.lockCanvas(null);
            if(canvas != null && first == true){
                w = canvas.getWidth();
                h = canvas.getHeight();
                for (int i= 0; i<n; i++){
                    abc[i]= (w-seekbarw)/2*(n-i-1)/(n-1) + (w + seekbarw) / 2*i/(n-1);
                }

                Log.d("0", abc[0]+"");
                Log.d("1", abc[1]+"");
                Log.d("2", abc[2]+"");
                Log.d("3", abc[3]+"");
                Log.d("4", abc[4]+"");


                myX = w/10;
                first =false;
            }
            synchronized (holder) {
                if (canvas != null) {
                    draw(canvas);
                }
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

}



