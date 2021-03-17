package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @˵��:����Ԫ�صĻ���
 * @author Administrator
 *
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private boolean live=true;//����״̬����true������ڣ�false��������
						 //���Բ���ö��ֵ��������������棬�����������޵У�
//	ע���������¶���һ�����ж�״̬�ı�������Ҫ˼����1.��ʼ����2.ֵ�ĸı䣻3.ֵ���ж�
//	����....���ֱ�Ҫ��״ֵ̬�����磺�Ƿ����档����
	private String name;
//	true������Ա��ƻ���
	private boolean Destructible=false;
	private String type;
	private boolean reverse=false;
	private String setby;
	private boolean inpao=false;
	private int unshuilei=0;//���ڷ�ˮ��
	private boolean stop=false;
	public ElementObj(){//û�����ã�Ϊ���ڼ̳�ʱ������
	}
	/**
	 * @˵�������εĹ��췽�������������ഫ�����ݵ�����
	 * @param x		���Ͻ�X����
	 * @param y		���Ͻ�Y����
	 * @param w		���
	 * @param h		�߶�
	 * @param icon	ͼƬ
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
	 * ���󷽷� ��ʾԪ��
	 * @param g �������ڽ��л滭
	 */
	public abstract void showElement(Graphics g);
	
	/**
	 * @˵�� ʹ�ø��ඨ����ռ����¼��ķ���
	 * 		 ֻ����Ҫʵ�ּ��̼��������࣬��д���������Լ����
	 * @˵�� ��ʽ2 ʹ�ýӿڵķ�ʽ��ʹ�ýӿڷ�ʽ��Ҫ�ڼ������������ת��
	 * 
	 * @param bl  ��������� true�����£�false�����ɿ�
	 * @param key �������ļ��̵�codeֵ
	 * @��չ �������Ƿ���Է�Ϊ����������һ�����հ��£�һ�����ڽ����ɿ�
	 */
	public void keyClick(boolean bl,int key,String name){//�����������ǿ�Ʊ���ʵ�ֵġ�
		
	}
	/**
	 * @˵�� �ƶ���������Ҫ�ƶ������࣬��ʵ���������
	 */
	protected void move(){
		
	}
	
	/**
	 * @���ģʽ ģ��ģʽ����ģ��ģʽ�ж��� ����ִ�з������Ⱥ�˳��������ѡ������д����
	 * 			1.�ƶ�  2.��װ  3.�ӵ�����
	 */
	
	public final void model(long gameTime){
//		�Ȼ�װ
		updateImage(gameTime);
//		���ƶ�
		move();
//		�ٷ����ӵ�
		add(gameTime);
	}
//	long ... aaa �����������飬�����������������������
	protected void updateImage(long time){}
	protected void add(long gameTime) {}
	
//	��������  ������̳�
	public void die(){//����Ҳ��һ������
		
	}
	
	
	public ElementObj createElement(String str){
		return null;
	}
	
	
	/**
	 * @˵�� ���������� Ԫ�ص���ײ���ζ���ʵʱ���أ�
	 * @return
	 */
	public Rectangle getRectangle(){
		return new Rectangle(x,y,w,h);
	}
	/**
	 * @˵�� ��ײ����
	 * һ����this����һ������ֵobj
	 * @param obj
	 * @return boolean ����true��˵������ײ������false˵��û����ײ
	 */
	public boolean pk(ElementObj obj){
		
		return this.getRectangle().intersects(obj.getRectangle());
	}
	public void back(){
		
	}
//	���ݱ�ը��
	public boolean isPlay(ElementObj obj){
		
		return false;
	}
	public boolean Destroy(ElementObj obj){
		return false;
	}
	
//	����Ե�����
	public void eat(ElementObj obj){
		
	}
	
//	�Ե�ˮ�׵��жϷ���
	public void hurt(){
		
	}
//	�Ե�ң������ķ���
	public void Stop(){
		
	}
//	�������ݱ�ըʱ�����������ݾ��������ݡ�
	public boolean isBoom(ElementObj obj){
		return false;
	}
	
	public void PKBoom(){
		
	}
	/**
	 * ֻҪ��VO���ҪΪ��������get��set����
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
