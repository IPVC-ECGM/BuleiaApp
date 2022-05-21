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
import kotlinx.android.synthetic.main.activity_settings.*

class MainActivity : AppCompatActivity() {

    fun loadData(){
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", switch_Splash.isChecked)

        switch_Splash.isChecked = savedBoolean
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val saved = false;

        if(saved == false) {
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreen::class.java)
                intent.putExtra("shared", true)
                startActivity(intent)

        }else{
                // Your Code
                val intent = Intent(this@MainActivity, SplashScreenComplete::class.java)
                intent.putExtra("shared", false)
                startActivity(intent)
        }
    }


}