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
  list.add(Item('var list = ["a", "b"];', ItemType.CODE));
  return list;
}
