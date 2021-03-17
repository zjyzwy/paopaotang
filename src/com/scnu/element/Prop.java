package com.scnu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameLoad;

public class Prop extends ElementObj{
	private ElementManager em=ElementManager.getManager();
	private int imgx=0;
	private long imgtime=0;//用于控制图片控制
	private int wuditime=200;
	private int c=0;
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				0+this.getX(), 0+this.getY(), 
				30+this.getX(),30+this.getY(), 
				0+(imgx*32), 1, 
				34+(imgx*32),48, 
				null);
	}
	
	@Override
	protected void updateImage(long time) {
		judgeTime();
		if(time-imgtime>100){
			imgtime=time;
			imgx++;
		}
		if(imgx>3){
			imgx=0;
		}
	}
	
	@Override
	public ElementObj createElement(String str) {
		this.setName("prop");
		String[] split=str.split(",");
		ImageIcon icon=null;
		for(String s:split){
			String[] split2=s.split(":");
			switch(split2[0]){
			case "x":this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "t":this.setType(split2[1]);icon=GameLoad.imgMap.get(split2[1]);break;
			}
		}
		em.add(this.getX(), this.getY(), this.getName());
		this.setIcon(icon);
		this.setW(30);
		this.setH(30);
		this.imgtime=0;
		return this;
	}
	public void judgeTime(){
		c++;
		if(c==this.wuditime){
			this.setDestructible(true);
			c=0;
		}
	}
}
