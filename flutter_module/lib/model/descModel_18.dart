import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_18() {
  List<Item> list = [];
  list.add(Item(
      "和Android中的FrameLayout布局相似,子组件根据父组件容器的四个角的位置来确定"
      "自身的位置,允许子组件堆叠起来.",
      ItemType.BODY));
  list.add(Item.widgetItem(Column(
    children: [
      Stack(
        alignment: Alignment.topLeft,
        children: [
          Container(
            child: Text(
              "hello world! hello world! hello world! hello world! hello world! "
              "hello world! hello world! hello world!",
              style: TextStyle(color: Colors.white),
            ),
            margin: EdgeInsets.all(15),
            padding: EdgeInsets.all(15),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(5),
              color: Colors.blue,
            ),
          ),
          Positioned(
            right: 20,
            top: 20,
            child: Icon(
              Icons.content_copy,
              size: 20,
              color: Colors.white,
            ),
          )
        ],
      )
    ],
  )));
  list.add(Item(
      "Stack(\n" +
          "  alignment: Alignment.topLeft,\n" +
          "  children: [\n" +
          "    Container(\n" +
          "      child: Text(\n" +
          "        \"hello world! hello world! hello world! hello world! hello world! \"\n" +
          "        \"hello world! hello world! hello world!\",\n" +
          "        style: TextStyle(color: Colors.white),\n" +
          "      ),\n" +
          "      padding: EdgeInsets.all(15),\n" +
          "      decoration: BoxDecoration(\n" +
          "        borderRadius: BorderRadius.circular(5),\n" +
          "        color: Colors.blue,\n" +
          "      ),\n" +
          "    ),\n" +
          "    Positioned(\n" +
          "      right: 5,\n" +
          "      top: 5,\n" +
          "      child: Icon(\n" +
          "        Icons.content_copy,\n" +
          "        size: 20,\n" +
          "        color: Colors.white,\n" +
          "      ),\n" +
          "    )\n" +
          "  ],\n" +
          ")",
      ItemType.CODE));
  return list;
}
