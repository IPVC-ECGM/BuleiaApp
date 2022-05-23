package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ecgm.app.buleia.R
import ecgm.app.buleia.adapter.DriverAdapter
import ecgm.app.buleia.adapter.MyAdapter
import ecgm.app.buleia.model.DriverModel
import java.util.ArrayList

class DriverActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var driverRecyclerview: RecyclerView
    private lateinit var driverList: ArrayList<DriverModel>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers)
        supportActionBar?.hide()

        driverRecyclerview = findViewById(R.id.DriverRecyclerView)
        driverRecyclerview.layoutManager = LinearLayoutManager(this )

        //Guardar dados da base de dados usando a classe Ride
        driverList = arrayListOf<DriverModel>()

        databaseReference = FirebaseDatabase.getInstance().getReference("Driver/")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(driverSnapshot in snapshot.children){
                        val driver = driverSnapshot.getValue(DriverModel::class.java)
                        driverList.add(driver!!)
                    }

                    driverRecyclerview.adapter = DriverAdapter(driverList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    fun button1(view: View) {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}