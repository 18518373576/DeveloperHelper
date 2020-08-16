import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_16() {
  List<Item> list = [];
  list.add(Item(
      "Flutter中的弹性布局主要通过Flex和Expanded配合实现.Expanded可以按比例收缩Row,Column和"
      "Flex子组件所占用的空间.",
      ItemType.BODY));
  list.add(Item.widgetItem(Column(
    children: [
      Flex(
        direction: Axis.horizontal,
        children: [
          Expanded(
            //相当于安卓中的weight,子控件设置一样,均分父控件空间
            flex: 1,
            child: Container(
              margin: EdgeInsets.all(10),
              height: 45,
              color: Colors.orange,
            ),
          ),
          Expanded(
            flex: 1,
            child: Container(
              margin: EdgeInsets.all(10),
              height: 45,
              color: Colors.blue,
            ),
          ),
          Expanded(
            flex: 1,
            child: Container(
              margin: EdgeInsets.all(10),
              height: 45,
              color: Colors.green,
            ),
          )
        ],
      )
    ],
  )));
  list.add(Item(
      "Column(\n" +
          "    children: [\n" +
          "      Flex(\n" +
          "        direction: Axis.horizontal,\n" +
          "        children: [\n" +
          "          Expanded(\n" +
          "            //相当于安卓中的weight,子控件设置一样,均分父控件空间\n" +
          "            flex: 1,\n" +
          "            child: Container(\n" +
          "              margin: EdgeInsets.all(10),\n" +
          "              height: 45,\n" +
          "              color: Colors.orange,\n" +
          "            ),\n" +
          "          ),\n" +
          "          Expanded(\n" +
          "            flex: 1,\n" +
          "            child: Container(\n" +
          "              margin: EdgeInsets.all(10),\n" +
          "              height: 45,\n" +
          "              color: Colors.blue,\n" +
          "            ),\n" +
          "          ),\n" +
          "          Expanded(\n" +
          "            flex: 1,\n" +
          "            child: Container(\n" +
          "              margin: EdgeInsets.all(10),\n" +
          "              height: 45,\n" +
          "              color: Colors.green,\n" +
          "            ),\n" +
          "          )\n" +
          "        ],\n" +
          "      )\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  return list;
}
