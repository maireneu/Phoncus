package com.phoncus.app.alpha.choice;



import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.etc.DrawingView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class PerformanceFragment extends Fragment implements View.OnClickListener,ChoiceActivity.onKeyBackPressedListener {

	private LinearLayout footer = null;
	private ImageView nextbutton = null;
	private String current_performance_save;
	private String select_performance_save;

	private ImageView call=null;
	private ImageView message=null;
	private ImageView kakaotalk=null;
	private ImageView youtube=null;
	private ImageView video=null;
	private ImageView internet=null;
	private ImageView casual=null;
	private ImageView latestgame=null;
	private ImageView bestperformance=null;

	private boolean call_ = false;
	private boolean message_ = false;
	private boolean kakaotalk_ = false;
	private boolean youtube_ = false;
	private boolean video_ = false;
	private boolean internet_ = false;
	private boolean casual_ = false;
	private boolean latestgame_ = false;
	private boolean bestperformance_ = false;

	private float myX = 10;
	private boolean first = true;
	private boolean touchcheck = false;
	private boolean checkdraw = true;
	private float w;
	private float h;
	private float seekbarh; //seekbar 높이
	private float seekbarw; //seekbar 너비
	private float buttonh; //seekbar 버튼 높이
	private float buttonw; //seekbar 버튼 너비
	private boolean touch=true; //touch 인식
	private int n = 5; // seekbar의 갯수 변수
	float[] ncoordinate = new float[n];


	SharedPreferences prefs;

	DrawingView ov=null;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choice_performance2, container, false);
		nextbutton = (ImageView) view.findViewById(R.id.next);
		nextbutton.setOnClickListener(this);

		call = (ImageView) view.findViewById(R.id.performance_call);
		message = (ImageView) view.findViewById(R.id.performance_message);
		kakaotalk = (ImageView) view.findViewById(R.id.performance_kakao);
		internet = (ImageView) view.findViewById(R.id.performance_internet);
		video = (ImageView) view.findViewById(R.id.performance_video);
		youtube = (ImageView) view.findViewById(R.id.performance_youtube);
		casual = (ImageView) view.findViewById(R.id.performance_casual);
		latestgame = (ImageView) view.findViewById(R.id.performance_latestgame);
		bestperformance = (ImageView) view.findViewById(R.id.performance_bestperformance);

		call.setOnClickListener(this);
		message.setOnClickListener(this);
		kakaotalk.setOnClickListener(this);
		internet.setOnClickListener(this);
		video.setOnClickListener(this);
		youtube.setOnClickListener(this);
		casual.setOnClickListener(this);
		latestgame.setOnClickListener(this);
		bestperformance.setOnClickListener(this);


		prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

		current_performance_save=prefs.getString("PERFORMANCE","0");

		if (prefs.getString("SELECT_PERFORMANCE","0").equals("")) {
			select_performance_save = current_performance_save;
			switch (Integer.parseInt(select_performance_save)) {
					case 2:

						call.setImageResource(R.mipmap.call_blue);
						message.setImageResource(R.mipmap.message_blue);
						kakaotalk.setImageResource(R.mipmap.kakao_blue);
						internet.setImageResource(R.mipmap.internet_blue);
						call_ = true;
						message_ =true;
						kakaotalk_ = true;
						internet_=true;
						break;
					case 3:
						call.setImageResource(R.mipmap.call_blue);
						message.setImageResource(R.mipmap.message_blue);
						kakaotalk.setImageResource(R.mipmap.kakao_blue);
						internet.setImageResource(R.mipmap.internet_blue);
					video.setImageResource(R.mipmap.video_blue);
					youtube.setImageResource(R.mipmap.youtube_blue);
					call_ = true;
					message_ =true;
					kakaotalk_ = true;
					internet_=true;
					video_ = true;
					youtube_ = true;

					break;
				case 4:
					call.setImageResource(R.mipmap.call_blue);
					message.setImageResource(R.mipmap.message_blue);
					kakaotalk.setImageResource(R.mipmap.kakao_blue);
					internet.setImageResource(R.mipmap.internet_blue);
					video.setImageResource(R.mipmap.video_blue);
					youtube.setImageResource(R.mipmap.youtube_blue);
					casual.setImageResource(R.mipmap.casual_blue);
					call_ = true;
					message_ =true;
					kakaotalk_ = true;
					internet_=true;
					video_ = true;
					youtube_ = true;
					casual_=true;
					break;
				case 5:
					call.setImageResource(R.mipmap.call_blue);
					message.setImageResource(R.mipmap.message_blue);
					kakaotalk.setImageResource(R.mipmap.kakao_blue);
					internet.setImageResource(R.mipmap.internet_blue);
					video.setImageResource(R.mipmap.video_blue);
					youtube.setImageResource(R.mipmap.youtube_blue);
					casual.setImageResource(R.mipmap.casual_blue);
					latestgame.setImageResource(R.mipmap.latestgame_blue);
					call_ = true;
					message_ =true;
					kakaotalk_ = true;
					internet_=true;
					video_ = true;
					youtube_ = true;
					casual_=true;
					latestgame_=true;
					break;
				case 6:
					call.setImageResource(R.mipmap.call_blue);
					message.setImageResource(R.mipmap.message_blue);
					kakaotalk.setImageResource(R.mipmap.kakao_blue);
					internet.setImageResource(R.mipmap.internet_blue);
					video.setImageResource(R.mipmap.video_blue);
					youtube.setImageResource(R.mipmap.youtube_blue);
					casual.setImageResource(R.mipmap.casual_blue);
					latestgame.setImageResource(R.mipmap.latestgame_blue);
					bestperformance.setImageResource(R.mipmap.bestperformance_blue);

					call_ = true;
					message_ =true;
					kakaotalk_ = true;
					internet_=true;
					video_ = true;
					youtube_ = true;
					casual_=true;
					latestgame_=true;
					bestperformance_=true;
					break;
			}

		}else {
			select_performance_save = prefs.getString("SELECT_PERFORMANCE","0");
			if(prefs.getBoolean("CALL",call_) == true){
				call.setImageResource(R.mipmap.call_blue);
				call_=true;
			}else{
				Log.d("aa",(prefs.getString("CALL","0")) + "a");
			}
			if(prefs.getBoolean("MESSAGE",message_)==true) {message.setImageResource(R.mipmap.message_blue); message_=true;}
			if(prefs.getBoolean("KAKAOTALK",kakaotalk_)==true) {kakaotalk.setImageResource(R.mipmap.kakao_blue); kakaotalk_=true;}
			if(prefs.getBoolean("INTERNET",internet_)==true) {internet.setImageResource(R.mipmap.internet_blue);internet_=true;}
			if(prefs.getBoolean("YOUTUBE",youtube_)==true) {youtube.setImageResource(R.mipmap.youtube_blue);youtube_=true;}
			if(prefs.getBoolean("VIDEO",video_)==true) {video.setImageResource(R.mipmap.video_blue);video_=true;}
			if(prefs.getBoolean("CASUAL",casual_)==true) {casual.setImageResource(R.mipmap.casual_blue);casual_=true;}
			if(prefs.getBoolean("LATEST",latestgame_)==true) {latestgame.setImageResource(R.mipmap.latestgame_blue);latestgame_=true;}
			if(prefs.getBoolean("BEST",bestperformance_)==true) {bestperformance.setImageResource(R.mipmap.bestperformance_blue);bestperformance_=true;}


		}

		footer = (LinearLayout) view.findViewById(R.id.performanceseekbar);

		return view;
	}


	@Override
	public void onResume() {
		super.onResume();
		ov = new DrawingView(this.getActivity());
		ov.setZOrderOnTop(true);
		ov.getHolder().setFormat(PixelFormat.TRANSPARENT);
		footer.addView(ov);
		this.Moveseekbar();
	}


	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.next) {

			footer.removeView(ov);
			prefs = getActivity().getSharedPreferences("__setting",
					Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("SELECT_PERFORMANCE",select_performance_save + "");
			editor.putBoolean("CALL",call_);
			editor.putBoolean("MESSAGE",message_);
			editor.putBoolean("KAKAOTALK",kakaotalk_);
			editor.putBoolean("INTERNET",internet_);
			editor.putBoolean("YOUTUBE",youtube_);
			editor.putBoolean("VIDEO",video_);
			editor.putBoolean("CASUAL",casual_);
			editor.putBoolean("LATEST",latestgame_);
			editor.putBoolean("BEST",bestperformance_);


			editor.commit();


			((ChoiceActivity)getActivity()).nextFragmentReplace();
		}
		switch (v.getId()){
			case R.id.performance_call:
				if (call_ == true){
					call_ = false;
					call.setImageResource(R.mipmap.call_gray);
				}else{
					call_ = true;
					call.setImageResource(R.mipmap.call_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_message:
				if (message_ == true){
					message_ = false;
					message.setImageResource(R.mipmap.message_gray);
				}else{
					message_ = true;
					message.setImageResource(R.mipmap.message_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_kakao:
				if (kakaotalk_== true){
					kakaotalk_ = false;
					kakaotalk.setImageResource(R.mipmap.kakao_gray);
				}else{
					kakaotalk_ = true;
					kakaotalk.setImageResource(R.mipmap.kakao_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_internet:
				if (internet_== true){
					internet_ = false;
					internet.setImageResource(R.mipmap.internet_gray);
				}else{
					internet_ = true;
					internet.setImageResource(R.mipmap.internet_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_video:
				if (video_== true){
					video_ = false;
					video.setImageResource(R.mipmap.video_gray);
				}else{
					video_ = true;
					video.setImageResource(R.mipmap.video_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_youtube:
				if (youtube_== true){
					youtube_ = false;
					youtube.setImageResource(R.mipmap.youtube_gray);
				}else{
					youtube_ = true;
					youtube.setImageResource(R.mipmap.youtube_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_casual:
				if (casual_== true){
					casual_ = false;
					casual.setImageResource(R.mipmap.casual_gray);
				}else{
					casual_ = true;
					casual.setImageResource(R.mipmap.casual_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_latestgame:
				if (latestgame_== true){
					latestgame_ = false;
					latestgame.setImageResource(R.mipmap.latestgame_gray);
				}else{
					latestgame_ = true;
					latestgame.setImageResource(R.mipmap.latestgame_blue);
				}
				this.Moveseekbar();
				break;
			case R.id.performance_bestperformance:
				if (bestperformance_== true){
					bestperformance_ = false;
					bestperformance.setImageResource(R.mipmap.bestperformance_gray);
				}else{
					bestperformance_ = true;
					bestperformance.setImageResource(R.mipmap.bestperformance_blue);
				}
				this.Moveseekbar();
				break;

		}

	}

	public void Moveseekbar() {

		if(bestperformance_==true){
			myX=ncoordinate[4];
			select_performance_save = "6";
		} else if(latestgame_==true){
			myX=ncoordinate[3];
			select_performance_save ="5";
		} else if(casual_==true){
			myX=ncoordinate[2];
			select_performance_save ="4";
		}else if(video_==true || youtube_==true){
			myX=ncoordinate[1];
			select_performance_save ="3";
		}else{
			myX=ncoordinate[0];
			select_performance_save = "2";
		}
		checkdraw = true;

	}

	class DrawingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {



		private Bitmap img1; //button
		private Bitmap img2; //seekbar
		private Bitmap arrow_check;
		Canvas canvas = null;
		private float arrow_w;
		private float arrow_h;

		Paint paint = new Paint();
		private float tmp = 0;


		private SurfaceHolder holder = null;
		private boolean isRunning = false;
		private Thread thread = null;
		private int tmpi;

		public DrawingView(Context context) {
			super(context);

			holder= this.getHolder();
			holder.addCallback(this);
			img1 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_button);
			img2 = BitmapFactory.decodeResource(getResources(), R.mipmap.seekbar_1);
			arrow_check = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow_check);

			DisplayMetrics metrics = new DisplayMetrics();
			WindowManager windowManager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
			windowManager.getDefaultDisplay().getMetrics(metrics);

			img2 = Bitmap.createScaledBitmap(img2, metrics.widthPixels*711/1080 , metrics.heightPixels*21/1920 ,false);
			img1 = Bitmap.createScaledBitmap(img1, metrics.widthPixels*65/1080 , metrics.heightPixels*65/1920 ,false);
			arrow_check = Bitmap.createScaledBitmap(arrow_check, metrics.widthPixels*30/1080 , metrics.heightPixels*51/1920 ,false);

			arrow_w = arrow_check.getWidth();
			arrow_h = arrow_check.getHeight();
			seekbarw = img2.getWidth();
			seekbarh = img2.getHeight();
			buttonh = img1.getWidth();
			buttonw = img1.getHeight();

			paint.setAntiAlias(true);
			paint.setColor(Color.argb(255, 255, 255, 255));
		}


		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_MOVE:
					Log.d("aa", "m" + event.getX() + "");
					myX = event.getX();
					break;
				case MotionEvent.ACTION_UP:
					Log.d("aa", "u" + event.getX() + "");
					touch=false;
					break;
				case MotionEvent.ACTION_DOWN:
					Log.d("aa", "d" + event.getX() + "");
					myX = event.getX();
					touch=true;
					touchcheck = true;
					checkdraw = true;
					break;
			}
			return true;// super.onTouchEvent(event);
		}


		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);
			canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
			this.canvas = canvas;
			paint.setTextSize(w/40);

			canvas.drawText("매우낮음", (w-seekbarw)/2-w/17, (h - seekbarh)/48*13, paint);
			canvas.drawText("낮음", w/2-seekbarw/4-w/40, (h - seekbarh)/48*13, paint);
			canvas.drawText("보통", w/2-w/29, (h - seekbarh)/48*13, paint);
			canvas.drawText("좋음", w/2+seekbarw/4-w/31, (h - seekbarh)/48*13, paint);
			canvas.drawText("매우좋음", (w+seekbarw)/2-w/26, (h - seekbarh)/48*13, paint);
			canvas.drawBitmap(img2, (w-seekbarw)/2, (h - seekbarh)/12*4, null);


			if(touch==true) {
				if ((w - seekbarw) / 2 > myX) {
					myX = (w - seekbarw) / 2;
				}
				else if ((w + seekbarw) / 2 < myX) {
					myX = (w + seekbarw) / 2;
				}

			}
			else {
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
					//int progress =(int) tmp/10;
					//bitmap.getWidth();
					//bitmap1 = Bitmap.createScaledBitmap(bitmap, progress , progress , false);
					if (touchcheck==true) {

						getActivity().runOnUiThread(new Thread(new Runnable() {
							public void run() {
								select_performance_save = String.valueOf(tmpi + 2);
								switch (Integer.parseInt(select_performance_save)) {
									case 2:
										call.setImageResource(R.mipmap.call_blue);
										message.setImageResource(R.mipmap.message_blue);
										kakaotalk.setImageResource(R.mipmap.kakao_blue);
										internet.setImageResource(R.mipmap.internet_blue);
										video.setImageResource(R.mipmap.video_gray);
										youtube.setImageResource(R.mipmap.youtube_gray);
										casual.setImageResource(R.mipmap.casual_gray);
										latestgame.setImageResource(R.mipmap.latestgame_gray);
										bestperformance.setImageResource(R.mipmap.bestperformance_gray);
										call_ = true;
										message_ = true;
										kakaotalk_ = true;
										internet_ = true;
										video_ = false;
										youtube_ = false;
										casual_ = false;
										latestgame_ = false;
										bestperformance_ = false;
										break;
									case 3:
										call.setImageResource(R.mipmap.call_blue);
										message.setImageResource(R.mipmap.message_blue);
										kakaotalk.setImageResource(R.mipmap.kakao_blue);
										internet.setImageResource(R.mipmap.internet_blue);
										video.setImageResource(R.mipmap.video_blue);
										youtube.setImageResource(R.mipmap.youtube_blue);
										casual.setImageResource(R.mipmap.casual_gray);
										latestgame.setImageResource(R.mipmap.latestgame_gray);
										bestperformance.setImageResource(R.mipmap.bestperformance_gray);
										call_ = true;
										message_ = true;
										kakaotalk_ = true;
										internet_ = true;
										video_ = true;
										youtube_ = true;
										casual_ = false;
										latestgame_ = false;
										bestperformance_ = false;
										break;
									case 4:
										call.setImageResource(R.mipmap.call_blue);
										message.setImageResource(R.mipmap.message_blue);
										kakaotalk.setImageResource(R.mipmap.kakao_blue);
										internet.setImageResource(R.mipmap.internet_blue);
										video.setImageResource(R.mipmap.video_blue);
										youtube.setImageResource(R.mipmap.youtube_blue);
										casual.setImageResource(R.mipmap.casual_blue);
										latestgame.setImageResource(R.mipmap.latestgame_gray);
										bestperformance.setImageResource(R.mipmap.bestperformance_gray);
										call_ = true;
										message_ = true;
										kakaotalk_ = true;
										internet_ = true;
										video_ = true;
										youtube_ = true;
										casual_ = true;
										latestgame_ = false;
										bestperformance_ = false;
										break;
									case 5:
										call.setImageResource(R.mipmap.call_blue);
										message.setImageResource(R.mipmap.message_blue);
										kakaotalk.setImageResource(R.mipmap.kakao_blue);
										internet.setImageResource(R.mipmap.internet_blue);
										video.setImageResource(R.mipmap.video_blue);
										youtube.setImageResource(R.mipmap.youtube_blue);
										casual.setImageResource(R.mipmap.casual_blue);
										latestgame.setImageResource(R.mipmap.latestgame_blue);
										bestperformance.setImageResource(R.mipmap.bestperformance_gray);
										call_ = true;
										message_ = true;
										kakaotalk_ = true;
										internet_ = true;
										video_ = true;
										youtube_ = true;
										casual_ = true;
										latestgame_ = true;
										bestperformance_ = false;
										break;
									case 6:
										call.setImageResource(R.mipmap.call_blue);
										message.setImageResource(R.mipmap.message_blue);
										kakaotalk.setImageResource(R.mipmap.kakao_blue);
										internet.setImageResource(R.mipmap.internet_blue);
										video.setImageResource(R.mipmap.video_blue);
										youtube.setImageResource(R.mipmap.youtube_blue);
										casual.setImageResource(R.mipmap.casual_blue);
										latestgame.setImageResource(R.mipmap.latestgame_blue);
										bestperformance.setImageResource(R.mipmap.bestperformance_blue);

										call_ = true;
										message_ = true;
										kakaotalk_ = true;
										internet_ = true;
										video_ = true;
										youtube_ = true;
										casual_ = true;
										latestgame_ = true;
										bestperformance_ = true;
										break;
								}
								touchcheck=false;

							}

						}));

					}



				}



			}

			canvas.drawBitmap(img1, myX - buttonw / 2, (h / 12 - buttonh / 12)*4, null);

			if(first==true){
				first = false;
				checkdraw = false;
			}

			switch (Integer.parseInt(current_performance_save)){
				case 2:
					canvas.drawBitmap(arrow_check, ncoordinate[0]-arrow_w/2, (h / 12 - buttonh / 12)*7-arrow_h/2, null);
					canvas.drawText("현재 성능", ncoordinate[0]-w/19, (h / 12 - buttonh / 12)*9, paint);
					break;
				case 3:
					canvas.drawBitmap(arrow_check, ncoordinate[1]-arrow_w/2, (h / 12 - buttonh / 12)*7-arrow_h/2, null);
					break;
				case 4:
					canvas.drawBitmap(arrow_check, ncoordinate[2]-arrow_w/2, (h / 12 - buttonh / 12)*7-arrow_h/2, null);
					break;
				case 5:
					canvas.drawBitmap(arrow_check, ncoordinate[3]-arrow_w/2, (h / 12 - buttonh / 12)*7-arrow_h/2, null);
					break;
				case 6:
					canvas.drawBitmap(arrow_check, ncoordinate[4]-arrow_w/2, (h / 12 - buttonh / 12)*7-arrow_h/2, null);
					break;
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

						Log.d("cc", "onDraw");
						switch (Integer.parseInt(select_performance_save)){
							case 2:
								myX=ncoordinate[0];
								break;
							case 3:
								myX=ncoordinate[1];
								break;
							case 4:
								myX=ncoordinate[2];
								break;
							case 5:
								myX=ncoordinate[3];
								break;
							case 6:
								myX=ncoordinate[4];
								break;
						}


					}
					synchronized (holder) {
						if (canvas != null) {
							draw(canvas);
						}
						try {
							Thread.sleep(10);
						} catch (Exception e) {
						}
					}
					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}

	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		((ChoiceActivity) activity).setOnKeyBackPressedListener(this);
	}

	@Override
	public void onBack() {

		ChoiceActivity activity = (ChoiceActivity) getActivity();
		footer.removeView(ov);
		prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("SELECT_PERFORMANCE",select_performance_save + "");
		editor.putBoolean("CALL",call_);
		editor.putBoolean("MESSAGE",message_);
		editor.putBoolean("KAKAOTALK",kakaotalk_);
		editor.putBoolean("INTERNET",internet_);
		editor.putBoolean("YOUTUBE",youtube_);
		editor.putBoolean("VIDEO",video_);
		editor.putBoolean("CASUAL",casual_);
		editor.putBoolean("LATEST",latestgame_);
		editor.putBoolean("BEST",bestperformance_);
		editor.commit();
		activity.pastFragment();

		//activity.setOnKeyBackPressedListener(null);
	}


}