package com.scnu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scnu.element.ElementObj;

/**
 *@说明 元素管理器  用于存储所有的元素，同时提供方法
 * 		 给予视图和控制获取数据
 * @author Administrator
 *@问题一：存储所有元素数据，怎么存放？
 *@问题二：管理器是视图和控制要访问，管理器就必须只能有一个，即单例模式
 */
public class ElementManager {
//	private List<Object> ListMap;
//	private List<Object> Listplay;
	/*
	 * String 作为key匹配所有元素 play->List<Object> Listplay;
	 * 							enemy->List<Object> ListMap;
	 * 枚举类型：当做map的key用来区分不一样的资源，用于资源获取
	 * List中元素的泛型应该是元素基类
	 * 所有元素都可以存放到map集合中，显示模块只需要获取到map
	 * 显示是有的界面需要显示的元素（调用元素基类的showelement())
	 */
	private String[][] map=new String[19][19];
	private Map<GameElement, List<ElementObj>> gameElements;
	//方法不够用
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
//	添加元素（多半由加载器调用）
	public void addElement(ElementObj obj,GameElement ge){
//		List<ElementObj> list = gameElements.get(ge);
//		list.add(obj);
		gameElements.get(ge).add(obj);
		
	}
//	依据key返回list集合，取出某一类元素
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	
	/*
	 * 单例模式：内存中有且只有一个实例。
	 * 饿汉模式：启动就自动加载实例
	 * 饱汉模式：需要使用时，才加载实例
	 * 
	 * 
	 * 编写方式：
	 * 1、需要一个静态的属性（定义一个常量）单例的引用
	 * 2、提供一个静态的方法（返回这个实例） return单例的引用
	 * 3.一般为防止其他人自己使用（类是可以实例化），所以会构造方法的私有化；
	 */
	
	private static ElementManager EM=null;//引用
	//synchronized线程锁->保证本方法执行中只有一个线程
	public static ElementManager getManager(){
		if(EM == null){//控制判定
			EM=new ElementManager();
		}
		return EM;
	}
	
	private ElementManager(){//私有化构造方法
		init();//实例化方法
	}
//	static { //饿汉实例化  //静态语句块是在类被加载直接执行
//		EM=new ElementManager();
//	}
	/**
	 * 本方法是为了将来可能出现的功能扩展，重写init方法准备
	 */
	public void init(){//实例化在这里完成
		//hashmap hash散列
		gameElements=new HashMap<GameElement,List<ElementObj>>();
		//将每种元素集合都放进到map中
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
	 * false表示有不能前进,true能前进
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
