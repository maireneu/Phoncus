package com.phoncus.app.alpha.result;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;


public class ResultDrawUseroption extends View{

        private String size = null;
        private String performance = null;
        private String camera = null;
        private int[] check;

        public ResultDrawUseroption(Context context, int rank) {
            super(context);

            SharedPreferences prefs = context.getSharedPreferences("__setting",
                    Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

            size = prefs.getString("RESULT_RANK"+rank+"_SIZE", "0");
            performance = prefs.getString("RESULT_RANK"+rank+"_PERFORMANCE", "0");
            camera = prefs.getString("RESULT_RANK"+rank+"_CAMERA", "0");

            ResultMethod rm = new ResultMethod();
            check = rm.check(context,rank);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setTextSize(35);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.DEFAULT_BOLD);

            float w = canvas.getWidth();
            float h = canvas.getHeight();

            draw1(canvas,w,h,paint);


            //글쓰기
            canvas.drawText("옵션 절대값", 130, 60, paint);
            paint.setTextSize(20);
            paint.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawText("이 폰이 평균적으로", 130, 95, paint);
            canvas.drawText("어떤지 알려줄게요", 130, 115, paint);


            paint.setTextSize(25);
            paint.setColor(Color.parseColor("#1f97ee"));
            canvas.drawText("화면크기", 400, 78, paint);
            canvas.drawText("카메라", 635, 235, paint);
            canvas.drawText("성능", 175, 235, paint);
            canvas.drawText("기타기능", 560, 480, paint);
            canvas.drawText("화질", 250, 480, paint);

        }

        //오각형 그릴 메소드
        public void draw1(Canvas canvas, float w, float h, Paint paint) {

            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(72))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(72))+ 20), (float) (w / 2 + w / 4 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(144))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(144))+ 20), (float) (w / 2 + w / 4 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(216))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(216))+ 20), (float) (w / 2 + w / 4 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(288))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(288))+ 20), (float) (w / 2 + w / 4 * Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(360))+ 20), paint);

            canvas.drawLine((float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(72))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(72))+ 20), (float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(144))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(144))+ 20), (float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(216))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(216))+ 20), (float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(288))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(288))+ 20), (float) (w / 2 + w / 4 *4/5* Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 *4/5* Math.cos(Math.toRadians(360))+ 20), paint);

            canvas.drawLine((float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(72))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(72))+ 20), (float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(144))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(144))+ 20), (float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(216))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(216))+ 20), (float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(288))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(288))+ 20), (float) (w / 2 + w / 4 *3/5* Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 *3/5* Math.cos(Math.toRadians(360))+ 20), paint);

            canvas.drawLine((float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(72))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(72))+ 20), (float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(144))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(144))+ 20), (float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(216))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(216))+ 20), (float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(288))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(288))+ 20), (float) (w / 2 + w / 4 *2/5* Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 *2/5* Math.cos(Math.toRadians(360))+ 20), paint);

            canvas.drawLine((float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(72))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(72))+ 20), (float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(144))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(144))+ 20), (float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(216))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(216))+ 20), (float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(288))+ 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(288))+ 20), (float) (w / 2 + w / 4 *1/5* Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 *1/5* Math.cos(Math.toRadians(360))+ 20), paint);

            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(0)) + 20), (float) (w / 2), (float) (h / 2 + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(72))+ 20), (float) (w / 2), (float) (h / 2 + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(144))+ 20), (float) (w / 2), (float) (h / 2 + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(216))+ 20), (float) (w / 2), (float) (h / 2 + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * Math.cos(Math.toRadians(288))+ 20), (float) (w / 2), (float) (h / 2 + 20), paint);

            paint.setColor(Color.parseColor("#FFF612"));
            paint.setStrokeWidth(5);



                    canvas.drawLine((float) (w / 2 + w / 4 * (Float.parseFloat(size)-4)*5/2 / 5 * Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 * (Float.parseFloat(size)-4)*5/2  / 5 * Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 * Float.parseFloat(camera) / 5 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * Float.parseFloat(camera) / 5 * Math.cos(Math.toRadians(72)) + 20), paint);
                    canvas.drawLine((float) (w / 2 + w / 4 * Float.parseFloat(camera) / 5 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * Float.parseFloat(camera) / 5 * Math.cos(Math.toRadians(72)) + 20), (float) (w / 2 + w / 4 * check[1] / 5 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * check[1] / 5 * Math.cos(Math.toRadians(144)) + 20), paint);
                    canvas.drawLine((float) (w / 2 + w / 4 * check[1] / 5 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * check[1] / 5 * Math.cos(Math.toRadians(144)) + 20), (float) (w / 2 + w / 4 * check[0] / 5 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * check[0] / 5 * Math.cos(Math.toRadians(216)) + 20), paint);
                    canvas.drawLine((float) (w / 2 + w / 4 * check[0] / 5 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * check[0] / 5 * Math.cos(Math.toRadians(216)) + 20), (float) (w / 2 + w / 4 * (Float.parseFloat(performance)-1) / 5 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * (Float.parseFloat(performance)-1) / 5 * Math.cos(Math.toRadians(288)) + 20), paint);
                    canvas.drawLine((float) (w / 2 + w / 4 * (Float.parseFloat(performance)-1) / 5 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * (Float.parseFloat(performance)-1) / 5 * Math.cos(Math.toRadians(288)) + 20), (float) (w / 2 + w / 4 * (Float.parseFloat(size)-4)*5/2  / 5 * Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 *(Float.parseFloat(size)-4)*5/2  / 5 * Math.cos(Math.toRadians(360)) + 20), paint);


        }



}




