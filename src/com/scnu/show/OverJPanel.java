package com.scnu.show;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class OverJPanel extends JPanel {
	ImageIcon icon=new ImageIcon("image/GameOvers.png");
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		super.paint(g);
		g.drawImage(icon.getImage(),0,0, 590, 590,null);
	}
//	@Override
//	public void run() {
//		while(true){
//			
//			this.repaint();
////			一般情况下，多线程都会使用一个休眠，控制速度
//			try{
//				Thread.sleep(10);//休眠10毫秒1秒刷新20次
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//		
//	}

}
