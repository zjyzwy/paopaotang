# paopaotang

#### 介绍
一款基于MVC架构的使用JAVA开发的经典游戏泡泡堂————为一款双人游戏，使用键盘监听事件实现游戏人物的移动以及炸弹的放置

MVC是Model View Controller的缩写，所谓的MVC架构把复杂的程序分成Model（模型）、View（视图）、Controller（控制器）三个部分，各部分的功能相互独立，实现了高内聚低耦合的设计理念。
- Model（模型）部分主要负责游戏元素的生成，通过读取配置文件加载需要的游戏资源，并根据需要创建游戏对象。
- View（视图）部分主要负责游戏元素在界面的显示，当Model中的游戏元素发生改变时，也会View中相应地显示出来。
- Controller（控制器）部分主要负责游戏状态的监听，通过读取玩家的键盘输入可以控制游戏人物的行为以及游戏的状态（开始、暂停、结束）。

![开始界面](https://images.gitee.com/uploads/images/2021/0317/213847_4fd178dc_8512800.png "微信截图_20210317213634.png")

![游戏界面](https://images.gitee.com/uploads/images/2021/0317/213912_ca37ce85_8512800.png "微信截图_20210317213811.png")

![结束界面](https://images.gitee.com/uploads/images/2021/0317/213930_ffe82424_8512800.png "微信截图_20210317213714.png")