package ecgm.app.buleia.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ecgm.app.buleia.R
import ecgm.app.buleia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding:ActivityLoginBinding

    //barra
    private lateinit var actionBar: ActionBar

    //Progresso
    private lateinit var progressDialog: ProgressDialog


    //Firebase autenticação
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        actionBar = supportActionBar!!
        actionBar.title="@string/login"

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Logginf in...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.criarconta.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.buttonLogin.setOnClickListener{
            validateData()
        }
        val password = findViewById<EditText>(R.id.passwordText)
        password.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

        val email = findViewById<EditText>(R.id.emailText)
        email.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)

    }
    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun validateData() {

        //Pegar na informação
        email = binding.emailText.text.toString().trim()
        password = binding.passwordText.text.toString().trim()


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailTf.error = "Invalid email format"
        }else if (TextUtils.isEmpty(password)){
            binding.emailTf.error="Please Enter a password"
        }else{
            firebaselogin()
        }
    }

    private fun firebaselogin(){
        progressDialog.show()




        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Login com sucesso
                progressDialog.dismiss()
                //Buscar info do utilizador
                val firebaseUser = firebaseAuth.currentUser
                val emailVerified: Boolean = firebaseUser!!.isEmailVerified()

                if(emailVerified != true){
                   // Toast.makeText(this, "$emailVerified", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Email not verified", Toast.LENGTH_SHORT).show()
                }else {
                    val email = firebaseUser!!.email
                    Toast.makeText(this, "Login as $email", Toast.LENGTH_SHORT).show()
                    //Abrir Pagina do utilizador
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
            .addOnFailureListener{
                    e->
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}