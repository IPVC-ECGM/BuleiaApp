package ecgm.app.buleia.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ecgm.app.buleia.databinding.ActivityPerfilBinding
import android.view.View
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import ecgm.app.buleia.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
            circularProgressBar.apply {
                progressMax = 100f
                setProgressWithAnimation(50f, 1000)
                progressBarWidth = 5f
                backgroundProgressBarWidth = 7f
                progressBarColor = Color.GREEN
            }!!
    }

    fun button1(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}