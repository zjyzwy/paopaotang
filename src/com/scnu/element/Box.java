package com.scnu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;

public class Box extends ElementObj{
	private ElementManager em=ElementManager.getManager();
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),this.getX(),
				this.getY(),this.getW(),this.getH(),null);
	}
	@Override
	public ElementObj createElement(String str) {
		this.setName("box");
		String[] arr=str.split(",");
		ImageIcon icon=null;
		switch(arr[0]){
		case "BOX1":icon=GameLoad.imgMap.get("box1");break;
		case "BOX2":icon=GameLoad.imgMap.get("box2");break;
		case "BOX6":icon=GameLoad.imgMap.get("box6");break;
		case "BOX4":icon=GameLoad.imgMap.get("box4");break;
		case "BOX5":icon=GameLoad.imgMap.get("box5");break;
		}
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		this.setH(30);
		this.setW(30);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		em.add(this.getX(), this.getY(), this.getName());
		this.setDestructible(true);
		return this;
	}
	
	@Override
	public void die() {
		GameLoad.loadObj();
		Random ran=new Random();
		int x=ran.nextInt(9)+1;//设置出现道具的概率
//		int x=10;
		String str="x:"+this.getX()+",y:"+this.getY()+",t:";
		if(x>=5){
			ElementObj obj=null;
			int y=ran.nextInt(8)+1;
			switch(y){
			case 1:obj=GameLoad.getObj("prop");str=str+"rguitou";break;
			case 2:obj=GameLoad.getObj("prop");str=str+"guitou";break;
			case 3:obj=GameLoad.getObj("prop");str=str+"jieyao";break;
			case 4:obj=GameLoad.getObj("prop");str=str+"pao";break;
			case 5:obj=GameLoad.getObj("prop");str=str+"shuilei";break;
			case 6:obj=GameLoad.getObj("prop");str=str+"yaokongqi";break;
			case 7:obj=GameLoad.getObj("prop");str=str+"yaoping";break;
			case 8:obj=GameLoad.getObj("prop");str=str+"Dka";break;
			}
			ElementObj element =obj.createElement(str);
			ElementManager.getManager().addElement(element, GameElement.PROP);
		}
	}
	

}
