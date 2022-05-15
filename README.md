# Painting Application

## Intro

Nanjing University Spring Semester 2022 Department of Computer Science and Technology *Object-Oriented Design* course assignment

## Package Intro

### Shape

Shape包主要存储画图工具中的各种图形，所有图形都继承了父类 `AbstractShape` 主要包括：
1. `Brush`: 笔刷
2. `Circle`: 空心圆
3. `FillCircle`: 实心圆
4. `FillOval`: 实心椭圆
5. `FillRect`: 实心矩形
6. `FillRoundRect`: 实心圆角矩形
7. `Hexagon`: 六边形
8. `Images`: 图片
9. `Line`: 直线
10. `Oval`: 椭圆
11. `Pencil`: 铅笔
12. `Pentagon`: 五边形
13. `Rectangle`: 矩形
14. `RoundRect`: 圆角矩形
15. `Text`: 文字
16. `Triangle`: 三角形

### Window

### Utils

## Requirements

简要介绍一下作业的需求：
1. 基础需求
   1. 设计良好的图形用户界面，界面中要求至少有默认大小的三角形、方框、圆形、椭圆、连接线等五种元素可供用户选择后，绘制到画布上。
   2. 允许用户添加文字描述
   3. 单击可以选中图形，并允许对图形的拷贝复制
   4. 多个图形可以组合，组合后的图形同样有拷贝复制功能
   5. 支持撤销上一步操作的功能
2. 扩展功能需求：
   1. 右键点击图形可通过文本框对图形的尺寸进行调整
   2. 支持个性化界面，包括设置界面字体大小、界面皮肤等
   3. 支持图形（包括组合图形）的拖拽调整图形大小
   4. 支持撤销多步的功能
   5. 设计一种硬盘文件存储格式可以保存用户绘制的图形，并可以加载

## 素材来源于参考

1. [画图软件Java实现](https://github.com/HansGerry/Painting)
