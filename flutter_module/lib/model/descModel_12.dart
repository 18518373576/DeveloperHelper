import 'package:flutter/material.dart';
import 'package:flutter_module/utils/constant.dart';
import 'package:flutter_module/utils/onClick.dart';
import 'package:flutter_module/widgets/descListView.dart';

bool _value = false;

void setValue(bool value) {
  _value = value;
}

getDescList_12(OnClick click) {
  List<Item> list = [];
  list.add(Item("单选按钮Switch", ItemType.TITLE));
  list.add(Item.widgetItem(Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      Switch(
        value: _value,
        onChanged: (value) {
          click.onClickListener(Constant.switch_id, value: value);
        },
      )
    ],
  )));
  list.add(Item(
      "Switch(\n" +
          "    value: _value,\n" +
          "    onChanged: (value) {\n" +
          "      click.onClickListener(Constant.switch_id, value);\n" +
          "    },\n" +
          "  )",
      ItemType.CODE));
  list.add(Item("复选框CheckBox", ItemType.TITLE));
  list.add(Item.widgetItem(Checkbox(
    value: _value,
    onChanged: (value) {
      click.onClickListener(Constant.switch_id, value: value);
    },
  )));

  list.add(Item(
      "//与Switch单选按钮使用了同一个state\n" +
          "Checkbox(\n" +
          "    value: _value,\n" +
          "    onChanged: (value) {\n" +
          "      click.onClickListener(Constant.switch_id, value);\n" +
          "    },\n" +
          "  )",
      ItemType.CODE));
  return list;
}
