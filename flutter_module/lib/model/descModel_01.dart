import 'package:flutter_module/widgets/descListView.dart';

/*
变量与常量
 */
List<Item> getDescList_01() {
  List<Item> list = [];
  list.add(Item(
      "在程序中经常使用大量的数据来代表程序的状态,其中有些数据的值在程序运行过程中会发生改变."
      "有些数据不能发生改变,这些数据在程序中分别叫做变量和常量.",
      ItemType.BODY));
  list.add(Item("变量", ItemType.TITLE));
  list.add(Item("在Dart中声明一个变量的方法非常简单:", ItemType.BOLD_BODY));

  list.add(Item(
      "main() {\n" +
          "  //使用关键字var定义,变量会根据值自动推导类型\n" +
          "  var apple = \"苹果\";\n\n" +
          "  //使用精确类型定义\n" +
          "  String banana = \"香蕉\";\n\n" +
          "  //使用动态类型关键字\n" +
          "  dynamic any = \"水果\";\n" +
          "  any = 1;\n\n" +
          "  /**\n" +
          "   * 注意:使用var和精确类型定义的变量,类型一旦确定便不可修改,如果对apple赋值apple = 1\n" +
          "   * 会导致编译错误.但是dynamic关键字修饰的变量可以动态的确定类型,如对any赋值any = 1时允许的.\n" +
          "   * 打印输出结果为:\n" +
          "   * 苹果\n" +
          "   * 香蕉\n" +
          "   * 1\n" +
          "   */\n\n" +
          "  print(apple);\n" +
          "  print(banana);\n" +
          "  print(any);\n" +
          "}",
      ItemType.CODE));
  list.add(Item("常量", ItemType.TITLE));
  list.add(Item("所谓常量,简单的说,就是其引用的对象不可更改.在Dart语言中,使用final和const关键字来声明一个常量:",
      ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  var k = 10;\n\n" +
          "  /**\n" +
          "   * final和const的区别:final是运行时常量,意味着可以在运行时根据需求进行赋值.\n" +
          "   * const是编译时常量.也就是定义const常量的时候必须给出准确的值.\n" +
          "   * 下面代码const j = k * 10;编译出错.\n" +
          "   */\n\n" +
          "  final i = k * 10;\n" +
          "  const j = k * 10;\n" +
          "  const m = 10;\n" +
          "}",
      ItemType.CODE));
  list.add(Item("const关键字也可以用来创建不变的值.也可以定义构造函数为const,代表不可变对象.", ItemType.BODY));
  return list;
}
