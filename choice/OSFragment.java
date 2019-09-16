package com.phoncus.app.alpha.choice;



import com.phoncus.app.alpha.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OSFragment extends Fragment implements View.OnClickListener, ChoiceActivity.onKeyBackPressedListener {

	private ImageView nextbutton = null;
	private ImageView android_icon = null;
	private ImageView android_checkbox = null;
	private TextView android_text = null;
	private ImageView apple_icon = null;
	private ImageView apple_checkbox = null;
	private TextView apple_text = null;
	private TextView os_text = null;

	private String selectos = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choice_os2, container, false);
		nextbutton = (ImageView) view.findViewById(R.id.next);
		android_icon = (ImageView) view.findViewById(R.id.android_icon);
		android_checkbox = (ImageView) view.findViewById(R.id.android_checkbox);
		android_text = (TextView) view.findViewById(R.id.android_text);
		apple_icon = (ImageView) view.findViewById(R.id.apple_icon);
		apple_checkbox = (ImageView) view.findViewById(R.id.apple_checkbox);
		apple_text = (TextView) view.findViewById(R.id.apple_text);
		os_text = (TextView) view.findViewById(R.id.os_text);

		nextbutton.setOnClickListener(this);
		android_icon.setOnClickListener(this);
		android_checkbox.setOnClickListener(this);
		android_text.setOnClickListener(this);
		apple_icon.setOnClickListener(this);
		apple_text.setOnClickListener(this);
		apple_checkbox.setOnClickListener(this);

		SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
				Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		if(prefs.getString("SELECT_OS","0") == "apple"){
			android_checkbox.setImageResource(R.mipmap.gray_check);
			apple_checkbox.setImageResource(R.mipmap.blue_check);
			android_text.setTextColor(Color.parseColor("#5D5D5D"));
			apple_text.setTextColor(Color.parseColor("#0100FF"));
			os_text.setText("iOS는 Apple의 기기 아이폰, 아이패드에서 사용하는 OS이에요.\n직관성과 통일성이 장점으로 설명서 없이 어플을 사용할 수 있어요.\n 다만 아이폰의 경우 뒤로가기 버튼이 없고 기본어플들의 다양성은\n 안드로이드 보다 다소 부족할 수 있어요.");


		}else if (prefs.getString("SELECT_OS","0") == "android"){
			android_checkbox.setImageResource(R.mipmap.blue_check);
			apple_checkbox.setImageResource(R.mipmap.gray_check);

			android_text.setTextColor(Color.parseColor("#0100FF"));
			apple_text.setTextColor(Color.parseColor("#5D5D5D"));

		}


		return view;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.android_icon || v.getId() == R.id.android_checkbox || v.getId() == R.id.android_text){
			getActivity().runOnUiThread (new Thread(new Runnable() {
				public void run() {
					android_checkbox.setImageResource(R.mipmap.blue_check);
					apple_checkbox.setImageResource(R.mipmap.gray_check);
					android_text.setTextColor(Color.parseColor("#0100FF"));
					apple_text.setTextColor(Color.parseColor("#5D5D5D"));
					os_text.setText("안드로이드 OS는 현재 사용하고 있는 운영체제 이에요.\n애플 아이폰을 제외한 대부분의 스마트폰은 안드로이드 \nOS를 사용해요. 국내에서 점유율이 높아서 다양한\n게임 및 어플들을 사용할 수 있어요. ");

					SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
							Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("SELECT_OS","android");
					editor.commit();

				}

			}));
		}
		if(v.getId() == R.id.apple_icon || v.getId() == R.id.apple_checkbox || v.getId() == R.id.apple_text){
			getActivity().runOnUiThread (new Thread(new Runnable() {
				public void run() {
					android_checkbox.setImageResource(R.mipmap.gray_check);
					apple_checkbox.setImageResource(R.mipmap.blue_check);
					android_text.setTextColor(Color.parseColor("#5D5D5D"));
					apple_text.setTextColor(Color.parseColor("#0100FF"));
					os_text.setText("iOS는 Apple의 기기 아이폰, 아이패드에서 사용하는 OS이에요.\n직관성과 통일성이 장점으로 설명서 없이 어플을 사용할 수 있어요.\n 다만 아이폰의 경우 뒤로가기 버튼이 없고 기본어플들의 다양성은\n 안드로이드 보다 다소 부족할 수 있어요.");
					SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
							Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("SELECT_OS","apple");
					editor.commit();

				}

			}));
		}


		if (v.getId() == R.id.next) {
			((ChoiceActivity)getActivity()).nextFragmentReplace();
		}
		/*if (v.getId() == R.id.prev) {
			((ChoiceActivity)getActivity()).pastFragment();
		}*/
	}

	public final class AndroidBridge {

		@JavascriptInterface
		public void test1() {

			SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
					Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
			// 캐쉬 저장
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("OS1", "android");
			editor.commit();

			Toast.makeText(getActivity(), "android", Toast.LENGTH_LONG).show();

			((ChoiceActivity)getActivity()).nextFragmentReplace();
			
		}

		@JavascriptInterface
		public void test2() {

			SharedPreferences prefs = getActivity().getSharedPreferences("__setting",
					Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
			// 캐쉬 저장
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("OS1", "apple");
			editor.commit();

			Toast.makeText(getActivity(), "apple", Toast.LENGTH_LONG).show();

			((ChoiceActivity)getActivity()).pastFragment();
		}
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
	} // in SearchFragment

}