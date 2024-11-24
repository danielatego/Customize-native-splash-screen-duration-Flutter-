package com.mahanaim.customized.splash.screen.customize_splashscreen_duration

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

    private val CHANNEL = "com.mahanaim.customize.splash.screen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        // keep the splashscreen on the screen indeterminately
        splashScreen.setKeepOnScreenCondition{true}

        //method executed on the flutter side to stop the flashscreen
        flutterEngine?.dartExecutor?.let {
            MethodChannel(it,CHANNEL).setMethodCallHandler{call,result ->
                if(call.method == "dismissSplashScreen"){
                    splashScreen.setKeepOnScreenCondition{false}
                    result.success(null)
                }else{
                    result.notImplemented()
                }
            }
        }
    }
}
