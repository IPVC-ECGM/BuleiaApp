package ecgm.app.buleia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ecgm.app.buleia.databinding.ActivityPerfilBinding

class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.buttonlogout.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
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
}