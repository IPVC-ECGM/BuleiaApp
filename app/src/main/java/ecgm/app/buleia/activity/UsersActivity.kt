package ecgm.app.buleia.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ecgm.app.buleia.adapter.UserAdapter
import ecgm.app.buleia.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import ecgm.app.buleia.R
import ecgm.app.buleia.firebase.FirebaseService
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    var userList = ArrayList<User>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        actionBar = supportActionBar!!
        actionBar.title = "Messages"

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token
        }

        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        getUsersList()
    }

    fun getUsersList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        var userid = firebase.uid
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")

        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

//                val currentUser = snapshot.getValue(User::class.java)
////                if (currentUser!!.profileImage == ""){
////                    imgProfile.setImageResource(R.drawable.profile_image)
////                }else{
//                if (currentUser != null) {
//                    Glide.with(this@UsersActivity).load(currentUser.profileImage).into(imgProfile)
//                }
////                }

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)

                    if (!user!!.userId.equals(firebase.uid)) {

                        userList.add(user)
                    }
                }

                val userAdapter = UserAdapter(this@UsersActivity, userList)

                userRecyclerView.adapter = userAdapter
            }
        })
    }
}