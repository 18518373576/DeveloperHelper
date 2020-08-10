import 'package:flutter_module/widgets/descListView.dart';

getDescList_03() {
  List<Item> list = [];
  list.add(Item(
      "Dart是一种面向对象的语言,其特征之一为一切皆对象.因此一个方法也是一个对象,而且具有类型Function,因此可以赋值给某个变量,"
      "也可以当做一个方法的参数,还可以当做方法调用Dart类的一个实例.",
      ItemType.BODY));
  list.add(Item("定义一个方法", ItemType.TITLE));
  list.add(Item(
      "//开头的int是静态类型定义,表示此方法返回一个int类型的值.可以省略不写.\n" +
          "int fun_01() {\n" +
          "  return 1;\n" +
          "}\n" +
          "\n" +
          "//对于只有一个表达式的方法可以简写\n" +
          "//=>相当于return,后面只能使用表达式,不能使用语句\n" +
          "fun_02() => fun_01() + 1;",
      ItemType.CODE));
  list.add(Item("参数", ItemType.TITLE));
  list.add(Item("方法的参数包括可选参数和必选参数两种.", ItemType.BODY));
  list.add(Item(
      "//参数name为必选参数,{}中的参数为可选参数,{}定义的可选参数在调用时必须指明给定的值是哪个参数的\n" +
          "fun_03(var name, {int age, String weight}) {\n" +
          "  print(\"您好:\$name!您的年龄是:\${age}岁,体重是\${weight}\");\n" +
          "}\n" +
          "\n" +
          "//参数name为必选参数,[]中的参数为可选参数,[]定义的可选参数在调用时需要按照定义的顺序进行赋值.\n" +
          "//因为调用赋值是基于位置的,如果想要给weight赋值,则必须给age赋值\n" +
          "fun_04(var name, [int age, String weight]) {\n" +
          "  print(\"您好:\$name!您的年龄是:\${age}岁,体重是\${weight}\");\n" +
          "}\n\n"
          "//调用方法\n"
          "fun_03(\"zhang\", weight: \"70kg\", age: 18);\n" +
          "fun_04(\"zhang\", 18, \"70kg\");",
      ItemType.CODE));
  list.add(Item("main()方法", ItemType.TITLE));
  list.add(
      Item("在Dart中,main()方法即主方法,程序从这个方法的方法体开始执行,因此也称为入口函数.", ItemType.BODY));
  list.add(Item("main()方法返回void,以及一个可选的参数List<String>", ItemType.BODY));
  list.add(Item("返回值", ItemType.TITLE));
  list.add(Item("如果方法没有被void修饰,则说明此方法带有返回值,如果方法内没有return语句,则默认返回null.",
      ItemType.BOLD_BODY));
  return list;
}
