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
import 'package:flutter_module/model/descModel_11.dart';
import 'package:flutter_module/model/descModel_12.dart';
import 'package:flutter_module/model/descModel_13.dart';
import 'package:flutter_module/utils/constant.dart';
import 'package:flutter_module/utils/onClick.dart';
import 'package:flutter_module/widgets/descListView.dart';

class ShowFlutterStudy extends BaseWidget {
  @override
  StatefulWidget createHomePage(BuildContext context, String title) {
    return Content(context, title);
  }

  /*
   *构造方法
   */
  ShowFlutterStudy(String title) : super(title);
}

class Content extends BaseHomePage implements OnClick {
  Content(BuildContext rootContext, String title) : super(rootContext, title);

  //设置内容控件
  @override
  Widget setBody() {
    switch (title) {
      case "变量与常量":
        return getDescListView(getDescList_01());
      case "基本数据类型":
        return getDescListView(getDescList_02());
      case "方法":
        return getDescListView(getDescList_03());
      case "运算符":
        return getDescListView(getDescList_04());
      case "面向对象":
        return getDescListView(getDescList_05());
      case "运算符重载":
        return getDescListView(getDescList_06());
      case "异步任务":
        return getDescListView(getDescList_07());
      case "Flutter基础":
        return getDescListView(getDescList_08());
      case "文本(Text)":
        return getDescListView(getDescList_09());
      case "按钮(Button)":
        return getDescListView(getDescList_10());
      case "Image和Icon":
        return getDescListView(getDescList_11());
        break;
      case "单选按钮和复选框":
        return getDescListView(getDescList_12(this));
        break;
      case "输入框和表单":
        return getDescListView(getDescList_13(rootContext));
        break;
      default:
        return null;
    }
  }

  @override
  onClickListener(int id, {var value}) {
    switch (id) {
      case Constant.switch_id:
        homePageState.setState(() {
          setValue(value);
        });
        break;
    }
  }
}
