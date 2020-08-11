import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_10() {
  List<Item> list = [];
  list.add(Item("RaisedButton", ItemType.TITLE));
  list.add(Item("RaisedButton默认带有背景", ItemType.BODY));
  list.add(Item.widgetItem(RaisedButton(
    child: Text("按钮"),
    onPressed: () {},
  )));
  list.add(Item(
      "RaisedButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("FlatButton", ItemType.TITLE));
  list.add(Item("FlatButton默认背景透明,带有点击效果", ItemType.BODY));
  list.add(Item.widgetItem(FlatButton(
    child: Text("按钮"),
    onPressed: () {},
  )));
  list.add(Item(
      "FlatButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("OutlineButton", ItemType.TITLE));
  list.add(Item.widgetItem(OutlineButton(
    child: Text("按钮"),
    borderSide: BorderSide(color: Colors.blue, width: 1),
    onPressed: () {},
  )));
  list.add(Item(
      "OutlineButton(\n" +
          "    child: Text(\"按钮\"),\n" +
          "    borderSide: BorderSide(color: Colors.blue, width: 1),\n" +
          "    onPressed: () {},\n" +
          "  )",
      ItemType.CODE));
  return list;
}
