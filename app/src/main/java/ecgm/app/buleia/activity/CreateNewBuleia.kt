package ecgm.app.buleia.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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
        var id = 0

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

                                var pick = findViewById<EditText>(R.id.text1).text
                                var pickA = findViewById<EditText>(R.id.text2).text
                                var pickB = findViewById<EditText>(R.id.text3).text
                                var drop = findViewById<EditText>(R.id.text4).text
                                var date = findViewById<EditText>(R.id.text5).text

                                /*val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
                                val user_id = sharedPref.getString("user_id", "anonimo")*/
                                //var user = FirebaseAuth.getInstance().currentUser?.uid
                                val buleia = Buleia(
                                    pick.toString(),
                                    pickA.toString(),
                                    pickB.toString(),
                                    drop.toString(),
                                    date.toString(),
                                    user.toString()
                                )
                                myRef.child(id.toString()).setValue(buleia)
                                id++

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

}

