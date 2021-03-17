package com.scnu.element;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;

public class Playfile extends ElementObj{
	private int imgx=0;
	private long imgtime=0;//用于控制图片控制
	private int size;
	public Playfile(){}
	@Override
	public void showElement(Graphics g) {
		if(imgx<=3){
			g.drawImage(this.getIcon().getImage(), 
					0+this.getX(), 0+this.getY(), 
					30+this.getX(),30+this.getY(), 
					0+(imgx*32), 8, 
					32+(imgx*32),47, 
					null);
			}
	}
	@Override
	protected void updateImage(long time) {
		if(time-imgtime>100){
			imgtime=time;
			imgx++;
		}
		if(imgx==3){
			this.setLive(false);
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
				this.setW(30);
				this.setH(30);
				break;
			case "s":this.size=Integer.parseInt(split2[1]);break;
			case "p":this.setSetby(split2[1]);break;
			}
		}
		return this;
	}
	
	@Override
	public void die() {
		GameLoad.loadObj();
		ElementObj obj=GameLoad.getObj("boom");
		ElementObj element =obj.createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.BOOM);
		
	}
	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY();
		int w=this.getW();
		int h=this.getH();
		return "x:"+x+",y:"+y+",w:"+w+",h:"+h+",s:"+this.size+",f:boom";
	}

}
