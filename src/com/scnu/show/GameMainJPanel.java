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
 * @˵������Ϸ����Ҫ���
 * @author Administrator
 *@����˵�� ��Ҫ����Ԫ�ص���ʾ��ͬʱ���н����ˢ�£����̣߳�
 *
 *
 *java��������˼�����ǣ����̳л����ǽӿ�ʵ��
 *
 *@���߳�ˢ�� 1.����ʵ���߳̽ӿ�
 *			  2.�����ж���һ���ڲ�����ʵ��
 */
public class GameMainJPanel extends JPanel implements Runnable{
//  ����������
	private ElementManager em;
	
	public GameMainJPanel(){
		init();
	}
	public void init(){
		em=ElementManager.getManager();//�õ�Ԫ�ع���������
	}
	/**
	 * paint�����ǽ��л滭Ԫ�ء�
	 * �滭ʱ���й̶���˳���Ȼ滭��ͼƬ���ڵײ㣬��滭�Ḳ���Ȼ滭��
	 * Լ����������ֻʹ��һ��
	 */
	@Override		//	���ڻ滭��   ���� ר�����ڻ滭��
	public void paint(Graphics g) {
		super.paint(g);
//		map key-value  key�����򲻿��ظ��ġ�
//		set  ��map��keyһ�����򲻿��ظ���
		Map<GameElement,List<ElementObj>> all=em.getGameElements();
//		GameElement.values();//���ط�����Ĭ�Ϸ����� ����ֵ��һ�����飬˳����Ƕ���ö�ٵ�˳��
		for(GameElement ge:GameElement.values()){
			List<ElementObj> list=all.get(ge);
			for(int i=0;i<list.size();i++){
				ElementObj obj=list.get(i);
				obj.showElement(g);//����ÿ�����show��������Լ�����ʾ
			}
		}
		
		
		
//		Set<GameElement> set=all.keySet();//�õ����е�key����
//		for(GameElement ge:set){//������
//			List<ElementObj> list=all.get(ge);
//			for(int i=0;i<list.size();i++){
//				ElementObj obj=list.get(i);
//				obj.showElement(g);//����ÿ�����show��������Լ�����ʾ
//			}
//		}
		
	}
	@Override
	public void run() {//�ӿ�ʵ��
		while(true){
			
			this.repaint();
//			һ������£����̶߳���ʹ��һ�����ߣ������ٶ�
			try{
				Thread.sleep(10);//����10����1��ˢ��20��
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}
