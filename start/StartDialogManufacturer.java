package com.phoncus.app.alpha.start;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.phoncus.app.alpha.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class StartDialogManufacturer extends Dialog {


    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private StratManufacturerAdapter adapter = null;

    private ListView listView ;
    private Context context = null;

    private View.OnClickListener clickListener;
    private TextView text_manufacturer;


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
        adapter = new StratManufacturerAdapter(context,R.layout.listview_start);
        listView.setAdapter(adapter);


        try {
            adapter.addItem("");
            adapter.addItem("삼성");
            adapter.addItem("LG");
            adapter.addItem("팬택");
            adapter.addItem("그 외");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                text_manufacturer.setText(item.getManufacturer());
                text_manufacturer.setTextColor(Color.parseColor("#000000"));
                clickListener.onClick(v);

                // TODO : use item data.
            }
        }) ;


    }


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public StartDialogManufacturer(Context context,View.OnClickListener listener,TextView text) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;
        this.text_manufacturer = text;
        this.clickListener = listener;
    }

    class ListViewItem {

        //리스트뷰에 들어갈 이미지, 텍스트등 변수 선언
        private String manufacturer;

        //해당 이미지나 텍스트를 리스트뷰에 띄우기 위해 set하는 (넣어주는) 메소드
        public void setManufacturer(String text) {
            manufacturer = text ;
        }

        //위에서 set한 리스트뷰 요소를 액티비티에 연결시켜 리스트뷰ui 구성하는 getView에서 불러오는 메소드
        public String getManufacturer() {
            return this.manufacturer ;
        }
    }

    class StratManufacturerAdapter extends BaseAdapter {


        //변수초기화
        private Context context = null;
        private int resource = 0;
        private TextView petname_view = null;


        //context와 resource에 연결
        public StratManufacturerAdapter(Context context, int resource) {
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
            petname_view.setText(listViewItem.getManufacturer());

            //위에서 설정한 뷰 적용
            return view;
        }

        //리스트뷰에 한 줄 삽입하기위해 호출하는 메소드
        public void addItem(String manufactuer) throws ExecutionException, InterruptedException {

            //각종 변수선언
            ListViewItem item = new ListViewItem();
            item.setManufacturer(manufactuer);


            // 위에서 설정한 리스트뷰 한줄을 getView로 넘겨준다
            listViewItemList.add(item);

        }

    }

}
