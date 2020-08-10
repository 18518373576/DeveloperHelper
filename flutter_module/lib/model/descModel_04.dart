import 'package:flutter_module/widgets/descListView.dart';

getDescList_04() {
  List<Item> list = [];
  list.add(Item(
      "运算符也称为操作符,Dart定义了很多操作符,很多与Java一样,这里仅列出一些Dart特有的操作符:", ItemType.BODY));
  list.add(Item("类型判定操作符", ItemType.BOLD_BODY));
  list.add(Item(
      "main() {\n" +
          "  var num_01 = 1.0;\n" +
          "  var num_02 = 1;\n" +
          "  \n" +
          "  //使用as进行类型转换,目前感觉没啥用,不支持数值类型之间的转换\n" +
          "  Object num_03 = num_01 as Object;\n" +
          "  //is是指定类型返回true\n" +
          "  print(num_01 is int);\n" +
          "  print(num_02 is int);\n" +
          "  //is!不是指定类型返回true\n" +
          "  print(num_03 is! int);\n" +
          "}",
      ItemType.CODE));
  list.add(Item("条件表达式", ItemType.BOLD_BODY));
  list.add(Item(
      "fun_01() {\n" +
          "  var num = 1;\n" +
          "  var str = \"1\";\n" +
          "  //与java三元运算表达式一样\n" +
          "  print(num == str ? \"相等\" : \"不相等\");\n" +
          "  //??表达式,前面的表达式为true则返回num != str的值,为false则返回??后面的表达式的值\n" +
          "  print(num != str ?? \"相等\");\n" +
          "  //?.表示如果str为空,则返回null,不为空则返回str的length\n" +
          "  print(str?.length);\n" +
          "}",
      ItemType.CODE));
  list.add(Item("级联运算符(..)", ItemType.BOLD_BODY));
  list.add(Item(
      "fun_02() {\n" +
          "  //类似于建造者模式,使用..可以继续使用List的方法\n" +
          "  print(List()\n" +
          "    ..add(1)\n" +
          "    ..add(2)\n" +
          "    ..add(3)\n" +
          "    ..toString());\n" +
          "\n" +
          "  //输出结果:\n" +
          "  //[1, 2, 3]\n" +
          "}",
      ItemType.CODE));
  return list;
}
