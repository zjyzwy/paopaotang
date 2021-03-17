package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameLoad;

public class Map extends ElementObj{
	private ElementManager em=ElementManager.getManager();;
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),this.getX(),
				this.getY(),this.getW(),this.getH(),null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] arr=str.split(",");
		ImageIcon icon=null;
		switch(arr[0]){
		case "TREE":icon=GameLoad.imgMap.get("tree");this.setName("tree");this.setH(45);break;
		case "GRASS2":icon=GameLoad.imgMap.get("grass2");this.setH(30);this.setName("map");break;
		case "HOUSE1":icon=GameLoad.imgMap.get("house1");this.setH(30);this.setName("map");break;
		case "HOUSE2":icon=GameLoad.imgMap.get("house2");this.setH(30);this.setName("map");break;
		}
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		this.setW(30);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		em.add(this.getX(), this.getY(), this.getName());
		return this;
	}
	@Override
	public Rectangle getRectangle() {
		if(this.getName()=="tree"){
			return new Rectangle(this.getX(),this.getY()+15,30,30);
		}
		else{
			return super.getRectangle();
		}	
	}

}
