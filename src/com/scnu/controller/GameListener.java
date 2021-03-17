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
 * @说明：监听类，用于监听用户的操作 KeyListener
 * @author Administrator
 *
 */
public class GameListener implements KeyListener{

	private ElementManager em=ElementManager.getManager();
	
	/*能否通过一个集合来记录所有按下的键，如果重复触发，就直接结束
	 * 同时，第一次按下，记录到集合中，第二次判定集合中为否有
	 * 		松开就直接删除集合中的记录。
	 * set集合*/
	
	private Set<Integer> set=new HashSet<Integer>();
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	/**
	 * 按下：左37，上38，右39，下40
	 * 实现主角的移动
	 */
	@Override
	public void keyPressed(KeyEvent e) {
//		拿到玩家的集合
		
		int  key=e.getKeyCode();
		if(set.contains(key)){//判定集合中是否存在（包含）这个对象
//			如果包含了就直接结束方法
			return;
		}
		set.add(key);
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play){
			obj.keyClick(true,e.getKeyCode(),obj.getName());
		}
	}
	/**
	 * 松开
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(!set.contains(e.getKeyCode())){//如果这个不存在，就停止
			return;
		}//存在（已经按过这个案件）
		set.remove(e.getKeyCode());//移除数据
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play){
			obj.keyClick(false,e.getKeyCode(),obj.getName());
		}
	}

}
