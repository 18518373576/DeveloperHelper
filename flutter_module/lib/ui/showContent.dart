import 'package:flutter/material.dart';
import 'package:flutter_module/base/baseWidget.dart';
import 'package:flutter_module/model/descModel_01.dart';
import 'package:flutter_module/model/descModel_02.dart';
import 'package:flutter_module/model/descModel_03.dart';
import 'package:flutter_module/model/descModel_04.dart';
import 'package:flutter_module/model/descModel_05.dart';
import 'package:flutter_module/model/descModel_06.dart';
import 'package:flutter_module/model/descModel_07.dart';
import 'package:flutter_module/model/descModel_08.dart';
import 'package:flutter_module/model/descModel_09.dart';
import 'package:flutter_module/model/descModel_10.dart';
import 'package:flutter_module/widgets/descListView.dart';

class ShowFlutterStudy extends BaseWidget {
  String title;

  @override
  StatefulWidget createHomePage(BuildContext context) {
    return Content(context, title);
  }

  /*
   *构造方法
   */
  ShowFlutterStudy(this.title);
}

class Content extends BaseHomePage {
  String title;

  /*
   * 构造方法
   */
  Content(BuildContext context, this.title) : super(context);

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
      case "基本数据类型":
        return getDescListView(getDescList_02());
        break;
      case "方法":
        return getDescListView(getDescList_03());
        break;
      case "运算符":
        return getDescListView(getDescList_04());
        break;
      case "面向对象":
        return getDescListView(getDescList_05());
        break;
      case "运算符重载":
        return getDescListView(getDescList_06());
        break;
      case "异步任务":
        return getDescListView(getDescList_07());
        break;
      case "Flutter基础":
        return getDescListView(getDescList_08());
        break;
      case "文本(Text)":
        return getDescListView(getDescList_09());
        break;
      case "按钮(Button)":
        return getDescListView(getDescList_10());
        break;
    }
  }

  //设置标题
  @override
  String setTitle() {
    return title;
  }
}
