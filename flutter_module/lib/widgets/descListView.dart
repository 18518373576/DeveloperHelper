/*
获取用于描述的listView
 */
import 'package:flutter/material.dart';

getDescListView(List<Item> items) {
  return ListView.builder(
      padding: EdgeInsets.all(10),
      itemCount: items.length,
      itemBuilder: (BuildContext context, int index) {
        switch (items[index].type) {
          //添加title
          case ItemType.TITLE:
            return Text(
              '\n${items[index].content}\n',
              textAlign: TextAlign.center,
              style: TextStyle(
                  fontSize: 18,
                  color: Colors.orange,
                  height: 1.5,
                  fontWeight: FontWeight.w500),
            );
            break;
          //添加内容
          case ItemType.BODY:
            //首航缩进
            return Text(
              '        ${items[index].content}',
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.black54,
                  height: 1.5,
                  fontWeight: FontWeight.w300),
            );
            break;
          //添加代码
          case ItemType.CODE:
            return Container(
                padding: EdgeInsets.all(15),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(5),
                  color: Color(0xFF282b2e),
                ),
                child: SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Text(
                    '${items[index].content}',
                    style: TextStyle(
                      fontSize: 15,
                      color: Color(0xFF969696),
                      fontWeight: FontWeight.w500,
                      height: 1.5,
                    ),
                  ),
                ));

            break;
          //添加加粗的内容
          case ItemType.BOLD_BODY:
            //首航缩进
            return Text(
              '        ${items[index].content}',
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.black54,
                  fontWeight: FontWeight.w500,
                  height: 1.5),
            );
            break;
          case ItemType.WIDGET:
            return items[index].widget;
            break;
        }
      });
}

enum ItemType { TITLE, BODY, CODE, BOLD_BODY, WIDGET }

class Item {
  String content;
  ItemType type;
  Widget widget;

  Item(this.content, this.type);

  Item.widgetItem(this.widget) {
    type = ItemType.WIDGET;
  }
}
