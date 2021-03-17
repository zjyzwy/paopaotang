package com.scnu.element;

import java.awt.Graphics;
import java.util.List;
import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;
/**
 * 
 * @author Administrator
 * 1.继承父类
 * 2.重写各种方法，实现业务逻辑
 * 3.编写主线程的碰撞
 * 4.如果配置文件格式有改变，请修改GameLoad的加载方式
 * 
 */
public class PaoPao extends ElementObj{
	private ElementManager em=ElementManager.getManager();
	private boolean left=false;//左边
	private boolean up=false;//上边
	private boolean right=false;//右边
	private boolean down=false;//下边
	private int imgx=0;
	private long imgtime=0;//用于控制图片
	private int imgfx=-1;//用于控制图片行走方向
	private int size=1;//泡泡爆炸后水柱的大小
	private int speed=2;
	private boolean pkType=false;
	private int lastx;
	private int lasty;
	private int paonum=2;
	private int stoptime=500;
	private int c=0;
	private int addx;
	private int addy;
	@Override
	public void showElement(Graphics g) {
//		图片分割
//		System.out.println(this.getName());
		if(imgfx!=-1){
			g.drawImage(this.getIcon().getImage(), 
				0+this.getX(),0+this.getY(), 
				22+this.getX(),22+this.getY(),
				26+(imgx*100), 42+(imgfx*100), 
				72+(imgx*100), 99+(imgfx*100), 
				null);
		}else{
			g.drawImage(this.getIcon().getImage(), 
					this.getX(), this.getY(), 
					22+this.getX(),22+this.getY(), 
					26+(imgx*100), 42, 
					72+(imgx*100), 99, 
					null);
		}
	}
	@Override
	protected void updateImage(long time) {
		judgeTime();
		PKBoom();
		if(time-imgtime>10){
			imgtime=time;
			imgx++;
			if(imgx>3){
				imgx=0;
			}
		}
	}
	@Override
	protected void move() {
		if(this.isStop()==false){
			lastx=this.getX();
			lasty=this.getY();
			if (this.left && this.getX()>0) {
				this.setX(this.getX()-speed);
			}
			if (this.up && this.getY()>0) {
				this.setY(this.getY()-speed);
			}
			if (this.right && this.getX()<540) {//坐标的微小调整
				this.setX(this.getX()+speed);
			}
			if (this.down && this.getY()<540) {
				this.setY(this.getY()+speed);
			}	
		}
	}
	@Override
	public void keyClick(boolean bl, int key,String name) {
		if(this.getName().equals("play1")){
		if(this.isReverse()==true){
			if(bl){
				switch(key){
				case 37:
					this.right=true;this.imgfx=2;break;
				case 38:
					this.down=true;this.imgfx=0;break;
				case 39:
					this.left=true;this.imgfx=1;break;
				case 40:
					this.up=true;this.imgfx=3;break;
				case 32:
					this.pkType=true;break;//开启攻击状态
				}
			}else{
				switch(key){
				case 37:this.right=false;this.imgfx=-1;break;
				case 38:this.down=false;this.imgfx=-1;break;
				case 39:this.left=false;this.imgfx=-1;break;
				case 40:this.up=false;this.imgfx=-1;break;
				case 32:this.pkType=false;break;//关闭攻击状态
				}
			}
		}else{
			if(bl){
				switch(key){
				case 37:
					this.left=true;this.imgfx=1;break;
				case 38:
					this.up=true;this.imgfx=3;break;
				case 39:
					this.right=true;this.imgfx=2;break;
				case 40:
					this.down=true;this.imgfx=0;break;
				case 32:
					this.pkType=true;break;//开启攻击状态
				}
			}else{
				switch(key){
				case 37:this.left=false;this.imgfx=-1;break;
				case 38:this.up=false;this.imgfx=-1;break;
				case 39:this.right=false;this.imgfx=-1;break;
				case 40:this.down=false;this.imgfx=-1;break;
				case 32:this.pkType=false;break;//关闭攻击状态
				}
			}
		}
		}else if(this.getName().equals("play2")){
			if(this.isReverse()==true){
				if(bl){
					switch(key){
					case 65:
						this.right=true;this.imgfx=2;break;
					case 87:
						this.down=true;this.imgfx=0;break;
					case 68:
						this.left=true;this.imgfx=1;break;
					case 83:
						this.up=true;this.imgfx=3;break;
					case 74:
						this.pkType=true;break;//开启攻击状态
					}
				}else{
					switch(key){
					case 65:this.right=false;this.imgfx=-1;break;
					case 87:this.down=false;this.imgfx=-1;break;
					case 68:this.left=false;this.imgfx=-1;break;
					case 83:this.up=false;this.imgfx=-1;break;
					case 74:this.pkType=false;break;//关闭攻击状态
					}
				}
			}else{
				if(bl){
					switch(key){
					case 65:
						this.left=true;this.imgfx=1;break;
					case 87:
						this.up=true;this.imgfx=3;break;
					case 68:
						this.right=true;this.imgfx=2;break;
					case 83:
						this.down=true;this.imgfx=0;break;
					case 74:
						this.pkType=true;break;//开启攻击状态
					}
				}else{
					switch(key){
					case 65:this.left=false;this.imgfx=-1;break;
					case 87:this.up=false;this.imgfx=-1;break;
					case 68:this.right=false;this.imgfx=-1;break;
					case 83:this.down=false;this.imgfx=-1;break;
					case 74:this.pkType=false;break;//关闭攻击状态
					}
				}
			}
		}
		if(this.left){
			this.imgfx=1;
		}else if(this.right){
			this.imgfx=2;
		}else if(this.up){
			this.imgfx=3;
		}else if(this.down){
			this.imgfx=0;
		}
	}
	
	
	@Override
	protected void add(long gameTime) {
		int count=0;
		GameLoad.loadObj();
		if(!this.pkType){//如果是不发射状态，就直接return
			return;
		}
		this.pkType=false;
		List<ElementObj> file=em.getElementsByKey(GameElement.PLAYFILE);
		for(ElementObj obj:file){
			if(obj.getSetby().equals(this.getName()) && obj.isLive()==true){
				count+=1;
			}
		}
		if(count==this.paonum/2){
			return;
		}else{
			this.setInpao(true);
			ElementObj obj=GameLoad.getObj("file");
			ElementObj element =obj.createElement(this.toString());
			ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		}
	}
	
	
	
	
	@Override
	public ElementObj createElement(String str) {
		String[] split=str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setName("play"+split[3]);
		ImageIcon icon=GameLoad.imgMap.get(split[2]);
		this.setIcon(icon);
		this.setW(22);
		this.setH(22);
		return this;
	}
	@Override
	public String toString() {
		int x=compare(this.getX());
		int y=compare(this.getY());
		this.addx=x;
		this.addy=y;
		return "x:"+x+",y:"+y+",s:"+this.size+",f:playfile,p:"+this.getName();
	}
	
