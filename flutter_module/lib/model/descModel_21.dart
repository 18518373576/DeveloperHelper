import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_21() {
  List<Item> list = [];
  list.add(Item("尺寸限制类容器用于限制控件的大小.", ItemType.BODY));
  list.add(Item("ConstrainedBox", ItemType.TITLE));
  list.add(Item("ConstrainedBox用于对子组件添加额外的约束.", ItemType.BODY));
  list.add(Item.widgetItem(ConstrainedBox(
    //使宽度尽可能的大,最小高度为50
    constraints: BoxConstraints(minWidth: double.infinity, minHeight: 50),
    child: Container(
      margin: EdgeInsets.only(left: 10, right: 10),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10), color: Colors.orange),
    ),
  )));
  list.add(Item(
      "ConstrainedBox(\n" +
          "    //使宽度尽可能的大,最小高度为50\n" +
          "    constraints: BoxConstraints(minWidth: double.infinity, minHeight: 50),\n" +
          "    child: Container(\n" +
          "      margin: EdgeInsets.only(left: 10, right: 10),\n" +
          "      decoration: BoxDecoration(\n" +
          "          borderRadius: BorderRadius.circular(10), color: Colors.orange),\n" +
          "    ),\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("SizeBox", ItemType.TITLE));
  list.add(Item("SizeBox用于给子控件指定固定的宽高.", ItemType.BODY));
  return list;
}
