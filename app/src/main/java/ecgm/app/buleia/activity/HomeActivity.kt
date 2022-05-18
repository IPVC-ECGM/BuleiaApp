package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import ecgm.app.buleia.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val btnShow:Button = findViewById(R.id.btnShow)

        btnShow.setOnClickListener{
            val view = layoutInflater.inflate(R.layout.item_bottom_sheet,null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }
    }

    fun button1(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

}