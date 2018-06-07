package com.v1rex.ehope.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        login_btn.setOnClickListener {
            val intent = Intent(this, LoginRegisterActivity::class.java)
            intent.putExtra("type","login")
            startActivity(intent)
        }

        register_btn.setOnClickListener {
            val intent = Intent(this, SliderActivity::class.java)
            intent.putExtra("type","register")
            startActivity(intent)
        }
    }
}
