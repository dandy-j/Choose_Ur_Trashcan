package com.example.touchme;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.input.touch.TouchEvent;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
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

public class TouchIt extends CCColorLayer {
	protected ArrayList<CCSprite> _sampah;
	protected ArrayList<CCSprite> _tsampah;
	public int del=4;
	public boolean dragging0=false,dragging1=false,dragging2=false;
	public int tumpuk=0;
	public CCSprite  recycled = CCSprite.sprite("Recycled.png");
	public CCSprite  unrecycled = CCSprite.sprite("Unrecycled.png");
	public CCSprite  iron = CCSprite.sprite("Iron.png");
	public CCSprite  recycledtrash = CCSprite.sprite("RecycledTrash.png");
	protected CCSprite  unrecycledtrash = CCSprite.sprite("UnRecycledTrash.png");
	public CCSprite  irontrash = CCSprite.sprite("IronTrash.png");
	public int flag0=0,flag1=0,flag2=0;
	
	float touchX;
	float touchY;
	int offX; 
    int offY; 
 
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new TouchIt(ccColor4B.ccc4(0, 0, 0, 0));
	 
	    scene.addChild(layer);
	 
	    return scene;
	}
	public void addsampah(){
		Random rando = new Random();
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
			recycledtrash.setPosition(CGPoint.ccp(recycledtrash.getContentSize().width/2.0f,winSize.height-recycled.getContentSize().height/2.0f));
			addChild(recycledtrash);
			flag0=1;
			unrecycledtrash.setPosition(CGPoint.ccp(winSize.width/2.0f,winSize.height-unrecycled.getContentSize().height/2.0f));
			addChild(unrecycledtrash);
			flag1=1;
			irontrash.setPosition(CGPoint.ccp(winSize.width-irontrash.getContentSize().width/2.0f,winSize.height-iron.getContentSize().height/2.0f));
			addChild(irontrash);
			flag2=1;
	}
	public void tempatsampah(){
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		    
		    recycled.setPosition(CGPoint.ccp(recycled.getContentSize().width/2.0f,recycled.getContentSize().height/2.0f));
		    addChild(recycled);
		    unrecycled.setPosition(CGPoint.ccp(winSize.width / 2.0f,unrecycled.getContentSize().height/2.0f));
		    addChild(unrecycled);
		    iron.setPosition(CGPoint.ccp(winSize.width - (iron.getContentSize().width/2.0f),iron.getContentSize().height/2.0f));
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
	    
	    tempatsampah();
	    //addsampah();
	    this.schedule("gamelogic", 5.0f);
	    
	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event){
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    CGRect pointt = CGRect.make(240,240,100,100);
		if (recycledtrash.getBoundingBox().contains(offX, offY)){
			Log.d("nha","bok");
			dragging0=true;
			return true;
			
		}
		if (unrecycledtrash.getBoundingBox().contains(offX, offY)){
			Log.d("nho","bok");
			dragging1=true;
			return true;
		}
		if (irontrash.getBoundingBox().contains(offX, offY)){
			Log.d("nhe","bok");
			dragging2=true;
			return true;
		}
		return true;
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
		if (dragging0==true){
			recycledtrash.setPosition(offX, offY);
			}
		else if (dragging1==true){
			unrecycledtrash.setPosition(offX, offY);
			Log.d("","");
		}
		else if (dragging2==true){
			irontrash.setPosition(offX, offY);
		}	
		
		
		return true;
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		
		
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		
	    if (dragging0==true){
	    	if (CGRect.intersects(recycled.getBoundingBox(),recycledtrash.getBoundingBox())){
				recycledtrash.setPosition(CGPoint.ccp(-1000,0));
				flag1=0;
				}
			else{
				recycledtrash.setPosition(CGPoint.ccp(recycledtrash.getContentSize().width/2.0f,winSize.height-recycled.getContentSize().height/2.0f));
				Log.d("kwok","kwok");
			}}
	    
	    else if (dragging1==true){
	    	if (CGRect.intersects(unrecycled.getBoundingBox(),unrecycledtrash.getBoundingBox())){
				unrecycledtrash.setPosition(CGPoint.ccp(-1000,0));
				flag2=0;
				}
			else{
				unrecycledtrash.setPosition(CGPoint.ccp(unrecycledtrash.getContentSize().width/2.0f,winSize.height-unrecycled.getContentSize().height/2.0f));
				Log.d("kwok","kwok");
			}}
	    else if (dragging2==true){
	    	if (CGRect.intersects(iron.getBoundingBox(),irontrash.getBoundingBox())){
				irontrash.setPosition(CGPoint.ccp(-1000,0));
				flag1=0;
				}
			else{
				irontrash.setPosition(CGPoint.ccp(irontrash.getContentSize().width/2.0f,winSize.height-iron.getContentSize().height/2.0f));
				Log.d("kwok","kwok");
			}}
	    dragging0 = false;
		dragging1 = false;
		dragging2 = false;
		return true;
		 
	}
}
