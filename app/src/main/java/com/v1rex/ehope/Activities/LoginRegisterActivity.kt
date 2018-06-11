package com.v1rex.ehope.Activities

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_login_register.*
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth


class LoginRegisterActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mAuth = FirebaseAuth.getInstance()


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
        } else cancel = !isPasswordValid(password)

        if (TextUtils.isEmpty(email)) {
            // TODO add error to the layout
            cancel = true
        } else cancel = !isEmailValid(email)

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

    private fun register(){
        if (mAuth == null) {
            return
        }

        var cancel = false

        var email = user_register_edit_text.text.toString()
        var password = password_register_edit_text.text.toString()

        if (!TextUtils.isEmpty(password) ) {
            // TODO add error to the layout
            cancel = true
        } else cancel = !isPasswordValid(password)

        if (TextUtils.isEmpty(email)) {
            // TODO add error to the layout
            cancel = true
        } else cancel = !isEmailValid(email)

        if(!cancel){
            // TODO add the progress layout

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
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


    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
