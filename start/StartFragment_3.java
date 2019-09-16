package com.phoncus.app.alpha.start;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.choice.ChoiceActivity;
import com.phoncus.app.alpha.etc.Config;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


//start의 세번째 화면
public class StartFragment_3 extends Fragment implements View.OnClickListener {

    String model = Build.MODEL; //스마트폰 모델명 담기


    //변수선언
    private TextView smartphone = null;
    private StartDialogPetname mCustomDialogPetname;
    private StartDialogManufacturer mCustomDialogManufacturer;

    private ImageView startbutton = null;
    private TextView manufacturer = null;
    private String[] str;


    //fragment에서는 oncreate가 onCreateView 임. fragment 생명주기 참조
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_3, container, false);


        //해당 스마트폰모델을 인식한 결과를 서버로 전송
        WebTaskPost wtp = new WebTaskPost();
        wtp.execute();


        //스타트 버튼 활성화
        startbutton = (ImageView) view.findViewById(R.id.startbutton);
        startbutton.setOnClickListener(this);


        smartphone = (TextView) view.findViewById(R.id.start_smartphone);
        smartphone.setOnClickListener(this);

        manufacturer = (TextView) view.findViewById(R.id.start_manufacturer);
        manufacturer.setOnClickListener(this);


        //smartphone.setKeyListener(null);


        return view;
    }


    //스마트폰의 모델명을 서버로 보내면, 서버에서 스마트폰의 이름과 제조사를 리턴해 준다
    public class WebTaskPost extends AsyncTask<Void, Void, Void> {
        private String result;

        @Override
        protected Void doInBackground(Void... Void) {
            try {
                URL Url = new URL("http://221.143.21.39/curation/modeltoname");  // URL화 한다.
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST"); // post방식 통신
                conn.setDoOutput(true);       // 쓰기모드 지정
                conn.setDoInput(true);        // 읽기모드 지정
                conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정

                String body = "MODEL=" + model + "";
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


            } catch (IOException io) {
                io.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (result != null) {

                str = parserStirngname(result); //JSON 방식으로 보내주므로 파싱필요
                if (str == null) {
                } else {

                    startbutton.setImageResource(R.mipmap.startbutton_blue);
                    smartphone.setTextColor(Color.parseColor("#000000"));
                    smartphone.setText(str[0]);
                    manufacturer.setTextColor(Color.parseColor("#000000"));
                    manufacturer.setText(str[1]);
                }
            }
        }
    }

    //파싱
    private String[] parserStirngname(String result) {
        try {

            JSONObject jobj = new JSONObject(result);
            // 핸드폰 이름은 화면에 출력
            String[] str = new String[2];
            str[0] = jobj.getString("PETNAME");
            str[1] = jobj.getString("MANUFACTURER");
            return str;

        } catch (Exception e) {
            return null;


        }


    }




    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.start_smartphone) {


            mCustomDialogPetname = new StartDialogPetname(getActivity(),petnamelistener,smartphone,manufacturer.getText().toString()); // 오른쪽 버튼 이벤트
            mCustomDialogPetname.show();

        }

        if (v.getId() == R.id.start_manufacturer) {


            mCustomDialogManufacturer = new StartDialogManufacturer(getActivity(),manufacuterlistener,manufacturer); // 오른쪽 버튼 이벤트
            mCustomDialogManufacturer.show();

        }


        //스타트 버튼 누르면 서버로 스마트폰의 이름을 보내고 현재폰의 size, 성능, 화질, 카메라성능 등을 리턴 해 준다
        if ((v.getId() == R.id.startbutton)) {

            //서버로 이름보내고 정보 받는 클래스
            Log.d("ERROR", "onPostExecute: " + smartphone.getText());

            if (smartphone.getText().equals("스마트폰명") || smartphone.getText() == null) {
                Toast.makeText(getActivity(), "스마트폰 기종인식에 실패하였습니다\n스마트폰을 직접 선택 해 주세요", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("ERROR", "onPostExecute: " + smartphone.getText());
                WebTaskPost2 wtp2 = new WebTaskPost2();
                wtp2.execute();
            }
        }
    }

    private View.OnClickListener manufacuterlistener = new View.OnClickListener() {
        public void onClick(View v) {
            mCustomDialogManufacturer.dismiss();
        }
    };

    private View.OnClickListener petnamelistener = new View.OnClickListener() {
        public void onClick(View v) {
            mCustomDialogPetname.dismiss();
        }
    };


    //서버로 이름보내고 정보 받는 클래스
    public class WebTaskPost2 extends AsyncTask<Void, Void, String> {
        private String result;
        private String petname;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            petname = smartphone.getText().toString();
        }

        @Override
        protected String doInBackground(Void... Void) {
            try {
                URL Url = new URL(Config.SERVER_ADDR);//"http://221.143.21.39/curation/modelconfirm");  // URL화 한다.

                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST"); // post방식 통신
                conn.setDoOutput(true);       // 쓰기모드 지정
                conn.setDoInput(true);        // 읽기모드 지정
                conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정


                //petname = URLEncoder.encode(petname, "UTF-8");
                String body = "PETNAME=" + petname + "";

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

                String[] arr;
                arr = parserStirngname2(result); //JSON으로 받아왔으니 파싱
                //smartphone.setText(str[0]);

                //받아온 정보를 file로 데이터 저장
                SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
                        Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("NAME", arr[0]);
                editor.putString("SIZE", arr[1]);
                editor.putString("PERFORMANCE", arr[2]);
                editor.putString("PPI", arr[3]);
                editor.putString("CAMERA", arr[4]);
                editor.commit();

                //정보 받아와서 저장했으면 Choice 액티비로 넘어간다.
                Intent intent = new Intent(getActivity(), ChoiceActivity.class);
                getActivity().startActivity(intent);

            }
        }
    }

    //파싱 메소드
    private String[] parserStirngname2(String result) {
        try {

            JSONObject jobj = new JSONObject(result);
            // 핸드폰 이름은 화면에 출력
            String[] arr;
            arr = new String[5];
            arr[0] = jobj.getString("PETNAME");
            arr[1] = jobj.getString("SIZE");
            arr[2] = jobj.getString("PERFORMANCE");
            arr[3] = jobj.getString("PPI");
            arr[4] = jobj.getString("CAMERA");
            return arr;

        } catch (Exception e) {
            return null;

        }

    }


}