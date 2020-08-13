import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/base/baseWidget.dart';
import 'package:flutter_module/model/mainModel.dart';
import 'package:flutter_module/ui/showContent.dart';

void main() => runApp(MyApp("Flutter"));

/*
 * BaseWidget
 */
class MyApp extends BaseWidget {
  MyApp(String title) : super(title);

  @override
  StatefulWidget createHomePage(BuildContext context, String title) {
    return Content(context, title);
  }
}

class Content extends BaseHomePage {
  final List<String> list = getData();

  Content(BuildContext rootContext, String title) : super(rootContext, title);

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
            Navigator.push(context, CupertinoPageRoute(builder: (context) {
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
          leading: Image.asset(
            index <= 6
                ? 'asserts/images/icon_dart.png'
                : 'asserts/images/icon_flutter.png',
            width: 25,
            height: 25,
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

  /*
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
