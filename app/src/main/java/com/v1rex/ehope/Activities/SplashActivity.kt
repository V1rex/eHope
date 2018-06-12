package com.v1rex.ehope.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, ProfileActivity::class.java))
        } else if (FirebaseAuth.getInstance().currentUser == null){
            startActivity(Intent(this,MainActivity::class.java))
        }
        finish()
    }
}
