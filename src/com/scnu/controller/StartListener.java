package com.scnu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.scnu.show.GameJFrame;
import com.scnu.show.GameMainJPanel;

public class StartListener implements KeyListener{
	private JPanel jPanel=null;
	public StartListener() {}
	public StartListener(JPanel jpanel){
		this.jPanel=jpanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getKeyCode()==32){
		GameJFrame gj=(GameJFrame)SwingUtilities.getRoot(this.jPanel);
		gj.clear();
		gj.remove(this);
		GameMainJPanel jp=new GameMainJPanel();
//		ʵ��������
		GameListener listener=new GameListener();
//		ʵ�������߳�
		GameThread th=new GameThread();
		th.setJpanel(jp);
		gj.setjPanel(jp);
		gj.setKeyListener(listener);
		gj.setThead(th);
		gj.start();
		}
	}
	public void setjPanel(JPanel jpanel){
		this.jPanel=jpanel;
	}

}
