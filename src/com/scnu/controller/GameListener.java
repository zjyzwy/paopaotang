package com.scnu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.scnu.element.ElementObj;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

/**
 * @˵���������࣬���ڼ����û��Ĳ��� KeyListener
 * @author Administrator
 *
 */
public class GameListener implements KeyListener{

	private ElementManager em=ElementManager.getManager();
	
	/*�ܷ�ͨ��һ����������¼���а��µļ�������ظ���������ֱ�ӽ���
	 * ͬʱ����һ�ΰ��£���¼�������У��ڶ����ж�������Ϊ����
	 * 		�ɿ���ֱ��ɾ�������еļ�¼��
	 * set����*/
	
	private Set<Integer> set=new HashSet<Integer>();
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	/**
	 * ���£���37����38����39����40
	 * ʵ�����ǵ��ƶ�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
//		�õ���ҵļ���
		
		int  key=e.getKeyCode();
		if(set.contains(key)){//�ж��������Ƿ���ڣ��������������
//			��������˾�ֱ�ӽ�������
			return;
		}
		set.add(key);
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play){
			obj.keyClick(true,e.getKeyCode(),obj.getName());
		}
	}
	/**
	 * �ɿ�
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(!set.contains(e.getKeyCode())){//�����������ڣ���ֹͣ
			return;
		}//���ڣ��Ѿ��������������
		set.remove(e.getKeyCode());//�Ƴ�����
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play){
			obj.keyClick(false,e.getKeyCode(),obj.getName());
		}
	}

}
