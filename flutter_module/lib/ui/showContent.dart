import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_module/base/baseWidget.dart';

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
    return null;
  }

  //设置标题
  @override
  String setTitle() {
    return title;
  }
}
