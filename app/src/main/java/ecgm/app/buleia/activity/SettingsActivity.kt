package ecgm.app.buleia.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ecgm.app.buleia.Constants.Constants
import ecgm.app.buleia.R
import ecgm.app.buleia.model.User
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class SettingsActivity : AppCompatActivity() {
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference

    private var filePath: Uri? = null

    private val PICK_IMAGE_REQUEST: Int = 2020

    private lateinit var storage: FirebaseStorage
    private lateinit var storageRef: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.hide()

        //shared Preferences

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        databaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        storage = FirebaseStorage.getInstance()
        storageRef = storage.reference

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                etUserName.setText(user!!.userName)

                if (user.profileImage == "") {
                    userImage.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@SettingsActivity).load(user.profileImage).into(userImage)
                }
            }
        })

        userImage.setOnClickListener {
            chooseImage()
        }

        btnSave.setOnClickListener {
            uploadImage()
        }

        btnName.setOnClickListener {
            changeName()
        }

        etUserName.setOnClickListener {
            btnName.visibility = View.VISIBLE
        }
    }

    fun button1(view: View) {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun changeName() {
        val userId: String = firebaseUser!!.uid
        val hashMap: HashMap<String, String> = HashMap()

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

        hashMap.put("userName", etUserName.text.toString())
        databaseReference.updateChildren(hashMap as Map<String, Any>)
        Toast.makeText(applicationContext, "Name Changed", Toast.LENGTH_SHORT).show()
        btnName.visibility = View.GONE
    }

    private fun chooseImage() {
        val intent: Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode != null) {
            filePath = data!!.data
            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                userImage.setImageBitmap(bitmap)
                btnSave.visibility = View.VISIBLE
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun uploadImage() {
        if (filePath != null) {

            var ref: StorageReference = storageRef.child("image/" + UUID.randomUUID().toString())
            ref.putFile(filePath!!)
                .addOnSuccessListener {

                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap.put("userName", etUserName.text.toString())
                    hashMap.put("profileImage", filePath.toString())
                    databaseReference.updateChildren(hashMap as Map<String, Any>)
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_SHORT).show()
                    btnSave.visibility = View.GONE
                }
                .addOnFailureListener {
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed" + it.message, Toast.LENGTH_SHORT)
                        .show()

                }

        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {

  /*          when (view.id) {
                R.id.checkbox_SplashScreen -> {
                    if ( == true) {
                        //Normal SplashScreen
                        val toast = Toast.makeText(applicationContext, "Splash Screen Activated", Toast.LENGTH_SHORT)
                        toast.show()
                    } else {
                        //Small SplashScreen
                        val toast = Toast.makeText(applicationContext, "Splash Screen Deactivated", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            }*/
        }
    }
}