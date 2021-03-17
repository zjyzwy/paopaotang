package com.scnu.show;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @˵������Ϸ���� ��Ҫʵ�ֵĹ��ܣ��رգ���ʾ�������С��
 * @author Administrator
 *@����˵������ҪǶ����壬�������̵߳ȵ�
 *@����˵����swing awt �����С����¼�û��ϴ�ʹ������Ĵ�����ʽ
 *
 *
 *@������1.���󶨵�����
 *		 2.������
 *		 3.��Ϸ���߳�����
 */
public class GameJFrame extends JFrame{
	
	public static int GameX=587;//GAMEX
	public static int GameY=610;
	private JPanel jPanel=null;//������ʾ�����
	private KeyListener keyListener=null;//���̼���
	private MouseMotionListener mouseMotionListener=null;
	private MouseListener mouseListener=null;
	private Thread thead=null;//��Ϸ���߳�
	private String state=null;
	private KeyListener last=null;
	
	public GameJFrame(){
		init();
	}
	public void init(){
		this.setSize(GameX, GameY);//���ô����С
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùر��˳�
		this.setLocationRelativeTo(null);//��Ļ������ʾ
	}
	/*���岼�֣����Խ��浵������.....button*/
	public void addButton(){
//		this.setLayout(manager);//���ָ�ʽ��������ӿؼ�
	}
	
	
	
	
	/**
	 * ��������
	 */
	public void start(){
		if(jPanel!=null){
			this.add(jPanel);
		}
		if(keyListener!=null){
			this.addKeyListener(keyListener);
		}
		if(thead!=null){
			thead.start();//�����߳�
		}
//		�����ˢ��
		this.setVisible(true);//��ʾ����
//		���jp��runnable������ʵ�����
		if(this.jPanel instanceof Runnable){
//			�Ѿ����������ж���ǿ������ת���������
			new Thread((Runnable)this.jPanel).start();
		}
	}
	
	/*setע�룺ssm��ͨ��set����ע�������ļ��У��������ļ��е����ݸ�ֵΪ�������
	 * ����ע�룺��Ҫ��Ϲ��췽��
	 * spring�е�ioc���ж�����Զ����ɣ�����
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
