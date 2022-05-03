package ecgm.app.buleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        val menu1 : NavigationView = findViewById(R.id.menu_1)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
                drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menu1.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment(),it.title.toString())
                R.id.history -> Toast.makeText(applicationContext,"clicked History",Toast.LENGTH_SHORT).show()
                R.id.notificacao -> Toast.makeText(applicationContext,"clicked notificacao",Toast.LENGTH_SHORT).show()
                R.id.definicao -> Toast.makeText(applicationContext,"clicked definicao",Toast.LENGTH_SHORT).show()
                R.id.sair -> Toast.makeText(applicationContext,"clicked sair",Toast.LENGTH_SHORT).show()

            }
            true

        }

    }

    private  fun replaceFragment(fragment: Fragment,title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(toggle.onOptionsItemSelected(item)){
           return true
       }
        return super.onOptionsItemSelected(item)
    }
}

