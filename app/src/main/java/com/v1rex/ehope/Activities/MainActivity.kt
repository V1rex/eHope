package com.v1rex.ehope.Activities

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit Application?")
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            moveTaskToBack(true)
                            android.os.Process.killProcess(android.os.Process.myPid())
                            System.exit(1)
                        })

                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
