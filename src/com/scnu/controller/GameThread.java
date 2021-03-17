package com.scnu.controller;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.scnu.element.ElementObj;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;
import com.scnu.show.GameJFrame;
import com.scnu.show.OverJPanel;

/**
 * @˵�� ��Ϸ�����̣߳����ڿ�����Ϸ���أ���Ϸ�ؿ�����Ϸ�����Զ���
 * 		 ��Ϸ�ж�		��Ϸ��ͼ�л� ��Դ�ͷź����¶�ȡ������
 * @author Administrator
 *@�̳� ʹ�ü̳еķ�ʽʵ�ֶ��̣߳�һ�㽨��ʹ�ýӿ�ʵ�֣�
 */
public class GameThread extends Thread{
	private JPanel jpanel=null;
	private boolean state=true;
	private ElementManager em;
	public GameThread(){
		em=ElementManager.getManager();
	}
	@Override
	public void run() {//��Ϸ��run����  ���߳�
		while(state){//��չ�����Խ�true��Ϊһ���������ڿ��ƽ���
//		��Ϸ��ʼǰ  ����������������Ϸ��Դ��������Դ��
			gameLoad();
//		��Ϸ����ʱ  ��Ϸ������
			gameRun();
//		��Ϸ��������  ��Ϸ��Դ���գ�������Դ��
			gameOver();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
//		System.out.println("over");
	}
	/**
	 * ��Ϸ�ļ���
	 */
	private void gameLoad() {
		GameLoad.loadImg();//����ͼƬ
		GameLoad.MapLoad(1);//����Ϊ������ÿһ�ض����¼���   ���ص�ͼ
//		��������
		GameLoad.LoadPlay();//���Դ�������������������
//		���ص���NPC��
		
		
//		ȫ��������ɣ���Ϸ��ʼ
	}
	/**
	 * @˵�� ��Ϸ����ʱ
	 * @����˵�� ��Ϸ��������Ҫ�������飺1.�Զ�����ҵ��ƶ�����ײ������
	 * 								   2.��Ԫ�ص����ӣ�NPC����������ֵ��ߣ�
	 * 								   3.��ͣ�ȵȡ�����
	 * ��ʵ�����ǵ��ƶ�
	 */
//	private long gameTime=0L;
	private void gameRun() {
		long gameTime=0L;//��int���;Ϳ���
		while(state){//Ԥ����չtrue���Ա�Ϊ���������ڿ��ƹؿ�������
			int count=1;
			Map<GameElement,List<ElementObj>> all=em.getGameElements();
			List<ElementObj> files=em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> BOX=em.getElementsByKey(GameElement.BOX);
			List<ElementObj> boom=em.getElementsByKey(GameElement.BOOM);
			List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> map=em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> prop=em.getElementsByKey(GameElement.PROP);
			List<ElementObj> trap=em.getElementsByKey(GameElement.TRAP);
			moveAndUpdate(all,gameTime);//��ϷԪ���Զ�������ʹ��
			
			
			PlayPKBox(play,BOX);
			PlayPKMap(play,map);
			PlayPKProp(play,prop);
			PlayPKFile(play,files);
			judge(play,boom);
			judge2(prop,boom);
			judge2(BOX,boom);
			Booms(boom);
			if(play.size()==1 || play.size()==0){
				if(trap.size()==0){
					count=0;
				}
			}
			gameTime++;//Ψһ��ʱ�����
			if(count==0){
				this.state=false;
			}
			
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
//	public void ElementPK(List<ElementObj> listA,List<ElementObj> listB){
//		
////		ʹ��ѭ������һ��һ���ж������Ϊ�棬�������������������״̬
//		for(int i=0;i<listA.size();i++){
//			ElementObj enemy=listA.get(i);
//			for(int j=0;j<listB.size();j++){
//				ElementObj file=listB.get(j);
//				if(enemy.pk(file)){
////					��setLive��false����Ϊһ���ܹ����ķ����������Դ�����һ������Ĺ�������
////					���ܵ�����������ִ��ʱ�����Ѫ����Ϊ0�ٽ�����״̬����Ϊfalse
//					enemy.setLive(false);
//					file.setLive(false);
//					break;
//				}
//			}
//		}
//	}
	
	
//	��ϷԪ���Զ�������
	public void moveAndUpdate(Map<GameElement,List<ElementObj>> all,long gameTime){
//		GameElement.values();//���ط�����Ĭ�Ϸ����� ����ֵ��һ�����飬˳����Ƕ���ö�ٵ�˳��
		for(GameElement ge:GameElement.values()){
			List<ElementObj> list=all.get(ge);
//			for(int i=0;i<list.size();i++){
				for(int i=list.size()-1;i>=0;i--){
				ElementObj obj=list.get(i);
				if(!obj.isLive()){//�������
//					list.remove(i--);
//					����һ�����������������п������������飩��������������װ�����䡣
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//���õ�ģ��ķ���
			}
		}
	}
	
//	��������Ӻ͵�ͼ����ײ
	public void PlayPKBox(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj player=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj box=listB.get(j);
				if(player.pk(box)){
					player.back();
					break;
				}
			}
		}
	}
//	����͵�ͼ����ײ
	public void PlayPKMap(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj player=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj map=listB.get(j);
				if(player.pk(map)){
					player.back();
					break;
				}
			}
		}
	}
//	������ҳԵ��ߵ�
	public void PlayPKProp(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj player=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj prop=listB.get(j);
				if(player.pk(prop)){
					player.eat(prop);
					prop.setLive(false);
					break;
				}
			}
		}
	}
