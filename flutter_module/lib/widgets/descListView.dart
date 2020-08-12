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
          case ItemType.WIDGET:
            //如果是文本控件,直接返回,因为在Row里面会超出屏幕
            if (items[index].widget is Text) {
              return items[index].widget;
            } else {
              return Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                      padding: EdgeInsets.all(15), child: items[index].widget)
                ],
              );
            }
            break;
          default:
            return null;
        }
      });
}

enum ItemType { TITLE, BODY, CODE, BOLD_BODY, WIDGET }

class Item {
  String content;
  ItemType type;
  Widget widget;
  double widgetWidth;
  double widgetHeight;

  Item(this.content, this.type);

  Item.widgetItem(this.widget, {widgetWidth, widgetHeight}) {
    type = ItemType.WIDGET;
  }
}
