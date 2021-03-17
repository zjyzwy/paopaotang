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
 * @说明 游戏的主线程，用于控制游戏加载，游戏关卡，游戏运行自动化
 * 		 游戏判定		游戏地图切换 资源释放和重新读取。。。
 * @author Administrator
 *@继承 使用继承的方式实现多线程（一般建议使用接口实现）
 */
public class GameThread extends Thread{
	private JPanel jpanel=null;
	private boolean state=true;
	private ElementManager em;
	public GameThread(){
		em=ElementManager.getManager();
	}
	@Override
	public void run() {//游戏的run方法  主线程
		while(state){//扩展，可以将true变为一个变量用于控制结束
//		游戏开始前  读进度条，加载游戏资源（场景资源）
			gameLoad();
//		游戏进行时  游戏过程中
			gameRun();
//		游戏场景结束  游戏资源回收（场景资源）
			gameOver();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
//		System.out.println("over");
	}
	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadImg();//加载图片
		GameLoad.MapLoad(1);//可以为变量，每一关都重新加载   加载地图
//		加载主角
		GameLoad.LoadPlay();//可以带参数，单机还是两人
//		加载敌人NPC等
		
		
//		全部加载完成，游戏开始
	}
	/**
	 * @说明 游戏进行时
	 * @任务说明 游戏过程中需要做的事情：1.自动化玩家的移动，碰撞，死亡
	 * 								   2.新元素的增加（NPC的死亡后出现道具）
	 * 								   3.暂停等等。。。
	 * 先实现主角的移动
	 */
//	private long gameTime=0L;
	private void gameRun() {
		long gameTime=0L;//给int类型就可以
		while(state){//预留扩展true可以变为变量，用于控制关卡结束等
			int count=1;
			Map<GameElement,List<ElementObj>> all=em.getGameElements();
			List<ElementObj> files=em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> BOX=em.getElementsByKey(GameElement.BOX);
			List<ElementObj> boom=em.getElementsByKey(GameElement.BOOM);
			List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> map=em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> prop=em.getElementsByKey(GameElement.PROP);
			List<ElementObj> trap=em.getElementsByKey(GameElement.TRAP);
			moveAndUpdate(all,gameTime);//游戏元素自动化方法使用
			
			
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
			gameTime++;//唯一的时间控制
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
////		使用循环，做一对一的判定，如果为真，就设置两个对象的死亡状态
//		for(int i=0;i<listA.size();i++){
//			ElementObj enemy=listA.get(i);
//			for(int j=0;j<listB.size();j++){
//				ElementObj file=listB.get(j);
//				if(enemy.pk(file)){
////					将setLive（false）变为一个受攻击的方法，还可以传入另一个对象的攻击力。
////					当受到攻击方法里执行时，如果血量减为0再将生存状态设置为false
//					enemy.setLive(false);
//					file.setLive(false);
//					break;
//				}
//			}
//		}
//	}
	
	
//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement,List<ElementObj>> all,long gameTime){
//		GameElement.values();//隐藏方法（默认方法） 返回值是一个数组，顺序就是定义枚举的顺序
		for(GameElement ge:GameElement.values()){
			List<ElementObj> list=all.get(ge);
//			for(int i=0;i<list.size();i++){
				for(int i=list.size()-1;i>=0;i--){
				ElementObj obj=list.get(i);
				if(!obj.isLive()){//如果死亡
//					list.remove(i--);
//					启动一个死亡方法（方法中可以做其他事情）例如死亡动画，装备掉落。
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//调用的模板的方法
			}
		}
	}
	
//	人物和箱子和地图的碰撞
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
//	人物和地图的碰撞
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
//	用于玩家吃道具的
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
//	用于判断玩家有没有被炸到
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
//	用于泡泡爆炸之后，判断附近有没有box和prop
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
//	泡泡和人物碰撞
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
	 * 游戏切换关卡
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
////		图片导入
//		ImageIcon icon=new ImageIcon("image/tank/play1/player1_up.png");
//		ElementObj obj=new Play(100,100,50,50,icon);
////		将对象放入到元素管理器中
////		em.getElementsByKey(GameElement.PLAY).add(obj);
//		em.addElement(obj,GameElement.PLAY);//直接添加
////		for(int i=0;i<10;i++){//创建敌人的
////			em.addElement(new Enemy().createElement(""),GameElement.ENEMY);
////		}
//		
//		
//		
//		
//		
//		
////		作业要求：添加一个敌人类，仿照玩家类编写，注意：不需要实现键盘监听
////		实现敌人的显示，同时实现最简单的移动，例如坐标100，100移动到500，500，然后调头
////		实现子弹的发射，和子弹移动，元素的死亡
////		注意：只讲子弹的发射和死亡。思考：道具的掉落是否和子弹的发射方式相近？
//		
//	}
}
