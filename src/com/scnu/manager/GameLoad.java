package com.scnu.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.scnu.element.ElementObj;

/**
 * @˵�� �����������ߣ��û���ȡ�����ļ��Ĺ��ߣ������࣬����ṩ����satic����
 * @author Administrator
 *
 */
public class GameLoad {
//	�õ���Դ������
	private static ElementManager em=ElementManager.getManager();
	
	
//	ͼƬ����,ʹ��map�����д洢  ö����������ƶ�
//	�Ͻ�ͷmap
	public static Map<String,ImageIcon> imgMap=new HashMap<>();
	public static Map<String,ImageIcon> EnemyMap;
	
	
	
//	Collections ���ڼ�������Ĺ����࣬����Ϊ���е����Ͷ���ļ�¼��������
//	����ֻ��ΪCollections������
	
//	�û���ȡ�ļ�����
	private static Properties pro= new Properties();
	
	/**
	 * @˵�� �����ͼid�м��ط��������ļ������Բ�����ͼ�ļ����ƣ������ļ�
	 * @param mapId �ļ���ţ��ļ�id
	 */
	public static void MapLoad(int mapId){
		GameLoad.loadObj();
//		�õ��ļ�·��
		String mapName="com/scnu/text/"+mapId+".map";
//		ʹ��io������ȡ�ļ�����
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps=classLoader.getResourceAsStream(mapName);
		if(maps==null){
			System.out.println("�����ļ��쳣�������¶�ȡ");
			return;
		}
		
		try {
//			�Ժ��õĶ���xml��json
			pro.clear();
			pro.load(maps);
//			����ֱ�Ӷ�̬�Ļ�ȡ���е�key����key�Ϳ��Ի�ȡvalue
			Enumeration<?> names=pro.propertyNames();
			while(names.hasMoreElements()){//��ȡ�������
//				�����ĵ�������һ�����⣬һ�ε���һ��Ԫ��
				String key=names.nextElement().toString();
//				System.out.println(pro.getProperty(key));
	//			�Ϳ����Զ��Ĵ����ͼ��ص�ͼ
				String[] arrs=pro.getProperty(key).split(":");
				for(int i=0;i<arrs.length;i++){
					ElementObj obj=null;
					if(key.equals("BOX1") || key.equals("BOX2")|| key.equals("BOX3")|| key.equals("BOX4")|| key.equals("BOX5")|| key.equals("BOX6")){
						obj=GameLoad.getObj("box");
						ElementObj element=obj.createElement(key+","+arrs[i]);
						em.addElement(element, GameElement.BOX);
					}
					else if(key.equals("GRASS")){
						obj=GameLoad.getObj("background");
						ElementObj element=obj.createElement(key+","+arrs[i]);
						em.addElement(element, GameElement.BACKGROUND);
					}else{
						obj=GameLoad.getObj("map");
						ElementObj element=obj.createElement(key+","+arrs[i]);
						em.addElement(element, GameElement.MAPS);
					}
				}
				
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @˵�� ����ͼƬ����
	 * ����ͼƬ �����ͼƬ֮���һ��·������
	 */
	public static void loadImg(){//���Դ���������Ϊ��ͬ�Ĺؿ��в�ͬ��ͼƬ
		String texturl="com/scnu/text/GameData.pro";//�ļ��������Ը��й���
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream texts=classLoader.getResourceAsStream(texturl);
//		imgMap���ڴ������
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				imgMap.put(o.toString(),new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * �������
	 */
	public static void LoadPlay(){
		loadObj();
		String playStr="32,32,paopao1,1";//û�зŵ������ļ���
		ElementObj obj=getObj("play");//��Ϊ�����ַ�������ȡ��
//		����ַ�����key
		ElementObj play=obj.createElement(playStr);
//		ElementObj play=new Play().createElement(playStr);
//		������ʹ���ʹ���֮�����϶� ����ֱ�ӽӿڻ����ǳ�����ľͿ��Ի�ȡ��ʵ�����
		em.addElement(play,GameElement.PLAY);
		String str="482,482,paopao2,2";
		ElementObj obj2=getObj("play");
		ElementObj play2=obj2.createElement(str);
		em.addElement(play2,GameElement.PLAY);
	}
	
	public static ElementObj getObj(String str){
		try {
			Class<?> class1=objMap.get(str);
			Object newInstance=class1.newInstance();
			
			if(newInstance instanceof ElementObj){
				return (ElementObj)newInstance;//�������ͺ�new Play()�ȼ�
//				�½�����һ����GamePlay����
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * ��չ��ʹ�������ļ���ʵ��������ͨ���̶���key���ַ�����ʵ������
	 */
	private static Map<String,Class<?>> objMap=new HashMap<>();
	public static void loadObj(){
		String texturl="com/scnu/text/obj.pro";//�ļ��������Ը��й���
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream texts=classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set=pro.keySet();
			for(Object o:set){
				String ClassUrl=pro.getProperty(o.toString());
//				ʹ�÷���ķ�ʽֱ�ӽ�����л�ȡ
				Class<?> forName = Class.forName(ClassUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
//	���ڲ���
//	public static void main(String[] args) {
//		MapLoad(5);
//	}
	
	
}
