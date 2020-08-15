import 'package:flutter/material.dart';
import 'package:flutter_module/utils/dialogUtils.dart';
import 'package:flutter_module/utils/onClick.dart';
import 'package:flutter_module/widgets/descListView.dart';

class Click implements OnClick {
  BuildContext context;

  @override
  onClickListener(int id, {value}) {
    Navigator.of(context).pop();
  }

  Click(this.context);
}

getDescList_13(BuildContext context) {
  //保存输入框输入内容
  TextEditingController _controller = TextEditingController();
  TextEditingController _formController = TextEditingController();
  //获取form状态
  GlobalKey _formKey = new GlobalKey<FormState>();
  List<Item> list = [];
  list.add(Item("TextFiled", ItemType.TITLE));
  list.add(
      Item.widgetItem(Column(mainAxisSize: MainAxisSize.min, children: <Widget>[
    Container(
      height: 20,
    ),
    TextField(
      controller: _controller,
      //输入框描述
      decoration: InputDecoration(
          //获得焦点时的边框
          focusedBorder:
              OutlineInputBorder(borderSide: BorderSide(color: Colors.blue)),
          //能够使用时的边框
          enabledBorder: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.grey[200])),
          labelText: "用户名",
          labelStyle: TextStyle(color: Colors.grey),
          hintText: "手机号或邮箱",
          hintStyle: TextStyle(color: Colors.grey, fontSize: 16),
          prefixIcon: Icon(
            Icons.account_box,
            color: Colors.blue,
            size: 20,
          )),
    ),
    Container(
      height: 20,
    ),
    TextField(
      //输入限制
      maxLength: 16,
      //达到输入限制字符后,阻止输入
      maxLengthEnforced: true,
      obscureText: true,
      decoration: InputDecoration(
          focusedBorder:
              OutlineInputBorder(borderSide: BorderSide(color: Colors.blue)),
          enabledBorder: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.grey[200])),
          labelText: "密码",
          labelStyle: TextStyle(color: Colors.grey),
          hintText: "请输入密码",
          hintStyle: TextStyle(color: Colors.grey, fontSize: 16),
          prefixIcon: Icon(
            Icons.vpn_key,
            color: Colors.blue,
            size: 20,
          )),
    ),
    RaisedButton(
      //按钮颜色
      color: Colors.blue,
      //点击后的颜色
      highlightColor: Colors.blue[700],
      //水波纹颜色,这里设置透明色
      splashColor: Colors.transparent,
      child: Text("登录"),
      textColor: Colors.white,
      //设置圆角背景
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
      elevation: 3,
      highlightElevation: 5,
      onPressed: () async {
        //弹出对话框并等待其关闭
        await DialogUtils.showAlert(context, Click(context),
            content: "用户名:${_controller.text}",
            leftButton: "取消",
            rightButton: "登录");
      },
    ),
    Container(
      height: 20,
    )
  ])));
  list.add(Item(
      "TextField(\n" +
          "      //自动获取焦点\n" +
          "      autofocus: true,\n" +
          "      //输入框描述\n" +
          "      decoration: InputDecoration(\n" +
          "          //获得焦点时的边框\n" +
          "          focusedBorder:\n" +
          "              OutlineInputBorder(borderSide: BorderSide(color: Colors.blue)),\n" +
          "          //能够使用时的边框\n" +
          "          enabledBorder: OutlineInputBorder(\n" +
          "              borderSide: BorderSide(color: Colors.grey[200])),\n" +
          "          labelText: \"用户名\",\n" +
          "          labelStyle: TextStyle(color: Colors.grey),\n" +
          "          hintText: \"手机号或邮箱\",\n" +
          "          hintStyle: TextStyle(color: Colors.grey, fontSize: 16),\n" +
          "          prefixIcon: Icon(\n" +
          "            Icons.account_box,\n" +
          "            color: Colors.blue,\n" +
          "            size: 20,\n" +
          "          )),\n" +
          "    )",
      ItemType.CODE));

  list.add(Item("表单Form", ItemType.TITLE));
  list.add(Item("Flutter提供了一个Form组件,它可以对输入框进行分组,进行一些统一操作.比如校验和清除一组输入框数据.",
      ItemType.BODY));
  list.add(Item("", ItemType.BODY));
  list.add(Item.widgetItem(Form(
    autovalidate: true,
    key: _formKey,
    child: Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        TextFormField(
          controller: _formController,
          //内容校验
          validator: (v) {
            return v.isEmpty ? "用户名不能为空" : null;
          },
          //输入框描述
          decoration: InputDecoration(
              //获得焦点时的边框
              focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.blue)),
              //能够使用时的边框
              enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.grey[200])),
              errorBorder:
                  OutlineInputBorder(borderSide: BorderSide(color: Colors.red)),
              focusedErrorBorder:
                  OutlineInputBorder(borderSide: BorderSide(color: Colors.red)),
              labelText: "用户名",
              labelStyle: TextStyle(color: Colors.grey),
              hintText: "手机号或邮箱",
              hintStyle: TextStyle(color: Colors.grey, fontSize: 16),
              prefixIcon: Icon(
                Icons.account_box,
                color: Colors.blue,
                size: 20,
              )),
        ),
      ],
    ),
  )));
  list.add(Item("", ItemType.BODY));
  list.add(Item(
      "Form(\n" +
          "    autovalidate: true,\n" +
          "    key: _formKey,\n" +
          "    child: Column(\n" +
          "      children: <Widget>[\n" +
          "        TextFormField(\n" +
          "          controller: _formController,\n" +
          "          //内容校验\n" +
          "          validator: (v) {\n" +
          "            return v.isEmpty ? \"用户名不能为空\" : null;\n" +
          "          },\n" +
          "          //输入框描述\n" +
          "          decoration: InputDecoration(\n" +
          "              //获得焦点时的边框\n" +
          "              focusedBorder: OutlineInputBorder(\n" +
          "                  borderSide: BorderSide(color: Colors.blue)),\n" +
          "              //能够使用时的边框\n" +
          "              enabledBorder: OutlineInputBorder(\n" +
          "                  borderSide: BorderSide(color: Colors.grey[200])),\n" +
          "              errorBorder:\n" +
          "                  OutlineInputBorder(borderSide: BorderSide(color: Colors.red)),\n" +
          "              focusedErrorBorder:\n" +
          "                  OutlineInputBorder(borderSide: BorderSide(color: Colors.red)),\n" +
          "              labelText: \"用户名\",\n" +
          "              labelStyle: TextStyle(color: Colors.grey),\n" +
          "              hintText: \"手机号或邮箱\",\n" +
          "              hintStyle: TextStyle(color: Colors.grey, fontSize: 16),\n" +
          "              prefixIcon: Icon(\n" +
          "                Icons.account_box,\n" +
          "                color: Colors.blue,\n" +
          "                size: 20,\n" +
          "              )),\n" +
          "        ),\n" +
          "      ],\n" +
          "    ),\n" +
          "  )",
      ItemType.CODE));
  return list;
}
