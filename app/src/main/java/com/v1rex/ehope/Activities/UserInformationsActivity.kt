package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.v1rex.ehope.Model.User
import kotlinx.android.synthetic.main.activity_user_informations.*
import java.text.SimpleDateFormat
import java.util.*
import com.v1rex.ehope.R
import android.R.string.cancel
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.v1rex.ehope.Utilities.ImagePicker
import java.io.ByteArrayOutputStream
import java.io.IOException


class UserInformationsActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    var mDatabase : FirebaseDatabase? = null
    var mRef : DatabaseReference? = null
    var cal : Calendar? = null

    private val storage : FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference : StorageReference = storage.reference

    private val REFERENCE_PROFILE_PHOTO : String = "profileImages/"

    private val PICK_IMAGE_REQUEST : Int = 71
    private var filePath : Uri? = null
    private var bmpImage : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_informations)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase!!.getReference("Data").child("Users")

        mAuth = FirebaseAuth.getInstance()

        fun chooseImage(){
            var intent : Intent  = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }


        save_user.setOnClickListener {
            sendInformations()
        }

        set_image_profile_linearlayout.setOnClickListener {
            chooseImage()
        }

        circle_image_uploaded_view.setOnClickListener {
            chooseImage()
        }

        cal = Calendar.getInstance()

        val dateSetLisener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal!!.set(Calendar.YEAR, year)
            cal!!.set(Calendar.MONTH, monthOfYear)
            cal!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf : String = SimpleDateFormat(myFormat, Locale.US).format(cal!!.time)

            birth_day_edit_text.setText(sdf)
        }

        birth_day_edit_text.setOnClickListener{
            DatePickerDialog(this, dateSetLisener,
                    cal!!.get(Calendar.YEAR),
                    cal!!.get(Calendar.MONTH),
                    cal!!.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === PICK_IMAGE_REQUEST && resultCode === Activity.RESULT_OK
                && data != null && data.data != null) {
            filePath = data.data
            try {
                set_image_profile_linearlayout.visibility = View.GONE
                circle_image_linearlayout.visibility = View.VISIBLE
                var bmp = ImagePicker.getImageFromResult(this , resultCode, data)
                bmpImage = bmp
                Glide.with(this).load(bmp).fitCenter().into(circle_image_uploaded_view)

            } catch (e: IOException) {
                e.printStackTrace()
            }

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


        val date = SimpleDateFormat("dd.MM.yyyy")
        val dateAndTime = date.format(cal!!.getTime())
        if(TextUtils.isEmpty(dateAndTime)) cancel = true else cancel = false


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

            var profilePhotoUrl : String = ""
            if(bmpImage != null){
                var referrencePhoto : StorageReference = storageReference.child(REFERENCE_PROFILE_PHOTO + mAuth!!.uid.toString())
                val byteArrayOutputStream = ByteArrayOutputStream()
                bmpImage!!.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
                val data = byteArrayOutputStream.toByteArray()
                profilePhotoUrl = referrencePhoto.downloadUrl.toString()
                referrencePhoto.putBytes(data)
            }
            var userId :  String? = mAuth!!.uid
            var userInformations = User(name, phoneNumber, dateAndTime, weight, sexe,"beginner", 0,0,0,profilePhotoUrl, userId)

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
