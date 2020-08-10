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
  list.add(Item("继承", ItemType.TITLE));
  return list;
}
