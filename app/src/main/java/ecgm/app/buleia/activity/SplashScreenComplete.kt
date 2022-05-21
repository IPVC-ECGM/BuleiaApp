package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ecgm.app.buleia.R

class SplashScreenComplete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_complete)

        //Esconder Barra do menu
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this@SplashScreenComplete, LoginActivity::class.java)
            startActivity(intent)
        }, 0)
    }
}