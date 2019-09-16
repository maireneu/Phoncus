package com.phoncus.app.alpha.choice;



import com.phoncus.app.alpha.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SizeFragment extends Fragment implements  View.OnClickListener{

	DrawingView ov= null;
	private LinearLayout footer = null;
	private ImageView startbutton = null;
	private ImageView smartphone =null;
	private ImageView smartphone2 =null;
	private ImageView thousand_won = null;
	private TextView currentsizetext = null;
	private TextView currentsizetext2 = null;
	private TextView selectsizetext = null;
	private TextView foot_text =null;

	private float smartphonex;
	private float smartphoney;
	private String currentsize;
	private String selectsize;

	private Bitmap bitmap = null;
	Bitmap bitmap1 = null;
	private float tmpi;
	private float tmpi2;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_choice_size2, container, false);

		//현재 폰 size 데이터 가져오기
		SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		currentsize = prefs.getString("SIZE", "0");

 		//만약 골라놨던 사이즈가 없으면 현재폰 사이즈가 고른 사이즈로 해준다 (처음 페이지를 볼경우)
		//골라놨던 사이즈가 있으면 고른 사이즈를 초기값으로
		if (prefs.getString("SELECT_SIZE","0") == ""){
			selectsize = currentsize;
		}else{
			selectsize = prefs.getString("SELECT_SIZE", "0");
		}



		tmpi2=Float.parseFloat(currentsize);
		foot_text= (TextView) view.findViewById(R.id.footer_text);

		startbutton = (ImageView) view.findViewById(R.id.next);
		startbutton.setOnClickListener(this);


		smartphone = (ImageView) view.findViewById(R.id.currentsmartphoneimage);
		smartphone2 = (ImageView) view.findViewById(R.id.smartphone2);
		thousand_won = (ImageView) view.findViewById(R.id.thousand_won2);
		currentsizetext = (TextView) view.findViewById(R.id.currentsizetext);
		currentsizetext2 = (TextView) view.findViewById(R.id.currentsizetext2);
		selectsizetext = (TextView) view.findViewById(R.id.selectsize);
		currentsizetext2.setText(currentsize + "인치");
		selectsizetext.setText(selectsize + "인치");
		thousand_won.setImageResource(R.mipmap.thousand_won2);



		//초기 화면 크기에 따른 첫 화면 결정
		if(Float.parseFloat(currentsize)<4.5){

			foot_text.setText("4인치 초반대는 매우 작은 스마트폰이에요\n"+
					"한 손에 쏙 들어와 아담하고 저성능 저가형 폰이 주를 이루죠\n"+
					"아이폰4, 아이폰SE 등이 이 구간에 속해요");

		}else if(Float.parseFloat(currentsize) < 5){
			foot_text.setText("4인치 후반대는 평균보다 작은 편인 스마트폰 크기예요\n" +
					"한손으로 다루기 간편해요\n" +
					"아이폰 7과 6S가 이정도 크기예요");

		}else if(Float.parseFloat(currentsize)<=5.35){
			foot_text.setText("5인치 초반대는 가장 많이 사용하는 스마트폰 크기예요.\n" +
					"손이 크면 한손으로 조작할 수 있어요\n" +
					"갤럭시s 시리즈가 이정도 크기예요");

		}else if(Float.parseFloat(currentsize)<5.7){
			foot_text.setText("5인치 중반대는 상당히 큰 스마트폰 형태예요\n" +
					"동영상보기는 좋지만 한손으로 조작하기 어렵고 주머니에 넣기 어렵죠\n" +
					"갤럭시 노트시리즈와 아이폰6s+가 대표적이죠");

		}else{
			foot_text.setText("5인치 후반대는 매우 큰 스마트폰 형태예요.\n" +
					"특별 컨셉 형태로 나온 일부 스마트폰이 있으나 매우 드물어요.\n" +
					"예전 베가NO.6 가 여기 속해요");
		}


		//중간에 표시는 폰 크기, 사이즈 결정. 스마트폰 화면에 따라 다르게 표시된다.
		com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) smartphone.getLayoutParams();
		smartphonex = Math.round(params.getScale_Width()*(Float.parseFloat(currentsize)/4.75) - params.getScale_Width());
		smartphoney = Math.round(params.getScale_Height()*(Float.parseFloat(currentsize)/4.75) - params.getScale_Height());

		params.setScale_Width(Math.round(params.getScale_Width()*(Float.parseFloat(currentsize)/4.75)));
		params.setScale_Height(Math.round(params.getScale_Height()*(Float.parseFloat(currentsize)/4.75)));

		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) thousand_won.getLayoutParams();
		params.setScale_Left(params.getScale_Left()-smartphonex);

		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) currentsizetext.getLayoutParams();
		//params.setScale_Width(Math.round(params.getScale_Width()*(Float.parseFloat(currentsize)/4.75)));
		params.setScale_TextSize(Math.round(params.getScale_TextSize()*(Float.parseFloat(currentsize)/4.75)));
		params.setScale_Left(params.getScale_Left()-smartphonex/2 - (Float.parseFloat(currentsize) - 4)*15);
		params.setScale_Top(params.getScale_Top()-smartphoney/2);

		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) currentsizetext2.getLayoutParams();
		//params.setScale_Width(Math.round(params.getScale_Width()*(Float.parseFloat(currentsize)/4.75)));
		params.setScale_TextSize(Math.round(params.getScale_TextSize()*(Float.parseFloat(currentsize)/4.75)));
		params.setScale_Left(params.getScale_Left()-smartphonex/2);
		params.setScale_Top(params.getScale_Top()-smartphoney/2);


		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) smartphone.getLayoutParams();
		smartphonex = Math.round(params.getScale_Width()*(Float.parseFloat(selectsize)/4.75) - params.getScale_Width());
		smartphoney = Math.round(params.getScale_Height()*(Float.parseFloat(selectsize)/4.75) - params.getScale_Height());

		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) selectsizetext.getLayoutParams();
		params.setScale_Width(Math.round(params.getScale_Width()*(Float.parseFloat(selectsize)/4.75)));
		params.setScale_TextSize(Math.round(params.getScale_TextSize()*(Float.parseFloat(selectsize)/4.75)));
		params.setScale_Left(params.getScale_Left()+smartphonex/2);
		params.setScale_Top(params.getScale_Top()-smartphoney/2);

		params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) smartphone2.getLayoutParams();
		smartphonex=params.getScale_Width();
		smartphoney=params.getScale_Height();
		params.setScale_Width(Math.round(params.getScale_Width()*(Float.parseFloat(selectsize)/4.75)));
		params.setScale_Height(Math.round(params.getScale_Height()*(Float.parseFloat(selectsize)/4.75)));


		//footer 에 size seekbar 연결
		footer = (LinearLayout) view.findViewById(R.id.sizeseekbar);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		// customview 켜기
		ov = new DrawingView(this.getActivity());
		ov.setZOrderOnTop(true);

		ov.getHolder().setFormat(PixelFormat.TRANSPARENT);
		footer.addView(ov);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.next) {
			footer.removeView(ov);


			//
			SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
					Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("SELECT_SIZE",tmpi2 + "");
			editor.commit();

			startbutton.setImageResource(R.mipmap.next);

			((ChoiceActivity)getActivity()).nextFragmentReplace();
		}
	}

	class DrawingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

		private float w;
		private float h;
		private float seekbarh; //seekbar 높이
		private float seekbarw; //seekbar 너비
		private float buttonh; //seekbar 버튼 높이
		private float buttonw; //seekbar 버튼 너비
		private float waterdroph; //waterdrop 높이
		private float waterdropw; //waterdrop 너비
		private boolean touch=true; //touch 인식
		private int n = 41; // seekbar의 갯수 변수
		private Bitmap img1; //button
		private Bitmap img2; //seekbar
		private Bitmap img3; //water
		Canvas canvas = null;
		float myX = 10;
		boolean first = true;
		boolean checkdraw = true;
		float[] ncoordinate = new float[n];
		Paint paint = new Paint();
		private float tmp = 0;
		private float tmpj = 0;


		private SurfaceHolder holder = null;
		private boolean isRunning = false;
		private Thread thread = null;

		public DrawingView(Context context) {
			super(context);
			holder= this.getHolder();
			holder.addCallback(this);
			img1 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_button);
			img2 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_1);
			img3 = BitmapFactory.decodeResource(getResources(), R.mipmap.waterdrop);

			DisplayMetrics metrics = new DisplayMetrics();
			WindowManager windowManager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
			windowManager.getDefaultDisplay().getMetrics(metrics);



			img2 = Bitmap.createScaledBitmap(img2, metrics.widthPixels*711/1080 , metrics.heightPixels*21/1920 ,false);
			img1 = Bitmap.createScaledBitmap(img1, metrics.widthPixels*65/1080 , metrics.heightPixels*65/1920 ,false);
			img3 = Bitmap.createScaledBitmap(img3, metrics.widthPixels*70/1080 , metrics.heightPixels*105/1920 ,false);

			//img2 = Bitmap.createScaledBitmap(img2, 711, 21 , false);
			seekbarw = img2.getWidth();
			seekbarh = img2.getHeight();
			buttonh = img1.getWidth();
			buttonw = img1.getHeight();
			waterdropw = img3.getWidth();
			waterdroph = img3.getHeight();

			bitmap =  BitmapFactory.decodeResource(getResources(), R.mipmap.smartphone5);
			paint.setAntiAlias(true);
			paint.setColor(Color.argb(255, 255, 255, 255));
		}


		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_MOVE:
					Log.d("aa", "m" + event.getX() + "");
					myX = event.getX();
					//draw(this.canvas);
					break;
				case MotionEvent.ACTION_UP:
					Log.d("aa", "u" + event.getX() + "");
					touch=false;
					break;
				case MotionEvent.ACTION_DOWN:
					Log.d("aa", "d" + event.getX() + "");
					myX = event.getX();
					touch=true;
					checkdraw = true;
					break;
			}
			return true;// super.onTouchEvent(event);
		}


		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
			this.canvas = canvas;



			Log.d("aa", "onDraw");
					paint.setTextSize(w/33);

					canvas.drawText("4.0", (w-seekbarw)/2-20, (h - seekbarh)/48*13, paint);
					canvas.drawText("~4.5", w/2-seekbarw/4-40, (h - seekbarh)/48*13, paint);
					canvas.drawText("~5.0", w/2-30, (h - seekbarh)/48*13, paint);
					canvas.drawText("~5.5", w/2+seekbarw/4-40, (h - seekbarh)/48*13, paint);
					canvas.drawText("6.0", (w+seekbarw)/2-30, (h - seekbarh)/48*13, paint);


					//canvas.drawBitmap(img2, (w-seekbarw)/2, (h - seekbarh)/12*4, null);

					paint.setTextSize(w/38);

					if(touch==true) {
						if ((w - seekbarw) / 2 > myX) {
							myX = (w - seekbarw) / 2;
						}
						else if ((w + seekbarw) / 2 < myX) {
							myX = (w + seekbarw) / 2;
						}
						if(first==false) {
							canvas.drawBitmap(img3, myX - waterdropw / 2, (h / 12 - waterdroph / 12) * 4 - waterdroph / 5 * 4, null);
							for(int i=0; i<n; i++){
								if (Math.abs(ncoordinate[i] - myX)<w/400) {
									tmp = myX;
									tmpj = i;
								}
							}
							Log.d("dd", myX+"");
							float ft = 4+tmpj/20;
							String st = String.format("%.2f",ft);
							canvas.drawText(st,myX - waterdropw / 2 + waterdropw/12, (h / 12) * 4 - waterdroph / 5 * 4 + waterdroph/12, paint);

						}

					}
					else {
						if ((w - seekbarw) / 2 > myX) {
							myX = (w - seekbarw) / 2;
						}
						else if ((w + seekbarw) / 2 < myX) {
							myX = (w + seekbarw) / 2;
						}
						tmp = 999999;
						for(int i=0; i<n; i++){
							if(Math.abs(tmp-myX) > Math.abs(myX-ncoordinate[i])){
								tmp=ncoordinate[i];
								tmpi=i;
							}
						}

						Log.d("bb", tmp+"");
						if(tmp > myX+w/400){
							myX = myX + w/200;
						}else if(tmp<myX-w/400){
							myX = myX - w/200;
						}else{
							checkdraw = false;
							getActivity().runOnUiThread (new Thread(new Runnable() {
								public void run() {
									tmpi= 4 + tmpi/20;
									com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) smartphone2.getLayoutParams();

									float tmpx = Math.round(smartphonex*((tmpi)/4.75) - params.getScale_Width());
									float tmpy = Math.round(smartphoney*((tmpi)/4.75) - params.getScale_Height());

									if(tmpi<4.5){

										foot_text.setText("4인치 초반대는 매우 작은 스마트폰이에요\n"+
												"한 손에 쏙 들어와 아담하고 저성능 저가형 폰이 주를 이루죠\n"+
												"아이폰4, 아이폰SE 등이 이 구간에 속해요");

									}else if(tmpi < 5){
										foot_text.setText("4인치 후반대는 평균보다 작은 편인 스마트폰 크기예요\n" +
												"한손으로 다루기 간편해요\n" +
												"아이폰 7과 6S가 이정도 크기예요");

									}else if(tmpi<5.35){
										foot_text.setText("5인치 초반대는 가장 많이 사용하는 스마트폰 크기예요.\n" +
												"손이 크면 한손으로 조작할 수 있어요\n" +
												"갤럭시s 시리즈가 이정도 크기예요");

									}else if(tmpi<5.7){
										foot_text.setText("5인치 중반대는 상당히 큰 스마트폰 형태예요\n" +
												"동영상보기는 좋지만 한손으로 조작하기 어렵고 주머니에 넣기 어렵죠\n" +
												"갤럭시 노트시리즈와 아이폰6s+가 대표적이죠");

									}else{
										foot_text.setText("5인치 후반대는 매우 큰 스마트폰 형태예요.\n" +
												"특별 컨셉 형태로 나온 일부 스마트폰이 있으나 매우 드물어요.\n" +
												"예전 베가NO.6 가 여기 속해요");
									}




									params.setScale_Width(Math.round(smartphonex*((tmpi)/4.75)));
									params.setScale_Height(Math.round(smartphoney*((tmpi)/4.75)));
									bitmap1 = Bitmap.createScaledBitmap(bitmap, (int) params.getScale_Width() , (int) params.getScale_Height() , false);
									smartphone2.setImageBitmap(bitmap1);

									params = (com.ssomai.android.scalablelayout.ScalableLayout.LayoutParams) selectsizetext.getLayoutParams();
									params.setScale_TextSize(Math.round(params.getScale_TextSize()*(tmpi/tmpi2)));
									params.setScale_Left(params.getScale_Left()+tmpx/2);
									params.setScale_Top(params.getScale_Top()-tmpy/2);
									selectsizetext.setText( tmpi + "인치");
									tmpi2=tmpi;






									Log.d("cc",String.valueOf(tmpi));
									Log.d("cc",String.valueOf(4+tmpi/20));
								}

							}));

						}


					}

				canvas.drawBitmap(img1, myX - buttonw / 2, (h / 12 - buttonh / 12)*4, null);
				//canvas.drawBitmap(img1, myX - buttonw / 2, h / 2 - buttonh / 2, null);

			//canvas.drawBitmap(img1, myX, 200, null);

			if(first==true){
				first = false;
				checkdraw = false;
			}


		}


		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			//this.holder = holder;
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
					Log.d("TAG", Log.getStackTraceString(e));
				}
			}
			thread = null;
		}


		@Override
		public void run() {
			Canvas canvas = null;
			while (isRunning) {
				if(checkdraw==true) {
					canvas = holder.lockCanvas(null);
					if (canvas != null && first == true) {
						w = canvas.getWidth();
						h = canvas.getHeight();
						for (int i = 0; i < n; i++) {
							ncoordinate[i] = (w - seekbarw) / 2 * (n - i - 1) / (n - 1) + (w + seekbarw) / 2 * i / (n - 1);
						}

						//myX = w / 10;
						myX = ncoordinate[((int) Math.round((Float.parseFloat(selectsize) -4) *20))];
						//Log.d("aa", myX + "," + Math.round((Float.parseFloat(currentsize) -4) *20) + "," + currentsize + "");

						}
						synchronized (holder) {
							try {
							if (canvas != null) {
								draw(canvas);
							}

							Thread.sleep(10);
						} catch (Exception e) {
							Log.d("TAG", Log.getStackTraceString(e));
						}
					}

					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}

	}

}