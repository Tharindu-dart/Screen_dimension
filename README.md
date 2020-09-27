# screen_dimension

Flutter plugin for getting Android device height.

## Usage

```dart
Future<void> _getHeight() async {
    double height;
    try {
      height = await ScreenDimension.getHeight;
    } on PlatformException {
      height = 0;
    }
}
```

```ScreenDimension.getHeight``` is asynchronous task.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[BSD](https://opensource.org/licenses/BSD-2-Clause)
