package com.phoncus.app.alpha.choice;



import com.phoncus.app.alpha.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class etcFragment extends Fragment implements View.OnClickListener,ChoiceActivity.onKeyBackPressedListener {

	private ImageView nextbutton = null;
	private ImageView togglebutton_rom;
	private ImageView togglebutton_finger;
	private ImageView togglebutton_pay;
	private ImageView togglebutton_knockon;
	private ImageView togglebutton_wirelesscharge;
	private ImageView togglebutton_waterresistance;
	private ImageView togglebutton_stylus;
	private ImageView togglebutton_battery;
	private ImageView togglebutton_hb;
	private TextView toggletext_rom;
	private TextView toggletext_finger;
	private TextView toggletext_pay;
	private TextView toggletext_knockon;
	private TextView toggletext_wirelesscharge;
	private TextView toggletext_waterresistance;
	private TextView toggletext_stylus;
	private TextView toggletext_battery;
	private TextView toggletext_hb;
	private boolean toggle_rom =false;
	private boolean toggle_finger = false;
	private boolean toggle_pay =false;
	private boolean toggle_knockon = false;
	private boolean toggle_wirelesscharge = false;
	private boolean toggle_waterresistance = false;
	private boolean toggle_stylus = false;
	private int toggle_battery = 0;
	private int toggle_hb = 0;



	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choice_etc2, container, false);


		nextbutton = (ImageView) view.findViewById(R.id.next);
		nextbutton.setOnClickListener(this);
		togglebutton_rom = (ImageView) view.findViewById(R.id.togglebutton_rom);
		togglebutton_finger = (ImageView) view.findViewById(R.id.togglebutton_finger);
		togglebutton_pay = (ImageView) view.findViewById(R.id.togglebutton_pay);
		togglebutton_knockon = (ImageView) view.findViewById(R.id.togglebutton_knockon);
		togglebutton_wirelesscharge = (ImageView) view.findViewById(R.id.togglebutton_wirelesscharge);
		togglebutton_waterresistance = (ImageView) view.findViewById(R.id.togglebutton_waterresistance);
		togglebutton_stylus = (ImageView) view.findViewById(R.id.togglebutton_stylus);
		togglebutton_battery = (ImageView) view.findViewById(R.id.togglebutton_battery);
		togglebutton_hb = (ImageView) view.findViewById(R.id.togglebutton_hb);
		toggletext_rom = (TextView) view.findViewById(R.id.toggletext_rom);
		toggletext_finger = (TextView) view.findViewById(R.id.toggletext_finger);
		toggletext_pay = (TextView) view.findViewById(R.id.toggletext_pay);
		toggletext_knockon = (TextView) view.findViewById(R.id.toggletext_knockon);
		toggletext_wirelesscharge = (TextView) view.findViewById(R.id.toggletext_wirelesscharge);
		toggletext_waterresistance = (TextView) view.findViewById(R.id.toggletext_waterresistance);
		toggletext_stylus = (TextView) view.findViewById(R.id.toggletext_stylus);
		toggletext_battery = (TextView) view.findViewById(R.id.toggletext_battery);
		toggletext_hb = (TextView) view.findViewById(R.id.toggletext_hb);

		togglebutton_rom.setOnClickListener(this);
		togglebutton_finger.setOnClickListener(this);
		togglebutton_pay.setOnClickListener(this);
		togglebutton_knockon.setOnClickListener(this);
		togglebutton_wirelesscharge.setOnClickListener(this);
		togglebutton_waterresistance.setOnClickListener(this);
		togglebutton_stylus.setOnClickListener(this);
		togglebutton_battery.setOnClickListener(this);
		togglebutton_hb.setOnClickListener(this);

		SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

		if(prefs.getString("SELECT_ROM","0") == "O"){
			toggle_rom = true;
			togglebutton_rom.setImageResource(R.mipmap.togglebutton1);
			toggletext_rom.setTextColor(Color.parseColor("#4374D9"));
			toggletext_rom.setText("대용량");
		}
		if(prefs.getString("SELECT_FINGERPRINT","0") == "O"){
			toggle_finger = true;
			togglebutton_finger.setImageResource(R.mipmap.togglebutton1);
			toggletext_finger.setTextColor(Color.parseColor("#4374D9"));
			toggletext_finger.setText("필요해요");
		}
		if(prefs.getString("SELECT_SAMSUNGPAY","0") == "O"){
			toggle_pay=true;
			togglebutton_pay.setImageResource(R.mipmap.togglebutton1);
			toggletext_pay.setTextColor(Color.parseColor("#4374D9"));
			toggletext_pay.setText("필요해요");
		}
		if(prefs.getString("SELECT_KNOCKCODE","0") == "O"){
			toggle_knockon=true;
			togglebutton_knockon.setImageResource(R.mipmap.togglebutton1);
			toggletext_knockon.setTextColor(Color.parseColor("#4374D9"));
			toggletext_knockon.setText("원해요");

		}
		if(prefs.getString("SELECT_WIRELESSCHARGE","0") == "O"){
			toggle_wirelesscharge=true;
			togglebutton_wirelesscharge.setImageResource(R.mipmap.togglebutton1);
			toggletext_wirelesscharge.setTextColor(Color.parseColor("#4374D9"));
			toggletext_wirelesscharge.setText("필요해요");

		}
		if(prefs.getString("SELECT_WATERPROOF","0") == "O"){
			toggle_waterresistance = true;
			togglebutton_waterresistance.setImageResource(R.mipmap.togglebutton1);
			toggletext_waterresistance.setTextColor(Color.parseColor("#4374D9"));
			toggletext_waterresistance.setText("필요해요");

		}
		if(prefs.getString("SELECT_STYLUS","0") == "O"){
			toggle_stylus = true;
			togglebutton_stylus.setImageResource(R.mipmap.togglebutton1);
			toggletext_stylus.setTextColor(Color.parseColor("#4374D9"));
			toggletext_stylus.setText("필요해요");
		}
		if(prefs.getString("SELECT_BUTTON","0") == "O"){
			toggle_hb = 1;
			togglebutton_hb.setImageResource(R.mipmap.togglebutton3);
			toggletext_hb.setTextColor(Color.parseColor("#4374D9"));
			toggletext_hb.setText("정면버튼");
		}else if(prefs.getString("SELECT_BUTTON","0") == "후면버튼"){
			toggle_hb = 2;
			togglebutton_hb.setImageResource(R.mipmap.togglebutton4);
			toggletext_hb.setTextColor(Color.parseColor("#1f8f13"));
			toggletext_hb.setText("후면버튼");
		}else{
			toggle_hb = 0;
			togglebutton_hb.setImageResource(R.mipmap.togglebutton5);
			toggletext_hb.setTextColor(Color.parseColor("#5D5D5D"));
			toggletext_hb.setText("필요없어요");
		}
		if(prefs.getString("SELECT_BATTRYSAPERATE","0") == "O"){
			toggle_battery = 1;
			togglebutton_battery.setImageResource(R.mipmap.togglebutton3);
			toggletext_battery.setTextColor(Color.parseColor("#4374D9"));
			toggletext_battery.setText("탈착형");
		}else if(prefs.getString("SELECT_BATTRYSAPERATE","0") == "X"){
			toggle_battery = 2;
			togglebutton_battery.setImageResource(R.mipmap.togglebutton4);
			toggletext_battery.setTextColor(Color.parseColor("#1f8f13"));
			toggletext_battery.setText("내장형");
		}else{
			toggle_battery = 0;
			togglebutton_battery.setImageResource(R.mipmap.togglebutton5);
			toggletext_battery.setTextColor(Color.parseColor("#5D5D5D"));
			toggletext_battery.setText("상관없어요");
		}

		return view;
	}

	@Override
	public void onClick(View v) {
		SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = prefs.edit();

		switch(v.getId()){

			case R.id.next:
				((ChoiceActivity)getActivity()).nextFragmentReplace();
				break;
			case R.id.togglebutton_rom:

				if(toggle_rom==false){
					toggle_rom = true;
					editor.putString("SELECT_ROM","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_rom.setImageResource(R.mipmap.togglebutton1);
							toggletext_rom.setTextColor(Color.parseColor("#4374D9"));
							toggletext_rom.setText("대용량");


						}

					}));
				}else{
					toggle_rom = false;
					editor.putString("SELECT_ROM","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_rom.setImageResource(R.mipmap.togglebutton2);
							toggletext_rom.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_rom.setText("상관없어요");
						}

					}));
				}

				break;
			case R.id.togglebutton_finger:
				if(toggle_finger==false){
					toggle_finger = true;
					editor.putString("SELECT_FINGERPRINT","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_finger.setImageResource(R.mipmap.togglebutton1);
							toggletext_finger.setTextColor(Color.parseColor("#4374D9"));
							toggletext_finger.setText("필요해요");
						}

					}));
				}else{
					toggle_finger = false;
					editor.putString("SELECT_FINGERPRINT","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_finger.setImageResource(R.mipmap.togglebutton2);
							toggletext_finger.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_finger.setText("아니요");
						}

					}));
				}
				break;
			case R.id.togglebutton_pay:
				if(toggle_pay==false){
					toggle_pay = true;
					editor.putString("SELECT_SAMSUNGPAY","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_pay.setImageResource(R.mipmap.togglebutton1);
							toggletext_pay.setTextColor(Color.parseColor("#4374D9"));
							toggletext_pay.setText("필요해요");
						}

					}));
				}else{
					toggle_pay = false;
					editor.putString("SELECT_SAMSUNGPAY","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_pay.setImageResource(R.mipmap.togglebutton2);
							toggletext_pay.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_pay.setText("아니요");
						}

					}));
				}
				break;
			case R.id.togglebutton_knockon:
				if(toggle_knockon==false){
					toggle_knockon = true;
					editor.putString("SELECT_KNOCKCODE","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_knockon.setImageResource(R.mipmap.togglebutton1);
							toggletext_knockon.setTextColor(Color.parseColor("#4374D9"));
							toggletext_knockon.setText("원해요");
						}

					}));
				}else{
					toggle_knockon = false;
					editor.putString("SELECT_KNOCKCODE","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_knockon.setImageResource(R.mipmap.togglebutton2);
							toggletext_knockon.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_knockon.setText("괜찮아요");
						}

					}));
				}
				break;
			case R.id.togglebutton_wirelesscharge:
				if(toggle_wirelesscharge==false){
					toggle_wirelesscharge = true;
					editor.putString("SELECT_WIRELESSCHARGE","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_wirelesscharge.setImageResource(R.mipmap.togglebutton1);
							toggletext_wirelesscharge.setTextColor(Color.parseColor("#4374D9"));
							toggletext_wirelesscharge.setText("필요해요");
						}

					}));
				}else{
					toggle_wirelesscharge = false;
					editor.putString("SELECT_WIRELESSCHARGE","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_wirelesscharge.setImageResource(R.mipmap.togglebutton2);
							toggletext_wirelesscharge.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_wirelesscharge.setText("아니요");
						}

					}));
				}
				break;
			case R.id.togglebutton_waterresistance:
				if(toggle_waterresistance==false){
					toggle_waterresistance = true;
					editor.putString("SELECT_WATERPROOF","O");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_waterresistance.setImageResource(R.mipmap.togglebutton1);
							toggletext_waterresistance.setTextColor(Color.parseColor("#4374D9"));
							toggletext_waterresistance.setText("필요해요");
						}

					}));
				}else{
					toggle_waterresistance = false;
					editor.putString("SELECT_WATERPROOF","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_waterresistance.setImageResource(R.mipmap.togglebutton2);
							toggletext_waterresistance.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_waterresistance.setText("괜찮아요");
						}

					}));
				}

				break;
			case R.id.togglebutton_stylus:
				if(toggle_stylus==false){
					editor.putString("SELECT_STYLUS","O");
					toggle_stylus = true;
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_stylus.setImageResource(R.mipmap.togglebutton1);
							toggletext_stylus.setTextColor(Color.parseColor("#4374D9"));
							toggletext_stylus.setText("필요해요");
						}

					}));
				}else{
					toggle_stylus = false;
					editor.putString("SELECT_STYLUS","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_stylus.setImageResource(R.mipmap.togglebutton2);
							toggletext_stylus.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_stylus.setText("괜찮아요");
						}

					}));
				}

				break;
			case R.id.togglebutton_hb:
				if(toggle_hb==0){
					editor.putString("SELECT_BUTTON","O");
					toggle_hb = 1;
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_hb.setImageResource(R.mipmap.togglebutton4);
							toggletext_hb.setTextColor(Color.parseColor("#4374D9"));
							toggletext_hb.setText("정면버튼");
						}

					}));
				}else if(toggle_hb==1){
					toggle_hb = 2;
					editor.putString("SELECT_BUTTON","후면버튼");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_hb.setImageResource(R.mipmap.togglebutton3);
							toggletext_hb.setTextColor(Color.parseColor("#1f8f13"));
							toggletext_hb.setText("후면버튼");
						}

					}));
				}else{
					toggle_hb = 0;
					editor.putString("SELECT_BUTTON","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_hb.setImageResource(R.mipmap.togglebutton5);
							toggletext_hb.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_hb.setText("필요없어요");
						}

					}));
				}


				break;
			case R.id.togglebutton_battery:
				if(toggle_battery==0){
					editor.putString("SELECT_BATTRYSAPERATE","O");
					toggle_battery = 1;
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_battery.setImageResource(R.mipmap.togglebutton4);
							toggletext_battery.setTextColor(Color.parseColor("#4374D9"));
							toggletext_battery.setText("탈착형");
						}

					}));
				}else if(toggle_battery==1){
					toggle_battery = 2;
					editor.putString("SELECT_BATTRYSAPERATE","X");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_battery.setImageResource(R.mipmap.togglebutton3);
							toggletext_battery.setTextColor(Color.parseColor("#1f8f13"));
							toggletext_battery.setText("내장형");
						}

					}));
				}else{
					toggle_battery = 0;
					editor.putString("SELECT_BATTRYSAPERATE","");
					getActivity().runOnUiThread (new Thread(new Runnable() {
						public void run() {
							togglebutton_battery.setImageResource(R.mipmap.togglebutton5);
							toggletext_battery.setTextColor(Color.parseColor("#5D5D5D"));
							toggletext_battery.setText("상관없어요");
						}

					}));
				}


				break;

		}
		editor.commit();
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		((ChoiceActivity) activity).setOnKeyBackPressedListener(this);
	}

	@Override
	public void onBack(){

		ChoiceActivity activity = (ChoiceActivity) getActivity();
		activity.pastFragment();
		//activity.setOnKeyBackPressedListener(null);
		//activity.onBackPressed();

	}
}