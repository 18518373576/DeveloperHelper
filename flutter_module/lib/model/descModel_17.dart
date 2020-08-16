import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_17() {
  List<Item> list = [];
  list.add(Item("Wrap", ItemType.TITLE));
  list.add(Item(
      "在Row和Column中,如果子view超出屏幕范围,就会出现溢出错误,而Wrap布局超出屏幕则会"
      "自动换行.",
      ItemType.BODY));
  list.add(Item.widgetItem(Column(
    children: [
      Wrap(
        direction: Axis.horizontal,
        //子控件水平方向间距
        spacing: 10,
        //子控件竖直方向间距
        runSpacing: 10,
        alignment: WrapAlignment.start,
        children: [
          Chip(
            avatar: CircleAvatar(
              backgroundColor: Colors.blue,
              child: Icon(Icons.add),
            ),
            label: Text("Wrap测试"),
          ),
          Chip(
            avatar: CircleAvatar(
              backgroundColor: Colors.blue,
              child: Icon(Icons.add),
            ),
            label: Text("Wrap测试"),
          ),
          Chip(
            avatar: CircleAvatar(
              backgroundColor: Colors.blue,
              child: Icon(Icons.add),
            ),
            label: Text("Wrap测试"),
          ),
          Chip(
            avatar: CircleAvatar(
              backgroundColor: Colors.blue,
              child: Icon(Icons.add),
            ),
            label: Text("Wrap测试"),
          )
        ],
      )
    ],
  )));
  list.add(Item(
      "Column(\n" +
          "    children: [\n" +
          "      Wrap(\n" +
          "        direction: Axis.horizontal,\n" +
          "        //子控件水平方向间距\n" +
          "        spacing: 10,\n" +
          "        //子控件竖直方向间距\n" +
          "        runSpacing: 10,\n" +
          "        alignment: WrapAlignment.start,\n" +
          "        children: [\n" +
          "          Chip(\n" +
          "            avatar: CircleAvatar(\n" +
          "              backgroundColor: Colors.blue,\n" +
          "              child: Icon(Icons.add),\n" +
          "            ),\n" +
          "            label: Text(\"Wrap测试\"),\n" +
          "          ),\n" +
          "          Chip(\n" +
          "            avatar: CircleAvatar(\n" +
          "              backgroundColor: Colors.blue,\n" +
          "              child: Icon(Icons.add),\n" +
          "            ),\n" +
          "            label: Text(\"Wrap测试\"),\n" +
          "          ),\n" +
          "          Chip(\n" +
          "            avatar: CircleAvatar(\n" +
          "              backgroundColor: Colors.blue,\n" +
          "              child: Icon(Icons.add),\n" +
          "            ),\n" +
          "            label: Text(\"Wrap测试\"),\n" +
          "          ),\n" +
          "          Chip(\n" +
          "            avatar: CircleAvatar(\n" +
          "              backgroundColor: Colors.blue,\n" +
          "              child: Icon(Icons.add),\n" +
          "            ),\n" +
          "            label: Text(\"Wrap测试\"),\n" +
          "          )\n" +
          "        ],\n" +
          "      )\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("Flow", ItemType.TITLE));
  list.add(Item("Flow有以下优点:", ItemType.BODY));
  list.add(Item(
      "①性能好,Flow是一个对子组件尺寸以及位置调整非常高效的控件，"
      "Flow用转换矩阵在对子组件进行位置调整的时候进行了优化：在Flow定位过后，"
      "如果子组件的尺寸或者位置发生了变化，在FlowDelegate中的paintChildren()方法中"
      "调用context.paintChild 进行重绘，而context.paintChild在重绘时使用了转换矩阵，"
      "并没有实际调整组件位置。",
      ItemType.BODY));
  list.add(Item(
      "②灵活；由于我们需要自己实现FlowDelegate的paintChildren()方法，"
      "所以我们需要自己计算每一个组件的位置，因此，可以自定义布局策略。",
      ItemType.BODY));
  list.add(Item("缺点:", ItemType.BODY));
  list.add(Item("①使用复杂。", ItemType.BODY));
  list.add(Item("②不能自适应子组件大小，必须通过指定父容器大小或实现TestFlowDelegate的getSize返回固定大小。",
      ItemType.BODY));
  return list;
}
