package com.v1rex.ehope.Activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.text.TextUtils
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_profile.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.v1rex.ehope.Model.User
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.header.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.SharedPreferences
import android.net.ConnectivityManager




class ProfileActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var mAuth : FirebaseAuth? = null



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        when(id){
            R.id.test_abilities -> startActivity(Intent(this, TestAbilitiesActivity::class.java))
            R.id.sensibilisation -> startActivity(Intent(this, SensibilisationActivity::class.java))
            R.id.nearby_center -> startActivity(Intent(this, NearbyCenterActivity::class.java))
            R.id.logout -> { logout() }
        }
        return false
    }

    private var drawerToggle : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()

        val userId = mAuth!!.uid.toString()



        var mDatabase : FirebaseDatabase? = FirebaseDatabase.getInstance()
        var mRef = mDatabase!!.getReference("Data").child("Users").child(userId)

        var isConnected = isOnline()
        if(isConnected){
            var valueEventListenerUser = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var user = dataSnapshot.getValue(User::class.java)

                    var sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE)


                    var editor : SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("username",user!!.mName)
                    editor.putString("userHeroType", user!!.mHeroType)
                    editor.putString("userPoint",user!!.mPoints.toString())
                    editor.apply()

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }

            }
            mRef.addValueEventListener(valueEventListenerUser)
        }
        else if (!isConnected){
            Toast.makeText(this, "You need to be connected to internet !", Toast.LENGTH_SHORT).show();

        }
        changeUi()
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSupportActionBar(my_toolbar)
        drawerToggle = ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
        drawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp)
        drawer.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        earn_button.setOnClickListener {
            startActivity(Intent(this, EarnPointsActivity::class.java))
            finish()
        }

        edit_profile_icon.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
            finish()

        }

        see_points.setOnClickListener {
            startActivity(Intent(this, UserDonationsActivity::class.java))
            finish()
        }



    }




    fun logout(){
        mAuth!!.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun changeUi(){
        var sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        var heroName : String? = sharedPref.getString("username","")
        hero_name.text = heroName

        var heroType : String? = sharedPref.getString("userHeroType", "")
        hero_type.text = "Hero type: $heroType"

        var heroPoints : String? = sharedPref.getString("userPoint","")
        hero_points.text = "${heroPoints.toString()}"


    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            var sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            var heroName : String? = sharedPref.getString("username","")
            var heroType : String? = sharedPref.getString("userHeroType", "")
            header_hero_name.text = heroName
            header_hero_type.text = "Hero type: $heroType"
            return true
        }


        return super.onOptionsItemSelected(item)
    }

    protected fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else {
            false
        }
    }




}

