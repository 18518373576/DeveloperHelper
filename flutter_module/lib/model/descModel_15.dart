import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_15() {
  List<Item> list = [];
  list.add(Item("Row", ItemType.TITLE));
  list.add(Item("Row可以在水平方向排列子Widget,有如下参数:", ItemType.BODY));
  list.add(Item(
      "①textDirection：表示水平方向子组件的布局顺序(是从左往右还是从右往左)，"
      "默认为系统当前Locale环境的文本方向(如中文、英语都是从左往右，而阿拉伯语是从右往左)。",
      ItemType.BOLD_BODY));
  list.add(Item(
      "②mainAxisSize：表示Row在主轴(水平)方向占用的空间，默认是MainAxisSize.max，"
      "表示尽可能多的占用水平方向的空间，此时无论子widgets实际占用多少水平空间，"
      "Row的宽度始终等于水平方向的最大宽度；而MainAxisSize.min表示尽可能少的占用水平空间，"
      "当子组件没有占满水平剩余空间，则Row的实际宽度等于所有子组件占用的的水平空间；",
      ItemType.BOLD_BODY));
  list.add(Item(
      "③mainAxisAlignment：表示子组件在Row所占用的水平空间内对齐方式，"
      "如果mainAxisSize值为MainAxisSize.min，则此属性无意义，因为子组件的宽度等于Row的宽度。"
      "只有当mainAxisSize的值为MainAxisSize.max时，此属性才有意义，MainAxisAlignment.start表示沿textDirection的初始方向对齐，"
      "如textDirection取值为TextDirection.ltr时，则MainAxisAlignment.start表示左对齐，"
      "textDirection取值为TextDirection.rtl时表示从右对齐。而MainAxisAlignment.end和MainAxisAlignment.start正好相反；"
      "MainAxisAlignment.center表示居中对齐。",
      ItemType.BOLD_BODY));
  list.add(Item(
      "④verticalDirection：表示Row纵轴（垂直）的对齐方向，默认是VerticalDirection.down，表示从上到下。",
      ItemType.BOLD_BODY));
  list.add(Item(
      "⑤crossAxisAlignment：表示子组件在纵轴方向的对齐方式，Row的高度等于子组件中最高的子元素高度，"
      "它的取值和MainAxisAlignment一样(包含start、end、 center三个值)，不同的是crossAxisAlignment的参考系是verticalDirection，"
      "即verticalDirection值为VerticalDirection.down时crossAxisAlignment.start指顶部对齐，"
      "verticalDirection值为VerticalDirection.up时，crossAxisAlignment.start指底部对齐；"
      "而crossAxisAlignment.end和crossAxisAlignment.start正好相反；",
      ItemType.BOLD_BODY));
  list.add(Item("⑥children ：子组件数组。", ItemType.BOLD_BODY));
  list.add(Item.widgetItem(Container(
    margin: EdgeInsets.all(15),
    child: Row(
      //子控件水平方向对齐方式
      mainAxisAlignment: MainAxisAlignment.center,
      //子控件竖向对齐方式
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Icon(
          Icons.android,
          color: Colors.green,
          size: 60,
        ),
        Text(
          "测试一下Row布局",
          style: TextStyle(
              color: Colors.black54, fontWeight: FontWeight.w700, fontSize: 30),
        )
      ],
    ),
  )));
  list.add(Item(
      "Row(\n" +
          "    //子控件水平方向对齐方式\n" +
          "    mainAxisAlignment: MainAxisAlignment.center,\n" +
          "    //子控件竖向对齐方式\n" +
          "    crossAxisAlignment: CrossAxisAlignment.center,\n" +
          "    children: <Widget>[\n" +
          "      Icon(\n" +
          "        Icons.android,\n" +
          "        color: Colors.green,\n" +
          "        size: 60,\n" +
          "      ),\n" +
          "      Text(\n" +
          "        \"测试一下Row布局\",\n" +
          "        style: TextStyle(\n" +
          "            color: Colors.black54, fontWeight: FontWeight.w700, fontSize: 30),\n" +
          "      )\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("Column", ItemType.TITLE));
  list.add(Item("Column表示竖向排列布局.参数和Row一样.", ItemType.BODY));
  list.add(Item.widgetItem(Column(
    //控制Column高度为包裹内容
    mainAxisSize: MainAxisSize.min,
    //表示在竖直方向的对齐方式,mainAxisSize为 MainAxisSize.min时,
    //此参数没有意义
    //mainAxisAlignment: MainAxisAlignment.center,
    //在水平方向的对齐方式
    crossAxisAlignment: CrossAxisAlignment.center,
    children: [
      Text(
        "Column",
        style: TextStyle(
            height: 1.5,
            color: Colors.green,
            fontWeight: FontWeight.w700,
            fontSize: 30),
      ),
      Text(
        "布局",
        style: TextStyle(
            height: 1.5,
            color: Colors.blue,
            fontWeight: FontWeight.w700,
            fontSize: 30),
      ),
      Text(
        "Column布局",
        style: TextStyle(
            height: 1.5,
            color: Colors.orange,
            fontWeight: FontWeight.w700,
            fontSize: 30),
      )
    ],
  )));
  list.add(Item(
      "Column(\n" +
          "    //控制Column高度为包裹内容\n" +
          "    mainAxisSize: MainAxisSize.min,\n" +
          "    //表示在竖直方向的对齐方式,mainAxisSize为 MainAxisSize.min时,\n" +
          "    //此参数没有意义\n" +
          "    //mainAxisAlignment: MainAxisAlignment.center,\n" +
          "    //在水平方向的对齐方式\n" +
          "    crossAxisAlignment: CrossAxisAlignment.center,\n" +
          "    children: [\n" +
          "      Text(\n" +
          "        \"Column\",\n" +
          "        style: TextStyle(\n" +
          "            height: 1.5,\n" +
          "            color: Colors.green,\n" +
          "            fontWeight: FontWeight.w700,\n" +
          "            fontSize: 30),\n" +
          "      ),\n" +
          "      Text(\n" +
          "        \"布局\",\n" +
          "        style: TextStyle(\n" +
          "            height: 1.5,\n" +
          "            color: Colors.blue,\n" +
          "            fontWeight: FontWeight.w700,\n" +
          "            fontSize: 30),\n" +
          "      ),\n" +
          "      Text(\n" +
          "        \"Column布局\",\n" +
          "        style: TextStyle(\n" +
          "            height: 1.5,\n" +
          "            color: Colors.orange,\n" +
          "            fontWeight: FontWeight.w700,\n" +
          "            fontSize: 30),\n" +
          "      )\n" +
          "    ],\n" +
          "  )",
      ItemType.CODE));
  return list;
}
