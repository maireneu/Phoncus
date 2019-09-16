package com.phoncus.app.alpha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.phoncus.app.alpha.etc.ScalableLayout;
import com.phoncus.app.alpha.start.StartActivity;

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

public class HomeActivity2 extends Activity implements AbsListView.OnScrollListener, View.OnClickListener {

    //ListVeiw 준비
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private MyArrayAdapter adapter = null;

    //애니메이션 변수 선언
    private Animation anim_text = null;
    private Animation anim_listview = null;


    //View 변수 선언
    private ListView listView = null;
    private TextView curation_text = null;
    private TextView curation_text2 = null;
    private ImageView home_image1 = null;
    private ImageView home_image2 = null;

    //UI 접근위한 핸들러 선언
    private Handler handler_home;

    //이미지 몇개 받아왔는지 기억하는 변수
    public static int initial=0;

    BackThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initial = 0;

        //레이아웃 연결
        setContentView(R.layout.activity_home);

        //뷰 연결
        home_image1 = (ImageView) findViewById(R.id.home_image1);
        home_image2 = (ImageView) findViewById(R.id.home_image2);

        listView = (ListView) findViewById(R.id.list_smartphone);
        curation_text = (TextView) findViewById(R.id.curation_text);
        curation_text2 = (TextView) findViewById(R.id.curation_text2);

