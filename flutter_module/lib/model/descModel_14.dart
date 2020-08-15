import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_14() {
  List<Item> list = [];
  list.add(Item("LinearProgressIndicator", ItemType.TITLE));
  list.add(Item("不设置value属性,进度条执行重复动画.", ItemType.BODY));
  list.add(Item.widgetItem(Column(
    mainAxisSize: MainAxisSize.min,
    children: <Widget>[
      Container(
        padding: EdgeInsets.all(15),
        child: LinearProgressIndicator(
          valueColor: AlwaysStoppedAnimation(Colors.blue),
          backgroundColor: Colors.grey[200],
        ),
      ),
    ],
  )));
  list.add(Item(
      "Column(\n" +
          "    children: <Widget>[\n" +
          "      Container(\n" +
          "        padding: EdgeInsets.all(15),\n" +
          "        child: LinearProgressIndicator(\n" +
          "          valueColor: AlwaysStoppedAnimation(Colors.blue),\n" +
          "          backgroundColor: Colors.grey[200],\n" +
          "        ),\n" +
          "      ),\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("设置value属性0-1,进度条按照设置的进度显示.", ItemType.BODY));
  list.add(Item.widgetItem(Column(
    mainAxisSize: MainAxisSize.min,
    children: <Widget>[
      Container(
        padding: EdgeInsets.all(15),
        child: LinearProgressIndicator(
          value: 0.5,
          valueColor: AlwaysStoppedAnimation(Colors.blue),
          backgroundColor: Colors.grey[200],
        ),
      ),
    ],
  )));
  list.add(Item(
      "Column(\n" +
          "    children: <Widget>[\n" +
          "      Container(\n" +
          "        padding: EdgeInsets.all(15),\n" +
          "        child: LinearProgressIndicator(\n" +
          "          value: 0.5,\n" +
          "          valueColor: AlwaysStoppedAnimation(Colors.blue),\n" +
          "          backgroundColor: Colors.grey[200],\n" +
          "        ),\n" +
          "      ),\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("CircularProgressIndicator", ItemType.TITLE));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
        padding: EdgeInsets.all(15),
        child: CircularProgressIndicator(
          value: 0.5,
          valueColor: AlwaysStoppedAnimation(Colors.blue),
          backgroundColor: Colors.grey[200],
        ),
      ),
      Container(
        padding: EdgeInsets.all(15),
        child: CircularProgressIndicator(
          valueColor: AlwaysStoppedAnimation(Colors.blue),
          backgroundColor: Colors.grey[200],
        ),
      ),
    ],
  )));
  list.add(Item(
      "Row(\n" +
          "    mainAxisAlignment: MainAxisAlignment.center,\n" +
          "    children: <Widget>[\n" +
          "      Container(\n" +
          "        padding: EdgeInsets.all(15),\n" +
          "        child: CircularProgressIndicator(\n" +
          "          value: 0.5,\n" +
          "          valueColor: AlwaysStoppedAnimation(Colors.blue),\n" +
          "          backgroundColor: Colors.grey[200],\n" +
          "        ),\n" +
          "      ),\n" +
          "      Container(\n" +
          "        padding: EdgeInsets.all(15),\n" +
          "        child: CircularProgressIndicator(\n" +
          "          valueColor: AlwaysStoppedAnimation(Colors.blue),\n" +
          "          backgroundColor: Colors.grey[200],\n" +
          "        ),\n" +
          "      ),\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  return list;
}
