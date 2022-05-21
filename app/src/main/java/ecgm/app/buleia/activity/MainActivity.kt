package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ecgm.app.buleia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreen::class.java)
                startActivity(intent)
        }, 0)

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this@MainActivity, SplashScreenComplete::class.java)
            startActivity(intent)
        }, 0)

    }
}