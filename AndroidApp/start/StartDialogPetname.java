package com.phoncus.app.alpha.start;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.phoncus.app.alpha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class StartDialogPetname extends Dialog {


    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private StratPetnameAdapter adapter = null;

    private ListView listView ;
    private Context context = null;

    private View.OnClickListener clickListener;
    private TextView text_petname;
    private String manufacturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_start_petname);
        listView = (ListView) findViewById(R.id.list_smartphone);
        adapter = new StratPetnameAdapter(context,R.layout.listview_start);
        listView.setAdapter(adapter);

        new petnamelistWebTaskPost().execute(manufacturer);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                text_petname.setText(item.getpetname());
                text_petname.setTextColor(Color.parseColor("#000000"));
                clickListener.onClick(v);
            }
        }) ;
    }


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public StartDialogPetname(Context context,View.OnClickListener listener,TextView text, String manufacturer) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;
        this.text_petname = text;
        this.clickListener = listener;

        if(manufacturer.equals("제조사") || manufacturer == null || manufacturer.equals("") ) {
            this.manufacturer = "전체";
        }else{
            this.manufacturer = manufacturer;
        }

    }

    class ListViewItem {

        //리스트뷰에 들어갈 이미지, 텍스트등 변수 선언
        private String petname;

        //해당 이미지나 텍스트를 리스트뷰에 띄우기 위해 set하는 (넣어주는) 메소드
        public void setpetname(String text) {
            petname = text ;
        }

        //위에서 set한 리스트뷰 요소를 액티비티에 연결시켜 리스트뷰ui 구성하는 getView에서 불러오는 메소드
        public String getpetname() {
            return this.petname ;
        }


    }

    class StratPetnameAdapter extends BaseAdapter {

        //변수초기화
        private Context context = null;
        private int resource = 0;
        private TextView petname_view = null;


        //context와 resource에 연결
        public StratPetnameAdapter(Context context, int resource) {
            this.context=context;
            this.resource= resource;
        }


        //리스트뷰 사이즈 확인 메소드
        @Override
        public int getCount() {
            return listViewItemList.size();
        }

        //리스트뷰 위치 메소드?
        @Override
        public Object getItem(int position) {
            return listViewItemList.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        //리스트뷰 ui 조립해서 액티비티에 연결
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            if(view == null){
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(resource, null);
            }

            // xml과 뷰 연결
            petname_view = (TextView) view.findViewById(R.id.list_starttext);


            ListViewItem listViewItem = listViewItemList.get(position);

            //해당 이미지뷰나 텍스트뷰에 이미지와 텍스트 삽입
            petname_view.setText(listViewItem.getpetname());

            //위에서 설정한 뷰 적용
            return view;
        }


        //리스트뷰에 한 줄 삽입하기위해 호출하는 메소드
        public void addItem(String petname) throws ExecutionException, InterruptedException {

            //각종 변수선언
            ListViewItem item = new ListViewItem();
            item.setpetname(petname);

            // 위에서 설정한 리스트뷰 한줄을 getView로 넘겨준다
            listViewItemList.add(item);

        }

    }

    public class petnamelistWebTaskPost extends AsyncTask<String, Void, String> {

        private String result;

        @Override
        protected String doInBackground(String... Params) {
            try {
                URL Url = new URL("http://221.143.21.39/curation/phonelist");  // URL화 한다.
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST");
                conn.setDoInput(true);        // 읽기모드 지정
                conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
                conn.connect();

                String body = "MANUFACTURER="+Params[0];


                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes("UTF-8"));
                os.flush();
                os.close();

                InputStream is = conn.getInputStream();
                StringBuilder builder = new StringBuilder();   //문자열을 담기 위한 객체
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));  //문자열 셋 세팅
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                result = builder.toString();
                return result;


            } catch (IOException io) {
                io.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d("whatthehell", "onPostExecute" + result);

            if(result != null) {
                try {

                    // 서버에서 JSONArray 형식으로 받아오므로 이를 parsing 해서 일반 string 으로 변환해야한다
                    JSONArray jarr = new JSONArray(result);

                    // 서버에서 준 URL 갯수만큼 이미지 호출. 2개라서 2번 작업
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject jobj = jarr.getJSONObject(i);

                        //위에서 받은 스마트폰 이름과 제조사 넣기
                        adapter.addItem(jobj.getString("PETNAME"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
