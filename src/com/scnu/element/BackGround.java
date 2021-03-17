package com.scnu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.manager.GameLoad;

public class BackGround extends ElementObj{

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),this.getX(),
				this.getY(),this.getW(),this.getH(),null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] arr=str.split(",");
		ImageIcon icon=GameLoad.imgMap.get("grass");
		this.setH(30);
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		this.setW(30);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		return this;
	}
}
