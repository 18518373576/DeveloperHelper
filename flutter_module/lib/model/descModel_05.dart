import 'package:flutter_module/widgets/descListView.dart';

getDescList_05() {
  List<Item> list = [];
  list.add(Item(
      "Dart是一门面向对象的编程语言.Dart所有的类都是Object的的直接或间接子类.Dart中一切皆对象.", ItemType.BODY));
  list.add(Item("类的实例化", ItemType.TITLE));
  list.add(Item(
      "class Num {\n" +
          "  //静态成员变量,只能通过类名.调用\n" +
          "  static var num_static;\n" +
          "\n" +
          "  //实例变量\n" +
          "  var mum_01;\n" +
          "  var mum_02;\n" +
          "\n" +
          "  //构造方法,默认给实例变量赋值\n" +
          "  Num(this.mum_01, this.mum_02);\n" +
          "\n" +
          "  @override\n" +
          "  String toString() {\n" +
          "    return 'Num{mum_01: \$mum_01, mum_02: \$mum_02}';\n" +
          "  }\n" +
          "}\n\n"
              "创建类的实例即对象:\n"
              "var num = Num(1, 2);",
      ItemType.CODE));
  list.add(Item("类的继承和接口实现", ItemType.TITLE));
  list.add(Item("Dart是一种单继承多实现的面向对象编程语言,继承使用extends关键字,实现使用with和implements关键字.",
      ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  //创建一辆五菱汽车\n" +
          "  var wuLing = WuLing();\n" +
          "  //一辆红色的五菱汽车\n" +
          "  wuLing.setColor(\"红色\");\n" +
          "  //拉货\n" +
          "  wuLing.pickGoods(\"快递\");\n" +
          "  //接人送人\n" +
          "  wuLing.pickUp(\"老婆\");\n" +
          "  //兜风\n" +
          "  wuLing.run();\n" +
          "}\n" +
          "\n" +
          "//汽车,抽象类,不能被实例化,提供一类事物的共有属性\n" +
          "abstract class Car {\n" +
          "  run() {\n" +
          "    print(\"兜风\");\n" +
          "  }\n" +
          "\n" +
          "  //抽象方法,需要子类实现\n" +
          "  void setColor(var color);\n" +
          "}\n" +
          "\n" +
          "//货拉拉接口,此接口提供事物的附加属性\n" +
          "abstract class HuoLaLa {\n" +
          "  void pickGoods(var goods);\n" +
          "}\n" +
          "\n" +
          "//私家车\n" +
          "class PrivateCar {\n" +
          "  void pickUp(var person) {\n" +
          "    print(\"接送\$person上下班\");\n" +
          "  }\n" +
          "}\n" +
          "\n" +
          "//五菱汽车,首先这是一辆汽车,继承了Car类,实现了Car的方法,具有了Car的属性.\n"
              "//其次这是一辆私家车(with)具有接送属性,使用(with)实现的类的方法不必要覆写.\n" +
          "//再其次这辆车还实现(implements)了货拉拉接口可以拉货赚钱.使用(implements)实现的类的方法必须覆写.\n" +
          "class WuLing extends Car with PrivateCar implements HuoLaLa {\n" +
          "  //覆写汽车的抽象方法\n" +
          "  @override\n" +
          "  void setColor(color) {\n" +
          "    print(\"颜色:\$color\");\n" +
          "  }\n" +
          "\n" +
          "  //实现货拉拉接口的抽象方法\n" +
          "  @override\n" +
          "  pickGoods(var goods) {\n" +
          "    print(\"拉货:\$goods\");\n" +
          "  }\n" +
          "}",
      ItemType.CODE));
  list.add(Item(
      "当一个类作为子类存在时,它的构造方法开始执行之前会先调用父类的无名构造方法,如果父类没有无名构造方法,则需要显式的使用:调用"
      "父类的其他构造方法.在java中因为有此特性.",
      ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  print(WuLing().color);\n" +
          "}\n" +
          "\n" +
          "class Car {\n" +
          "  var color;\n" +
          "\n" +
          "  Car(this.color);\n" +
          "}\n" +
          "\n" +
          "class WuLing extends Car {\n" +
          "  WuLing() : super(\"蓝色\");\n" +
          "}",
      ItemType.CODE));
  list.add(Item("工厂方法的构造方法", ItemType.BOLD_BODY));
  list.add(Item(
      "为了减少资源的消耗,Dart提供了工厂方法,也就是为类的对象提供了缓存.如果一个对象已经被实例化过,那么从缓存中取出即可,"
      "不必重新实例化.",
      ItemType.BODY));
  list.add(Item(
      "class WuLing extends Car {\n" +
          "  //车架号\n" +
          "  final String id;\n\n" +
          "  //final修饰的_map的引用不可更改,但是map对象的内容可修改\n" +
          "  static final Map<String, WuLing> _map = {};\n" +
          "\n" +
          "  factory WuLing(String id) {\n" +
          "    //如果缓存中没有此对象,则创建对象并添加到缓存\n" +
          "    if (!_map.containsKey(id)) {\n" +
          "      _map[id] = WuLing.newCar(id);\n" +
          "    }\n" +
          "    return _map[id];\n" +
          "  }\n" +
          "\n" +
          "  //构造方法重定向\n" +
          "  WuLing.newCar(this.id) : super(\"蓝色\");\n" +
          "}",
      ItemType.CODE));
  return list;
}
