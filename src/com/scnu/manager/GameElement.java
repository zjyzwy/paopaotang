package com.scnu.manager;

public enum GameElement {
	/*
	 * PLAY 玩家
	 * MAPS 地图
	 * ENEMY 敌人
	 * BOSS BOSS
	 */
	BACKGROUND,MAPS,BOX,PLAY,ENEMY,BOSS,PLAYFILE,BOOM,PROP,TRAP,DIE;//枚举类型的顺序是声明顺序
//	我们定义的枚举类型，在编译时，虚拟机会自动帮助生成class文件
//  并且会加载很多代码和方法
//	构造不允许是public的，只能是private的
}
