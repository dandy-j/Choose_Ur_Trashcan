package com.example.touchme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.andengine.input.touch.TouchEvent;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import android.util.Log;
import android.hardware.Camera.Area;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class TouchIt extends CCColorLayer {
	public boolean[] dragging;
	public CCSprite  recycled = CCSprite.sprite("MVGA/trash1.png");
	public CCSprite  unrecycled = CCSprite.sprite("MVGA/trash3.png");
	public CCSprite  iron = CCSprite.sprite("MVGA/trash2.png");
	public CCSprite  recycled1 = CCSprite.sprite("HVGA/trash4.png");
	public CCSprite  unrecycled1 = CCSprite.sprite("HVGA/trash6.png");
	public CCSprite  iron1 = CCSprite.sprite("HVGA/trash5.png");
	public CCSprite[]  trash;
	public boolean win=false;
	public int level=1;
	public boolean[] threw;
	boolean respawn=true;
	int check=0;
	int countScore=0;
	public CCLabel labelScore; 
	int offX; 
    int offY; 
    int count;
    int random;
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
	
	public void randomine(){
		Random rand = new Random();
		int x,y;
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		for (int i=0;i<45;i++){
			if (dragging[i]==false){
				x=rand.nextInt((int)winSize.width-100);
				y=rand.nextInt((int)winSize.height *3/5);
			trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
			//addChild(trash[i],0,i);
			}
		}
	}
	public void addsampah(){
		trash = new CCSprite[45];
		threw = new boolean[45];
		Random rand = new Random();
		int x,y;
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		if (scale<=1){
		 for (int i=0;i<45;i++){
			 threw[i] = false;
			 dragging = new boolean[45];
			 dragging[i]=false;
			 //flag[i]=false;
			 if (i<5){
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/apel.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag0=1;
				 }
			 if (i<10 && i>4){
				 
					 y=rand.nextInt((int)winSize.height *3/5);
					 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/botol.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag1=1;
				 }
			 if (i<15 && i>9){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/baut.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<20 && i>14){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/bayam.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<25 && i>19){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/sandal.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<30 && i>24){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/kaleng.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<35 && i>29){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/pisang.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<40 && i>34){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/bebek.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<45 && i>39){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("MVGA/sendok.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
		 }
		}
		 if (scale>1){
			 for (int i=0;i<45;i++){
				 threw[i] = false;
			 dragging = new boolean[45];
			 dragging[i]=false;
			 
			 if (i<5){
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/apel.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag0=1;
				 }
			 if (i<10 && i>4){
				 
					 y=rand.nextInt((int)winSize.height *3/5);
					 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/botol.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag1=1;
				 }
			 if (i<15 && i>9){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/baut.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<20 && i>14){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/bayam.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<25 && i>19){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/sandal.png");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<30 && i>24){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/kaleng.png");
					 Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<35 && i>29){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/pisang.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<40 && i>34){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/bebek.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 if (i<45 && i>39){
				 
				 y=rand.nextInt((int)winSize.height *3/5);
				 x=rand.nextInt((int)winSize.width-100);
					 trash[i] = new CCSprite().sprite("HVGA/sendok.png");
					 //Log.d("asdawd","asjdkajsld");
					 trash[i].setPosition(CGPoint.ccp(x+unrecycled.getContentSize().width/2.f,y+unrecycled.getContentSize().height + trash[i].getContentSize().height/2.f));
					 addChild(trash[i],0,i);
					 //flag2=1;
				 }
			 }}
		 random=rand.nextInt(25)+25;
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
	
	public void cek(){
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		StringBuilder str= new StringBuilder();
		String teks;
		int chek=0;
		if(countScore<=random){
		for(int i=0;i<45;i++){
			if (threw[i]==true){
				chek++;
			}
		}
		if (chek>=45){
			respawn=false;
			win=true;
		}}
		else{
			chek=0;
			respawn=true;	
		}
	}
	public void gamelogic(float t){
		//cek();
		if (respawn==true){
			countScore=0;
			randomine();
		}
	}
	public void update(float t){
		cek();
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		countScore++;
		
		StringBuilder a = new StringBuilder();
		String teks;
		teks = a.append(countScore).toString();
	    labelScore.setString(teks);
	   
	    if (win==true){
	    	labelScore.setPosition(winSize.getWidth()/2.f, winSize.getHeight()/2.f);
			labelScore.setString("You WIN!");
	    }
	}
	public TouchIt(ccColor4B color)
	{
		
	    super(color);
	    labelScore = CCLabel.makeLabel("" + countScore, "DroidSans", 25);
	    labelScore.setColor(ccColor3B.ccBLACK);
	    addChild(labelScore, 11);
	    labelScore.setTag(11);
	    this.setIsTouchEnabled(true);
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    labelScore.setPosition(CGPoint.ccp(winSize.getWidth()-50, winSize.getHeight()-50));
	    labelScore.setString("Level 1");
		random=0;
	    tempatsampah();
	    addsampah();
	    this.schedule("gamelogic",(float)random);
	    this.schedule("update",1.0f);
	    
	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event){
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    for (int i=44;i>=0;i--){
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
			//StringBuilder a = new StringBuilder();
			//String teks;
			//a.append(dragging[i]);
			//teks=a.toString();
			//Log.d("dragging",teks);
			return true;
		}}
	    if(i<35 && i>29){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ci","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<40 && i>34){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ca","bok");
			dragging[i]=true;
			return true;
		}}
	    if(i<45 && i>39){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
			Log.d("ce","bok");
			dragging[i]=true;
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
	    for (int i=0;i<45;i++){
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
			for (int i=44;i>=0;i--){
				if (i<5){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(recycled.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;
					}
		    }}
				if(i<10 && i>4){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(unrecycled.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;	
		    	}
				}}
				if(i<15 && i>9){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(iron.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;	
		    	}
		    	}}
				if (i<20 && i>14){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(recycled.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
				    }}
						if(i<25 && i>19){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(unrecycled.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
						}}
						if(i<30 && i>24){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(iron.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
				    	}}
						if (i<35 && i>29){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(recycled.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
						    }}
								if(i<40 && i>34){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(unrecycled.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
								}}
								if(i<45 && i>39){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(iron.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
						    	}}
			}
		}
		if(scale>1){
			for (int i=44;i>=0;i--){
				if (i<5){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(recycled1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;	
		    	}
		    }}
				if(i<10 && i>4){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(unrecycled1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;	
		    	}
				}}
				if(i<15 && i>9){
		    if (dragging[i]==true){
		    	if (CGRect.intersects(iron1.getBoundingBox(),trash[i].getBoundingBox())){
					trash[i].setPosition(CGPoint.ccp(-1000,0));
					//flag[i]=true;
					threw[i]=true;	
		    	}
		    	}}
				if (i<20 && i>14){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(recycled1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
				    }}
						if(i<25 && i>19){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(unrecycled1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
						}}
						if(i<30 && i>24){
				    if (dragging[i]==true){
				    	if (CGRect.intersects(iron1.getBoundingBox(),trash[i].getBoundingBox())){
							trash[i].setPosition(CGPoint.ccp(-1000,0));
							//flag[i]=true;
							threw[i]=true;	
				    	}
				    	}}
						if (i<35 && i>29){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(recycled1.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
						    }}
								if(i<40 && i>34){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(unrecycled1.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
								}}
								if(i<45 && i>39){
						    if (dragging[i]==true){
						    	if (CGRect.intersects(iron1.getBoundingBox(),trash[i].getBoundingBox())){
									trash[i].setPosition(CGPoint.ccp(-1000,0));
									//flag[i]=true;
									threw[i]=true;	
						    	}
						    	}}
			}
		}
		for (int i=0;i<45;i++){
	    dragging[i] = false;
		}
		
		return true;
		 
	}
}
