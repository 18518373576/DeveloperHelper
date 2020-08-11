import 'package:flutter_module/widgets/descListView.dart';

getDescList_07() {
  List<Item> list = [];
  list.add(
      Item("在Dart中,通常返回Future对象或Stream对象的方法就是异步方法.演示代码如下:", ItemType.BODY));
  list.add(Item("阻塞线程的耗时操作", ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  print(\"执行耗时操作:${DateTime.now().toString()}\");\n" +
          "  sleep(Duration(seconds: 2));\n" +
          "  print(\"执行耗时操作:${DateTime.now().toString()}\");\n" +
          "\n" +
          "  /*\n" +
          "    打印结果:\n" +
          "    执行耗时操作:2020-08-11 09:28:46.277020\n" +
          "    执行耗时操作:2020-08-11 09:28:48.286984\n" +
          "   */\n" +
          "}",
      ItemType.CODE));
  list.add(Item("Dart是单线程模型没有多线程的概念,只有isolate,isolate不共享内存.",ItemType.BODY));
  list.add(Item(
      "main() {\n" +
          "  var result;\n" +
          "  //使用Future执行异步任务\n" +
          "  Future(() {\n" +
          "    sleep(Duration(seconds: 2));\n" +
          "    print(\"执行任务1\");\n" +
          "    return \"传递数据给任务2\";\n" +
          "  }).then((m) {\n" +
          "    print(m);\n" +
          "    sleep(Duration(seconds: 2));\n" +
          "    print(\"执行任务2\");\n" +
          "    result = \"任务结束\";\n" +
          "  }).whenComplete(() {\n" +
          "    print(result);\n" +
          "  });\n" +
          "  print(\"主线程的其他操作\");\n" +
          "}",
      ItemType.CODE));
  return list;
}
