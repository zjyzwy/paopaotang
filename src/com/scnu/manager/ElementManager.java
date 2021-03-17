package com.scnu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scnu.element.ElementObj;

/**
 *@˵�� Ԫ�ع�����  ���ڴ洢���е�Ԫ�أ�ͬʱ�ṩ����
 * 		 ������ͼ�Ϳ��ƻ�ȡ����
 * @author Administrator
 *@����һ���洢����Ԫ�����ݣ���ô��ţ�
 *@�����������������ͼ�Ϳ���Ҫ���ʣ��������ͱ���ֻ����һ����������ģʽ
 */
public class ElementManager {
//	private List<Object> ListMap;
//	private List<Object> Listplay;
	/*
	 * String ��Ϊkeyƥ������Ԫ�� play->List<Object> Listplay;
	 * 							enemy->List<Object> ListMap;
	 * ö�����ͣ�����map��key�������ֲ�һ������Դ��������Դ��ȡ
	 * List��Ԫ�صķ���Ӧ����Ԫ�ػ���
	 * ����Ԫ�ض����Դ�ŵ�map�����У���ʾģ��ֻ��Ҫ��ȡ��map
	 * ��ʾ���еĽ�����Ҫ��ʾ��Ԫ�أ�����Ԫ�ػ����showelement())
	 */
	private String[][] map=new String[19][19];
	private Map<GameElement, List<ElementObj>> gameElements;
	//����������
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
//	���Ԫ�أ�����ɼ��������ã�
	public void addElement(ElementObj obj,GameElement ge){
//		List<ElementObj> list = gameElements.get(ge);
//		list.add(obj);
		gameElements.get(ge).add(obj);
		
	}
//	����key����list���ϣ�ȡ��ĳһ��Ԫ��
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	
	/*
	 * ����ģʽ���ڴ�������ֻ��һ��ʵ����
	 * ����ģʽ���������Զ�����ʵ��
	 * ����ģʽ����Ҫʹ��ʱ���ż���ʵ��
	 * 
	 * 
	 * ��д��ʽ��
	 * 1����Ҫһ����̬�����ԣ�����һ������������������
	 * 2���ṩһ����̬�ķ������������ʵ���� return����������
	 * 3.һ��Ϊ��ֹ�������Լ�ʹ�ã����ǿ���ʵ�����������Իṹ�췽����˽�л���
	 */
	
	private static ElementManager EM=null;//����
	//synchronized�߳���->��֤������ִ����ֻ��һ���߳�
	public static ElementManager getManager(){
		if(EM == null){//�����ж�
			EM=new ElementManager();
		}
		return EM;
	}
	
	private ElementManager(){//˽�л����췽��
		init();//ʵ��������
	}
//	static { //����ʵ����  //��̬���������౻����ֱ��ִ��
//		EM=new ElementManager();
//	}
	/**
	 * ��������Ϊ�˽������ܳ��ֵĹ�����չ����дinit����׼��
	 */
	public void init(){//ʵ�������������
		//hashmap hashɢ��
		gameElements=new HashMap<GameElement,List<ElementObj>>();
		//��ÿ��Ԫ�ؼ��϶��Ž���map��
//		gameElements.put(GameElement.PLAY, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.MAPS, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.ENEMY, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.BOSS, new ArrayList<ElementObj>());
		for(GameElement ge :GameElement.values()){
			gameElements.put(ge,new ArrayList<ElementObj>());
		}
	}
	public void add(int x,int y,String name){
		if(name=="tree"){
			y=y+15;
		}
		map[y/30][x/30]=name;
	}
	public void remove(int x,int y){
		map[y/30][x/30]=null;
	}
	/**
	 * false��ʾ�в���ǰ��,true��ǰ��
	 * @param x
	 * @param y
	 * @return
	 */
	public String find(int x,int y){
		return map[y/30][x/30];
		
	}
//	public void show(){
//		for(int i=0;i<19;i++){
//			for(int j=0;j<19;j++){
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
}
