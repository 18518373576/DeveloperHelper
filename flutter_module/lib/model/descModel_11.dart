import 'package:flutter/material.dart';
import 'package:flutter_module/utils/myIcon.dart';
import 'package:flutter_module/widgets/descListView.dart';

getDescList_11() {
  List<Item> list = [];
  list.add(Item("加载asset图片", ItemType.TITLE));
  list.add(Item.widgetItem(Image(
    image: AssetImage("asserts/images/icon_dart.png"),
    width: 50,
    height: 50,
  )));
  list.add(Item(
      "Image(\n" +
          "    image: AssetImage(\"asserts/images/icon_dart.png\"),\n" +
          "    width: 80,\n" +
          "    height: 80,\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("加载网络图片", ItemType.BOLD_BODY));
  list.add(Item.widgetItem(Image.network(
    "https://gitee.com/ZhangQQ_123/DeveloperHelper/raw/master/pics/icon_lancher.png",
    width: 150,
    height: 150,
  )));
  list.add(Item(
      "Image.network(\n" +
          "    \"https://gitee.com/ZhangQQ_123/DeveloperHelper/raw/master/pics/icon_lancher.png\",\n" +
          "    width: 150,\n" +
          "    height: 150,\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("Image缓存", ItemType.BOLD_BODY));
  list.add(Item("Flutter框架对加载过的图片是有缓存的,默认最大数量1000,最大缓存空间100M.", ItemType.BODY));
  list.add(Item("Icon", ItemType.TITLE));
  list.add(Item("在Flutter中可以使用字体图标,这种图标具有①体积小,②矢量,③可以和文本混合使用,④可以改变颜色的特点.",
      ItemType.BODY));
  list.add(Item.widgetItem(Text.rich(
    TextSpan(children: [
      TextSpan(
          text: "\uE90D",
          style: TextStyle(
              height: 1.5,
              fontFamily: "MaterialIcons",
              fontSize: 20,
              color: Colors.green)),
      TextSpan(
          text: "文本",
          style: TextStyle(height: 1.5, fontSize: 16, color: Colors.green)),
    ]),
  )));
  list.add(Item(
      "Text.rich(\n" +
          "    TextSpan(children: [\n" +
          "      TextSpan(\n" +
          "          text: \"\\uE90D\",\n" +
          "          style: TextStyle(\n" +
          "              height: 1.5,\n" +
          "              fontFamily: \"MaterialIcons\",\n" +
          "              fontSize: 20,\n" +
          "              color: Colors.green)),\n" +
          "      TextSpan(\n" +
          "          text: \"文本\",\n" +
          "          style: TextStyle(height: 1.5, fontSize: 16, color: Colors.green)),\n" +
          "    ]),\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("使用Icon", ItemType.BOLD_BODY));
  list.add(Item.widgetItem(Icon(
    Icons.android,
    color: Colors.green,
    size: 100,
  )));
  list.add(Item(
      "Icon(\n" +
          "    Icons.android,\n" +
          "    color: Colors.green,\n" +
          "    size: 100,\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("使用自定义图标字体", ItemType.BOLD_BODY));
  list.add(Item.widgetItem(Icon(
    MyIcon.love,
    size: 100,
    color: Colors.red,
  )));
  list.add(Item(
      "Icon(\n" +
          "    MyIcon.love,\n" +
          "    size: 100,\n" +
          "    color: Colors.red,\n" +
          "  )",
      ItemType.CODE));
  return list;
}
