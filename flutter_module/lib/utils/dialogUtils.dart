import 'package:flutter/material.dart';
import 'package:flutter_module/utils/constant.dart';
import 'package:flutter_module/utils/onClick.dart';

class DialogUtils {
  static Future<bool> showAlert(BuildContext context, OnClick click,
      {String title: "提示",
      String content,
      String leftButton,
      String rightButton}) {
    return showDialog<bool>(
      context: context,
      builder: (context) {
        List<Widget> actions = [];
        if (leftButton.isNotEmpty) {
          actions.add(FlatButton(
            child: Text(
              leftButton,
              style: TextStyle(color: Colors.orange),
            ),
            onPressed: () {
              click.onClickListener(Constant.alert_left_Button);
            }, // 关闭对话框
          ));
        }
        if (rightButton.isNotEmpty) {
          actions.add(FlatButton(
            child: Text(
              rightButton,
              style: TextStyle(color: Colors.blue),
            ),
            onPressed: () {
              click.onClickListener(Constant.alert_right_Button);
            },
          ));
        }

        return AlertDialog(
          title: Text(title),
          content: Text(content == null ? "请输入提示内容" : content),
          actions: actions,
        );
      },
    );
  }
}
