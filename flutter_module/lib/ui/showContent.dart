import 'package:flutter/material.dart';
import 'package:flutter_module/base/baseWidget.dart';
import 'package:flutter_module/model/descModel_01.dart';
import 'package:flutter_module/widgets/descListView.dart';

class ShowFlutterStudy extends BaseWidget {
  String title;

  @override
  StatefulWidget createHomePage(BuildContext context) {
    return Content(context, title);
  }

  ShowFlutterStudy(String title) {
    this.title = title;
  }
}

class Content extends BaseHomePage {
  String title;

  Content(BuildContext context, String title) : super(context) {
    this.title = title;
  }

  //设置action按钮,没有就返回空
  @override
  Widget setAction() {
    return null;
  }

  //设置内容控件
  @override
  Widget setBody() {
    switch (title) {
      case "变量与常量":
        return getDescListView(getDescList_01());
        break;
    }
  }

  //设置标题
  @override
  String setTitle() {
    return title;
  }
}
