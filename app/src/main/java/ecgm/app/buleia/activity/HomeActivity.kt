package ecgm.app.buleia.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ecgm.app.buleia.R
import ecgm.app.buleia.`interface`.FirebaseLoadContry
import ecgm.app.buleia.model.Country
import ecgm.app.buleia.model.DriverModel
import ecgm.app.buleia.model.User
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), FirebaseLoadContry {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseLoadContry: FirebaseLoadContry
    private lateinit var databaseRIDER: DatabaseReference
    lateinit var button: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actionBar = supportActionBar!!
        actionBar.title = "Home"


        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout)
        val menu1 = findViewById<NavigationView>(R.id.menu_1)
//        val menu1 : NavigationView = findViewById(R.id.menu_1)


        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menu1.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.definicao -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        ButtonAdicionaBoleia222.setOnClickListener {
            val intent = Intent(this@HomeActivity, CreateNewBuleia::class.java)
            startActivity(intent)
        }

        //Init Interface Country
        firebaseLoadContry = this

        //Init DataBase for Coutry
        databaseReference = FirebaseDatabase.getInstance().getReference("country");

        //Load Data Country
        databaseReference.addValueEventListener(object:ValueEventListener{
            var countryList:MutableList<Country> = ArrayList<Country>()
            override fun onCancelled(error: DatabaseError) {
                firebaseLoadContry.onFirebaseLoadFailed(error.message)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for (CountrySnapShot in snapshot.children)
                    countryList.add(CountrySnapShot.getValue<Country>(Country::class.java!!)!!)
                firebaseLoadContry.onFirebaseLoadSuccess(countryList)
            }
        })


        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        ButtonAdicionaBoleia.setOnClickListener {
//            //Toast.makeText(this@HomeActivity, "FAB is clicked...", Toast.LENGTH_LONG).show()
//            val intent = Intent(this@HomeActivity, DriversActivity::class.java)
//            startActivity(intent)
//        }

        activateRide.setOnClickListener {
            checkRider()
        }

    }

    private fun checkRider () {
        var aument = 1
        val firebaseUser = firebaseAuth.currentUser
        val userId: String = firebaseUser!!.uid
        databaseRIDER = FirebaseDatabase.getInstance().getReference("Users")
        val database = Firebase.database
        val riderREF = database.getReference("Driver")

        activateRide.isSelected = !activateRide.isSelected
            if (expandableTo22.visibility == View.VISIBLE) {
                var driverStatus = 1

                databaseRIDER.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (userSnapshot in snapshot.children) {
                                val user = userSnapshot.getValue(User::class.java)
                                if (user?.userId == firebaseAuth.currentUser?.uid) {


                                        var statusId = +1
                                            statusId ++
                                        var driverName = user?.userName
                                        var numberBuleia = +1
                                            numberBuleia ++
                                            numberBuleia.toString().trim()
                                        var driverWhere = findViewById<Spinner>(R.id.spinner_to22).selectedItem
                                        var timeDriver = findViewById<EditText>(R.id.timeTo22).text.toString().trim()
                                        var pickUpLocation = findViewById<Spinner>(R.id.startTo22).selectedItem
                                        var numberSeats = findViewById<EditText>(R.id.seatsTo22).text.toString().trim()


                                        val driverGO = DriverModel(
                                            driverWhere?.toString(),
                                            timeDriver?.toString(),
                                            pickUpLocation?.toString(),
                                            numberSeats?.toString(),
                                            driverName?.toString(),
                                            numberBuleia?.toString().trim(),
                                            statusId?.toString(),
                                            driverStatus?.toString()
                                        )
                                        riderREF.child(userId).setValue(driverGO)
                                        driverON()
                                        aument ++
//                                    serchForRide.visibility = View.VISIBLE
//                                    expandableTo22.visibility = View.GONE
                                    }
                                }
                            }
                        }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            }
//            else if (expandableTo22.visibility == View.GONE) {
//                var driverStatus = 0
//
//                databaseRIDER.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        if (snapshot.exists()) {
//                            for (userSnapshot in snapshot.children) {
//                                val user = userSnapshot.getValue(User::class.java)
//                                if (user?.userId == firebaseAuth.currentUser?.uid) {
//
//                                        var hashMap: HashMap<String, Int> = HashMap()
//                                        hashMap.put("driverStatus", driverStatus)
//                                        riderREF.child(userId).setValue(hashMap)
//                                        driverOFF()
//                                        expandableTo22.visibility = View.VISIBLE
//                                        serchForRide.visibility = View.GONE
//                                    }
//                                }
//                            }
//                        }
//                    override fun onCancelled(error: DatabaseError) {
//                    }
//                })
//            }
    }

    fun driverON(){
        Toast.makeText(this, "Driver Active", Toast.LENGTH_LONG).show()
    }

    fun driverOFF(){
        Toast.makeText(this, "Driver OUT", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        if (id == R.id.action_msg) {

            val intent = Intent(this, UsersActivity::class.java)
            this.startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_chat, menu)
        return true
    }

    private fun checkUser() {
        //Verificar se o utilizador está logado
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser!= null){
            val email = firebaseUser.email
            //Colocar email atual na text view
            //binding.
        }else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun logoutClick(view: View) {
        firebaseAuth.signOut()
        checkUser()
    }

    override fun onFirebaseLoadSuccess(countryList: List<Country>) {
        //Get all names from list
        val country_name = getCoutryNameList(countryList)
        //Adapter
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, country_name)
//        spinner_from.adapter = adapter
//        spinner_to.adapter = adapter
        spinner_to22.adapter = adapter
        startTo22.adapter = adapter
    }

    private fun getCoutryNameList(countryList: List<Country>): List<String> {
        val result = ArrayList<String>()
        for (Country in countryList)
            result.add(Country.name!!)
        return result
    }

    override fun onFirebaseLoadFailed(message: String) {
        TODO("Not yet implemented")
    }
}