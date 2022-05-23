package ecgm.app.buleia.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ecgm.app.buleia.databinding.ActivityPerfilBinding
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import ecgm.app.buleia.R
import ecgm.app.buleia.firebase.FirebaseService
import ecgm.app.buleia.model.DriverModel
import ecgm.app.buleia.model.User
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_settings.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var storageRef: StorageReference
    var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token
        }

        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(50f, 1000)
            progressBarWidth = 5f
            backgroundProgressBarWidth = 7f
        }!!

        val circularProgressBar2 = findViewById<CircularProgressBar>(R.id.circularProgressBar2)
        circularProgressBar2.apply {
            progressMax = 100f
            setProgressWithAnimation(50f, 1000)
            progressBarWidth = 5f
            backgroundProgressBarWidth = 7f
        }!!

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child(firebaseUser.uid)

        storage = FirebaseStorage.getInstance()
        storageRef = storage.reference

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                val driver = snapshot.getValue((DriverModel::class.java))

                clients.text = driver!!.numberBuleia
                clients2.text = driver.reviewStar
                aboutTxet.text = driver!!.desProfile
                matricula.text = driver!!.matriculaCarro


                userName2.text = user!!.userName
                email22.text = firebaseUser.email
                clients.text = user.userId

                if (user.profileImage == "") {
                    profile_image2.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@ProfileActivity).load(user.profileImage).into(profile_image2)
                }
            }
        })
    }

    fun button1(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}