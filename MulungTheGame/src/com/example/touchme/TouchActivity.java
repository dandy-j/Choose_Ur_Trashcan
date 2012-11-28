package com.example.touchme;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.CameraScene;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class TouchActivity extends Activity {
	protected CCGLSurfaceView _glSurfaceView;
	//public boolean skipSplash = prefs.getBoolean("skipSplash", false);
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    DisplayMetrics metrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metrics);
	    Display displayparm=  getWindowManager().getDefaultDisplay();
	    //TouchIt.scale( metrics.density);
	    int width= displayparm.getWidth();
	    int scalex=480/displayparm.getWidth();
	    int Height= displayparm.getHeight();
	    int scaley=800/displayparm.getHeight();
	    //getWindow().setLayout(width*scalex, Height*scaley);    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    //getWindow().setFlags(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    _glSurfaceView = new CCGLSurfaceView(this);
	    setContentView(_glSurfaceView);
	}
	
	@Override
	public void onStart()
	{
	    super.onStart();
	    
	    CCDirector.sharedDirector().attachInView(_glSurfaceView);
	 
	    CCDirector.sharedDirector().setDisplayFPS(true);
	 
	    CCDirector.sharedDirector().setAnimationInterval(1.0f / 60.0f);
	    
	    //CCScene scene = TouchIt.scene();
	    //CCDirector.sharedDirector().runWithScene(scene);
	    
	    
	}
	@Override
	public void onPause()
	{
	    super.onPause();
	 
	    CCDirector.sharedDirector().pause();
	    
	}
	 
	@Override
	public void onResume()
	{
	    super.onResume();
	 
	    CCDirector.sharedDirector().resume();
	    
	}
	 
	@Override
	public void onStop()
	{
	    super.onStop();
	 
	    CCDirector.sharedDirector().end();
	    
	}
	
}

