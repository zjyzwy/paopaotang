package com.scnu.show;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @说明：游戏窗体 主要实现的功能：关闭，显示，最大最小化
 * @author Administrator
 *@功能说明：需要嵌入面板，启动主线程等等
 *@窗体说明：swing awt 窗体大小，记录用户上次使用软件的窗体样式
 *
 *
 *@分析：1.面板绑定到窗体
 *		 2.监听绑定
 *		 3.游戏主线程启动
 */
public class GameJFrame extends JFrame{
	
	public static int GameX=587;//GAMEX
	public static int GameY=610;
	private JPanel jPanel=null;//正在显示的面板
	private KeyListener keyListener=null;//键盘监听
	private MouseMotionListener mouseMotionListener=null;
	private MouseListener mouseListener=null;
	private Thread thead=null;//游戏主线程
	private String state=null;
	private KeyListener last=null;
	
	public GameJFrame(){
		init();
	}
	public void init(){
		this.setSize(GameX, GameY);//设置窗体大小
		this.setTitle("泡泡堂");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭退出
		this.setLocationRelativeTo(null);//屏幕居中显示
	}
	/*窗体布局：可以将存档，读档.....button*/
	public void addButton(){
//		this.setLayout(manager);//布局格式，可以添加控件
	}
	
	
	
	
	/**
	 * 启动方法
	 */
	public void start(){
		if(jPanel!=null){
			this.add(jPanel);
		}
		if(keyListener!=null){
			this.addKeyListener(keyListener);
		}
		if(thead!=null){
			thead.start();//启动线程
		}
//		界面的刷新
		this.setVisible(true);//显示界面
//		如果jp是runnable的子类实体对象
		if(this.jPanel instanceof Runnable){
//			已经做了类型判定，强制类型转换不会出错
			new Thread((Runnable)this.jPanel).start();
		}
	}
	
	/*set注入：ssm，通过set方法注入配置文件中，将配置文件中的数据赋值为类的属性
	 * 构造注入：需要配合构造方法
	 * spring中的ioc进行对象的自动生成，管理。
	 * */
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setThead(Thread thead) {
		this.thead = thead;
	}
	public void setState(String state){
		this.state=state;
	}
	public void clear(){
		this.jPanel=null;
		this.keyListener=null;
		this.thead=null;
	}
	public JPanel get(){
		return this.jPanel;
	}
	public void remove(KeyListener key){
		this.removeKeyListener(key);
	}
}
