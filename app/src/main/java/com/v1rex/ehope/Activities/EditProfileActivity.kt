package com.v1rex.ehope.Activities

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.v1rex.ehope.Model.User
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
    var mDatabase : FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setSupportActionBar(my_toolbar_edit)
        var user : User? = null

        var mAuth : FirebaseAuth? = FirebaseAuth.getInstance()
        val userId = mAuth!!.uid.toString()

        mDatabase = FirebaseDatabase.getInstance()


        var mRef = mDatabase!!.getReference("Data").child("Users").child(userId)


        var valueEventListenerUser = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                loadDataToUi(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }

        }

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar_edit.setNavigationOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        save_edit_profile.setOnClickListener {
            var cancel = false
            val userNameEdited :String = edit_user_name.text.toString()
            var userPhoneEdited = edit_user_phone.text.toString()
            var userWeightEdited = edit_user_weight.text.toString()
            var weight = Integer.parseInt(userWeightEdited)

            if(TextUtils.isEmpty(userNameEdited) || TextUtils.isEmpty(userPhoneEdited) || TextUtils.isEmpty(userWeightEdited)) cancel = true


            if(!cancel){
                var user2 = User(userNameEdited, userPhoneEdited, user!!.mBirthday, weight, user!!.mSexe , user!!.mHeroType , user!!.mPoints, mAuth.uid.toString())
                sendData(user2)


            } else if(cancel){
                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("You need to fill all the demanded informations")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                        "Okey",
                        DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                val alert11 = builder1.create()
                alert11.show()

            }
        }

        mRef.addValueEventListener(valueEventListenerUser)
    }

    fun loadDataToUi(userVal : User?){
        var userName = userVal!!.mName.toString()
        var userPhone = userVal!!.mPhoneNumber.toString()
        var userWeight = userVal!!.mWeight.toString()

        edit_user_name.setText(userName)
        edit_user_phone.setText(userPhone)
        edit_user_weight.setText(userWeight)
    }

    fun sendData(user : User?){
        var userId  = user!!.mUserId.toString()

        val ref = mDatabase!!.getReference("Data").child("Users").child(userId)

        ref.setValue(user)

        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}
