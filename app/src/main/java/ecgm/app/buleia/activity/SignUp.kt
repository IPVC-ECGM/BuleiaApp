package ecgm.app.buleia.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ecgm.app.buleia.databinding.ActivitySignUpBinding
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var databaseReference: DatabaseReference

    //Dados de para o sign up
    private var userName=""
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

        //Barra de Progresso
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("@string/")
        progressDialog.setMessage("Creating account")
        progressDialog.setCanceledOnTouchOutside(false)

        //Base de dados
        firebaseAuth = FirebaseAuth.getInstance()

        //Botão de Sign Up
        binding.buttonSignUp.setOnClickListener{
            validateData()
        }

    }

    //Variavel para dar store ao endereço predefenido
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "ipvc" +
                "(" +
                "\\." +
                "pt" +
                ")+"
    )

    //Validar Email
    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

    //validar dados do formulario
    private fun validateData() {
        userName = binding.nameText.text.toString().trim()
        email = binding.emailText.text.toString().trim()
        password = binding.passwordText.text.toString().trim()
        passwordrepeat = binding.passwordText2.text.toString().trim()

        //Validar email do ipvc
          if (!isValidString(email)) {
            binding.emailTF.error = "@string/useipvc"
        //Validar preenchimento do username
        }else if (TextUtils.isEmpty(userName)) {
              binding.nameTF.error = "Please enter username"
              //Validar preenchimento da password
          }else if (TextUtils.isEmpty(password)) {
            binding.passwordTF.error = "Please enter password"
        //Validar preenchimento da password repeat
        }else if(TextUtils.isEmpty(passwordrepeat)){
            binding.passwordTF2.error="Please repeat password"
        //Comparar passwords
        }else if(password != passwordrepeat) {
            binding.passwordTF2.error="Passwords don´t match"
        //Verificar se passwords são curtas
        }else if (password.length < 6){
            binding.passwordTF.error = "Password must have atleast 6 caracters"
      //Caso nada esteja incorreto, iniciar login
        }else{
            firebaseSignUp()
        }
    }

        //Login na base de dados
    private fun firebaseSignUp() {

        //Mostrar barra de progresso
        progressDialog.show()

        //Criar utilizador com os dados fornecidos
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                //Em caso do utilizador ser criado com sucesso
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                val userId: String = firebaseUser!!.uid

                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                val hashMap:HashMap<String,String> = HashMap()
                hashMap.put("userId",userId)
                hashMap.put("userName",userName)
                hashMap.put("profileImage","")

                databaseReference.setValue(hashMap)

                Toast.makeText(this, "Account have been created with email $email", Toast.LENGTH_LONG).show()
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    user.sendEmailVerification()
                };
                //Terminar sessão para validar o e-mail primeiro
                firebaseAuth.signOut()
                //Mudar de layout
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
                //Em caso do utilizador não ter cido criado
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign Up Failed due to ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //Voltar a atrás quando o botão é pressionado
        return super.onSupportNavigateUp()
    }
}