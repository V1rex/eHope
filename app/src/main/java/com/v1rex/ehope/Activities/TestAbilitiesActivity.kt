package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_test_abilities.*

class TestAbilitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_abilities)
        val number = IntArray(19)
        var message : String = "you can donate your blood !"
        var bool = true
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setSupportActionBar(my_toolbar_test)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fun algorithm() {
            for (i in 0..(number.size - 1)){
                if (number.get(i) == 100){
                    message = "Sorry but you can't make a blood donation"
                    bool = false
                    break

                }
            }

        }

        fun algorithm2(){
            for (i in 1..(number.size- 1)){
                if (number[i] > 0 && number[i] < 100){
                    message = "Sorry but you have to wait ${number[i]} months if you want to donate your blood"
                    bool = false
                    break

                }
            }
        }
        my_toolbar_test.setNavigationOnClickListener {
            finish()
        }

        yes_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE

            number[0] = 100
        }

        no_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE
            number[0] = 0
        }



        yes_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE

            number[1] = 100
        }

        no_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE
            number[1] = 0
        }



        yes_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
            number[2] = 100
        }

        no_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
            number[2] = 0
        }




        next_4.setOnClickListener {

            fun showView(){
                question_4_layout.visibility = View.GONE
                question_5_layout.visibility = View.VISIBLE
            }

            var string  = current_weight.text.toString()
            var boolean = string.isEmpty()
            if (boolean){
                Toast.makeText(this, "You need to give your weight", Toast.LENGTH_SHORT).show()

            }else if(!boolean){
                var int : Int = Integer.parseInt(string)
                if( int >= 50){
                    number[3] = 0
                    showView()
                } else if (int < 50){
                    number[3] = 2
                    showView()
                }
            }


        }



        yes_5.setOnClickListener {

            number[4] = 0
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }

        no_5.setOnClickListener {
            number[4] = 0
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }




        yes_6.setOnClickListener {

            number[5] = 100
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }

        no_6.setOnClickListener {
            number[5] = 0
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }




        yes_7.setOnClickListener {

            number[6] = 100
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }

        no_7.setOnClickListener {

            number[6] = 0
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }



        yes_8.setOnClickListener {

            number[7] = 10
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }

        no_8.setOnClickListener {

            number[7] = 0
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }




        yes_9.setOnClickListener {
            number[8] = 100
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }

        no_9.setOnClickListener {
            number[8] = 0
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }




        yes_10.setOnClickListener {

            number[9] = 12
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }

        no_10.setOnClickListener {
            number[9] = 0
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }




        yes_11.setOnClickListener {

            number[10] = 6
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }

        no_11.setOnClickListener {

            number[10] = 0
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }



        yes_12.setOnClickListener {

            number[11] = 1
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }

        no_12.setOnClickListener {

            number[11] = 0
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }



        yes_13.setOnClickListener {

            number[12] = 4
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }

        no_13.setOnClickListener {
            number[12] = 0
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }



        yes_14.setOnClickListener {

            number[13] = 4
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }

        no_14.setOnClickListener {

            number[13] = 0
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }




        yes_15.setOnClickListener {

            number[14] = 100
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }

        no_15.setOnClickListener {
            number[14] = 0
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }



        yes_16.setOnClickListener {
            number[15] = 1
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }

        no_16.setOnClickListener {
            number[15] = 0
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }


        yes_17.setOnClickListener {

            number[16] = 1
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }

        no_17.setOnClickListener {
            number[16] = 0
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }



        yes_18.setOnClickListener {

            number[17] = 100
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }

        no_18.setOnClickListener {
            number[17] = 0
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }



        yes_19.setOnClickListener {
            number[18] = 100
            question_19_layout.visibility = View.GONE
            result_20_layout.visibility = View.VISIBLE

            algorithm()
            if(bool){
                algorithm2()
                if (bool){
                    message = "you can donate your blood !"
                } else if(!bool){
                    result_text_20.text = message
                }

            } else if (!bool){
                result_text_20.text = message
            }

        }

        no_19.setOnClickListener {
            number[18] = 0
            question_19_layout.visibility = View.GONE
            result_20_layout.visibility = View.VISIBLE

            algorithm()
            if(bool){
                algorithm2()
                if (bool){
                    message = "you can donate your blood !"
                    result_text_20.text = message
                } else if(!bool){
                    result_text_20.text = message
                }

            } else if (!bool){
                result_text_20.text = message
            }

        }

        ok_20.setOnClickListener {
            finish()
        }



        }
    }





