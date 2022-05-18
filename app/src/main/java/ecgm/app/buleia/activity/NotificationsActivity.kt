package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ecgm.app.buleia.R

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        supportActionBar?.hide()
    }

    fun button1(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}