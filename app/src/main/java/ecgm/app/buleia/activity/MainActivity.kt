package ecgm.app.buleia.activity

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ecgm.app.buleia.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        if(savedBoolean) {
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreen::class.java)
                intent.putExtra("shared", true)
                startActivity(intent)
                finish()

        }else{
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreenComplete::class.java)
                intent.putExtra("shared", false)
                startActivity(intent)
                finish()
        }
    }


}