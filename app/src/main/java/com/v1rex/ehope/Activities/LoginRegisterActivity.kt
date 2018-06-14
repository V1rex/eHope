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
            login()
        }

        register_btn.setOnClickListener {
            register()
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
            input_layout_password_register.error = "This field is requierd"
            cancel = true
        } else if(!isPasswordValid(password)){
            input_layout_password_register.error = "This password is invalid"
            cancel = true
        } else{
            cancel = false
        }

        if (TextUtils.isEmpty(email)) {
            input_layout_email_register.error = "This field is required"
            cancel = true
        } else if (!isEmailValid(email)) {
            input_layout_email_register.error = "This email is invalid"
            cancel = true
        } else {
            cancel = false
        }

        if(!cancel){

            action_layout.visibility = View.GONE
            progress_action.visibility = View.VISIBLE
            action_message.text = "Login right now..."

            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->


                if (!task.isSuccessful) {
                    action_layout.visibility = View.VISIBLE
                    progress_action.visibility = View.GONE
                    input_layout_email_register.error = "Error: can't login"
                } else {
                    startActivity(Intent(this, ProfileActivity::class.java))
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
            input_layout_password_register.error = "This field is requierd"
            cancel = true
        } else if(!isPasswordValid(password)){
            input_layout_password_register.error = "This password is invalid"
            cancel = true
        } else{
            cancel = false
        }

        if (TextUtils.isEmpty(email)) {
            input_layout_email_register.error = "This field is required"
            cancel = true
        } else if (!isEmailValid(email)) {
            input_layout_email_register.error = "This email is invalid"
            cancel = true
        } else {
            cancel = false
        }

        if(!cancel){
            action_layout.visibility = View.GONE
            progress_action.visibility = View.VISIBLE
            action_message.text = "Registering right now..."

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->


                if (!task.isSuccessful) {
                    progress_action.visibility = View.GONE
                    action_layout.visibility = View.VISIBLE
                    input_layout_email_register.error = "Error: can't register"

                } else {

                    startActivity(Intent(this, UserInformationsActivity::class.java))
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
