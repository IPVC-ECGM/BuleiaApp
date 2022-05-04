package ecgm.app.buleia

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ecgm.app.buleia.databinding.ActivitySignUpBinding
import java.util.regex.Pattern


class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private var email=""
    private var password=""
    private var passwordrepeat=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title="Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating account")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener{
            validateData()
        }

    }
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "ipvc" +
                "(" +
                "\\." +
                "pt" +
                ")+"
    )

    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }
    private fun validateData() {
        email = binding.emailText.text.toString().trim()
        password = binding.passwordText.text.toString().trim()
        passwordrepeat = binding.passwordText2.text.toString().trim()

          if (!isValidString(email)) {
            binding.emailTF.error = "Use ipvc email"

       // } else if(){

        }else if (TextUtils.isEmpty(password)) {
            binding.passwordTF.error = "Please enter password"
        }else if(TextUtils.isEmpty(passwordrepeat)){
            binding.passwordTF2.error="Please repeat password"
        }else if(password != passwordrepeat) {
            binding.passwordTF2.error="Passwords don´t match"
        }else if (password.length < 6){
            binding.passwordTF.error = "Password must have atleast 6 caracters"
        }else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account have been created with email $email", Toast.LENGTH_LONG)
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    user.sendEmailVerification()
                };
                startActivity(Intent(this, Perfil::class.java))
                finish()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign Up Failded due to ${e.message}", Toast.LENGTH_LONG)
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //Voltar a atrás quando o botão é pressionado
        return super.onSupportNavigateUp()
    }


}