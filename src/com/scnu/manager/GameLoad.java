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
 * @说明 加载器（工具：用户读取配置文件的工具）工具类，大多提供的是satic方法
 * @author Administrator
 *
 */
public class GameLoad {
//	得到资源管理器
	private static ElementManager em=ElementManager.getManager();
	
	
//	图片集合,使用map来进行存储  枚举类型配合移动
//	合金弹头map
	public static Map<String,ImageIcon> imgMap=new HashMap<>();
	public static Map<String,ImageIcon> EnemyMap;
	
	
	
//	Collections 用于集合排序的工具类，可以为所有的类型对象的记录进行排序
//	排序只能为Collections的子类
	
//	用户读取文件的类
	private static Properties pro= new Properties();
	
	/**
	 * @说明 传入地图id有加载方法依据文件规则自产生地图文件名称，加载文件
	 * @param mapId 文件编号，文件id
	 */
	public static void MapLoad(int mapId){
		GameLoad.loadObj();
//		得到文件路径
		String mapName="com/scnu/text/"+mapId+".map";
//		使用io流来获取文件对象
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps=classLoader.getResourceAsStream(mapName);
		if(maps==null){
			System.out.println("配置文件异常，请重新读取");
			return;
		}
		
		try {
//			以后用的都是xml和json
			pro.clear();
			pro.load(maps);
//			可以直接动态的获取所有的key，有key就可以获取value
			Enumeration<?> names=pro.propertyNames();
			while(names.hasMoreElements()){//获取是无序的
//				这样的迭代都有一个问题，一次迭代一个元素
				String key=names.nextElement().toString();
//				System.out.println(pro.getProperty(key));
	//			就可以自动的创建和加载地图
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @说明 加载图片代码
	 * 加载图片 代码和图片之间差一个路径问题
	 */
	public static void loadImg(){//可以带参数，因为不同的关卡有不同的图片
		String texturl="com/scnu/text/GameData.pro";//文件命名可以更有规律
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream texts=classLoader.getResourceAsStream(texturl);
//		imgMap用于存放数据
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				imgMap.put(o.toString(),new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 加载玩家
	 */
	public static void LoadPlay(){
		loadObj();
		String playStr="32,32,paopao1,1";//没有放到配置文件中
		ElementObj obj=getObj("play");//因为依靠字符串来读取的
//		这个字符串是key
		ElementObj play=obj.createElement(playStr);
//		ElementObj play=new Play().createElement(playStr);
//		解耦，降低代码和代码之间的耦合度 可以直接接口或者是抽象父类的就可以获取到实体对象
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
				return (ElementObj)newInstance;//这个对象就和new Play()等价
//				新建立了一个叫GamePlay的类
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 扩展：使用配置文件来实例化对象，通过固定的key（字符串来实例化）
	 */
	private static Map<String,Class<?>> objMap=new HashMap<>();
	public static void loadObj(){
		String texturl="com/scnu/text/obj.pro";//文件命名可以更有规律
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream texts=classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set=pro.keySet();
			for(Object o:set){
				String ClassUrl=pro.getProperty(o.toString());
//				使用反射的方式直接将类进行获取
				Class<?> forName = Class.forName(ClassUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
//	用于测试
//	public static void main(String[] args) {
//		MapLoad(5);
//	}
	
	
}
