package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_earn_points.*


class EarnPointsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earn_points)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSupportActionBar(my_toolbar_earn)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar_earn.setNavigationOnClickListener {
            finish()
        }

        edit_time.setOnClickListener {
            time_picker_earn_layout.visibility = View.VISIBLE
        }

        finished_time_earn.setOnClickListener {
            time_picker_earn_layout.visibility = View.GONE
        }


        edit_date.setOnClickListener {
            date_picker_earn_layout.visibility = View.VISIBLE
        }

        finished_date_earn.setOnClickListener {
            date_picker_earn_layout.visibility = View.GONE
        }


        save_earn.setOnClickListener {
            finish()
        }
    }
}
