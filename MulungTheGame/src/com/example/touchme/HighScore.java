package com.example.touchme;

import org.andengine.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScore extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(org.andengine.R.layout.highscore);
		
	}
	public static void getscore(String score){
		
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
}
