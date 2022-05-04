package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import ecgm.app.buleia.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import android.view.View
import ecgm.app.buleia.HomeFragment
import ecgm.app.buleia.R

class Perfil : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var binding: ActivityPerfilBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        drawerLayout = findViewById(R.id.drawerlayout)
        val menu1 : NavigationView = findViewById(R.id.menu_1)

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
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
            }
            true
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        binding.buttonlogout.setOnClickListener{
//            firebaseAuth.signOut()
//            checkUser()
//        }
    }

    private  fun replaceFragment(fragment: Fragment, title:String){
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