package com.example.touchme;


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
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.andengine.R.layout.main);
        Button bStart = (Button)findViewById(org.andengine.R.id.bStart);
        Button bAbout = (Button)findViewById(org.andengine.R.id.bHelp);
        Button bExit = (Button)findViewById(org.andengine.R.id.bScore);
        bStart.setOnClickListener(this);
        bAbout.setOnClickListener(this);
        bExit.setOnClickListener(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean skipSplash = prefs.getBoolean("skipSplash", false);
        if(!skipSplash) {
	        Intent splash = new Intent(Menu.this,Splash.class);
	        startActivity(splash);
        }
    }

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case org.andengine.R.id.bScore:
				Intent score = new Intent(Menu.this,HighScore.class);
				startActivity(score);
				break;
			case org.andengine.R.id.bStart:
				String[] levels = {"1","2","3","4","5","6","7","8","9"};
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.levelSelect));
				builder.setItems(levels, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				    	TouchIt t = new TouchIt();
				    	t.level(item+1);
				    	Intent game = new Intent(Menu.this,TouchIt.class);
						startActivity(game);
				    }
				});
				AlertDialog alert = builder.create();
				alert.show();
				break;
			case org.andengine.R.id.bHelp:
				Intent help = new Intent(Menu.this,Help.class);
				startActivity(help);
				break;
		}
	}
}