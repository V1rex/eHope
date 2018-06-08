package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_user_donations.*

class UserDonationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_donations)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSupportActionBar(my_toolbar_donations)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar_donations.setNavigationOnClickListener {
            finish()
        }

    }
}
