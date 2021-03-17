package com.scnu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.manager.GameLoad;

public class Trap extends ElementObj{
	private int count=0;//用于控制
	private int imgx=0;
	private long imgtime=0;//用于控制图片
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), 
				0+this.getX(),0+this.getY(), 
				30+this.getX(),30+this.getY(),
				26+(imgx*100), 42, 
				72+(imgx*100), 99, 
				null);
	}
	
	@Override
	protected void updateImage(long time) {
		if(time-imgtime>50){
			imgtime=time;
			imgx++;
		}
		if(imgx==3){
			this.count+=1;
			imgx=0;
		}
		if(this.count==3){
			this.setLive(false);
		}
	}
	
	@Override
	public ElementObj createElement(String str) {
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
			}
		}
		return this;
	}

}
