import 'package:flutter/material.dart';
import 'package:flutter_module/base/baseWidget.dart';
import 'package:flutter_module/model/descModel_19.dart';
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
import 'package:flutter_module/model/descModel_14.dart';
import 'package:flutter_module/model/descModel_15.dart';
import 'package:flutter_module/model/descModel_16.dart';
import 'package:flutter_module/model/descModel_17.dart';
import 'package:flutter_module/model/descModel_18.dart';
import 'package:flutter_module/model/descModel_20.dart';
import 'package:flutter_module/model/descModel_21.dart';
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
      case "单选按钮和复选框":
        return getDescListView(getDescList_12(this));
      case "输入框和表单":
        return getDescListView(getDescList_13(rootContext));
      case "进度指示器":
        return getDescListView(getDescList_14());
        break;
      case "线性布局(Row和Column)":
        return getDescListView(getDescList_15());
        break;
      case "弹性布局(Flex)":
        return getDescListView(getDescList_16());
        break;
      case "流式布局(Wrap和Flow)":
        return getDescListView(getDescList_17());
        break;
      case "层叠布局(Stack)":
        return getDescListView(getDescList_18());
        break;
      case "相对定位(Align)":
        return getDescListView(getDescList_19());
        break;
      case "填充(Padding)":
        return getDescListView(getDescList_20());
        break;
      case "尺寸限制容器":
        return getDescListView(getDescList_21());
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
