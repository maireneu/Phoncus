package com.phoncus.app.alpha.choice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phoncus.app.alpha.R;
import com.phoncus.app.alpha.result.LoadingActivity;
import com.ssomai.android.scalablelayout.ScalableLayout;


public class SurveyFragment extends Fragment implements View.OnClickListener,ChoiceActivity.onKeyBackPressedListener {

	private ImageView man_icon = null;
	private ImageView man_checkbox = null;
	private TextView man_text = null;
	private ImageView woman_icon = null;
	private ImageView woman_checkbox = null;
	private TextView woman_text = null;
	private boolean genderenable = false;
	private boolean birthdayenable = false;
	private ImageView textbox = null;
	DrawingView ov=null;
	boolean dialogenable = false;
	private LinearLayout surveybackground =null;
	private ScalableLayout dialoginnercanvas = null;

	private TextView selectyearandmonth = null;
	private TextView dialogtitletext = null;


	private TextView dialogyeartext = null;
	private TextView dialogmonthtext = null;
	private View dialogline = null;
	private LinearLayout dialogcanvasyear=null;
	int myear = 2016;
	int mmonth = 9;
	String mgender = null;

	private TextView year_text=null;
	private TextView month_text=null;
	private ImageView resultbutton=null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_choice_survey2, container, false);

		dialogenable = false;
		genderenable = false;
		birthdayenable =false;

		man_icon = (ImageView) view.findViewById(R.id.man_icon);
		man_checkbox = (ImageView) view.findViewById(R.id.man_checkbox);
		man_text = (TextView) view.findViewById(R.id.man_text);
		woman_icon = (ImageView) view.findViewById(R.id.woman_icon);
		woman_checkbox = (ImageView) view.findViewById(R.id.woman_checkbox);
		woman_text = (TextView) view.findViewById(R.id.woman_text);
		textbox = (ImageView) view.findViewById(R.id.textbox);
		man_icon.setOnClickListener(this);
		man_checkbox.setOnClickListener(this);
		man_text.setOnClickListener(this);
		woman_icon.setOnClickListener(this);
		woman_text.setOnClickListener(this);
		woman_checkbox.setOnClickListener(this);
		textbox.setOnClickListener(this);

		selectyearandmonth = (TextView) view.findViewById(R.id.selectyearandmonth);
		surveybackground = (LinearLayout) view.findViewById(R.id.surveybackground);
		dialoginnercanvas = (ScalableLayout) view.findViewById(R.id.dialoginnercanvas);
		dialogtitletext = (TextView) view.findViewById(R.id.dialogtitletext);

		dialogyeartext = (TextView) view.findViewById(R.id.dialogyeartext);
		dialogmonthtext = (TextView) view.findViewById(R.id.dialogmonthtext);

		dialogline = (View) view.findViewById(R.id.dialogline);

		selectyearandmonth.setOnClickListener(this);
		dialogcanvasyear = (LinearLayout) view.findViewById(R.id.dialogcanvasyear);

		year_text = (TextView) view.findViewById(R.id.year_text);
		month_text = (TextView) view.findViewById(R.id.month_text);
		year_text.setText("");
		month_text.setText("");

		resultbutton = (ImageView) view.findViewById(R.id.resultbutton);
		resultbutton.setImageResource(R.mipmap.result_button2);

		return view;
	}


	@Override
	public void onClick(View v) {

		if(dialogenable==false) {
			if (v.getId() == R.id.man_icon || v.getId() == R.id.man_checkbox || v.getId() == R.id.man_text) {
					getActivity().runOnUiThread(new Thread(new Runnable() {
						public void run() {
							man_checkbox.setImageResource(R.mipmap.blue_check);
							woman_checkbox.setImageResource(R.mipmap.gray_check);
							man_text.setTextColor(Color.parseColor("#0100FF"));
							woman_text.setTextColor(Color.parseColor("#5D5D5D"));
						}


					}));
					mgender = "male";
					genderenable = true;
				if(birthdayenable==true){
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							resultbutton.setImageResource(R.mipmap.result_button);
						}

					}));
					resultbutton.setOnClickListener(this);
				}

			}
			if (v.getId() == R.id.woman_icon || v.getId() == R.id.woman_checkbox || v.getId() == R.id.woman_text) {
					getActivity().runOnUiThread(new Thread(new Runnable() {
						public void run() {
							man_checkbox.setImageResource(R.mipmap.gray_check);
							woman_checkbox.setImageResource(R.mipmap.blue_check);
							man_text.setTextColor(Color.parseColor("#5D5D5D"));
							woman_text.setTextColor(Color.parseColor("#0100FF"));
						}

					}));
					mgender = "female";
					genderenable = true;
				if(birthdayenable==true){
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							resultbutton.setImageResource(R.mipmap.result_button);
						}

					}));
					resultbutton.setOnClickListener(this);
				}
			}

			if (v.getId() == R.id.textbox) {

				surveybackground.setBackgroundColor(Color.parseColor("#CC000000"));
				dialoginnercanvas.setBackgroundColor(Color.parseColor("#4374D9"));
				dialogtitletext.setTextColor(Color.parseColor("#9DCEFF"));
				selectyearandmonth.setTextColor(Color.parseColor("#9DCEFF"));
				dialogyeartext.setTextColor(Color.parseColor("#8BBCFF"));
				dialogmonthtext.setTextColor(Color.parseColor("#8BBCFF"));
				dialogline.setBackgroundColor(Color.parseColor("#5586EB"));

				dialogenable = true;
				getActivity().runOnUiThread (new Thread(new Runnable() {
					public void run() {
						ov = new DrawingView(getActivity());
						ov.setZOrderOnTop(true);

						ov.getHolder().setFormat(PixelFormat.TRANSPARENT);
						dialogcanvasyear.addView(ov);
					}

				}));


			}

			if(v.getId() == R.id.resultbutton && birthdayenable == true && genderenable == true){
				SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
						Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("YEAR",myear+"");
				editor.putString("MONTH",mmonth+"");
				editor.putString("GENDER",mgender+"");
				editor.commit();

				Intent intent = new Intent(getActivity(), LoadingActivity.class);
				startActivity(intent);
				//getActivity().finish();
			}

		}else {
			if (v.getId() == R.id.selectyearandmonth) {
				surveybackground.setBackgroundColor(Color.parseColor("#00000000"));
				dialoginnercanvas.setBackgroundColor(Color.parseColor("#004374D9"));
				dialogtitletext.setTextColor(Color.parseColor("#009DCEFF"));
				selectyearandmonth.setTextColor(Color.parseColor("#009DCEFF"));
				dialogyeartext.setTextColor(Color.parseColor("#008BBCFF"));
				dialogmonthtext.setTextColor(Color.parseColor("#008BBCFF"));
				dialogline.setBackgroundColor(Color.parseColor("#005586EB"));
				dialogcanvasyear.removeView(ov);

				getActivity().runOnUiThread (new Thread(new Runnable() {
					public void run() {
						year_text.setText(String.valueOf(myear));
						month_text.setText(String.valueOf(String.format("%02d",mmonth)));

					}

				}));

				birthdayenable = true;
				if(genderenable==true){
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							resultbutton.setImageResource(R.mipmap.result_button);

						}

					}));
					resultbutton.setOnClickListener(this);
				}

				dialogenable = false;
			}
		}




	}




	class DrawingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

		private float w;
		private float yearw;
		private float monthw;
		private float h;
		private boolean yeartouch=false; //touch 인식
		private boolean monthtouch=false;
		Canvas canvas = null;
		float inY = 0;
		float finY = 0;
		float mvY = 0;
		float tmp = 0;
		float myX = 0;
		boolean first = true;
		boolean yearcheckdraw = true;
		boolean monthcheckdraw = true;
		boolean checkdraw = true;

		Paint paint = new Paint();
		float acc = 0;


		float[] yc_box = new float[7];
		int[] yearbox = new int[7];

		float[] mc_box = new float[12];
		int[] monthbox = new int[12];

		private SurfaceHolder holder = null;
		private boolean isRunning = false;
		private Thread thread = null;

		public DrawingView(Context context) {
			super(context);

			holder= this.getHolder();
			holder.addCallback(this);



			paint.setAntiAlias(true);

		}


		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_MOVE:
					//Log.d("aa", "m" + event.getX() + "");
					mvY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					//Log.d("aa", "u" + event.getX() + "");
					finY = event.getY();
					yeartouch=false;
					monthtouch=false;

					//Log.d("bb", finY - tmp + "");
					//Log.d("h", h + "");
					acc = finY - tmp;

					break;
				case MotionEvent.ACTION_DOWN:
					Log.d("aa", "d" + event.getX() + "");
					inY = event.getY();
					mvY = event.getY();
					tmp = event.getY();
					myX = event.getX();
					if(myX < yearw){
						yeartouch=true;
						yearcheckdraw = true;
					}
					if(myX < w && myX > monthw){
						monthtouch=true;
						monthcheckdraw = true;
					}
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




			if(first==true) {
				//첫터치때 그리기
				paint.setColor(Color.argb(255, 255, 255, 255));
				paint.setTextSize(Math.round(yearw/340*140));


				first = false;
				yearcheckdraw = false;

			}
			else if(yearcheckdraw==true){
				//아닐때 년 그릴것
					if(yeartouch==true) {

						for(int i=0; i<7; i++) {
							if( yc_box[i] < (h/5*0 - h/45*2 - h/10 )) {
								yc_box[i] = yc_box[i] - tmp + mvY + h / 5 * 7;
								yearbox[i] = yearbox[i] + 7;
							}else if (yc_box[i] > (h/5*6 - h/45*2 + h/10)){
								yc_box[i] = yc_box[i] - tmp + mvY - h / 5 * 7;
								yearbox[i] = yearbox[i] - 7;
							}else{
								yc_box[i] = yc_box[i] - tmp + mvY;

							}
						}

					}else if(Math.abs(acc) > h/100){

						if(acc>h/10){
							acc=h/10;
						}else if(acc<-h/10){
							acc=-h/10;
						}

						if(acc>0) {
							for (int i = 0; i < 7; i++) {
								if (yc_box[i] < (h / 5 * 0 - h / 45 * 2 - h / 10)) {
									yc_box[i] = yc_box[i] + acc/2 + h / 5 * 7;
									yearbox[i] = yearbox[i] + 7;
								} else if (yc_box[i] > (h / 5 * 6 - h / 45 * 2 + h / 10)) {
									yc_box[i] = yc_box[i] + acc/2 - h / 5 * 7;
									yearbox[i] = yearbox[i] - 7;
								} else {
									yc_box[i] = yc_box[i] + acc/2;
								}
							}
							acc = acc-h/1000;

						}

						if(acc<0) {
							for (int i = 0; i < 7; i++) {
								if (yc_box[i] < (h / 5 * 0 - h / 45 * 2 - h / 10)) {
									yc_box[i] = yc_box[i] + acc/2 + h / 5 * 7;
									yearbox[i] = yearbox[i] + 7;
								} else if (yc_box[i] > (h / 5 * 6 - h / 45 * 2 + h / 10)) {
									yc_box[i] = yc_box[i] + acc/2 - h / 5 * 7;
									yearbox[i] = yearbox[i] - 7;
								} else {
									yc_box[i] = yc_box[i] + acc/2;
								}
							}
							acc = acc+h/1000;
						}

					}else{

						float tmp2 = 999999;
						for(int i=0;i<7;i++){
							if(Math.abs(yc_box[i]-(h/5*3 - h/45*2))<Math.abs(tmp2-(h/5*3 - h/45*2))){
								tmp2= yc_box[i];
								myear = yearbox[i];
							}
						}

						for (int i = 0; i < 7; i++) {
							yc_box[i] = yc_box[i] - (tmp2 - (h/5*3 - h/45*2));
						}
						yearcheckdraw = false;

					}

			}else if (monthcheckdraw==true){

				if(monthtouch==true) {
					for(int i=0; i<12; i++) {
						if( mc_box[i] < (h/5*(-5) - h/45*2 - h/10 )) {
							mc_box[i] = mc_box[i] - tmp + mvY + h / 5 * 12;

						}else if (mc_box[i] > (h/5*6 - h/45*2 + h/10)){
							mc_box[i] = mc_box[i] - tmp + mvY - h / 5 * 12;
						}else{
							mc_box[i] = mc_box[i] - tmp + mvY;
						}
					}
				}else if(Math.abs(acc) > h/100){

					if(acc>h/10){
						acc=h/10;
					}else if(acc<-h/10){
						acc=-h/10;
					}

					if(acc>0) {

						for(int i=0; i<12; i++) {
							if( mc_box[i] < (h/5*(-5) - h/45*2 - h/10 )) {
								mc_box[i] = mc_box[i] + acc/2  + h / 5 * 12;

							}else if (mc_box[i] > (h/5*6 - h/45*2 + h/10)){
								mc_box[i] = mc_box[i] + acc/2  - h / 5 * 12;
							}else{
								mc_box[i] = mc_box[i] + acc/2 ;
							}
						}

						acc = acc-h/1000;

					}

					if(acc<0) {
						for(int i=0; i<12; i++) {
							if( mc_box[i] < (h/5*(-5) - h/45*2 - h/10 )) {
								mc_box[i] = mc_box[i] + acc/2  + h / 5 * 12;

							}else if (mc_box[i] > (h/5*6 - h/45*2 + h/10)){
								mc_box[i] = mc_box[i] + acc/2  - h / 5 * 12;
							}else{
								mc_box[i] = mc_box[i] + acc/2 ;
							}
						}
						acc = acc+h/1000;
					}

				}else{

					float tmp2 = 999999;
					for(int i=0;i<12;i++){
						if(Math.abs(mc_box[i]-(h/5*3 - h/45*2))<Math.abs(tmp2-(h/5*3 - h/45*2))){
							tmp2= mc_box[i];
							mmonth = monthbox[i];
						}
					}

					for (int i = 0; i < 12; i++) {
						mc_box[i] = mc_box[i] - (tmp2 - (h/5*3 - h/45*2));
					}
					monthcheckdraw = false;

				}

			}else{
				checkdraw=false;
			}

			paint.setColor(Color.argb(255, 255, 255, 255));
			canvas.drawText(String.valueOf(yearbox[0]), 0, yc_box[0] ,paint);
			canvas.drawText(String.valueOf(yearbox[1]), 0, yc_box[1] ,paint);
			canvas.drawText(String.valueOf(yearbox[2]), 0, yc_box[2] ,paint);
			canvas.drawText(String.valueOf(yearbox[3]), 0, yc_box[3] ,paint);
			canvas.drawText(String.valueOf(yearbox[4]), 0, yc_box[4] ,paint);
			canvas.drawText(String.valueOf(yearbox[5]), 0, yc_box[5] ,paint);
			canvas.drawText(String.valueOf(yearbox[6]), 0, yc_box[6] ,paint);
			for(int i=0;i<12;i++){
				canvas.drawText(String.format("%02d",monthbox[i]), monthw, mc_box[i] ,paint);
			}
			paint.setColor(Color.parseColor("#CC4374D9"));
			canvas.drawRect(0, 0, w, h / 5, paint);
			paint.setColor(Color.parseColor("#994374D9"));
			canvas.drawRect(0, h / 5, w, h / 5 * 2, paint);
			paint.setColor(Color.parseColor("#994374D9"));
			canvas.drawRect(0, h / 5 * 3, w, h / 5 * 4, paint);
			paint.setColor(Color.parseColor("#CC4374D9"));
			canvas.drawRect(0, h / 5 * 4, w, h, paint);


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
						//첫터치일때 설정
						w = canvas.getWidth();
						yearw = w * 272 / 560;
						monthw = w * 416/560;
						h = canvas.getHeight();

						for(int i=0;i<7;i++) {
							yc_box[i] = h / 5 * (6-i) - h / 45 * 2;
						}

						yearbox[0] = myear+3;
						yearbox[1] = myear+2;
						yearbox[2] = myear+1;
						yearbox[3] = myear;
						yearbox[4] = myear-1;
						yearbox[5] = myear-2;
						yearbox[6] = myear-3;

						for(int i=0;i<12;i++){
							mc_box[i] = h / 5 * (6-i) - h / 45 * 2;
							monthbox[i] = 12-i;
						}



					}
					synchronized (holder) {
						if (canvas != null) {

							draw(canvas);
							tmp = mvY;
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
	public void onBack(){
		if(dialogenable==false) {
			ChoiceActivity activity = (ChoiceActivity) getActivity();
			dialogcanvasyear.removeView(ov);
			activity.pastFragment();
		}else {

				surveybackground.setBackgroundColor(Color.parseColor("#00000000"));
				dialoginnercanvas.setBackgroundColor(Color.parseColor("#004374D9"));
				dialogtitletext.setTextColor(Color.parseColor("#009DCEFF"));
				selectyearandmonth.setTextColor(Color.parseColor("#009DCEFF"));
				dialogyeartext.setTextColor(Color.parseColor("#008BBCFF"));
				dialogmonthtext.setTextColor(Color.parseColor("#008BBCFF"));
				dialogline.setBackgroundColor(Color.parseColor("#005586EB"));
				dialogcanvasyear.removeView(ov);

				dialogenable = false;

		}
	}



}