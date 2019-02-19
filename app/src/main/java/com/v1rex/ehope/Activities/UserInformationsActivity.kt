package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.v1rex.ehope.Model.User
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_user_informations.*
import java.text.SimpleDateFormat
import java.util.*
import android.R.string.cancel
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UserInformationsActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    var mDatabase : FirebaseDatabase? = null
    var mRef : DatabaseReference? = null
    var cal : Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_informations)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase!!.getReference("Data").child("Users")

        mAuth = FirebaseAuth.getInstance()


        finished_date_user.setOnClickListener {
            date_picker_user_layout.visibility = View.GONE
        }

        save_user.setOnClickListener {
            sendInformations()
        }

    }

    override fun onBackPressed() {

    }

    fun sendInformations(){
        var cancel = false

        var name = user_name.text.toString()
        if (TextUtils.isEmpty(name)) cancel = true  else cancel = false

        var phoneNumber = user_phone.text.toString()
        if(TextUtils.isEmpty(phoneNumber)) cancel = true  else cancel = false

        val day = date_picker_user.getDayOfMonth()
        val month = date_picker_user.getMonth()
        val year = date_picker_user.getYear()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val date = SimpleDateFormat("yyyy.MM.dd")
        val dateAndTime = date.format(calendar.getTime())


        var weightString = user_weight.text.toString()
        var weight: Int = 0
        if (TextUtils.isEmpty(weightString)) {
            cancel = true
        } else{
            weight = Integer.parseInt(weightString)
            cancel = false
        }


        var maleChecked = male_selected.isChecked
        var femaleChecked = female_selected.isChecked

        var sexe :  String = ""
        if (maleChecked == false && femaleChecked == false){
           cancel = true
        } else {
            if (maleChecked){
               sexe = "Male"
            } else if(femaleChecked){
                sexe = "Female"
            }
           cancel = false
        }

        if(!cancel){
            var userId :  String? = mAuth!!.uid
            var userInformations = User(name, phoneNumber, dateAndTime, weight, sexe,"beginner", 0,0,0, userId)

            var sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE)


            var editor : SharedPreferences.Editor = sharedPref.edit()
            editor.putString("username",userInformations.mName)
            editor.putString("userHeroType", userInformations.mHeroType)
            editor.putString("userPoint",userInformations.mPoints.toString())
            editor.apply()

            var userId2 = userInformations.mUserId.toString()
            var mRef2 = mRef?.child(userId2)
            mRef2?.setValue(userInformations)
            startActivity(Intent(this, ProfileActivity::class.java))
        } else {
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
}
