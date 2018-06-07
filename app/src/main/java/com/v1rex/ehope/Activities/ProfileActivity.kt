package com.v1rex.ehope.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.v1rex.ehope.R
import com.v1rex.ehope.R.id.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        when(id){
            R.id.test_abilities -> Toast.makeText(this, "This abilities", Toast.LENGTH_SHORT).show()
            R.id.sensibilisation -> Toast.makeText(this, "This sensi", Toast.LENGTH_SHORT).show()
            R.id.nearby_center -> Toast.makeText(this, "This nearby", Toast.LENGTH_SHORT).show()
           R.id.logout -> Toast.makeText(this, "This logout", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private var drawerToggle : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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



        edit_profile_icon.setOnClickListener {
            Toast.makeText(this, "This edit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            return true
        }




        return super.onOptionsItemSelected(item)
    }


}

