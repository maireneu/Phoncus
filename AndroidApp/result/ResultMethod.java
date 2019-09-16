package com.phoncus.app.alpha.result;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoncus.app.alpha.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;


public class ResultMethod{

    private ImageView smartphone_view1=null;
    private ImageView smartphone_view2=null;

    private String performance;
    private TextView text_performance;
    private String display;
    private TextView text_display;

    private String camera;
    private TextView text_camera;
    private String addition = "";
    private TextView text_addition;

    private TextView text_petname;
    private TextView text_size;
    private TextView text_os;
    private TextView text_manufacturer;

    private Double size;
    private String os;
    private String osv;
    private String manufacturer;
    private String petname;


    public void Imageset(Context context, View view, int rank){
        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        smartphone_view1 = (ImageView) view.findViewById(R.id.smartphone_view1);
        smartphone_view2 = (ImageView) view.findViewById(R.id.smartphone_view2);

        ImageWebTaskPost iwtp = new ImageWebTaskPost(smartphone_view1);
        iwtp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,prefs.getString("RESULT_RANK"+rank+"_URL1", "0"), "0");
        ImageWebTaskPost iwtp2 = new ImageWebTaskPost(smartphone_view2);
        iwtp2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,prefs.getString("RESULT_RANK"+rank+"_URL2", "0"), "0");

        Log.d("Imageset:", "Imageset: "+prefs.getString("RESULT_RANK"+rank+"_URL", "0")+"1.png");

    }


    public class ImageWebTaskPost extends AsyncTask<String, Void, Bitmap> {
        private ImageView img1=null;
        Bitmap smartphone_image = null;

        public ImageWebTaskPost(ImageView iv) {
            this.img1 = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                Log.d("acynctask ::", params[0]+"");
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
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                img1.setImageBitmap(smartphone_image);
            }
        }
    }



    public void  startview(Context context, View view, int rank){

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        size = Double.parseDouble(prefs.getString("RESULT_RANK"+rank+"_SIZE", "0"));

        os = prefs.getString("RESULT_RANK"+rank+"_OS", "0");
        osv = prefs.getString("RESULT_RANK"+rank+"_OS_VERSION", "0");
        manufacturer = prefs.getString("RESULT_RANK"+rank+"_MANUFACTURER", "0");
        petname = prefs.getString("RESULT_RANK"+rank+"_PETNAME", "0");

        text_petname = (TextView) view.findViewById(R.id.result_text_petname);
        text_size = (TextView) view.findViewById(R.id.result_text_size);
        text_os = (TextView) view.findViewById(R.id.result_text_os);
        text_manufacturer = (TextView) view.findViewById(R.id.result_text_manufacturer);

        view.post(new Runnable() {
            @Override
            public void run() {
                text_petname.setText(petname);
                text_size.setText(size + "인치");
                text_os.setText(os + "  " + osv);
                text_manufacturer.setText("제조사 : " + manufacturer);
            }
        });

    }


    public void performance(Context context, View view, int rank){

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        performance = prefs.getString("RESULT_RANK"+rank+"_PERFORMANCE", "0");
        text_performance = (TextView) view.findViewById(R.id.result_text_performance);

        view.post(new Runnable() {
            @Override
            public void run() {
                switch (performance) {
                    case "2":
                        text_performance.setText("(저사양) 인터넷 , 카톡 등 간단한 업무에 적합해요");
                        break;
                    case "3":
                        text_performance.setText("(저사양) 유튜브와 고화질 동영상을 끊김없이 볼 수 있어요");
                        break;
                    case "4":
                        text_performance.setText("(중간사양) 게임을 무난하게 즐길 수 있어요");
                        break;
                    case "5":
                        text_performance.setText("(고사양) 3D 고사양게임까지 무리없이 돌릴 수 있어요");
                        break;
                    case "6":
                        text_performance.setText("(최고사양) 누구도 부럽지않은 최고수준 스마트폰");
                        break;
                }
            }
        });
    }



    public void display(Context context, View view, int rank){

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        display = prefs.getString("RESULT_RANK"+rank+"_PPI", "0");
        text_display = (TextView) view.findViewById(R.id.result_text_diplay);

        view.post(new Runnable() {
            @Override
            public void run() {
                if (Integer.parseInt(display) < 200) {
                    text_display.setText("(저화질) 동영상을 보라고 만든 폰은 아닌 듯 하네요");
                } else if (Integer.parseInt(display) < 300) {
                    text_display.setText("(HD) 화면보기에 나쁘지 않아요");
                } else if (Integer.parseInt(display) < 450) {
                    text_display.setText("(FHD) 화질 때문에 불편하실 일은 없을거에요.");
                } else if (Integer.parseInt(display) < 1000) {
                    text_display.setText("(QHD) 현재 나온 스마트폰 최고화질!");
                }
            }
        });


    }

    public void camera(Context context, View view, int rank){

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        camera = prefs.getString("RESULT_RANK"+rank+"_CAMERA", "0");
        text_camera = (TextView) view.findViewById(R.id.result_text_camera);

        view.post(new Runnable() {
            @Override
            public void run() {
                switch (camera) {
                    case "1":
                        text_camera.setText("(최저품질) 사진 찍으려면 다른 걸 쓰세요.");
                        break;
                    case "2":
                        text_camera.setText("(일반품질) 사진을 즐겨 찍는 사람에겐 비추천해요");
                        break;
                    case "3":
                        text_camera.setText("(중상품질) 무난한 카메라성능");
                        break;
                    case "4":
                        text_camera.setText("(고품질) 여행가서 사진을 찍는 건 당신의 스마트폰이 될 거에요");
                        break;
                    case "5":
                        text_camera.setText("(최고품질) 카메라 특화폰이에요");
                        break;
                }
            }
        });

    }

    public void addition(Context context, View view, int rank){

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);


        text_addition = (TextView) view.findViewById(R.id.result_text_additional);





        if ((prefs.getString("RESULT_RANK"+rank+"_BATTRYSAPERATE", "0")).equals("O")) {
            addition = addition + "배터리 탈착형";
        } else {
            addition = addition + "배터리 내장형";
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_WIRELESSCHARGE", "0")).equals("O")) {
            addition = addition + ", 무선충전";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_WATERPROOF", "0")).equals("O")) {
            addition = addition + ", 방수기능";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_FINGERPRINT", "0")).equals("O")) {
            addition = addition + ", 지문인식";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_STYLUS", "0")).equals("O")) {
            addition = addition + ", 펜";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_KNOCKCODE", "0")).equals("O")) {
            addition = addition + ", 노크온";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_SAMSUNGPAY", "0")).equals("O")) {
            addition = addition + ", 삼성페이";

        }
        if ((prefs.getString("RESULT_RANK"+rank+"_BUTTON", "0")).equals("O")) {
            addition = addition + ", 전면 홈버튼";
        } else if ((prefs.getString("RESULT_RANK"+rank+"_BUTTON", "0")).equals("X")) {
            addition = addition + ", 홈버튼 없음";
        } else if ((prefs.getString("RESULT_RANK"+rank+"_BUTTON", "0")).equals("후면버튼")) {
            addition = addition + ", 후면버튼";
        } else if ((prefs.getString("RESULT_RANK"+rank+"_BUTTON", "0")).equals("폴더형")) {
            addition = addition + ", 폴더폰";
        } else if ((prefs.getString("RESULT_RANK"+rank+"_BUTTON", "0")).equals("물리키패드")) {
            addition = addition + ", 물리키패드";
        }
        view.post(new Runnable() {
            @Override
            public void run() {
            text_addition.setText(addition);
            }
        });
    }

    public int[] check (Context context, int rank){

        int[] check = new int[2];

        check[0] = 0;
        check[1] = 1;

        SharedPreferences prefs = context.getSharedPreferences("__setting",
                Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        if (Integer.parseInt(prefs.getString("RESULT_RANK"+rank+"_PPI", "0")) < 200) {
            check[0] = 2;
        } else if (Integer.parseInt(prefs.getString("RESULT_RANK"+rank+"_PPI", "0")) < 300) {
            check[0] = 3;
        } else if (Integer.parseInt(prefs.getString("RESULT_RANK"+rank+"_PPI", "0")) < 450) {
            check[0] = 4;
        } else if (Integer.parseInt(prefs.getString("RESULT_RANK"+rank+"_PPI", "0")) < 1000) {
            check[0] = 5;
        }


        if ((prefs.getString("RESULT_RANK"+rank+"_WIRELESSCHARGE", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_WATERPROOF", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_FINGERPRINT", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_STYLUS", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_KNOCKCODE", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }
        if ((prefs.getString("RESULT_RANK"+rank+"_SAMSUNGPAY", "0")).equals("O")) {

            check[1] = check[1] +1 ;
        }

        return check;

    }


}
