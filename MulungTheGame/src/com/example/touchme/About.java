package com.example.touchme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class About extends Activity implements OnClickListener {
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);
		//View close = findViewById(R.id.closeAbout);
		//ImageView image = (ImageView)findViewById(R.id.aboutImg);
		//Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.zoom);
		//image.startAnimation(hyperspaceJump);
		//close.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		this.finish();
	}

}
