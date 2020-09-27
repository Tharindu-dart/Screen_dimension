import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:screen_dimension/screen_dimension.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  double _height = 0;

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> _getHeight() async {
    double height;
    try {
      height = await ScreenDimension.getHeight;
    } on PlatformException {
      height = 0;
    }

    if (!mounted) return;

    setState(() {
      _height = height;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Real Device Screen Dimensions'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('Screen Height: $_height\n',style: TextStyle(fontSize: 30,fontWeight: FontWeight.w600),),
              SizedBox(
                height: 100.0,
              ),
              RaisedButton(
                child: Text("Get Height",style: TextStyle(fontSize: 18)),
                color: Colors.blue,
                onPressed: () async{
                  await _getHeight();
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
