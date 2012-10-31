package com.example.touchme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.andengine.input.touch.TouchEvent;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;
import android.util.Log;
import android.hardware.Camera.Area;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchIt extends CCColorLayer {
	public boolean[] dragging;
	public CCSprite  recycled = CCSprite.sprite("trash1.png");
	public CCSprite  unrecycled = CCSprite.sprite("trash3.png");
	public CCSprite  iron = CCSprite.sprite("trash2.png");
	public CCSprite[]  trash;
	public int flag0=0,flag1=0,flag2=0;
	int offX; 
    int offY; 
    public static int scalex=0;
    public static int scaley=0;
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new TouchIt(ccColor4B.ccc4(253,209,161,255));
	    scene.addChild(layer);
	    return scene;
	}
	public static void scale(int x,int y){
		scalex=x;
		scaley=y;
		StringBuilder a = new StringBuilder();
		String b;
		a.append(scalex);
		b = a.toString();
		Log.d("scale", b);
	}
	
	public void addsampah(){
		
		trash = new CCSprite[15];
		Random rand = new Random();
		int x,y;
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		
		 for (int i=0;i<15;i++){
			 dragging = new boolean[15];
			 dragging[i]=false;
			 
			 if (i<5){
				 y=rand.nextInt((int)winSize.height-180);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kulit apel.png");
					 trash[i].setScale(scalex);
					 
					 trash[i].setPosition(CGPoint.ccp(x+50,y+100));
					 addChild(trash[i],0,i);
					 flag0=1;
				 }
			 if (i<10 && i>4){
				 
					 y=rand.nextInt((int)winSize.height-180);
					 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("botol plastik.png");
					 trash[i].setScale(scalex);
					 
					 trash[i].setPosition(CGPoint.ccp(x+50,y+100));
					 addChild(trash[i],0,i);
					 flag1=1;
				 }
			 if (i<15 && i>9){
				 
				 y=rand.nextInt((int)winSize.height-180);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("baut.png");
					 trash[i].setScale(scalex);
					 
					 trash[i].setPosition(CGPoint.ccp(x+50,y+100));
					 addChild(trash[i],0,i);
					 flag2=1;
				 }
		 }
			 }
		 
	
	public void tempatsampah(){
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		    
		    recycled.setPosition(CGPoint.ccp(winSize.width-(recycled.getContentSize().width/2.0f),recycled.getContentSize().height/2.0f));
		    recycled.setScale(scalex);
		   
		    addChild(recycled);
		    unrecycled.setPosition(CGPoint.ccp(unrecycled.getContentSize().width/2.0f,unrecycled.getContentSize().height/2.0f));
		    unrecycled.setScale(scalex);
		    
		    addChild(unrecycled);
		    iron.setPosition(CGPoint.ccp(winSize.width/2.0f,iron.getContentSize().height/2.0f));
		    iron.setScale(scalex);
		   
		    addChild(iron);
		
	}
	public void gamelogic(float t){
		
		if (flag0==0 && flag1==0 && flag2==0){
		addsampah();
		
		}
		
	}
	
	public TouchIt(ccColor4B color)
	{
		
	    super(color);
	    this.setIsTouchEnabled(true);
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    //winSize.set(480, 800);
	    tempatsampah();
	    addsampah();
	    //this.schedule("gamelogic", 1.0f);
	    
	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event){
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    //CGRect pointt = CGRect.make(240,240,100,100);
	    for (int i=0;i<15;i++){
	    	if (i<5){
	    		if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("nha","bok");
			dragging[i]=true;
			return true;
		}}
	    if (i<10 && i>4){
	    	if (trash[i].getBoundingBox().contains(offX, offY)){
			Log.d("nho","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<15 && i>9)
	    	if (trash[i].getBoundingBox().contains(offX, offY)){
			Log.d("nhe","bok");
			dragging[i]=true;
			return true;
		}
	    }
		return true;
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    for (int i=0;i<15;i++){
	    if (dragging[i]==true){
			trash[i].setPosition(offX, offY);
			}
		if (dragging[i]==true){
			trash[i].setPosition(offX, offY);
			Log.d("","");
		}
		if (dragging[i]==true){
			trash[i].setPosition(offX, offY);
		}	
	    }
		
		return true;
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		for (int i=0;i<15;i++){
			if (i<5){
	    if (dragging[i]==true){
	    	if (CGRect.intersects(recycled.getBoundingBox(),trash[i].getBoundingBox())){
				trash[i].setPosition(CGPoint.ccp(-1000,0));
				flag0=0;
				}
	    }}
			if(i<10 && i>4){
	    if (dragging[i]==true){
	    	if (CGRect.intersects(unrecycled.getBoundingBox(),trash[i].getBoundingBox())){
				trash[i].setPosition(CGPoint.ccp(-1000,0));
				flag1=0;
				}
			}}
			if(i<15 && i>9){
	    if (dragging[i]==true){
	    	if (CGRect.intersects(iron.getBoundingBox(),trash[i].getBoundingBox())){
				trash[i].setPosition(CGPoint.ccp(-1000,0));
				flag2=0;
				}
	    	}}
		}
		for (int i=0;i<15;i++){
	    dragging[i] = false;
		}
		return true;
		 
	}
}
