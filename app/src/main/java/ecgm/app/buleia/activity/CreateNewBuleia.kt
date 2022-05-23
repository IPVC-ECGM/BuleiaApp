package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ecgm.app.buleia.R
import ecgm.app.buleia.model.Buleia
import ecgm.app.buleia.model.User

class CreateNewBuleia : AppCompatActivity() {
    lateinit var button: Button
    private lateinit var dbref : DatabaseReference

    //Firebase autenticação
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_buleia)
        supportActionBar?.hide()

        val database = Firebase.database
        val myRef = database.getReference("Buleia")
        var id = 2

        dbref = FirebaseDatabase.getInstance().getReference("Users")
        firebaseAuth = FirebaseAuth.getInstance()

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        if (user?.userId == firebaseAuth.currentUser?.uid) {
                            var user = user?.userName
                            button = findViewById(R.id.ButtonAdicionaBoleia)
                            button.setOnClickListener {

                                var rideDay = findViewById<EditText>(R.id.rideDay).text
                                var rideTime = findViewById<EditText>(R.id.rideTime).text
                                var driveFrom = findViewById<EditText>(R.id.driveFrom).text
                                var driveTo = findViewById<EditText>(R.id.driveTo).text
                                var pick1 = findViewById<EditText>(R.id.pickUp_1).text
                                var pick2 = findViewById<EditText>(R.id.pickUp_2).text
                                var pick3 = findViewById<EditText>(R.id.pickUp_3).text

                                /*val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
                                val user_id = sharedPref.getString("user_id", "anonimo")*/
                                //var user = FirebaseAuth.getInstance().currentUser?.uid
                                val buleia = Buleia(
                                    rideDay.toString(),
                                    rideTime.toString(),
                                    driveFrom.toString(),
                                    driveTo.toString(),
                                    pick1.toString(),
                                    pick2.toString(),
                                    pick3.toString(),
                                    user.toString()
                                )
                                myRef.child(id.toString()).setValue(buleia)
                                id++

                                buleiaCreated()
                                // myRef.setValue(buleia)

                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun button1(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun getId(){

    }

    fun buleiaCreated(){
        val intent = Intent(this, HomeActivity::class.java)
        Toast.makeText(this, "Ride created", Toast.LENGTH_LONG).show()
        startActivity(intent)
        finish()
    }
}
