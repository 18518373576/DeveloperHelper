import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/utils/colorUtil.dart';
import 'package:flutter_module/utils/themeUtils.dart';

abstract class BaseWidget extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    //设置透明状态栏
    setTranStatusBar(context);
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: createMaterialColor(Color(0xFFF5F5F5)),
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      //添加内容控件,由子类实现
      home: createHomePage(context),
    );
  }

  //创建homePage,抽象方法由子类实现
  StatefulWidget createHomePage(BuildContext context);
}

/*
 * 内容控件基类,实现了appBar的设置
 */
abstract class BaseHomePage extends StatefulWidget {
  //在返回上一页的时候需要一个顶层控件的BuildContext.所以需要传过来
  BuildContext topContext;

  BaseHomePage(BuildContext context) {
    this.topContext = context;
  }

  //设置标题
  String setTitle();

  //设置内容
  Widget setBody();

  Widget setAction();

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<BaseHomePage> {
  close() {
    if (Theme.of(context).platform == TargetPlatform.android) {
      //退出flutter应用,相当于小程序的关闭按钮
      SystemNavigator.pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    //添加actions按钮
    List<Widget> actions = [];
    if (widget.setAction() != null) {
      actions.add(widget.setAction());
    }
    actions.add(IconButton(
      icon: Icon(Icons.close, color: Colors.black54),
      onPressed: () {
        close();
      },
    ));

    return Scaffold(
      appBar: AppBar(
        title: Text(
          widget.setTitle(),
          style: TextStyle(color: Colors.black54, fontSize: 18),
        ),
        elevation: 0,
        leading: IconButton(
          icon: Icon(Icons.arrow_back, color: Colors.black54),
          onPressed: () {
            //如果是首页的话
            if (widget.setTitle() == "Flutter") {
              close();
            } else {
              //不是首页返回上一页
              Navigator.pop(widget.topContext);
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
