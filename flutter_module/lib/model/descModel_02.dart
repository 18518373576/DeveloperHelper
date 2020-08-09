import 'package:flutter_module/widgets/descListView.dart';

/**
 * 基本数据类型
 */
List<Item> getDescList_02() {
  List<Item> list = [];
  list.add(Item("在Dart中有几种内置的数据类型:数值型,字符串,布尔,集合,键值对.", ItemType.BODY));
  list.add(Item("数值型", ItemType.TITLE));
  list.add(Item("Dart的数值类型包含:int和double", ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  //double表示浮点数类型,占内存8个字节64位\n" +
          "  double i = 1;\n" +
          "  double k = i;\n" +
          "  //int表示整数类型,占内存8个字节64位\n" +
          "  int j = 1;\n" +
          "\n" +
          "  //给double类型变量赋值int类型,会自动转换为double类型\n" +
          "  print(i);\n" +
          "  print(j);\n" +
          "  //判断值是否相等\n" +
          "  print(i == j);\n" +
          "  //判断是否为同一个引用\n" +
          "  print(identical(i, k));\n" +
          "}",
      ItemType.CODE));
  list.add(Item("字符串", ItemType.TITLE));
  list.add(Item("Dart中的字符串表示一个由多个字符组成的序列,采用UTF-16编码.在声明字符串时可以用单引号也可以用双引号.",
      ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  //使用单引号声明的字符串内可包含双引号\n" +
          "  var str_01 = 'hello \"你好\"';\n" +
          "  //使用双引号声明的字符串内可包含单引号\n" +
          "  var str_02 = \"hello '你好'\";\n" +
          "  //单引号声明内使用单引号需使用转义字符,双引号声明类同\n" +
          "  var str_03 = 'hello \\'你好\\'';\n" +
          "  var str_04 = \"hello \\\"你好\\\"\";\n" +
          "\n" +
          "  //同kotlin类似dart也可以使用表达式作为字符串的一部分\n" +
          "  var name = \"张\";\n" +
          "  var str_05 = \"hello \$name\";\n" +
          "\n" +
          "  //使用三个单引号和三个双引号定义换行字符串\n" +
          "  var str_06 = \"\"\"\n" +
          "  苹果\n" +
          "  香蕉\n" +
          "  桃子\n" +
          "  \"\"\";\n" +
          "  //以上定义方式等价于\n" +
          "  var str_07 = \"苹果\\n香蕉\\n桃子\";\n" +
          "\n" +
          "  //与其他类型之间的转换\n" +
          "  var str_08 = \"100\";\n" +
          "  var int_01 = int.parse(str_08);\n" +
          "  var str_09 = int_01.toString();\n" +
          "}",
      ItemType.CODE));
  list.add(Item("集合", ItemType.TITLE));
  list.add(Item("Dart 2.0版本提供了三种核心集合类型:\nList,Set,Map.", ItemType.BODY));
  list.add(Item("①List列表集合", ItemType.BOLD_BODY));
  list.add(Item("在Dart中使用数组表示列表对象,声明一个列表对象如下:", ItemType.BODY));
  list.add(Item(
      "fun_03() {\n" +
          "  //声明list列表集合\n" +
          "  var list_01 = [1, \"2\", 3];\n" +
          "  List<int> list_02 = [1, 2, 3];\n" +
          "\n" +
          "  //List列表常用的方法\n" +
          "  print(list_01.length); //获取列表长度\n" +
          "  list_01.add(\"c\"); //添加一个元素\n" +
          "  list_01.addAll(list_02); //添加一个列表\n" +
          "  print(list_01.elementAt(0)); //根据下标获取一个元素\n" +
          "  print(list_01.contains(\"c\")); //判断是否包含某个元素\n" +
          "  list_01.removeAt(0); //根据下标移除元素\n" +
          "  list_01[0] = \"A\"; //设置下标为0的元素的值\n" +
          "  list_01.clear(); //清空列表\n" +
          "  print(list_01.toString()); //打印列表内容\n" +
          "}",
      ItemType.CODE));
  list.add(Item("②Set散列表", ItemType.BOLD_BODY));
  list.add(Item("Set集合存储数据的特点是,不允许存在重复元素和元素无序存储,声明方式如下:", ItemType.BODY));
  list.add(Item(
      "fun_04() {\n" +
          "  //声明Set集合\n" +
          "  var temp = 1;\n" +
          "  var set_01 = {\"a\", \"b\", temp, temp};\n" +
          "  set_01.add(1);\n" +
          "  Set<String> set_02 = {\"a\", \"b\", \"c\", \"c\"};\n" +
          "\n" +
          "  //打印结果:\n" +
          "  //{a, b, 1}\n" +
          "  //{a, b, c}\n" +
          "  //添加的重复元素并没有被存储在集合中\n" +
          "  print(set_01.toString());\n" +
          "  print(set_02.toString());\n" +
          "  //从输出结果看,Set集合似乎是有序的,但实际上其内部存储是无序的,不能通过下标来获取元素.\n" +
          "}",
      ItemType.CODE));
  list.add(Item("③Map映射集合", ItemType.BOLD_BODY));
  list.add(Item(
      "Map集合存储的是键值对(key-value),每个键对应一个值,可分别存储不同的数据类型,其中,键(key)在Map中不允许重复,值(value)"
      "可以存在重复对象.声明Map集合方式如下:",
      ItemType.BODY));
  list.add(Item(
      "fun_05() {\n" +
          "  //声明Map集合\n" +
          "  var map_01 = {\"A\": \"a\", \"B\": \"b\", 1: 1};\n" +
          "  Map map_02 = {\"A\": \"a\", \"B\": \"b\", 1: \"1\"};\n" +
          "  Map<String, String> map_03 = {\"A\": \"a\", \"B\": \"b\", \"1\": \"1\"};\n" +
          "\n" +
          "  //常用方法\n" +
          "  map_01[\"C\"] = \"c\"; //添加一个元素,如果集合中存在key则修改,不存在则添加\n" +
          "\n" +
          "  //打印结果:\n" +
          "  //{A: a, B: b, 1: 1, C: c}\n" +
          "  print(map_01);\n" +
          "}",
      ItemType.CODE));
  list.add(Item("集合的遍历", ItemType.TITLE));
  list.add(Item(
      "fun_06() {\n" +
          "  var set = {1, 2, 3, 4, 5, 6, 7, 8, 9};\n" +
          "\n" +
          "  //方式一\n" +
          "  set.forEach((f) {\n" +
          "    print(f);\n" +
          "  });\n" +
          "\n" +
          "  //方式二\n" +
          "  for (var e in set) {\n" +
          "    print(e);\n" +
          "  }\n" +
          "\n" +
          "  //其他集合遍历不尽相同,不过都有forEach()方法可供使用\n" +
          "}",
      ItemType.CODE));
  return list;
}
