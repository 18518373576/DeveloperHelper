import 'dart:io';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/utils/colorUtil.dart';
import 'package:flutter_module/utils/themeUtils.dart';

abstract class BaseWidget extends StatelessWidget {
  final String title; //页面标题
  BaseWidget(this.title);

  // 根节点
  @override
  Widget build(BuildContext context) {
    //设置透明状态栏
    setTranStatusBar(context);
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Flutter',
        theme: ThemeData(
          primarySwatch: createMaterialColor(Color(0xFFF5F5F5)),
          accentColor: Colors.blue,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        //添加内容控件,由子类实现
        home: createHomePage(context, title));
  }

  //创建homePage,抽象方法由子类实现
  StatefulWidget createHomePage(BuildContext context, String title);
}

/*
 * 内容控件基类,实现了appBar的设置
 */
abstract class BaseHomePage extends StatefulWidget {
  final MyHomePageState homePageState = MyHomePageState();
  final BuildContext rootContext;
  final String title;

  BaseHomePage(this.rootContext, this.title);

  //设置内容
  Widget setBody();

  Widget setAction() {
    return null;
  }

  @override
  MyHomePageState createState() => homePageState;
}

class MyHomePageState extends State<BaseHomePage> {
  close() {
    if (Theme.of(context).platform == TargetPlatform.android) {
      //退出flutter应用,相当于小程序的关闭按钮
      SystemNavigator.pop();
    } else {
      exit(0);
    }
  }

  @override
  Widget build(BuildContext context) {
    //添加actions按钮
    List<Widget> actions = [];
    if (widget.setAction() != null) {
      actions.add(widget.setAction());
    }
//    actions.add(IconButton(
//      icon: Icon(Icons.close, color: Colors.black54),
//      onPressed: () {
//        close();
//      },
//    ));

    return Scaffold(
      appBar: AppBar(
        title: Text(
          widget.title,
          style: TextStyle(color: Colors.black54, fontSize: 18),
        ),
        elevation: 0,
        leading: IconButton(
          icon: Icon(Icons.arrow_back, color: Colors.black54),
          onPressed: () {
            //如果是首页的话
            if (widget.title == "Flutter") {
              close();
            } else {
              //不是首页返回上一页
              Navigator.pop(widget.rootContext);
            }
          },
        ),
        actions: actions,
      ),
      body: widget.setBody(),
      backgroundColor: Colors.white,
    );
  }
}
