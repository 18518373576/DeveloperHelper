import 'package:flutter/material.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_20() {
  List<Item> list = [];
  list.add(Item("Padding可以给其子组件添加填充.", ItemType.BODY));
  list.add(Item.widgetItem(Container(
    //设置margin
    margin: EdgeInsets.all(10),
    //设置圆角背景
    decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10), color: Colors.grey[100]),
    child: Padding(
      padding: EdgeInsets.all(15),
      child: Flex(
        direction: Axis.horizontal,
        children: [
          Icon(
            Icons.android,
            size: 45,
            color: Colors.green,
          ),
          Expanded(
            child: Padding(
              padding: EdgeInsets.only(left: 15),
              //使用Wrap,使子控件换行展示
              child: Wrap(
                //子控件的间距
                runSpacing: 5,
                //虽然布局是竖直方向显示的,但是这里要设置horizontal
                direction: Axis.horizontal,
                children: [
                  //第一行包含两个控件元素,标题和时间
                  Expanded(
                      child: Row(
                    children: [
                      Expanded(
                        child: Text(
                          "标题",
                          style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.w500,
                              color: Colors.black54),
                        ),
                        flex: 1,
                      ),
                      Text(
                        "2020-08-16",
                        style: TextStyle(
                            fontSize: 12,
                            fontWeight: FontWeight.w300,
                            color: Colors.black54),
                      )
                    ],
                  )),
                  //这里是内容控件
                  Expanded(
                    child: Text(
                      "这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!"
                      "这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!",
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                      style: TextStyle(
                          color: Colors.black45, fontWeight: FontWeight.w400),
                    ),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    ),
  )));
  list.add(Item(
      "Container(\n" +
          "    //设置margin\n" +
          "    margin: EdgeInsets.all(10),\n" +
          "    //设置圆角背景\n" +
          "    decoration: BoxDecoration(\n" +
          "        borderRadius: BorderRadius.circular(10), color: Colors.grey[100]),\n" +
          "    child: Padding(\n" +
          "      padding: EdgeInsets.all(15),\n" +
          "      child: Flex(\n" +
          "        direction: Axis.horizontal,\n" +
          "        children: [\n" +
          "          Icon(\n" +
          "            Icons.android,\n" +
          "            size: 45,\n" +
          "            color: Colors.green,\n" +
          "          ),\n" +
          "          Expanded(\n" +
          "            child: Padding(\n" +
          "              padding: EdgeInsets.only(left: 15),\n" +
          "              //使用Wrap,使子控件换行展示\n" +
          "              child: Wrap(\n" +
          "                //子控件的间距\n" +
          "                runSpacing: 5,\n" +
          "                //虽然布局是竖直方向显示的,但是这里要设置horizontal\n" +
          "                direction: Axis.horizontal,\n" +
          "                children: [\n" +
          "                  //第一行包含两个控件元素,标题和时间\n" +
          "                  Expanded(\n" +
          "                      child: Row(\n" +
          "                    children: [\n" +
          "                      Expanded(\n" +
          "                        child: Text(\n" +
          "                          \"标题\",\n" +
          "                          style: TextStyle(\n" +
          "                              fontSize: 16,\n" +
          "                              fontWeight: FontWeight.w500,\n" +
          "                              color: Colors.black54),\n" +
          "                        ),\n" +
          "                        flex: 1,\n" +
          "                      ),\n" +
          "                      Text(\n" +
          "                        \"2020-08-16\",\n" +
          "                        style: TextStyle(\n" +
          "                            fontSize: 12,\n" +
          "                            fontWeight: FontWeight.w300,\n" +
          "                            color: Colors.black54),\n" +
          "                      )\n" +
          "                    ],\n" +
          "                  )),\n" +
          "                  //这里是内容控件\n" +
          "                  Expanded(\n" +
          "                    child: Text(\n" +
          "                      \"这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!\"\n" +
          "                      \"这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!这是文本!\",\n" +
          "                      maxLines: 2,\n" +
          "                      overflow: TextOverflow.ellipsis,\n" +
          "                      style: TextStyle(\n" +
          "                          color: Colors.black45, fontWeight: FontWeight.w400),\n" +
          "                    ),\n" +
          "                  )\n" +
          "                ],\n" +
          "              ),\n" +
          "            ),\n" +
          "          )\n" +
          "        ],\n" +
          "      ),\n" +
          "    ),\n" +
          "  )",
      ItemType.CODE));
  return list;
}
