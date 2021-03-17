package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameLoad;

public class Boom extends ElementObj{
	private ElementManager em=ElementManager.getManager();
	private boolean up=true;
	private boolean down=true;
	private boolean left=true;
	private boolean right=true;
	private int ups=0;
	private int downs=0;
	private int lefts=0;
	private int rights=0;
	private int imgx=0;
	private long imgtime=0;//用于控制图片控制
	private int size=0;
	private int Xs=30;
	private int Ys=30;
	private List<Rectangle> re=new ArrayList<>();
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				0+this.getX(), 0+this.getY(), 
				30+this.getX(),30+this.getY(), 
				160,350, 
				190,383, 
				null);
//		if(imgx<=size){
//			//左中间
//			if(this.left){
//			g.drawImage(this.getIcon().getImage(), 
//					0+this.getX()-Xs*(size-1), 0+this.getY(), 
//					30+this.getX(),30+this.getY(), 
//					736,354, 
//					769,383, 
//					null);
//			}
//			//右中间
//			if(this.right){
//			g.drawImage(this.getIcon().getImage(), 
//					0+this.getX(), 0+this.getY(), 
//					30+this.getX()+Xs*(size-1),30+this.getY(), 
//					545,354, 
//					577,383, 
//					null);
//			}
//			//下中间
//			if(this.down){
//			g.drawImage(this.getIcon().getImage(), 
//					0+this.getX(), 0+this.getY(), 
//					30+this.getX(), 30+this.getY()+Ys*(size-1), 
//					353,353, 
//					384,384, 
//					null);
//			}
//			//上中间
//			if(this.up){
//			g.drawImage(this.getIcon().getImage(), 
//					0+this.getX(),0+this.getY()-Ys*(size-1), 
//					30+this.getX(),30+this.getY(), 
//					355,448, 
//					385,480, 
//					null);
//			}
//		}
		if(this.ups>0){
			g.drawImage(this.getIcon().getImage(), 
				0+this.getX(),0+this.getY()-Ys*(this.ups), 
				30+this.getX(),30+this.getY(), 
				355,448, 
				385,480, 
				null);
		}
		if(this.downs>0){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(), 0+this.getY(), 
					30+this.getX(), 30+this.getY()+Ys*(this.downs), 
					353,353, 
					384,384, 
					null);
		}
		if(this.lefts>0){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX()-Xs*(this.lefts), 0+this.getY(), 
					30+this.getX(),30+this.getY(), 
					736,354, 
					769,383, 
					null);
		}
		if(this.rights>0){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(), 0+this.getY(), 
					30+this.getX()+Xs*(this.rights),30+this.getY(), 
					545,354, 
					577,383, 
					null);
		}
			//左尽头
			if(this.left){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX()-Xs*size, 0+this.getY(), 
					30+this.getX(),30+this.getY(), 
					546,160, 
					576,192, 
					null);
			}
			//右尽头
			if(this.right){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(), 0+this.getY(), 
					30+this.getX()+Xs*size,30+this.getY(), 
					160,161, 
					193,192, 
					null);
			}
			//下尽头
			if(this.down){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(), 0+this.getY(), 
					30+this.getX(), 30+this.getY()+Ys*size,
					735,160, 
					768,194, 
					null);
			}
			//上尽头
			if(this.up){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(),0+this.getY()-Ys*size, 
					30+this.getX(),30+this.getY(), 
					160,445, 
					195,480, 
					null);
			}
		
	}
	@Override
	protected void updateImage(long time) {
		if(this.size>=3){
			if(time-imgtime>10){
				imgtime=time;
				imgx++;
			}
			if(imgx==size+1){
				this.setLive(false);
			}
		}else{
			if(time-imgtime>15){
				imgtime=time;
				imgx++;
			}
			if(imgx==size+1){
				this.setLive(false);
			}
		}
	}
	
	@Override
	public ElementObj createElement(String str){
		String[] split=str.split(",");
		for(String str1:split){
			String[] split2=str1.split(":");
			switch(split2[0]){
			case "x":this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "f":
				this.setName(split[2]);
				ImageIcon icon=GameLoad.imgMap.get(split2[1]);
				this.setIcon(icon);
				break;
			case "w":this.setW(Integer.parseInt(split2[1]));break;
			case "h":this.setH(Integer.parseInt(split2[1]));break;
			case "s":this.size=Integer.parseInt(split2[1]);break;
			}
		}
		boom();
		return this;
	}
	
	
	public void boom(){
//		向上搜索
		re.add(new Rectangle(this.getX(),this.getY(),30,30));
		for(int i=1;i<=this.size;i++){
			if(em.find(this.getX(),this.getY()-i*30)=="map" || em.find(this.getX(),this.getY()-i*30)=="tree"){
				this.up=false;
				break;
			}
			this.ups=i;
			re.add(new Rectangle(this.getX(),this.getY()-i*30,30,30));
		}
//		向下搜索
		for(int i=1;i<=this.size;i++){
			if(em.find(this.getX(),this.getY()+i*30)=="map" || em.find(this.getX(),this.getY()+i*30)=="tree"){
				this.down=false;
				break;
			}
			this.downs=i;
			re.add(new Rectangle(this.getX(),this.getY()+i*30,30,30));
		}
//		向左搜索
		for(int i=1;i<=this.size;i++){
			if(em.find(this.getX()-30*i,this.getY())=="map" || em.find(this.getX()-30*i,this.getY())=="tree"){
				this.left=false;
				break;
			}
			this.lefts=i;
			re.add(new Rectangle(this.getX()-30*i,this.getY(),30,30));
		}
//		向右搜索
		for(int i=1;i<=this.size;i++){
			if(em.find(this.getX()+30*i,this.getY())=="map" || em.find(this.getX()+30*i,this.getY())=="tree"){
				this.right=false;
				break;
			}
			this.rights=i;
			re.add(new Rectangle(this.getX()+30*i,this.getY(),30,30));
		}
	}
//	用于判断泡泡爆炸时，玩家是否被炸到
	@Override
	public boolean isPlay(ElementObj obj){
		for(Rectangle r:re){
			if(r.intersects(obj.getRectangle())==true){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean Destroy(ElementObj obj) {
		boom();
		if(this.ups!=0){
			for(int i=1;i<=this.ups;i++){
				if(obj.getX()==this.getX() && obj.getY()==this.getY()-30*i)
				{
					return true;
				}
			}
		}
		if(this.downs!=0){
			for(int i=1;i<=this.downs;i++){
				if(obj.getX()==this.getX()&& obj.getY()==this.getY()+30*i)
				{
					return true;
				}
			}
		}
		if(this.lefts!=0){
			for(int i=1;i<=this.lefts;i++){
				if(obj.getX()==this.getX()-i*30 && obj.getY()==this.getY())
				{
					return true;
				}
			}
		}
		if(this.rights!=0){
			for(int i=1;i<=this.rights;i++){
				if(obj.getX()==this.getX()+i*30 && obj.getY()==this.getY())
				{
					return true;
				}
			}
		}
		return false;
	}
	
//	public boolean isBoom(ElementObj obj){
//		boom();
//	}
}
