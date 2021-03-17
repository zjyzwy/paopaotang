package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @说明:所有元素的基类
 * @author Administrator
 *
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private boolean live=true;//生存状态，用true代表存在，false代表死亡
						 //可以采用枚举值来定义这个（生存，死亡，隐身，无敌）
//	注明：当重新定义一个用判定状态的变量，需要思考：1.初始化；2.值的改变；3.值的判定
//	还有....各种必要的状态值，例如：是否生存。。。
	private String name;
//	true代表可以被破坏的
	private boolean Destructible=false;
	private String type;
	private boolean reverse=false;
	private String setby;
	private boolean inpao=false;
	private int unshuilei=0;//用于防水雷
	private boolean stop=false;
	public ElementObj(){//没有作用，为了在继承时不报错
	}
	/**
	 * @说明：带参的构造方法；可以由子类传输数据到父类
	 * @param x		左上角X坐标
	 * @param y		左上角Y坐标
	 * @param w		宽度
	 * @param h		高度
	 * @param icon	图片
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	/**
	 * 抽象方法 显示元素
	 * @param g 画笔用于进行绘画
	 */
	public abstract void showElement(Graphics g);
	
	/**
	 * @说明 使用父类定义接收键盘事件的方法
	 * 		 只有需要实现键盘监听的子类，重写这个方法（约定）
	 * @说明 方式2 使用接口的方式：使用接口方式需要在监听类进行类型转换
	 * 
	 * @param bl  点击的类型 true代表按下，false代表松开
	 * @param key 代表触发的键盘的code值
	 * @扩展 本方法是否可以分为两个方法？一个接收按下，一个用于接收松开
	 */
	public void keyClick(boolean bl,int key,String name){//这个方法不是强制必须实现的。
		
	}
	/**
	 * @说明 移动方法；需要移动的子类，请实现这个方法
	 */
	protected void move(){
		
	}
	
	/**
	 * @设计模式 模板模式；在模板模式中定义 对象执行方法的先后顺序，由子类选择性重写方法
	 * 			1.移动  2.换装  3.子弹发射
	 */
	
	public final void model(long gameTime){
//		先换装
		updateImage(gameTime);
//		再移动
		move();
//		再发射子弹
		add(gameTime);
	}
//	long ... aaa 不定长的数组，可以向这个方法传输多个参数
	protected void updateImage(long time){}
	protected void add(long gameTime) {}
	
//	死亡方法  给子类继承
	public void die(){//死亡也是一个对象
		
	}
	
	
	public ElementObj createElement(String str){
		return null;
	}
	
	
	/**
	 * @说明 本方法返回 元素的碰撞矩形对象（实时返回）
	 * @return
	 */
	public Rectangle getRectangle(){
		return new Rectangle(x,y,w,h);
	}
	/**
	 * @说明 碰撞方法
	 * 一个是this对象，一个传入值obj
	 * @param obj
	 * @return boolean 返回true，说明有碰撞，返回false说明没有碰撞
	 */
	public boolean pk(ElementObj obj){
		
		return this.getRectangle().intersects(obj.getRectangle());
	}
	public void back(){
		
	}
//	泡泡爆炸用
	public boolean isPlay(ElementObj obj){
		
		return false;
	}
	public boolean Destroy(ElementObj obj){
		return false;
	}
	
//	人物吃道具用
	public void eat(ElementObj obj){
		
	}
	
//	吃到水雷的判断方法
	public void hurt(){
		
	}
//	吃到遥控器后的方法
	public void Stop(){
		
	}
//	用于泡泡爆炸时，附近有泡泡就引爆泡泡。
	public boolean isBoom(ElementObj obj){
		return false;
	}
	
	public void PKBoom(){
		
	}
	/**
	 * 只要是VO类就要为属性生成get和set方法
	 */
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getDestructible(){
		return this.Destructible;
	}
	public void setDestructible(boolean b){
		this.Destructible=b;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isReverse() {
		return reverse;
	}
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
	public String getSetby() {
		return setby;
	}
	public void setSetby(String setby) {
		this.setby = setby;
	}
	public int getUnshuilei() {
		return unshuilei;
	}
	public void setUnshuilei(int unshuilei) {
		this.unshuilei = unshuilei;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public boolean isInpao() {
		return inpao;
	}
	public void setInpao(boolean inpao) {
		this.inpao = inpao;
	}
	
	
	
	
}
