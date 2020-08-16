import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_19() {
  List<Item> list = [];
  list.add(Item("Align", ItemType.TITLE));
  list.add(Item("如果我们只想简单的调整一个子组件在父组件中的位置的话,使用Align会更简单一些.", ItemType.BODY));
  list.add(Item.widgetItem(Column(
    children: [
      Container(
        height: 120,
        width: 120,
        margin: EdgeInsets.all(15),
        decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(10), color: Colors.orange),
        child: Align(
          alignment: Alignment.center,
          child: FlutterLogo(
            size: 50,
          ),
        ),
      )
    ],
  )));
  list.add(Item(
      "Container(\n" +
          "  height: 120,\n" +
          "  width: 120,\n" +
          "  margin: EdgeInsets.all(15),\n" +
          "  decoration: BoxDecoration(\n" +
          "      borderRadius: BorderRadius.circular(10), color: Colors.orange),\n" +
          "  child: Align(\n" +
          "    alignment: Alignment.center,\n" +
          "    child: FlutterLogo(\n" +
          "      size: 50,\n" +
          "    ),\n" +
          "  ),\n" +
          ")",
      ItemType.CODE));
  return list;
}
