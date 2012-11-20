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
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import android.R.string;
import android.util.Log;
import android.content.Context;
import android.hardware.Camera.Area;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class TouchIt extends CCColorLayer {
	int[] time;//array untuk timer
	public boolean[] dragging;//array agar objek dapat di drag 
	public CCSprite[] trashcan;//array sebagai tempat sampah
	public CCSprite[]  trash;//array sebagai sampah
	boolean respawn=true;//respawn
	public CCLabel labelScore,labelWin;//label timer 
	int offX,offY,random=0,countScore=0,chek=0,mem=0; 
	public static int quantity;
    public CCSprite template = new CCSprite().sprite("HVGA/trash1.png");//template ukuran gambar
    public static float scalex=0;//variabel penyimpanan DP
    public static int scale=0,lvl=0;
	public static CCScene scene(){//menggambar tampilan melalui framework cocos2d
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new TouchIt(ccColor4B.ccc4(253,209,161,255));
	    scene.addChild(layer);
	    return scene;
	}
	public static void level(int lv){//mendapatkan level yang di inginkan
		lvl=lv;
		quantity=lvl*9;
	}
	public static void scale(float x){//mendapatkan DP setiap tipe Android
		scalex=x;
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
	public void randomine(){//digunakan untuk merandom posisi objek di dalam game. selanjutnya akan disebut respawn
		Random rand = new Random();
		int x,y;
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		for (int i=0;i<quantity;i++){
				x=rand.nextInt((int)winSize.width-100);
				y=rand.nextInt((int)winSize.height *3/5);
			trash[i].setPosition(x+trashcan[1].getContentSize().width/2.f,y+trashcan[1].getContentSize().height + trash[i].getContentSize().height/2.f);
			}
		
		
		random = rand.nextInt((quantity+lvl)/2)+(quantity+lvl)/2; 
	}
	public void randomcan(){
		Random rand = new Random();
		int ran=0;
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		ran=rand.nextInt(3);
		StringBuilder a = new StringBuilder();
		String teks = a.append(ran).toString();
		if (lvl>5){
			switch (ran) {
			case 0:
				trashcan[0].setPosition(trashcan[0].getContentSize().width/2.f, trashcan[0].getContentSize().height/2.f);
				trashcan[1].setPosition(winSize.width/2.f, trashcan[1].getContentSize().height/2.f);
				trashcan[2].setPosition(winSize.width-trashcan[2].getContentSize().width/2.f, trashcan[2].getContentSize().height/2.f);
				break;
			case 1:
				trashcan[0].setPosition(winSize.width/2.f, trashcan[0].getContentSize().height/2.f);
				trashcan[1].setPosition(winSize.width-trashcan[1].getContentSize().width/2.f, trashcan[1].getContentSize().height/2.f);
				trashcan[2].setPosition(trashcan[2].getContentSize().width/2.f, trashcan[2].getContentSize().height/2.f);
				break;
			case 2:
				trashcan[0].setPosition(winSize.width-trashcan[0].getContentSize().width/2.f, trashcan[0].getContentSize().height/2.f);
				trashcan[1].setPosition(trashcan[1].getContentSize().width/2.f, trashcan[1].getContentSize().height/2.f);
				trashcan[2].setPosition(winSize.width/2.f, trashcan[2].getContentSize().height/2.f);
				break;
			default:
				break;
			}
			}
	}
	public void addobj(){//digunakan untuk membuat objek di dalam game. fungsi ini hanya perlu dijalankan satu kali.
		trash = new CCSprite[quantity];
		trashcan = new CCSprite[3];
		dragging=new boolean[quantity];
		Random rand = new Random();
		int x,y;
		 CGSize winSize = CCDirector.sharedDirector().displaySize();
		 if (scalex>1){//menentukan gambar ketika DP besar 
		 for (int i=0;i<3;i++){
				 trashcan[i] = new CCSprite().sprite("HVGA/trash"+(i+1)+".png");
				if (i==0){
				 trashcan[i].setPosition(CGPoint.ccp(trashcan[i].getContentSize().width/2.f, trashcan[i].getContentSize().height/2.f));
				}
				if (i==1){
					 trashcan[i].setPosition(CGPoint.ccp(winSize.width/2.f, trashcan[i].getContentSize().height/2.f));
					}
				if (i==2){
					 trashcan[i].setPosition(CGPoint.ccp(winSize.width-trashcan[i].getContentSize().width/2.f, trashcan[i].getContentSize().height/2.f));
					}
				addChild(trashcan[i],0,i);
			 }
			for (int i=0;i<quantity;i++){
				dragging[i]=false;
					x=rand.nextInt((int)winSize.width-100);
					y=rand.nextInt((int)winSize.height *3/5);
				if(i<quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/1.png");}
				else if(i<2*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/2.png");}
				else if(i<3*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/3.png");}
				else if(i<4*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/4.png");}
				else if(i<5*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/5.png");}
				else if(i<6*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/6.png");}
				else if(i<7*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/7.png");}
				else if(i<8*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/8.png");}
				else if(i<9*quantity/9){
					trash[i] = new CCSprite().sprite("HVGA/9.png");}
				trash[i].setPosition(0,0);
					addChild(trash[i],0,i);
			}}
		 if (scalex<=1){//menentukan gambar ketika DPnya kecil
			 for (int i=0;i<3;i++){
				 trashcan[i] = new CCSprite().sprite("MVGA/trash"+(i+1)+".png");
				if (i==0){
				 trashcan[i].setPosition(CGPoint.ccp(trashcan[i].getContentSize().width/2.f, trashcan[i].getContentSize().height/2.f));
				}
				if (i==1){
					 trashcan[i].setPosition(CGPoint.ccp(winSize.width/2.f, trashcan[i].getContentSize().height/2.f));
					}
				if (i==2){
					 trashcan[i].setPosition(CGPoint.ccp(winSize.width-trashcan[i].getContentSize().width/2.f, trashcan[i].getContentSize().height/2.f));
					}
				addChild(trashcan[i],0,i);
			 }
			for (int i=0;i<quantity;i++){
				dragging[i]=false;
					x=rand.nextInt((int)winSize.width-100);
					y=rand.nextInt((int)winSize.height *3/5);
				if(i<quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/1.png");}
				else if(i<2*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/2.png");}
				else if(i<3*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/3.png");}
				else if(i<4*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/4.png");}
				else if(i<5*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/5.png");}
				else if(i<6*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/6.png");}
				else if(i<7*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/7.png");}
				else if(i<8*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/8.png");}
				else if(i<9*quantity/9){
					trash[i] = new CCSprite().sprite("MVGA/9.png");}
				trash[i].setPosition(x+trashcan[1].getContentSize().width/2.f,y+trashcan[1].getContentSize().height + trash[i].getContentSize().height/2.f);
					addChild(trash[i],0,i);
			}
		 }
		  }
	public void update(float t){/*fungsi yang digunakan untuk dilakukan berdasarkan waktu (schedular). hal ini meliputi
		timer, respawning, hingga kondisi menang-kalah*/ 
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		countScore++;
		
		if (countScore<=random){//menentukan kondisi win-lose, serta respawn sampah setiap waktu yuang di generate secara random
			if(lvl>5){
			if (countScore==random/2 || countScore==random){
				randomcan();
			}}
		else if (lvl>7){
			if (countScore==random/4 || countScore==2*random/4 || countScore==3*random/4 || countScore==random){
				randomcan();
			}
		}
			if (chek>=quantity){
				labelWin.setPosition(winSize.width/2.f,winSize.height/2.f);
				labelWin.setString("YOU WIN!");
				
				respawn=false;
				labelScore.setPosition(-1000,0);
			}
		}
		else{
			countScore=0;
			chek=0;
			if (respawn==true){
			randomine();
			}
		}
		StringBuilder a = new StringBuilder();
		String teks;
		//timer
				mem++;
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
			    labelScore.setString(teks);
			    //timer
	}
	public TouchIt(ccColor4B color)
	{//constructor, digunakan sebagai fungsi main.
	    super(color);
	    
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    time = new int[4];
	    for(int i =0;i<4;i++){
	    	time[i]=0;
	    }
	    quantity=lvl*9;//memberikan nilai kepada variabel quantity. variabel quantity digunakan untuk men-set jumlah sampah
	    labelScore = CCLabel.makeLabel("" + countScore, "DroidSans", 25);
	    labelScore.setColor(ccColor3B.ccBLACK);
	    addChild(labelScore, 11);
	    labelScore.setTag(11);
	    labelWin = CCLabel.makeLabel("" + countScore, "DroidSans", 25);
	    labelWin.setColor(ccColor3B.ccBLACK);
	    labelWin.setPosition(-1000,0);
	    addChild(labelWin, 11);
	    labelWin.setTag(11);
	    this.setIsTouchEnabled(true);
	    labelScore.setPosition(CGPoint.ccp(winSize.getWidth()-50, winSize.getHeight()-50));
	    labelScore.setString("START!");
	    addobj();
	    randomine();//dipanggil sekali untuk mendapatkan nilai dari respawn yang dirandom.
	    this.schedule("update",1.0f);
	    
	}
	@Override
	public boolean ccTouchesBegan(MotionEvent event){//fungsi yang dijalankan ketika layar disentuh
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    StringBuilder a = new StringBuilder();
	    String teks;
	    for (int i=quantity-1;i>=0;i--){
	    	if (trash[i].getBoundingBox().contains(offX, offY)==true){
	    		teks = a.append(i).toString();
	    		Log.d("array ke", teks);
	    		dragging[i]=true;
	    		break;
	    	}
	    }
	    Log.d("drag","start");
		return true;
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {//fungsi yang dijalankan ketika layar disentuh dan di drag
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		offX = (int)(location.x); 
	    offY = (int)(location.y); 
	    for(int i=0;i<quantity;i++){
	    if(dragging[i]==true){
	    	trash[i].setPosition(offX, offY);
	    }
	    }
	    Log.d("drag","drag");
		return true;
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {//fungsi yang dijalankan ketika layar telah selesai disentuh
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		for (int i=0;i<quantity;i++){
			if (dragging[i]==true){
				if (i<3*quantity/9){
					if (CGRect.intersects(trash[i].getBoundingBox(), trashcan[0].getBoundingBox())){
						trash[i].setPosition(-1000,0);
						chek++;
						//sound :D
						Context context = CCDirector.sharedDirector().getActivity();
						SoundEngine.sharedEngine().playEffect(context,org.andengine.R.raw.sound);
					}}
				else if (i<6*quantity/9){
					if (CGRect.intersects(trash[i].getBoundingBox(), trashcan[1].getBoundingBox())){
						trash[i].setPosition(-1000,0);
						chek++;
						//sound :D
						Context context = CCDirector.sharedDirector().getActivity();
						SoundEngine.sharedEngine().playEffect(context,org.andengine.R.raw.sound);
					}}
				else if (i<9*quantity/9){
					if (CGRect.intersects(trash[i].getBoundingBox(), trashcan[2].getBoundingBox())){
						trash[i].setPosition(-1000,0);
						chek++;
						//sound :D
						Context context = CCDirector.sharedDirector().getActivity();
						SoundEngine.sharedEngine().playEffect(context,org.andengine.R.raw.sound);
					}}
				}
			}
		 for(int j =0;j<quantity;j++){
			 dragging[j]=false;
		 }
		 Log.d("drag","end");
		 return true;
}
	}