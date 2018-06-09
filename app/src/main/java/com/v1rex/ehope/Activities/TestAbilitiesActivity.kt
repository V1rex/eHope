package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_test_abilities.*

class TestAbilitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_abilities)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setSupportActionBar(my_toolbar_test)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar_test.setNavigationOnClickListener {
            finish()
        }

        yes_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE
        }

        no_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE
        }



        yes_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE
        }

        no_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE
        }



        yes_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
        }

        no_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
        }




        next_4.setOnClickListener {
            question_4_layout.visibility = View.GONE
            question_5_layout.visibility = View.VISIBLE
        }



        yes_5.setOnClickListener {
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }

        no_5.setOnClickListener {
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }




        yes_6.setOnClickListener {
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }

        no_6.setOnClickListener {
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }




        yes_7.setOnClickListener {
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }

        no_7.setOnClickListener {
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }



        yes_8.setOnClickListener {
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }

        no_8.setOnClickListener {
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }




        yes_9.setOnClickListener {
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }

        no_9.setOnClickListener {
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }




        yes_10.setOnClickListener {
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }

        no_10.setOnClickListener {
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }




        yes_11.setOnClickListener {
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }

        no_11.setOnClickListener {
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }



        yes_12.setOnClickListener {
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }

        no_12.setOnClickListener {
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }



        yes_13.setOnClickListener {
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }

        no_13.setOnClickListener {
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }



        yes_14.setOnClickListener {
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }

        no_14.setOnClickListener {
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }




        yes_15.setOnClickListener {
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }

        no_15.setOnClickListener {
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }



        yes_16.setOnClickListener {
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }

        no_16.setOnClickListener {
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }


        yes_17.setOnClickListener {
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }

        no_17.setOnClickListener {
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }



        yes_18.setOnClickListener {
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }

        no_18.setOnClickListener {
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }



        yes_19.setOnClickListener {
            finish()
        }

        no_19.setOnClickListener {
            finish()
        }





    }
}
