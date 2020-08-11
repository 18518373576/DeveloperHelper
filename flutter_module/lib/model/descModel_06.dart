import 'package:flutter_module/widgets/descListView.dart';

getDescList_06() {
  List<Item> list = [];
  list.add(Item("Dart中能够被重载的操作符,如下:", ItemType.BODY));
  list.add(Item(
      " <    +    |    []\n" +
          " >    /    ^    []=\n" +
          " <=  ~/    &    ~\n" +
          " >=   *   <<    ==\n" +
          " -    %   >>",
      ItemType.CODE));
  list.add(Item("下面以*为例,演示操作符重载:", ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  var test_01 = Test(1, 2);\n" +
          "  var test_02 = Test(1, 2);\n" +
          "  print(\"x:\${(test_01 * test_02).x} y:\${(test_01 * test_02).y} \");\n" +
          "}\n" +
          "\n" +
          "class Test {\n" +
          "  final int x;\n" +
          "  final int y;\n" +
          "\n" +
          "  const Test(this.x, this.y);\n" +
          "\n" +
          "  //运算符重载\n" +
          "  Test operator *(Test test) {\n" +
          "    return Test(this.x * test.x, this.y * test.y);\n" +
          "  }\n" +
          "}",
      ItemType.CODE));
  return list;
}
