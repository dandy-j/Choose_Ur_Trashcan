package com.example.touchme;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

public class Game extends BaseGameActivity implements IOnSceneTouchListener{
	static final int CAMERA_WIDTH = 480;
	static final int CAMERA_HEIGHT = 800;
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private TextureRegion mPlayerTextureRegion;
	private ZoomCamera mCamera;
	private TextureOptions mTexture;
	private TextureRegion mFaceTextureRegion;
	private SurfaceScrollDetector mScrollDetector;
	private Scene mMainScene;
	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
		new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
		this.mCamera);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 32, 32);
		mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "HVGA/1.png", 0, 0);
		mBitmapTextureAtlas.load();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
		 
	    // Create Scene and set background colour to (1, 1, 1) = white
	    this.mMainScene = new Scene();
	    this.mMainScene.setBackground(new Background(1, 1, 1));
	 
	    // Centre the player on the camera.
	    final int iStartX = (CAMERA_WIDTH - mBitmapTextureAtlas.getWidth()) / 2;
	    final int iStartY = (CAMERA_HEIGHT - mBitmapTextureAtlas.getHeight()) / 2;
	 
	    /* Create the sprite and add it to the scene. */
	    final Sprite oPlayer = new Sprite(iStartX, iStartY, mPlayerTextureRegion, getVertexBufferObjectManager());
	    this.mMainScene.attachChild(oPlayer);
	 
	    //return this.mMainScene;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}
