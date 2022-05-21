package ecgm.app.buleia.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ecgm.app.buleia.Constants.Constants
import ecgm.app.buleia.R


class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        //Esconder Barra do menu
        supportActionBar?.hide()

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}