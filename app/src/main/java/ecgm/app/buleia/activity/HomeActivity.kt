package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import ecgm.app.buleia.R

class HomeActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        actionBar = supportActionBar!!
        actionBar.title = "Home"

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout)
        val menu1 = findViewById<NavigationView>(R.id.menu_1)
//        val menu1 : NavigationView = findViewById(R.id.menu_1)

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menu1.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.notificacao -> {
                    val intent = Intent(this, NotificationsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.definicao -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            true
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        val btnShow: Button = findViewById<Button?>(R.id.btnShow)

        btnShow.setOnClickListener{
            val view = layoutInflater.inflate(R.layout.item_bottom_sheet,null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        if (id == R.id.action_msg) {

            val intent = Intent(this, UsersActivity::class.java)
            this.startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_chat, menu)
        return true
    }

    private fun checkUser() {
        //Verificar se o utilizador está logado
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser!= null){
            val email = firebaseUser.email
            //Colocar email atual na text view
            //binding.
        }else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun logoutClick(view: View) {
        firebaseAuth.signOut()
        checkUser()
    }

}