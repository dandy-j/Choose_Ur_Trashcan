package com.example.touchme;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener {
	public static boolean exit=false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.andengine.R.layout.main);
        Button bStart = (Button)findViewById(org.andengine.R.id.bStart);
        Button bAbout = (Button)findViewById(org.andengine.R.id.bAbout);
        //Button bPrefs = (Button)findViewById(R.id.bPrefs);
        Button bExit = (Button)findViewById(org.andengine.R.id.bExit);
        bStart.setOnClickListener(this);
        bAbout.setOnClickListener(this);
        //bPrefs.setOnClickListener(this);
        bExit.setOnClickListener(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean skipSplash = prefs.getBoolean("skipSplash", false);
        if(!skipSplash) {
	        Intent splash = new Intent(Menu.this,Splash.class);
	        startActivity(splash);
        }
        if(exit==true){
        	finish();
        }
    }

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case org.andengine.R.id.bExit:
				finish();
				break;
			case org.andengine.R.id.bStart:
				    	Intent game = new Intent(Menu.this,TouchActivity.class);
						//game.putExtra("maze", maze);
						startActivity(game);
				break;
			case org.andengine.R.id.bAbout:
				Intent about = new Intent(Menu.this,About.class);
				startActivity(about);
				break;
		}
	}
}