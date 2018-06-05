package com.v1rex.ehope.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_btn.setOnClickListener {
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
        }

        register_btn.setOnClickListener {
            val intent = Intent(this, SliderActivity::class.java)
            startActivity(intent)
        }
    }
}
