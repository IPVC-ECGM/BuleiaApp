package ecgm.app.buleia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ecgm.app.buleia.R
import ecgm.app.buleia.`interface`.FirebaseLoadContry
import ecgm.app.buleia.model.Country
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*
import java.lang.ref.Reference
import java.util.ArrayList

class HomeActivity : AppCompatActivity(), FirebaseLoadContry {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseLoadContry: FirebaseLoadContry
    private lateinit var rideRecyclerview: RecyclerView
    private lateinit var rideArrayList: ArrayList<Ride>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actionBar = supportActionBar!!
        actionBar.title = "Home"

        rideRecyclerview = findViewById(R.id.rideList)
        rideRecyclerview.layoutManager = LinearLayoutManager(this )
        rideRecyclerview.setHasFixedSize(true)

        //Guardar dados da base de dados usando a classe Ride
        rideArrayList = arrayListOf<Ride>()
        getRideData()

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
                R.id.notification -> {
                    val intent = Intent(this, NotificationsActivity::class.java)
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

        val mFab = findViewById<FloatingActionButton>(R.id.ButtonAdicionaBoleia)
        mFab.setOnClickListener {
            //Toast.makeText(this@HomeActivity, "FAB is clicked...", Toast.LENGTH_LONG).show()
            val intent = Intent(this@HomeActivity, CreateNewBuleia::class.java)
            startActivity(intent)
        }

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