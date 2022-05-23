package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.*
import ecgm.app.buleia.R
import ecgm.app.buleia.adapter.MyAdapter
import ecgm.app.buleia.model.Ride
import kotlinx.android.synthetic.main.review_modal.*
import kotlinx.android.synthetic.main.ride_item.*
import java.util.ArrayList

class HistoryActivity : AppCompatActivity() {
    private lateinit var rideRecyclerview: RecyclerView
    private lateinit var rideArrayList: ArrayList<Ride>
    private lateinit var databaseReference: DatabaseReference

    lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.hide()

        rideRecyclerview = findViewById(R.id.rideList)
        rideRecyclerview.layoutManager = LinearLayoutManager(this )
        rideRecyclerview.setHasFixedSize(true)

//        ratingBar = findViewById<RatingBar>(R.id.ratingH)
//
//        ratingBar.setOn {
//            Toast.makeText("BLA")
//        }
        reviewTo2?.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialogFragment();
            bottomSheetDialog.show(supportFragmentManager, "GG")
        }

        //Guardar dados da base de dados usando a classe Ride
        rideArrayList = arrayListOf<Ride>()
        getRideData()
    }

    fun button1(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getRideData() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Buleia")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(rideSnapshot in snapshot.children){

                        val ride = rideSnapshot.getValue(Ride::class.java)
                        rideArrayList.add(ride!!)

                    }

                    rideRecyclerview.adapter = MyAdapter(rideArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}