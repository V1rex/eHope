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
import com.v1rex.ehope.R.id.*
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.activity_profile.*
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener



class ProfileActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var mAuth : FirebaseAuth? = null
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        when(id){
            R.id.test_abilities -> startActivity(Intent(this, TestAbilitiesActivity::class.java))
            R.id.sensibilisation -> startActivity(Intent(this, SensibilisationActivity::class.java))
            R.id.nearby_center -> startActivity(Intent(this, NearbyCenterActivity::class.java))
           R.id.logout -> startActivity(Intent(this, MainActivity::class.java))
        }
        return false
    }

    private var drawerToggle : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()

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
    }

    private fun login(){
        if (mAuth == null) {
            return
        }

        var cancel = false

        var email = user_register_edit_text.text.toString()
        var password = password_register_edit_text.text.toString()

        if (!TextUtils.isEmpty(password) ) {
            // TODO add error to the layout
            cancel = true
        } else if(!isPasswordValid(password)){
            // TODO add error to the layout
            cancel = true
        } else{
            cancel = false
        }

        if (TextUtils.isEmpty(email)) {
            // TODO add error to the layout
            cancel = true
        } else if (!isEmailValid(email)) {
            // add error to the layout
            cancel = true
        } else {
            cancel = false
        }

        if(!cancel){
            // TODO add the progress layout

            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                // TODO set the progress layout visibility to GONE

                if (!task.isSuccessful) {
                    // TODO if task isn't successfully add error to the layout

                } else {

                    // TODO start activity
                    finish()

                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            return true
        }


        return super.onOptionsItemSelected(item)
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }


}

