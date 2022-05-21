package ecgm.app.buleia.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ecgm.app.buleia.Constants.Constants
import ecgm.app.buleia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val shared = sharedPref.getBoolean(getString(R.string.splash_key), true)

        if(shared == false) {
            Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreen::class.java)
                intent.putExtra("shared", true)
                startActivity(intent)
            }, 0)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreenComplete::class.java)
                intent.putExtra("shared", false)
                startActivity(intent)
            }, 0)
        }
    }
}