package com.example.touchme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class Splash extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread logotimer = new Thread(){
			public void run(){
			try{
				int logotimer = 0;
				while(logotimer<3000){
					sleep(100);
					logotimer=logotimer+100;
				}
				startActivity(new Intent(Splash.this,Menu.class));
			}
			catch(Exception e){
				
			}
				finally{
					
				}
			}
		};
		logotimer.start();
	}

	

}
