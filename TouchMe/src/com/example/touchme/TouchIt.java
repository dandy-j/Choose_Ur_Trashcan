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
	public CCSprite  recycled1 = CCSprite.sprite("trash4.png");
	public CCSprite  unrecycled1 = CCSprite.sprite("trash6.png");
	public CCSprite  iron1 = CCSprite.sprite("trash5.png");
	public CCSprite[]  trash;
	public int flag0=0,flag1=0,flag2=0;
	int offX; 
    int offY; 
    public static float scalex=0;
    public static int scale=0;
    public static int scaley=0;
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new TouchIt(ccColor4B.ccc4(253,209,161,255));
	    scene.addChild(layer);
	    return scene;
	}
	public static void scale(float x){
		scalex=x;
		StringBuilder a = new StringBuilder();
		String teks;
		a.append(scalex);
		teks = a.toString();
		Log.d("density",teks);
		a.append(scaley);
		teks = a.toString();
		Log.d("density2",teks);
		if (scalex<1) {
			scale=0;
		}
		if (scalex==1) {
			scale=1;
		}
		if (scalex>1) {
			scale=2;
		}
		
	}
	
	public void addsampah(){
		
		trash = new CCSprite[30];
		Random rand = new Random();
		int x,y;
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		if (scale<=1){
		 for (int i=0;i<30;i++){
			 dragging = new boolean[30];
			 dragging[i]=false;
			 
			 if (i<5){
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kulit apel.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag0=1;
				 }
			 if (i<10 && i>4){
				 
					 y=rand.nextInt((int)winSize.height-220);
					 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("botol plastik.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag1=1;
				 }
			 if (i<15 && i>9){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("baut.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<20 && i>14){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("daun.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<25 && i>19){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kantong plastik.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<30 && i>24){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kaleng.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 }
		}
		 if (scale>1){
			 for (int i=0;i<30;i++){
		 
			 dragging = new boolean[30];
			 dragging[i]=false;
			 
			 if (i<5){
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kulit apel1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag0=1;
				 }
			 if (i<10 && i>4){
				 
					 y=rand.nextInt((int)winSize.height-220);
					 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("botol plastik1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag1=1;
				 }
			 if (i<15 && i>9){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("baut1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<20 && i>14){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("daun1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<25 && i>19){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kantong plastik1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<30 && i>24){
				 
				 y=rand.nextInt((int)winSize.height-220);
				 x=rand.nextInt((int)winSize.width-180);
					 trash[i] = new CCSprite().sprite("kaleng1.png");
					 trash[i].setPosition(CGPoint.ccp(x+50,y+150));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 }}
			 }
		 
	
	public void tempatsampah(){
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		    if(scale<=1){
		    recycled.setPosition(CGPoint.ccp(winSize.width-(recycled.getContentSize().width/2.0f),recycled.getContentSize().height/2.0f));
		    addChild(recycled);
		    unrecycled.setPosition(CGPoint.ccp(unrecycled.getContentSize().width/2.0f,unrecycled.getContentSize().height/2.0f));
		    addChild(unrecycled);
		    iron.setPosition(CGPoint.ccp(winSize.width/2.0f,iron.getContentSize().height/2.0f));
		    addChild(iron);
		    }
		    if(scale>1){
		    	recycled1.setPosition(CGPoint.ccp(winSize.width-(recycled1.getContentSize().width/2.0f),recycled1.getContentSize().height/2.0f));
			    addChild(recycled1);
			    unrecycled1.setPosition(CGPoint.ccp(unrecycled1.getContentSize().width/2.0f,unrecycled1.getContentSize().height/2.0f));
			    addChild(unrecycled1);
			    iron1.setPosition(CGPoint.ccp(winSize.width/2.0f,iron1.getContentSize().height/2.0f));
			    addChild(iron1);
		    }
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
	    for (int i=0;i<30;i++){
	    	if (i<5){
	    		if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("nha","bok");
			dragging[i]=true;
			return true;
		}}
	    if (i<10 && i>4){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("nho","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<15 && i>9){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("nhe","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<20 && i>14){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ci","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<25 && i>19){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ca","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<30 && i>24){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ce","bok");
			dragging[i]=true;
			StringBuilder a = new StringBuilder();
			String teks;
			a.append(dragging[i]);
			teks=a.toString();
			Log.d("dragging",teks);
			return true;
		}}
	    }
		return true;
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    for (int i=0;i<30;i++){
	    	    if (dragging[i]==true){
	    			trash[i].setPosition(offX, offY);
	    		break;	
	    	    }
	    }
		
		return true;
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		if (scale<=1){
			for (int i=0;i<30;i++){
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
				if (i<20 && i>14){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(recycled.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag0=0;
							}
				    }}
						if(i<25 && i>19){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(unrecycled.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag1=0;
							}
						}}
						if(i<30 && i>24){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(iron.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag2=0;
							}
				    	}}
			}
		}
		if(scale>1){
			for (int i=0;i<30;i++){
				if (i<5){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(recycled1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					flag0=0;
					}
		    }}
				if(i<10 && i>4){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(unrecycled1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					flag1=0;
					}
				}}
				if(i<15 && i>9){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(iron1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					flag2=0;
					}
		    	}}
				if (i<20 && i>14){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(recycled1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag0=0;
							}
				    }}
						if(i<25 && i>19){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(unrecycled1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag1=0;
							}
						}}
						if(i<30 && i>24){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(iron1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							flag2=0;
							}
				    	}}
			}
		}
		for (int i=0;i<30;i++){
	    dragging[i] = false;
		}
		return true;
		 
	}
}
