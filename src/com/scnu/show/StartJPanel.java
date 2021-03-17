package com.scnu.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartJPanel extends JPanel{
	ImageIcon icon=new ImageIcon("image/GameStart.png");
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(icon.getImage(),0,0, 590, 590,null);
		g.setFont(new Font("黑体",Font.BOLD,30));
		g.setColor(Color.ORANGE);
		g.drawString("点击空格键开始游戏",180,200);
	}

	
}
