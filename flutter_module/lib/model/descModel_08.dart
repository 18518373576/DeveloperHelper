import 'package:flutter_module/widgets/descListView.dart';

getDescList_08() {
  List<Item> list = [];
  list.add(Item(
      "Flutter包括了响应式框架,2D渲染引擎,各种UI控件等.Flutter是一套适用于不同平台的SDK,即编写一次代码即可在各种平台运行.",
      ItemType.BODY));
  list.add(Item("Widget和Element", ItemType.BOLD_BODY));
  list.add(Item("Element代表屏幕上显示的元素,Widget是一个Element的参数描述.", ItemType.BODY));
  list.add(Item("StatelessWidget", ItemType.BOLD_BODY));
  list.add(Item("StatelessWidget用于不需要维护状态的场景.", ItemType.BODY));
  list.add(Item("StatefulWidget", ItemType.BOLD_BODY));
  list.add(Item("StatefulWidget添加了createState()抽象方法.用于创建和StatefulWidget相关的状态.",
      ItemType.BODY));
  list.add(Item("State", ItemType.BOLD_BODY));
  list.add(Item(
      "一个StatefulWidget对应一个State,State表示与其对应的StatefulWidget要维护的状态."
      "当State被改变时,可以手动调用setState()方法通知Flutter框架状态改变,从而刷新界面跟新UI.",
      ItemType.BODY));
  return list;
}