//	�����ж������û�б�ը��
	public void judge(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj a=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj b=listB.get(j);
				if(b.isPlay(a)){
					a.setLive(false);
					break;
				}
			}
		}
	}
//	�������ݱ�ը֮���жϸ�����û��box��prop
	public void judge2(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj a=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj b=listB.get(j);
				if(b.Destroy(a)){
					if(a.getDestructible()==true){
						a.setLive(false);
					}
					break;
				}
			}
		}
	}
	public void Booms(List<ElementObj> listA){
		for(int i=0;i<listA.size();i++){
			ElementObj a=listA.get(i);
			for(int j=i;j<listA.size();j++){
				ElementObj b=listA.get(j);
				if(b.Destroy(a)){
					a.setLive(false);
					break;
				}
			}
		}
	}
//	���ݺ�������ײ
	public void PlayPKFile(List<ElementObj> listA,List<ElementObj> listB){
		for(int i=0;i<listA.size();i++){
			ElementObj a=listA.get(i);
			for(int j=0;j<listB.size();j++){
				ElementObj b=listB.get(j);
				if(a.isInpao()==false){
					if(a.pk(b)){
						a.back();
						break;
					}
				}
			}
		}
	}
//	public int isrun(int x,List<ElementObj> listA){
//		for(int i=0;i<listA.size();i++){
//			ElementObj a=listA.get(i);
//			if(a.isLive()){
//				x=x+1;
//			}
//		}
//		return x;
//	}
	/**
	 * ��Ϸ�л��ؿ�
	 */
	private void gameOver() {
		GameJFrame gj=(GameJFrame)SwingUtilities.getRoot(this.getJpanel());
//		System.out.println(gj);
		gj.clear();
		gj.remove(this.getJpanel());
		OverJPanel oj=new OverJPanel();
		gj.setjPanel(oj);
		gj.setThead(null);
		gj.start();
	}
	public JPanel getJpanel() {
		return jpanel;
	}
	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}
	
	
//	public void load(){
////		ͼƬ����
//		ImageIcon icon=new ImageIcon("image/tank/play1/player1_up.png");
//		ElementObj obj=new Play(100,100,50,50,icon);
////		��������뵽Ԫ�ع�������
////		em.getElementsByKey(GameElement.PLAY).add(obj);
//		em.addElement(obj,GameElement.PLAY);//ֱ�����
////		for(int i=0;i<10;i++){//�������˵�
////			em.addElement(new Enemy().createElement(""),GameElement.ENEMY);
////		}
//		
//		
//		
//		
//		
//		
////		��ҵҪ�����һ�������࣬����������д��ע�⣺����Ҫʵ�ּ��̼���
////		ʵ�ֵ��˵���ʾ��ͬʱʵ����򵥵��ƶ�����������100��100�ƶ���500��500��Ȼ���ͷ
////		ʵ���ӵ��ķ��䣬���ӵ��ƶ���Ԫ�ص�����
////		ע�⣺ֻ���ӵ��ķ����������˼�������ߵĵ����Ƿ���ӵ��ķ��䷽ʽ�����
//		
//	}
}