        //애니메이션 연결
        anim_text = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_anim1);
        anim_listview  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_anim2);

        mThread = new BackThread(mHandler);
        mThread.setDaemon(true);
        mThread.start();


        //이미지뷰 터치시 반응 설정, implements 필요
        home_image1.setOnClickListener(this);
        home_image2.setOnClickListener(this);
        //리스트뷰를 액티비티에 설치, 연결
        adapter = new MyArrayAdapter(this,R.layout.listview_home);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        postaddview();
    }

    private void postaddview(){
       new ImageURLWebTaskPost(initial).execute();
       initial = initial + 10;
        Log.d("A_pos", "postaddview: ");
    }

    class Text_ui extends Thread{
        Handler handler_home = new Handler();
        @Override
        public void run() {
            super.run();
            //핸들러 실행
            handler_home.post(run_anim_text);

        }
    }

    class BackThread extends Thread {
        Handler mHandler;
        int start = 0;
        int mBackValue = 0;
        public BackThread(Handler handler){
            mHandler = handler;
        }

        @Override
        public void run() {
            super.run();
            if(start == 0) {
                Message msg = Message.obtain();
                msg.what = 0;
                msg.arg1 = mBackValue;
                mHandler.sendMessage(msg);
                start++;
            }
        }
    }

    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0){
                curation_text.setAnimation(anim_text);
                curation_text2.setAnimation(anim_text);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    };

    Runnable run_anim_text = new Runnable() {
        @Override
        public void run() {
            try {
                //리스트뷰 올라오는 애니메이션
                curation_text.setAnimation(anim_text);
                curation_text2.setAnimation(anim_text);

            }catch (Exception e){
            }
        }
    };

    Runnable run_anim = new Runnable() {
        @Override
        public void run() {
            try {
                //리스트뷰 올라오는 애니메이션
                listView.startAnimation(anim_listview);
            }catch (Exception e){
            }
        }
    };

    //터치시 실행 메소드
    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.home_image1: //홈 이미지 1 누를때
                //start액티비티로 넘어감
                intent = new Intent(HomeActivity2.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);//애니메이션 초기화
                break;

            case R.id.home_image2: //홈 이미지 2 누를때
                //start액티비티로 넘어감
                intent = new Intent(HomeActivity2.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0); // 애니메이션 초기화
                break;

        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //리스트뷰 UI설정
    class ListViewItem {

        //리스트뷰에 들어갈 이미지, 텍스트등 변수 선언
        private Bitmap firstimage;
        private Bitmap secondimage;
        private String firstpetname;
        private String secondpetname;
        private String firstmanufacturer;
        private String secondmanufacturer;

        //해당 이미지나 텍스트를 리스트뷰에 띄우기 위해 set하는 (넣어주는) 메소드
        public void setFirstimage(Bitmap icon) {
            firstimage = icon ;
        }
        public void setSecondimage(Bitmap icon) {
            secondimage = icon ;
        }
        public void setFristpetname(String text) {
            firstpetname = text ;
        }
        public void setSecondpetname(String text) {
            secondpetname = text ;
        }
        public void setFirstmanufacturer(String text){
            firstmanufacturer = text;
        }
        public void setSecondmanufacturer(String text){
            secondmanufacturer = text;
        }

        //위에서 set한 리스트뷰 요소를 액티비티에 연결시켜 리스트뷰ui 구성하는 getView에서 불러오는 메소드
        public Bitmap getFirstimage() {
            return this.firstimage ;
        }
        public Bitmap getSecondimage() {
            return this.secondimage ;
        }
        public String getFirstpetname() {
            return this.firstpetname ;
        }
        public String getSecondpetname() {
            return this.secondpetname ;
        }
        public String getFirstmanufacturer() {
            return this.firstmanufacturer ;
        }
        public String getSecondmanufacturer() {
            return this.secondmanufacturer ;
        }

    }

    //리스트뷰 ui를 activity 연결
    class MyArrayAdapter extends BaseAdapter{

        //변수초기화
        private Context context = null;
        private int resource = 0;
        private ImageView smartphone_image1 = null;
        private ImageView smartphone_image2 = null;
        private TextView homelist_petname1 = null;
        private TextView homelist_petname2 = null;
        private TextView homelist_manufacturer1 = null;
        private TextView homelist_manufacturer2 = null;
        private com.ssomai.android.scalablelayout.ScalableLayout firstimagearea = null;
        private com.ssomai.android.scalablelayout.ScalableLayout secondimagearea = null;

        //context와 resource에 연결
        public MyArrayAdapter(Context context, int resource) {
            this.context=context;
            this.resource= resource;
        }

        //리스트뷰 사이즈 확인 메소드
        @Override
        public int getCount() {
            Log.d("getcont", "getCount: "+listViewItemList.size());
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if(view == null){
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(resource, null);
            }

            Log.d("postion4 ::", "onPostExecute: error");

            // xml과 뷰 연결
            smartphone_image1 = (ImageView)view.findViewById(R.id.list_smartphone_image1);
            smartphone_image2 = (ImageView)view.findViewById(R.id.list_smartphone_image2);
            homelist_petname1 = (TextView) view.findViewById(R.id.homelist_petname1);
            homelist_petname2 = (TextView) view.findViewById(R.id.homelist_petname2);
            homelist_manufacturer1 = (TextView) view.findViewById(R.id.homelist_manufacturer1);
            homelist_manufacturer2 = (TextView) view.findViewById(R.id.homelist_manufacturer2);

            firstimagearea = (com.ssomai.android.scalablelayout.ScalableLayout) view.findViewById(R.id.firstimage_area);
            secondimagearea = (com.ssomai.android.scalablelayout.ScalableLayout) view.findViewById(R.id.secondimage_area);

            final ListViewItem listViewItem = listViewItemList.get(position);

            //해당 이미지뷰나 텍스트뷰에 이미지와 텍스트 삽입
            smartphone_image1.setImageBitmap(listViewItem.getFirstimage());
            smartphone_image2.setImageBitmap(listViewItem.getSecondimage());
            homelist_petname1.setText(listViewItem.getFirstpetname());
            homelist_petname2.setText(listViewItem.getSecondpetname());
            homelist_manufacturer1.setText(listViewItem.getFirstmanufacturer());
            homelist_manufacturer2.setText(listViewItem.getSecondmanufacturer());

            firstimagearea.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    ListViewItem listViewItem = listViewItemList.get(position);

                }
            });
            secondimagearea.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    ListViewItem listViewItem = listViewItemList.get(position);
                }
            });

            Log.d("D_pos", "getView: ");
            return view;
        }

        //리스트뷰에 한 줄 삽입하기위해 호출하는 메소드
        public void addItem(String result_petname1, String result_petname2,String result_manufacturer1,String result_manufacturer2,String result_url1,String result_url2 ) throws ExecutionException, InterruptedException {

            //각종 변수선언
            ListViewItem item = new ListViewItem();
            Bitmap[] result = new Bitmap[2];

            try {
                Log.d("C_pos", "addItem: "+result_url1+"+"+result_url2);
                result[0] = new ImageWebTaskPost().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,result_url1).get();
                item.setFirstimage(result[0]);
                result[1] = new ImageWebTaskPost().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,result_url2).get();
                item.setSecondimage(result[1]);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // 받아온 이미지, 이름 제조사 등을 리스트뷰에 set시켜준다
            item.setFristpetname(result_petname1);
            item.setSecondpetname(result_petname2);
            item.setFirstmanufacturer(result_manufacturer1);
            item.setSecondmanufacturer(result_manufacturer2);

            // 위에서 설정한 리스트뷰 한줄을 getView로 넘겨준다
            notifyDataSetChanged();
            listViewItemList.add(item);

        }


    }



    private class ListViewClickListener implements AdapterView.OnItemClickListener{
        Context mcontext = null;
        public ListViewClickListener(Context context){
            mcontext = context;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListViewItem listViewItem = listViewItemList.get(position);
            String tmp = null;

            switch(view.getId()){

                case R.id.firstimage_area:

                    tmp = listViewItem.getFirstpetname();

                    break;
                case R.id.secondimage_area:

                    tmp = listViewItem.getSecondpetname();

                    break;
            }

            Toast.makeText(
                    mcontext,
                    "test"+position+tmp,
                    Toast.LENGTH_SHORT
            ).show();




        }
    }

    //서버로부터 이미지 url, 해당 폰이미지의 이름, 제조사등을 다운받는 클래스
    public class ImageURLWebTaskPost extends AsyncTask<Void, Void, String> {

        private String result;
        private int tmp_intinitial;

        public  ImageURLWebTaskPost(int initial) {
            tmp_intinitial = initial;
        }

        @Override
        protected String doInBackground(Void... Void) {
            try {
                URL Url = new URL("http://221.143.21.39/curation/homeimage");  // URL화 한다.
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST");
                conn.setDoInput(true);        // 읽기모드 지정
                conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
                conn.connect();

                String body = "INITIAL=" + tmp_intinitial + "&IMAGENUMBER=10";


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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String result_petname[] = new String[10];
            String result_manufacturer[] = new String[10];
            String result_url[] = new String[10];

            // 서버에서 JSONArray 형식으로 받아오므로 이를 parsing 해서 일반 string 으로 변환해야한다
            JSONArray jarr = null;
            try {
                jarr = new JSONArray(result);
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject jobj = jarr.getJSONObject(i);

                    //위에서 받은 스마트폰 이름과 제조사 넣기
                    result_url[i] = jobj.getString("URL");
                    result_petname[i] = jobj.getString("PETNAME");
                    result_manufacturer[i] = jobj.getString("MANUFACTURER");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                for (int i = 0; i < 5; i++) {
                    Log.d("B_pos", "onPostExecute: " + result_petname[2 * i] + "+" + result_url[2 * i]);
                    adapter.addItem(result_petname[2 * i], result_petname[2 * i + 1], result_manufacturer[2 * i], result_manufacturer[2 * i + 1], result_url[2 * i], result_url[2 * i + 1]);
                }


            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }


    //서버로부터 이미지를 다운받는 클래스
    public class ImageWebTaskPost extends AsyncTask<String, Void, Bitmap> {
        Bitmap smartphone_image = null;
         // 서버에서 준 URL 갯수만큼 이미지 호출. 2개라서 2번 작업
            @Override
            protected Bitmap doInBackground(String...params){
                try {
                    URL Url = new URL(params[0]);  // URL화 한다.
                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    smartphone_image = BitmapFactory.decodeStream(is);


                } catch (IOException io) {
                    io.printStackTrace();
                }
                return smartphone_image;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap){
                super.onPostExecute(bitmap);

                if (bitmap != null) {

                    //handler_home.post(run_anim);
                }

            }
    }

}


