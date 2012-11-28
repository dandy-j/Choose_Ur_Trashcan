package com.example.touchme;


import java.util.Random;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;

public class TouchIt extends BaseGameActivity implements IOnSceneTouchListener {
	private Camera mCamera;
	private Scene mMainScene;
	private BitmapTextureAtlas mFontTexture;
	private Font mFont;
	private ChangeableText score;
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private TextureRegion garbage1,garbage2, garbage3,garbage4,garbage5,garbage6,garbage7,garbage8,garbage9;
	private Sprite[] objtrash,objcan;
	private TextureRegion trashcan1,trashcan2,trashcan3;
	private int spawnx,spawny,random=1;
	private static int quantity,level;
	private boolean[] dragging;
	private int mem=0,count=0,con=0,r=0;
	private int[] time;
	
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}
private void createSpriteSpawnTimeHandler() {
	
    TimerHandler spriteTimerHandler;
    float mEffectSpawnDelay = 1f;
    spriteTimerHandler = new TimerHandler(mEffectSpawnDelay, true,
    new ITimerCallback() {
        @Override
        public void onTimePassed(TimerHandler pTimerHandler) {
        	StringBuilder a = new StringBuilder();
        	String teks;
        	mem++;
            count++;
        	cek(count);
        	if (level>4 && level<7){
        		if(count==random/2 || count==random){
        			
        		}
        	}
        	if (level>6 && level<9){
        		if(count==random/3 || count ==2*random/3 || count==random){
        			randomcan();
        		}
        		
        	}
        	if (level==9){
        		if(count==random/4 || count ==2*random/4 || count==3*random/4 || count==random){
        			randomcan();
        		}
        	}
			time[3]=mem;
			if (time[3]>9){
				mem=0;
				time[3]=0;
				time[2]++;
			}
			if(time[2]>6){
				time[2]=0;
				time[1]++;
			}
			if(time[1]>9){
				time[1]=0;
				time[0]++;
			}
			if (time[0]>6){
				for(int i =0;i<4;i++){
					time[i]=0;
				}
			}		
			teks = a.append(time[0]).toString();
			teks = a.append(time[1]).toString();
			teks = a.append(":").toString();
			teks = a.append(time[2]).toString();
			teks = a.append(time[3]).toString();
        	
        	score.setText(teks);
        }
    });
    getEngine().registerUpdateHandler(spriteTimerHandler);
}
public static void level(int lev){
	level=lev;
	quantity = 9*level;
}
public void cek(int t){
	if(t==random){
		if(con<quantity){
		randomize();
		con=0;
		count=0;}
		else{
			score.setText("YOU WIN!");
			score.setPosition(mCamera.getCenterX(), mCamera.getCenterY());
		}
	}
	else if (t<random){
		if (con==quantity){
			score.setText("YOU WIN!");
			score.setPosition(mCamera.getCenterX(), mCamera.getCenterY());
		}
	}
	
}
public void randomcan(){
	r++;
	if(r>3){
		r=0;
	}
	Log.d("",String.valueOf(r));
	if (r==0){
		objcan[0].setPosition(0, mCamera.getHeight()-objcan[0].getHeight());
		objcan[1].setPosition(mCamera.getWidth()/2-objcan[1].getWidth()/2, mCamera.getHeight()-objcan[1].getHeight());
		objcan[2].setPosition(mCamera.getWidth()-objcan[2].getWidth(), mCamera.getHeight()-objcan[2].getHeight());
	}
	if (r==1){
		objcan[0].setPosition(mCamera.getWidth()/2-objcan[1].getWidth()/2, mCamera.getHeight()-objcan[1].getHeight());
		objcan[1].setPosition(mCamera.getWidth()-objcan[2].getWidth(), mCamera.getHeight()-objcan[2].getHeight());
		objcan[2].setPosition(0, mCamera.getHeight()-objcan[0].getHeight());
	}
	if (r==2){
		objcan[0].setPosition(mCamera.getWidth()-objcan[2].getWidth(), mCamera.getHeight()-objcan[2].getHeight());
		objcan[1].setPosition(0, mCamera.getHeight()-objcan[0].getHeight());
		objcan[2].setPosition(mCamera.getWidth()/2-objcan[1].getWidth()/2, mCamera.getHeight()-objcan[1].getHeight());
	}
}
public void randomize(){
	Random rand = new Random();
	for (int i=0;i<quantity;i++){
	spawnx=rand.nextInt((int)mCamera.getWidth()-garbage1.getWidth());
	spawny=rand.nextInt((int)mCamera.getHeight()-garbage1.getHeight()-trashcan1.getHeight());
	objtrash[i].setPosition(spawnx, spawny);
	}
	random=rand.nextInt(quantity+level/2)+(quantity+level/2);
	
	
}

