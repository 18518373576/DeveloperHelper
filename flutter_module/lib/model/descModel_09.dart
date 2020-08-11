import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_09() {
  List<Item> list = [];
  list.add(Item("简单的文本展示", ItemType.TITLE));
  list.add(Item.widgetItem(Text(
    "文本展示!" * 4, //文本内容
    textAlign: TextAlign.center, //文本展示位置
    style: TextStyle(
        //设置文本颜色
        color: Colors.blue,
        //设置文字大小
        fontSize: 30,
        //设置字重,也就是加粗
        fontWeight: FontWeight.w500,
        //设置下划线
        decoration: TextDecoration.underline,
        decorationStyle: TextDecorationStyle.dashed),
  )));
  list.add(Item("", ItemType.BODY));
  list.add(Item(
      "Text(\n" +
          "    \"文本展示!\" * 4, //文本内容\n" +
          "    textAlign: TextAlign.center, //文本展示位置\n" +
          "    style: TextStyle(\n" +
          "        //设置文本颜色\n" +
          "        color: Colors.blue,\n" +
          "        //设置文字大小\n" +
          "        fontSize: 30,\n" +
          "        //设置字重,也就是加粗\n" +
          "        fontWeight: FontWeight.w500,\n" +
          "        //设置下划线\n" +
          "        decoration: TextDecoration.underline,\n" +
          "        decorationStyle: TextDecorationStyle.dashed),\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("TextSpan", ItemType.TITLE));
  list.add(Item.widgetItem(Text.rich(TextSpan(children: [
    TextSpan(
        text: "这是一个文本片段,我是这样式儿的!",
        style: TextStyle(color: Colors.grey, fontSize: 20)),
    TextSpan(
      text: "这是另一个一个文本片段,我是这样式儿的!",
      style: TextStyle(color: Colors.blue, fontSize: 20),
    )
  ]))));
  list.add(Item("", ItemType.BODY));
  list.add(Item(
      "Text.rich(TextSpan(children: [\n" +
          "    TextSpan(\n" +
          "        text: \"这是一个文本片段,我是这样式儿的!\",\n" +
          "        style: TextStyle(color: Colors.grey, fontSize: 20)),\n" +
          "    TextSpan(\n" +
          "      text: \"这是另一个一个文本片段,我是这样式儿的!\",\n" +
          "      style: TextStyle(color: Colors.blue, fontSize: 20),\n" +
          "    )\n" +
          "  ])))",
      ItemType.CODE));
  return list;
}
