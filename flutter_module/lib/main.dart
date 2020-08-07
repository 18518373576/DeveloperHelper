import 'package:flutter/material.dart';
import 'package:flutter_module/base/baseWidget.dart';
import 'package:flutter_module/model/mainModel.dart';
import 'package:flutter_module/ui/showContent.dart';

void main() => runApp(MyApp());

/*
 * BaseWidget
 */
class MyApp extends BaseWidget {
  @override
  StatefulWidget createHomePage(BuildContext context) {
    return Content(context);
  }
}

// ignore: must_be_immutable
class Content extends BaseHomePage {
  List<String> list = getData();

  Content(BuildContext context) : super(context);

  @override
  Widget setBody() {
    //listView
    return ListView.separated(
      itemCount: list.length,
      //列表项构造器
      itemBuilder: (BuildContext context, int index) {
        return ListTile(
          //item点击事件
          onTap: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) {
              return ShowFlutterStudy('${list[index]}');
            }));
          },
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(
                '${list[index]}',
                style: TextStyle(fontSize: 16, color: Colors.black54),
              ),
              Icon(
                Icons.chevron_right,
                color: Colors.black45,
              ),
            ],
          ),
          leading: Icon(
            Icons.list,
            color: Colors.black45,
          ),
        );
      },
      //分割线构造器
      separatorBuilder: (BuildContext context, int index) {
        return Divider(color: Colors.black12);
      },
    );
  }

  @override
  String setTitle() {
    return "Flutter";
  }

  /**
   * 添加右侧更多按钮
   */
  @override
  Widget setAction() {
    return PopupMenuButton<String>(
      icon: Icon(Icons.hdr_weak, color: Colors.black54),
      itemBuilder: (BuildContext context) => <PopupMenuItem<String>>[
            PopupMenuItem(
              child: Text('菜单1'),
            ),
            PopupMenuItem(
              child: Text('菜单2'),
            ),
            PopupMenuItem(
              child: Text('菜单3'),
            ),
          ],
    );
  }
}
