package com.scnu.game;

import com.scnu.controller.StartListener;
import com.scnu.show.GameJFrame;
import com.scnu.show.StartJPanel;

public class GameStart {
/**
 * �����Ψһ���
 */
	public static void main(String[] args){
		GameJFrame gj=new GameJFrame();
		/**
		 * ʵ������壬ע�뵽jframe��
		 */
		StartJPanel sj=new StartJPanel();
		StartListener listen=new StartListener(sj);
//		GameMainJPanel jp=new GameMainJPanel();
//		ʵ��������
//		GameListener listener=new GameListener();
//		ʵ�������߳�
//		GameThread th=new GameThread();
		
//		ע��
		gj.setjPanel(sj);
		gj.setKeyListener(listen);
		gj.start();
		
		
	}
	
}


/**
 * 1.������Ϸ�������Ϸ�������ļ���ʽ���ļ���ȡ��ʽ(load��ʽ)
 * 2.�����Ϸ��ɫ��������Ϸ���󣨳�����ڻ���ļ̳У�
 * 3.����pojo�ࣨVO��.....
 * 4.����ķ������ڸ�������д��������಻֧�֣����Բ����޸ĸ��ࣩ
 * 5.������ã���ɶ����load��add��manage��
 * 6.��ײ�ȵ�ϸ�ڴ��롣
 */
