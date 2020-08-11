import 'package:flutter/material.dart';

show(BuildContext context) {
  showModalBottomSheet(
    context: context,
    builder: (BuildContext context) {
      return new Container(
        height: 300.0,
        child: Image.asset(
          'asserts/images/icon_flutter.png',
          width: 50,
          height: 50,
        ),
      );
    },
  ).then((val) {
    print(val);
  });
}
