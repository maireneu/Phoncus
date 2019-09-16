package com.phoncus.app.alpha.result;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.phoncus.app.alpha.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadingActivity extends Activity {

    Handler handler_loading;
    private String size=null;
    private String os=null;
    private String grade=null;
    private String ppi=null;
    private String camera=null;
    private String rom=null;
    private String finger=null;
    private String pay=null;
    private String knockon=null;
    private String wirelesscharge=null;
    private String waterresistance=null;
    private String stylus=null;
    private String hb=null;
    private String manufacturer=null;
    private String battery=null;
    private String year =null;
    private String month =null;
    private String gender =null;
    private String petname =null;
    private String body = "nul";
    private String url = null;
    SharedPreferences prefs = null;
    SharedPreferences.Editor editor = null;



    private ImageView loadicon;

    int [] ImageId = { R.mipmap.loadicon1 , R.mipmap.loadicon2 , R.mipmap.loadicon3 , R.mipmap.loadicon4 , R.mipmap.loadicon5, R.mipmap.loadicon6, R.mipmap.loadicon7, R.mipmap.loadicon8 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading2);

        prefs = getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        editor = prefs.edit();

        WebTaskPost wtp = new WebTaskPost();
        wtp.execute();

        loadicon = (ImageView) findViewById(R.id.loadicon);
        loadicon.setVisibility(ImageView.VISIBLE);
        //loadicon.setBackgroundResource(R.anim.simple_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) loadicon.getBackground();
        frameAnimation.start();

        editor.putString("RESULT_RANK1_URL1", "");
        editor.putString("RESULT_RANK2_URL1", "");
        editor.putString("RESULT_RANK3_URL1", "");
        editor.putString("RESULT_RANK1_URL2", "");
        editor.putString("RESULT_RANK2_URL2", "");
        editor.putString("RESULT_RANK3_URL2", "");

        handler_loading = new Handler();
        handler_loading.postDelayed(run_loding, 3000);

    }

    Runnable run_loding = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        }
    };


    public class WebTaskPost extends AsyncTask<Void, Void, String> {
        private String result;
        private String petname;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedPreferences prefs = getSharedPreferences("__setting",
                    Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
            SharedPreferences.Editor editor = prefs.edit();
            year = prefs.getString("YEAR","0");
            month = prefs.getString("MONTH","0");
            gender = prefs.getString("GENDER","0");
            petname = prefs.getString("NAME","0");
            size = prefs.getString("SELECT_SIZE","0");
            os = prefs.getString("SELECT_OS","0");
            grade = prefs.getString("SELECT_PERFORMANCE","0");
            ppi = prefs.getString("SELECT_PPI","0");
            camera = prefs.getString("SELECT_CAMERA","0");
            manufacturer = prefs.getString("SELECT_MANUFACTURER","0");
            battery = prefs.getString("SELECT_BATTRYSAPERATE","0");
            wirelesscharge = prefs.getString("SELECT_WIRELESSCHARGE","0");
            waterresistance = prefs.getString("SELECT_WATERPROOF","0");
            finger = prefs.getString("SELECT_FINGERPRINT","0");
            stylus = prefs.getString("SELECT_STYLUS","0");
            knockon = prefs.getString("SELECT_KNOCKCODE","0");
            pay = prefs.getString("SELECT_SAMSUNGPAY","0");
            hb = prefs.getString("SELECT_BUTTON","0");
            rom = prefs.getString("SELECT_ROM","0");

        }

        @Override
        protected String doInBackground(Void... Void) {
            try {
                URL Url = new URL("http://221.143.21.39/curation/curation");  // URL화 한다.

                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST"); // post방식 통신
                conn.setDoOutput(true);       // 쓰기모드 지정
                conn.setDoInput(true);        // 읽기모드 지정
                conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정


                //petname = URLEncoder.encode(petname, "UTF-8");
                body = "YEAR=" + year + "&MONTH=" + month + "&GENDER=" + gender + "&MODEL=" + petname +"&SIZE=" + size
                        +"&OS=" + os +"&PERFORMANCE=" + grade +"&PPI=" + ppi +"&CAMERA=" + camera +"&MANUFACTURER=" + manufacturer
                        +"&BATTERYSAPERATE=" + battery +"&WIRELESSCHARGE=" + wirelesscharge
                        +"&WATERPROOF=" + waterresistance +"&FINGERPRINT=" + finger +"&STYLUS=" + stylus
                        +"&KNOCKCODE=" + knockon +"&QCBC=" + "null" +"&SAMSUNGPAY=" + pay+"&BUTTON=" + hb +"";



                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes("UTF-8"));
                os.flush();
                os.close();


                InputStream is = conn.getInputStream();        //input스트림 개방
                StringBuilder builder = new StringBuilder();   //문자열을 담기 위한 객체
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));  //문자열 셋 세팅
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                result = builder.toString();
                return result;

            } catch (Exception io) {
                Log.d("TAG", Log.getStackTraceString(io));
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Log.d("result", result+"");
                parserStringcuration(result);
                //Intent intent = new Intent(getActivity(), SizeActivity1.class);
                //getActivity().startActivity(intent);
            }
            Log.d("bodytext", body );
        }
    }

    private void parserStringcuration(String result) {
        try {
            // 핸드폰 이름은 화면에 출력
            JSONArray jarr = new JSONArray(result);

                   /* String[] arr = new String[17];
                    arr[0] = jobj.getString("NAME");
                    arr[1] = jobj.getString("SIZE");
                    arr[2] = jobj.getString("GRADE");
                    arr[3] = jobj.getString("PPI");
                    arr[4] = jobj.getString("CAMERA");*/

            prefs = this.getSharedPreferences("__setting",
                    Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
            editor = prefs.edit();

            for(int i=0; i<jarr.length(); i++) {

                JSONObject jobj = jarr.getJSONObject(i);
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PETNAME", jobj.getString("PETNAME"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_SIZE", jobj.getString("SIZE"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_OS", jobj.getString("OS"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_OS_VERSION", jobj.getString("OS_VERSION"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PERFORMANCE", jobj.getString("PERFORMANCE"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_PPI", jobj.getString("PPI"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_CAMERA", jobj.getString("CAMERA"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_MANUFACTURER", jobj.getString("MANUFACTURER"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_BATTRYSAPERATE", jobj.getString("BATTERYSAPERATE"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_WIRELESSCHARGE", jobj.getString("WIRELESSCHAREGE"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_WATERPROOF", jobj.getString("WATERPROOF"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_FINGERPRINT", jobj.getString("FINGERPRINT"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_STYLUS", jobj.getString("STYLUS"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_KNOCKCODE", jobj.getString("KNOCKCODE"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_SAMSUNGPAY", jobj.getString("SAMSUNGPAY"));
                editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_BUTTON", jobj.getString("BUTTON"));


                try {
                    if (jobj.getString("IMAGE_URL1").equals("")) {
                        Log.d("result=url::::", "error1");
                    }else{
                        editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_URL1", jobj.getString("IMAGE_URL1"));
                        Log.d("result=url::::", jobj.getString("IMAGE_URL1") + "");
                    }

                    if (jobj.getString("IMAGE_URL2").equals("")) {
                        Log.d("result=url::::", "error1");
                    }else{
                        editor.putString("RESULT_RANK" + String.valueOf(i+1) + "_URL2", jobj.getString("IMAGE_URL2"));
                        Log.d("result=url::::", jobj.getString("IMAGE_URL2") + "");
                    }

                }catch (Exception e){
                    Log.d("result=url::::", "error2" + e);
                }


                Log.d("loadinglog_"+i+"", "PETNAME : " + jobj.getString("PETNAME") +"_SIZE : "+  jobj.getString("SIZE") +"_BATTRYSAPERATE :" + jobj.getString("BATTERYSAPERATE") + "_WIRELESSCHARGE : " + jobj.getString("WIRELESSCHAREGE") );

            }

            editor.commit();


        } catch (Exception e) {
            Log.d("e", e + "" );
        }


    }

}
