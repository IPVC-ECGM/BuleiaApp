package ecgm.app.buleia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import ecgm.app.buleia.R
import ecgm.app.buleia.adapter.ProfileAdapter
import ecgm.app.buleia.model.ProfileData
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {
    lateinit var mDataBase: DatabaseReference
    private lateinit var profileList:ArrayList<ProfileData>
    private lateinit var mAdapter:ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        /**initialized*/
        profileList = ArrayList()
        mAdapter = ProfileAdapter(this,profileList)
        recyclerProfile.layoutManager = LinearLayoutManager(this)
        recyclerProfile.setHasFixedSize(true)
        //recyclerProfile.adapter = mAdapter
        /**getData firebase*/
        getProfileData()
    }

    private fun getProfileData() {

        mDataBase = FirebaseDatabase.getInstance().getReference("Animals")
        mDataBase.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (profileSnapshot in snapshot.children){
                        val profile = profileSnapshot.getValue(ProfileData::class.java)
                        profileList.add(profile!!)
                    }
                    recyclerProfile.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ReviewActivity,
                    error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}