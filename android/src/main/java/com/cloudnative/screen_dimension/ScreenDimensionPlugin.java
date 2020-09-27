package com.cloudnative.screen_dimension;

import android.app.WallpaperManager;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ScreenDimensionPlugin */
public class ScreenDimensionPlugin implements FlutterPlugin, MethodCallHandler {

  private MethodChannel channel;
  private static Context context;//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  //post-flutter-1.12 (for new-projects)
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "screen_dimension");
    channel.setMethodCallHandler(this);
  }
  //pre-Flutter-1.12 (for old-projects)
  public static void registerWith(Registrar registrar) {
    context = registrar.context();//
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "screen_dimension");
    channel.setMethodCallHandler(new ScreenDimensionPlugin());
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)//+++++++++++++++++++++++needed for getHeight++++++++++++++++++++++++++++++++++++++
  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getHeight")) {//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      result.success(getHeight());//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  //----------------------------------------------------------------------------
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  public static double getHeight() {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    display.getRealMetrics(displayMetrics);

    //int height = displayMetrics.heightPixels;
    //int width = displayMetrics.widthPixels;
    return displayMetrics.heightPixels;
  }
}
