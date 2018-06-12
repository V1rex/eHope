package com.v1rex.ehope.Activities

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




class ProfileActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var mAuth : FirebaseAuth? = null
    var mDatabase : FirebaseDatabase? = null
    var mRef : DatabaseReference? = null
    var user : User? = null
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

        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase!!.getReference("Data").child("Users").child(userId)

        var valueEventListenerUser = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                changeUi()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }

        }

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
        }

        edit_profile_icon.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        see_points.setOnClickListener {
            startActivity(Intent(this, UserDonationsActivity::class.java))
        }

        mRef!!.addValueEventListener(valueEventListenerUser)
    }

    fun changeUi(){
        var heroName = user!!.mName
        hero_name.text = heroName

        var heroType = user!!.mHeroType
        hero_type.text = "Hero type: $heroType"

        var heroPoints = user!!.mPoints
        hero_points.text = "${heroPoints.toString()}"
    }

    fun logout(){
        mAuth!!.signOut()
        startActivity(Intent(this, MainActivity::class.java))

    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            return true
        }


        return super.onOptionsItemSelected(item)
    }



}

