package com.v1rex.ehope.Activities

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_login_register.*
import android.view.WindowManager



class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)



        val type:String = intent.getStringExtra("type")

        when(type)
        {
            "login" -> {login_btn.visibility = View.VISIBLE
                        register_btn.visibility = View.GONE}

            "register" ->{register_btn.visibility = View.VISIBLE
                        login_btn.visibility = View.GONE}
        }


        login_btn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        register_btn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
