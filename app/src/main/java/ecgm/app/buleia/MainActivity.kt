package ecgm.app.buleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerlayout)
        val menu1 : NavigationView = findViewById(R.id.menu_1)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
                drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menu1.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> Toast.makeText(applicationContext,"clicked Home",Toast.LENGTH_SHORT).show()
                R.id.history -> Toast.makeText(applicationContext,"clicked History",Toast.LENGTH_SHORT).show()
                R.id.notificacao -> Toast.makeText(applicationContext,"clicked notificacao",Toast.LENGTH_SHORT).show()
                R.id.definicao -> Toast.makeText(applicationContext,"clicked definicao",Toast.LENGTH_SHORT).show()
                R.id.sair -> Toast.makeText(applicationContext,"clicked sair",Toast.LENGTH_SHORT).show()

            }
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(toggle.onOptionsItemSelected(item)){
           return true
       }
        return super.onOptionsItemSelected(item)
    }
}

