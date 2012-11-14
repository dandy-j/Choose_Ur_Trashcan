package com.example.touchme;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class Splash extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		View button = (View)findViewById(R.id.closeSplash);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.closeSplash) {
			if(((CheckBox)findViewById(R.id.chkSplash)).isChecked()) {
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("skipSplash", true);
				editor.commit();
			}
			finish();
		}
	}

}
