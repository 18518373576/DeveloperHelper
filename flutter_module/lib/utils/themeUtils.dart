import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

setTranStatusBar(BuildContext context) {
  // 除半透明状态栏
  if (Theme.of(context).platform == TargetPlatform.android) {
    // android 平台
    SystemUiOverlayStyle _style =
        SystemUiOverlayStyle(statusBarColor: Colors.transparent);
    SystemChrome.setSystemUIOverlayStyle(_style);
  }
}
