import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_10() {
  List<Item> list = [];
  list.add(Item("RaisedButton", ItemType.TITLE));
  list.add(Item("RaisedButton默认带有背景", ItemType.BODY));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
          padding: EdgeInsets.all(15),
          child: RaisedButton(
            child: Text("按钮"),
            onPressed: () {},
          ))
    ],
  )));
  list.add(Item(
      "RaisedButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("FlatButton", ItemType.TITLE));
  list.add(Item("FlatButton默认背景透明,带有点击效果", ItemType.BODY));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
          padding: EdgeInsets.all(15),
          child: FlatButton(
            child: Text("按钮"),
            onPressed: () {},
          ))
    ],
  )));
  list.add(Item(
      "FlatButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("OutlineButton", ItemType.TITLE));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
          padding: EdgeInsets.all(15),
          child: OutlineButton(
            child: Text("按钮"),
            borderSide: BorderSide(color: Colors.blue, width: 1),
            onPressed: () {},
          ))
    ],
  )));
  list.add(Item(
      "OutlineButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    borderSide: BorderSide(color: Colors.blue, width: 1),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("IconButton", ItemType.TITLE));
  list.add(Item.widgetItem(IconButton(
    icon: Icon(Icons.call),
    iconSize: 50,
    color: Colors.blue,
    onPressed: () {},
  )));
  list.add(Item(
      "IconButton(\n" +
          "    icon: Icon(Icons.call),\n" +
          "    iconSize: 50,\n" +
          "    color: Colors.blue,\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("带图标的按钮", ItemType.TITLE));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
          padding: EdgeInsets.all(15),
          child: OutlineButton.icon(
              onPressed: () {}, icon: Icon(Icons.add), label: Text("添加")))
    ],
  )));
  list.add(Item(
      "OutlineButton.icon(\n" +
          "      onPressed: () {}, icon: Icon(Icons.add), label: Text(\"添加\"))",
      ItemType.CODE));
  list.add(Item("定义按钮属性", ItemType.TITLE));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: <Widget>[
      Container(
          padding: EdgeInsets.all(15),
          child: RaisedButton(
            //按钮颜色
            color: Colors.blue,
            //点击后的颜色
            highlightColor: Colors.blue[700],
            //水波纹颜色,这里设置透明色
            splashColor: Colors.transparent,
            child: Text("按钮"),
            textColor: Colors.white,
            //设置圆角背景
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
            elevation: 3,
            highlightElevation: 5,
            onPressed: () {},
          ))
    ],
  )));
  list.add(Item(
      "RaisedButton(\n" +
          "    //按钮颜色\n" +
          "    color: Colors.blue,\n" +
          "    //点击后的颜色\n" +
          "    highlightColor: Colors.blue[700],\n" +
          "    //水波纹颜色,这里设置透明色\n" +
          "    splashColor: Colors.transparent,\n" +
          "    child: Text(\"按钮\"),\n" +
          "    textColor: Colors.white,\n" +
          "    //设置圆角背景\n" +
          "    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),\n" +
          "    elevation: 3,\n" +
          "    highlightElevation: 5,\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  return list;
}
