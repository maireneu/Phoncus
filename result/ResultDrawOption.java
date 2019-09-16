package com.phoncus.app.alpha.result;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;


public class ResultDrawOption extends View{

        private String size = null;
        private String performance = null;
        private String camera = null;
        private int reflect_size=0;
        private int reflect_performance=0;
        private int reflect_camera=0;
        private int reflect_ppi=0;
        private int reflect_addition=0;
        private String display = null;
        private int select_camera=0;

        public ResultDrawOption(Context context, int rank) {
            super(context);

            SharedPreferences prefs = context.getSharedPreferences("__setting",
                    Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

            size = prefs.getString("RESULT_RANK"+rank+"_SIZE", "0");
            performance = prefs.getString("RESULT_RANK"+rank+"_PERFORMANCE", "0");
            camera = prefs.getString("RESULT_RANK"+rank+"_CAMERA", "0");
            display = prefs.getString("RESULT_RANK"+rank+"_PPI", "0");

            Log.d("errorrrrrrrrrrrr", "ResultDrawOption: "+ prefs.getString("SELECT_SIZE", "0") + "mmm"+size);

            if (Math.abs(Float.parseFloat(prefs.getString("SELECT_SIZE", "0")) - Float.parseFloat(size)) <= 0.5) {
                reflect_size = 5;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_SIZE", "0")) - Float.parseFloat(size)) < 1.5) {
                reflect_size = 4;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_SIZE", "0")) - Float.parseFloat(size)) < 2) {
                reflect_size = 3;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_SIZE", "0")) - Float.parseFloat(size)) < 3) {
                reflect_size = 2;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_SIZE", "0")) - Float.parseFloat(size)) < 4) {
                reflect_size = 1;
            } else {
                reflect_size = 0;
            }

            if (Math.abs(Float.parseFloat(prefs.getString("SELECT_PERFORMANCE", "0")) - Float.parseFloat(performance)) <= 0) {
                reflect_performance = 5;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_PERFORMANCE", "0")) - Float.parseFloat(performance)) < 1.5) {
                reflect_performance = 4;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_PERFORMANCE", "0")) - Float.parseFloat(performance)) < 2.5) {
                reflect_performance = 3;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_PERFORMANCE", "0")) - Float.parseFloat(performance)) < 3.5) {
                reflect_performance = 2;
            } else if (Math.abs(Float.parseFloat(prefs.getString("SELECT_PERFORMANCE", "0")) - Float.parseFloat(performance)) < 4.5) {
                reflect_performance = 1;
            } else {
                reflect_performance = 0;
            }

            if (Math.abs(Integer.parseInt(prefs.getString("SELECT_PPI", "0")) - Integer.parseInt(display)) < 80) {
                reflect_ppi = 5;

            } else if (Math.abs(Integer.parseInt(prefs.getString("SELECT_PPI", "0")) - Integer.parseInt(display)) < 160) {
                reflect_ppi = 4;
            } else if (Math.abs(Integer.parseInt(prefs.getString("SELECT_PPI", "0")) - Integer.parseInt(display)) < 240) {
                reflect_ppi = 3;
            } else{
                reflect_ppi = 2;
            }

            if (Integer.parseInt(prefs.getString("SELECT_CAMERA", "0")) < 600) {
                select_camera = 1;
            } else if (Integer.parseInt(prefs.getString("SELECT_CAMERA", "0")) < 1000) {
                select_camera = 2;
            } else if (Integer.parseInt(prefs.getString("SELECT_CAMERA", "0")) < 1450) {
                select_camera = 3;
            } else if (Integer.parseInt(prefs.getString("SELECT_CAMERA", "0")) < 1950) {
                select_camera = 4;
            } else {
                select_camera = 5;
            }

            if (Math.abs(select_camera - Integer.parseInt(camera)) == 0) {
                reflect_camera = 5;
            } else if (Math.abs(select_camera - Integer.parseInt(camera)) == 1) {
                reflect_camera = 4;
            } else if (Math.abs(select_camera - Integer.parseInt(camera)) == 2) {
                reflect_camera = 3;
            } else if (Math.abs(select_camera - Integer.parseInt(camera)) == 3) {
                reflect_camera = 2;
            } else if (Math.abs(select_camera - Integer.parseInt(camera)) == 4) {
                reflect_camera = 1;
            } else {
                reflect_camera = 0;
            }

            if(prefs.getString("RESULT_RANK"+rank+"_BATTRYSAPERATE","0").equals(prefs.getString("SELECT_BATTRYSAPERATE","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_BATTRYSAPERATE","0").equals("X") && prefs.getString("SELECT_BATTRYSAPERATE","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }

            if(prefs.getString("RESULT_RANK"+rank+"_WIRELESSCHARGE","0").equals(prefs.getString("SELECT_WIRELESSCHARGE","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_WIRELESSCHARGE","0").equals("X") && prefs.getString("SELECT_WIRELESSCHARGE","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }
            if(prefs.getString("RESULT_RANK"+rank+"_WATERPROOF","0").equals(prefs.getString("SELECT_WATERPROOF","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_WATERPROOF","0").equals("X") && prefs.getString("SELECT_WATERPROOF","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }
            if(prefs.getString("RESULT_RANK"+rank+"_FINGERPRINT","0").equals(prefs.getString("SELECT_FINGERPRINT","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_FINGERPRINT","0").equals("X") && prefs.getString("SELECT_FINGERPRINT","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }
            if(prefs.getString("RESULT_RANK"+rank+"_STYLUS","0").equals(prefs.getString("SELECT_STYLUS","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_STYLUS","0").equals("X") && prefs.getString("SELECT_STYLUS","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }


            if(prefs.getString("RESULT_RANK"+rank+"_KNOCKCODE","0").equals(prefs.getString("SELECT_KNOCKCODE","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_KNOCKCODE","0").equals("X") && prefs.getString("SELECT_KNOCKCODE","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }

            if(prefs.getString("RESULT_RANK"+rank+"_SAMSUNGPAY","0").equals(prefs.getString("SELECT_SAMSUNGPAY","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_SAMSUNGPAY","0").equals("X") && prefs.getString("SELECT_SAMSUNGPAY","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }


            if(prefs.getString("RESULT_RANK"+rank+"_BUTTON","0").equals(prefs.getString("SELECT_BUTTON","0"))){
                reflect_addition = reflect_addition + 1;
            }else if(prefs.getString("RESULT_RANK"+rank+"_BUTTON","0").equals("X") && prefs.getString("SELECT_BUTTON","0").equals("")){
                reflect_addition = reflect_addition + 1;
            }

            if(reflect_addition <= 1){
                reflect_addition=0;
            }else if(reflect_addition <= 3){
                reflect_addition=1;
            }else if(reflect_addition <= 4){
                reflect_addition=2;
            }else if(reflect_addition <= 5){
                reflect_addition=3;
            }else if(reflect_addition <= 6){
                reflect_addition=4;
            }else if(reflect_addition <= 8){
                reflect_addition=5;
            }

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

            canvas.drawText("옵션 반영도", 130, 60, paint);
            paint.setTextSize(20);
            paint.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawText("고른 옵션과", 130, 95, paint);
            canvas.drawText("일치율을 뜻해요", 130, 115, paint);


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



            canvas.drawLine((float) (w / 2 + w / 4 * reflect_size / 5 * Math.sin(Math.toRadians(0))), (float) (h / 2 - w / 4 * reflect_size / 5 * Math.cos(Math.toRadians(0)) + 20), (float) (w / 2 + w / 4 * reflect_camera / 5 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * reflect_camera / 5 * Math.cos(Math.toRadians(72)) + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * reflect_camera / 5 * Math.sin(Math.toRadians(72))), (float) (h / 2 - w / 4 * reflect_camera / 5 * Math.cos(Math.toRadians(72)) + 20), (float) (w / 2 + w / 4 * reflect_addition / 5 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * reflect_addition / 5 * Math.cos(Math.toRadians(144)) + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * reflect_addition / 5 * Math.sin(Math.toRadians(144))), (float) (h / 2 - w / 4 * reflect_addition / 5 * Math.cos(Math.toRadians(144)) + 20), (float) (w / 2 + w / 4 * reflect_ppi / 5 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * reflect_ppi / 5 * Math.cos(Math.toRadians(216)) + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * reflect_ppi / 5 * Math.sin(Math.toRadians(216))), (float) (h / 2 - w / 4 * reflect_ppi / 5 * Math.cos(Math.toRadians(216)) + 20), (float) (w / 2 + w / 4 * reflect_performance / 5 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * reflect_performance / 5 * Math.cos(Math.toRadians(288)) + 20), paint);
            canvas.drawLine((float) (w / 2 + w / 4 * reflect_performance / 5 * Math.sin(Math.toRadians(288))), (float) (h / 2 - w / 4 * reflect_performance / 5 * Math.cos(Math.toRadians(288)) + 20), (float) (w / 2 + w / 4 * reflect_size / 5 * Math.sin(Math.toRadians(360))), (float) (h / 2 - w / 4 * reflect_size / 5 * Math.cos(Math.toRadians(360)) + 20), paint);

        }



}




