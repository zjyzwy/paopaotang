package com.scnu.show;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.scnu.element.ElementObj;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

/**
 * @说明：游戏的主要面板
 * @author Administrator
 *@功能说明 主要进行元素的显示，同时进行界面的刷新（多线程）
 *
 *
 *java开发首先思考的是：做继承或者是接口实现
 *
 *@多线程刷新 1.本类实现线程接口
 *			  2.本类中定义一个内部类来实现
 */
public class GameMainJPanel extends JPanel implements Runnable{
//  联动管理器
	private ElementManager em;
	
	public GameMainJPanel(){
		init();
	}
	public void init(){
		em=ElementManager.getManager();//得到元素管理器对象
	}
	/**
	 * paint方法是进行绘画元素。
	 * 绘画时是有固定的顺序，先绘画的图片会在底层，后绘画会覆盖先绘画的
	 * 约定：本方法只使用一次
	 */
	@Override		//	用于绘画的   画笔 专门用于绘画的
	public void paint(Graphics g) {
		super.paint(g);
//		map key-value  key是无序不可重复的。
//		set  和map的key一样无序不可重复的
		Map<GameElement,List<ElementObj>> all=em.getGameElements();
//		GameElement.values();//隐藏方法（默认方法） 返回值是一个数组，顺序就是定义枚举的顺序
		for(GameElement ge:GameElement.values()){
			List<ElementObj> list=all.get(ge);
			for(int i=0;i<list.size();i++){
				ElementObj obj=list.get(i);
				obj.showElement(g);//调用每个类的show方法完成自己的显示
			}
		}
		
		
		
//		Set<GameElement> set=all.keySet();//得到所有的key集合
//		for(GameElement ge:set){//迭代器
//			List<ElementObj> list=all.get(ge);
//			for(int i=0;i<list.size();i++){
//				ElementObj obj=list.get(i);
//				obj.showElement(g);//调用每个类的show方法完成自己的显示
//			}
//		}
		
	}
	@Override
	public void run() {//接口实现
		while(true){
			
			this.repaint();
//			一般情况下，多线程都会使用一个休眠，控制速度
			try{
				Thread.sleep(10);//休眠10毫秒1秒刷新20次
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}
