package com.scnu.show;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class OverJPanel extends JPanel {
	ImageIcon icon=new ImageIcon("image/GameOvers.png");
	@Override
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paint(g);
		g.drawImage(icon.getImage(),0,0, 590, 590,null);
	}
//	@Override
//	public void run() {
//		while(true){
//			
//			this.repaint();
////			һ������£����̶߳���ʹ��һ�����ߣ������ٶ�
//			try{
//				Thread.sleep(10);//����10����1��ˢ��20��
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//		
//	}

}
