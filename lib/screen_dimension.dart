import 'dart:async';

import 'package:flutter/services.dart';

class ScreenDimension {
  static const MethodChannel _channel = const MethodChannel('screen_dimension');

  /*static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }*/

  static Future<double> get getHeight async {
    final double height = await _channel.invokeMethod('getHeight');
    return height;
  }
}