public void addobj(){
	objtrash = new Sprite[quantity];
	dragging =new boolean[quantity];
	objcan = new Sprite[3];
	for (int i=0;i<3;i++){
	if (i==0){
		objcan[i]=new Sprite(0,0,trashcan1);
		mMainScene.attachChild(objcan[i]);
		objcan[i].setPosition(0, mCamera.getHeight()-objcan[i].getHeight());
	}
	if (i==1){
		objcan[i]=new Sprite(0,0,trashcan2);
		mMainScene.attachChild(objcan[i]);
		objcan[i].setPosition(mCamera.getWidth()/2-objcan[i].getWidth()/2, mCamera.getHeight()-objcan[i].getHeight());
	}
	if (i==2){
		objcan[i]=new Sprite(0,0,trashcan3);
		mMainScene.attachChild(objcan[i]);
		objcan[i].setPosition(mCamera.getWidth()-objcan[i].getWidth(), mCamera.getHeight()-objcan[i].getHeight());	
	}
	}
	for (int i=0;i<quantity;i++){
		dragging[i]=false;
		if (i<quantity/9){
			objtrash[i] = new Sprite(0,0, garbage1);	
		}
		else if (i<2*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage2);
			}
		else if (i<3*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage3);
			}
		else if (i<4*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage4);
			}
		else if (i<5*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage5);
			}
		else if (i<6*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage6);
		}
		else if (i<7*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage7);
		}
		else if (i<8*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage8);
		}
		else if (i<9*quantity/9){
			objtrash[i] = new Sprite(0,0, garbage9);
		}
	mMainScene.attachChild(objtrash[i]);
	}
}
	@Override
	public Engine onLoadEngine() {
		final Display display = getWindowManager().getDefaultDisplay();
		    int cameraWidth = display.getWidth();
		    int cameraHeight = display.getHeight();
		    mCamera = new Camera(0, 0, cameraWidth, cameraHeight);
		    return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT,new RatioResolutionPolicy(cameraWidth, cameraHeight), mCamera));
	}
	@Override
	public void onLoadResources() {
		
		mBitmapTextureAtlas = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		garbage1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "1.png",0, 0);
		garbage2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "2.png",100, 0);
		garbage3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "3.png",200, 0);
		garbage4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "4.png",300, 0);
		garbage5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "5.png",400, 0);
		garbage6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "6.png",500, 0);
		garbage7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "7.png",0, 100);
		garbage8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "8.png",0, 200);
		garbage9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "9.png",0, 300);
		trashcan1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "trash1.png",100, 100);
		trashcan2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "trash2.png",200, 100);
		trashcan3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "trash3.png",300, 100);
		
		
		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
		mFontTexture = new BitmapTextureAtlas(256, 256,
				    TextureOptions.BILINEAR_PREMULTIPLYALPHA);
				mFont = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT,
				    Typeface.BOLD), 40, true, Color.BLACK);
				mEngine.getTextureManager().loadTexture(mFontTexture);
				mEngine.getFontManager().loadFont(mFont);
	}
	@Override
	public Scene onLoadScene() {
		time = new int[4];
		mEngine.registerUpdateHandler(new FPSLogger());
		    mMainScene = new Scene();
		    mMainScene.setBackground(new ColorBackground(245/255f, 196/255f, 145/255f));
		    addobj();
		    randomize();
		    String teks = "START!      ";
		    score = new ChangeableText(0, 100, mFont, teks);
		    score.setPosition(0,0);

		    mMainScene.attachChild(score);
		    mMainScene.setOnSceneTouchListener(this);	
		    createSpriteSpawnTimeHandler();
		    return mMainScene;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		float touchX = pSceneTouchEvent.getX();
		float touchY = pSceneTouchEvent.getY();

		switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
        	for (int i=quantity-1;i>=0;i--){
        		if (objtrash[i].contains(touchX, touchY)){
        		dragging[i]=true;
        		break;
        		}
        	}
                break;
        case TouchEvent.ACTION_MOVE:
        	for (int i=0;i<quantity;i++){
        		if (dragging[i]==true){
        			objtrash[i].setPosition(pSceneTouchEvent.getX() - objtrash[i].getWidth() / 2, pSceneTouchEvent.getY() - objtrash[i].getHeight() / 2);
        		}
        	}
                break;
        case TouchEvent.ACTION_UP:
        	for (int i=quantity-1;i>=0;i--){
        		if (dragging[i]==true){
        			if(i<quantity/3){
        			if (objtrash[i].collidesWith(objcan[0])){
        			objtrash[i].setPosition(-1000, 0);
        			con++;
        			}
        			else{
        				randomize();
        				con=0;
        				count=0;
        			}
        			}
        			else if(i<2*quantity/3){
            			if (objtrash[i].collidesWith(objcan[1])){
            			objtrash[i].setPosition(-1000, 0);
            			con++;
            			}
            			else{
            			randomize();
            			con=0;
            			count=0;}
            			}
        			else if(i<3*quantity/3){
            			if (objtrash[i].collidesWith(objcan[2])){
            			objtrash[i].setPosition(-1000, 0);
            			con++;
            			}else{
            			randomize();
            			con=0;
            			count=0;}
            			}
        		}
        	}
        	for (int i=quantity-1;i>=0;i--){
        		dragging[i]=false;
        	}
                break;
}
return true;
	}

}
	