	public void back(){
		this.setX(lastx);
		this.setY(lasty);
	}
	
	public int compare(int num){
		int remain=num%30;
		if(remain>=15){
			num=num+30-remain;
		}
		else{
			num=num-remain;
		}
		return num;
	}
	
	@Override
	public void die() {
		GameLoad.loadObj();
		String str="x:"+this.getX()+",y:"+this.getY()+",f:trap";
		ElementObj obj=GameLoad.getObj("trap");
		ElementObj element =obj.createElement(str);
		ElementManager.getManager().addElement(element, GameElement.TRAP);
	}
	
	@Override
	public void eat(ElementObj obj) {
		ElementObj other=null;
		List<ElementObj> player=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj o:player){
			if(!this.getName().equals(o.getName())){
				other=o;
			}
		}
		String type=obj.getType();
		switch(type){
		case "pao":this.paonum+=1;break;
		case "yaoping":this.size+=1;break;
		case "guitou":this.setReverse(true);
		if(this.down){this.down=false;}
		if(this.left){this.left=false;}
		if(this.right){this.right=false;}
		if(this.up){this.up=false;}
		break;
		case "Dka":this.setUnshuilei(this.getUnshuilei() + 1);break;
		case "jieyao":this.setReverse(false);
		if(this.down){this.down=false;}
		if(this.left){this.left=false;}
		if(this.right){this.right=false;}
		if(this.up){this.up=false;}
		break;
		case "rguitou":other.setReverse(true);
		break;
		case "shuilei":obj.hurt();break;
		case "yaokongqi":other.setStop(true);break;
		}
	}
	@Override
	public void hurt() {
		if(this.getUnshuilei()>0){
			this.setUnshuilei(this.getUnshuilei()-1);
		}else{
			this.die();
		}
	}
	public void judgeTime(){
		if(this.isStop()==true){
			c++;
			if(c==this.stoptime){
				this.setStop(false);
				c=0;
			}
		}
	}
	@Override
	public void PKBoom(){
		ElementObj boom=null;
		List<ElementObj> files=em.getElementsByKey(GameElement.PLAYFILE);
		for(ElementObj obj:files){
			if(obj.getX()==this.addx && obj.getY()==this.addy){
				boom=obj;
			}
		}
		if(boom!=null){
			if(this.getRectangle().intersects(boom.getRectangle())==false){
				this.setInpao(false);
			}
		}
	}
}




