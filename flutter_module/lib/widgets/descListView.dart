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
              '${items[index].content}',
              textAlign: TextAlign.center,
              style: TextStyle(
                  fontSize: 18,
                  color: Colors.orange,
                  height: 2,
                  wordSpacing: 2,
                  fontWeight: FontWeight.w500),
            );
          //添加内容
          case ItemType.BODY:
            //首航缩进
            return Text(
              '    ${items[index].content}',
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.black54,
                  height: 1.8,
                  wordSpacing: 2,
                  fontWeight: FontWeight.w300),
            );
          //添加代码
          case ItemType.CODE:
            return Container(
                margin: EdgeInsets.only(top: 15, bottom: 15),
                padding: EdgeInsets.all(15),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(5),
                  color: Color(0xFF282b2e),
                ),
                child: SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: SelectableText(
                    '${items[index].content}',
                    style: TextStyle(
                      fontSize: 15,
                      wordSpacing: 2,
                      color: Color(0xFF969696),
                      fontWeight: FontWeight.w500,
                      height: 1.8,
                    ),
                  ),
                ));
          //添加加粗的内容
          case ItemType.BOLD_BODY:
            //首航缩进
            return Text(
              '    ${items[index].content}',
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.black54,
                  fontWeight: FontWeight.w500,
                  wordSpacing: 2,
                  height: 1.8),
            );
          case ItemType.WIDGET:
            //如果是文本控件,直接返回,因为在Row里面会超出屏幕
            return items[index].widget;
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

  Item.widgetItem(this.widget, {this.widgetWidth, this.widgetHeight}) {
    type = ItemType.WIDGET;
  }
}
