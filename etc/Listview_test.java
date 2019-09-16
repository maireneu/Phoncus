package com.phoncus.app.alpha.etc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.phoncus.app.alpha.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Listview_test extends Activity implements AbsListView.OnScrollListener {
    private ListView listView = null;

    private List<String[]> data = new ArrayList<String[]>();
    private ArrayAdapter arrayAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test);
        listView = (ListView) findViewById(R.id.listView);

        //리스트뷰에 들어갈 값을 보관
        for(int i=0;i<13;i++) {
            String[] str = new String[4];
            str[0] = "img.jpg";
            str[1] = "제목"+i;
            str[2] = "내용1";
            str[3] = "내용2";

            data.add(str);
        }


        //adapter 구성
        //arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data   );
        arrayAdapter = new MyArrayAdapter(this, R.layout.listview_item, data);

        //listview에 적용
        listView.setAdapter(arrayAdapter);
        listView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //if ((listview.getChildAt(0).getTop() == 0) && (listview.getFirstVisiblePosition() == 0)) {
        if (listView.getFirstVisiblePosition() == 0) {
            Log.d("TAG", "top");
            //webTask = new WebTask(2);
            //page += 10;
            //webTask.execute(SERVER + "/" + page, "b2", "c");
        }

        if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1
                && listView.getChildAt(listView.getChildCount() - 1).getBottom() <= listView.getHeight()) {
            Log.d("TAG", "bottom");
            for(int i=0;i<13;i++) {
                String[] str = new String[4];
                str[0] = "img.jpg";
                str[1] = "제목"+i;
                str[2] = "내용1";
                str[3] = "내용2";

                data.add(str);
            }
            arrayAdapter.notifyDataSetChanged();


        }
    }



    class MyArrayAdapter extends ArrayAdapter<String[]>{

        private List<String[]> data = null;
        private Context context = null;
        private int resource = 0;


        public MyArrayAdapter(Context context, int resource, List<String[]> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.data = objects;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(view == null){
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(resource, null);
            }

            String[] tmp = data.get(position);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            new WebTaskDownImage(imageView).execute("http://221.143.21.39/sp_image/galaxy7.jpg");


            TextView txt1 = (TextView)view.findViewById(R.id.txt1);
            TextView txt2 = (TextView)view.findViewById(R.id.txt2);
            TextView txt3 = (TextView)view.findViewById(R.id.txt3);

            txt1.setText(tmp[1]);
            txt2.setText(tmp[2]);
            txt3.setText(tmp[3]);

            return view;
        }
    }


    class WebTaskDownImage extends AsyncTask<String, Void, Bitmap> {
        private ImageView img = null;

        public WebTaskDownImage(ImageView img) {
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                //이미지 다운로드
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    HttpEntity httpEntity = httpResponse.getEntity();
                        if(httpEntity != null){
                            InputStream inputStream = httpEntity.getContent();
                            return BitmapFactory.decodeStream(inputStream);
                        }
                    }
                return null;
            }catch(Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            if(s!= null){


                img.setBackgroundDrawable(new BitmapDrawable(s));

            }
        }

        }


}