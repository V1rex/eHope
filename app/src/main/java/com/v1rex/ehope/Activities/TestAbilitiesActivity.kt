package com.v1rex.ehope.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.v1rex.ehope.Model.Test
import com.v1rex.ehope.Model.User
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_test_abilities.*


class TestAbilitiesActivity : AppCompatActivity() {
    var user: User? = null
    var numberOfDonationsFinal : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_abilities)


        var userId : String = ""
        var numberOfDonations : Int = 0


        var mResult = true
        var mNumberofMonths = 0
        var mDateToDonate = ""
        var mInfinity = false


        val number = IntArray(19)
        var message : String = "you can donate your blood !"
        var bool = true
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setSupportActionBar(my_toolbar_test)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        my_toolbar_test.setNavigationOnClickListener {
            finish()
        }


        var isConnected = isOnline()


        if(isConnected){

            var mAuth = FirebaseAuth.getInstance()

            userId = mAuth!!.uid.toString()

            var mDatabase : FirebaseDatabase? = FirebaseDatabase.getInstance()
            var mRef = mDatabase!!.getReference("Data").child("Users").child(userId)


            var valueEventListenerUser = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user = dataSnapshot.getValue(User::class.java)
                    numberOfDonations = user!!.mNumberOfDonations


                }

                override fun onCancelled(databaseError: DatabaseError) {

                }

            }

            mRef.addValueEventListener(valueEventListenerUser)

            if(numberOfDonations == 0){
                question_1_layout.visibility = View.VISIBLE
                message_layout.visibility = View.GONE
                numberOfDonationsFinal = 0
            } else if(numberOfDonations >0){
                loadTestInfo()
                numberOfDonationsFinal = numberOfDonations - 1
            }

        } else{
            message_layout.visibility = View.VISIBLE
        }




        fun algorithm() {
            for (i in 0..(number.size - 1)){
                if (number.get(i) == 100){
                    message = "Sorry but you can't make a blood donation"
                    bool = false
                    mResult = false
                    mNumberofMonths = number.get(i)
                    mInfinity = true
                    mDateToDonate = ""
                    var test = Test(numberOfDonationsFinal, mResult, mNumberofMonths, mDateToDonate,mInfinity, userId)
                    sendTestInfo(test)

                    break

                }
            }

        }

        fun algorithm2(){
            for (i in 1..(number.size- 1)){
                if (number[i] > 0 && number[i] < 100){
                    message = "Sorry but you have to wait ${number[i]} months if you want to donate your blood"
                    bool = false
                    mResult = false
                    mNumberofMonths = number.get(i)
                    mInfinity = false
                    mDateToDonate = ""
                    var test = Test(numberOfDonationsFinal, mResult, mNumberofMonths, mDateToDonate,mInfinity, userId)
                    sendTestInfo(test)
                    break

                }
            }
        }


        yes_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE

//            number[0] = 100
            number[0] = 100
        }

        no_1.setOnClickListener {
            question_1_layout.visibility = View.GONE
            question_2_layout.visibility = View.VISIBLE
//            number[0] = 0
            number[0] = 0
        }



        yes_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE

//            number[1] = 100
            number[1] = 100
        }

        no_2.setOnClickListener {
            question_2_layout.visibility = View.GONE
            question_3_layout.visibility = View.VISIBLE
//            number[1] = 0
            number[1] = 0
        }



        yes_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
//            number[2] = 100
            number[2] = 100
        }

        no_3.setOnClickListener {
            question_3_layout.visibility = View.GONE
            question_4_layout.visibility = View.VISIBLE
//            number[2] = 0
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
//                    number[3] = 0
                    number[14] = 0
                    showView()
                } else if (int < 50){
//                    number[3] = 2
                    number[14] = 2
                    showView()
                }
            }


        }



        yes_5.setOnClickListener {

//            number[4] = 0
            number[18] = 0
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }

        no_5.setOnClickListener {
//            number[4] = 0
            number[18] = 0
            question_5_layout.visibility = View.GONE
            question_6_layout.visibility = View.VISIBLE
        }




        yes_6.setOnClickListener {

//            number[5] = 100
            number[3] = 100
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }

        no_6.setOnClickListener {
//            number[5] = 0
            number[3] = 0
            question_6_layout.visibility = View.GONE
            question_7_layout.visibility = View.VISIBLE
        }




        yes_7.setOnClickListener {

//            number[6] = 100
            number[4] = 100
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }

        no_7.setOnClickListener {

//            number[6] = 0
            number[4] = 0
            question_7_layout.visibility = View.GONE
            question_8_layout.visibility = View.VISIBLE
        }



        yes_8.setOnClickListener {

//            number[7] = 10
            number[10] = 10
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }

        no_8.setOnClickListener {

//            number[7] = 0
            number[10] = 0
            question_8_layout.visibility = View.GONE
            question_9_layout.visibility = View.VISIBLE
        }




        yes_9.setOnClickListener {
//            number[8] = 100
            number[5] = 100
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }

        no_9.setOnClickListener {
//            number[8] = 0
            number[5] = 0
            question_9_layout.visibility = View.GONE
            question_10_layout.visibility = View.VISIBLE
        }




        yes_10.setOnClickListener {

//            number[9] = 12
            number[9] = 12
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }

        no_10.setOnClickListener {
//            number[9] = 0
            number[9] = 0
            question_10_layout.visibility = View.GONE
            question_11_layout.visibility = View.VISIBLE
        }




        yes_11.setOnClickListener {

//            number[10] = 6
            number[11] = 6
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }

        no_11.setOnClickListener {

//            number[10] = 0
            number[11] = 0
            question_11_layout.visibility = View.GONE
            question_12_layout.visibility = View.VISIBLE
        }



        yes_12.setOnClickListener {

//            number[11] = 1
            number[17] = 1
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }

        no_12.setOnClickListener {

//            number[11] = 0
            number[17] = 0
            question_12_layout.visibility = View.GONE
            question_13_layout.visibility = View.VISIBLE
        }



        yes_13.setOnClickListener {

//            number[12] = 4
            number[12] = 4
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }

        no_13.setOnClickListener {
//            number[12] = 0
            number[12] = 0
            question_13_layout.visibility = View.GONE
            question_14_layout.visibility = View.VISIBLE
        }



        yes_14.setOnClickListener {

//            number[13] = 4
            number[13] = 4
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }

        no_14.setOnClickListener {

//            number[13] = 0
            number[13] = 0
            question_14_layout.visibility = View.GONE
            question_15_layout.visibility = View.VISIBLE
        }




        yes_15.setOnClickListener {

//            number[14] = 100
            number[6] = 100
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }

        no_15.setOnClickListener {
//            number[14] = 0
            number[6] = 0
            question_15_layout.visibility = View.GONE
            question_16_layout.visibility = View.VISIBLE
        }



        yes_16.setOnClickListener {
//            number[15] = 1
            number[16] = 1
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }

        no_16.setOnClickListener {
//            number[15] = 0
            number[16] = 0
            question_16_layout.visibility = View.GONE
            question_17_layout.visibility = View.VISIBLE
        }


        yes_17.setOnClickListener {

//            number[16] = 1
            number[15] = 1
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }

        no_17.setOnClickListener {
//            number[16] = 0
            number[15] = 0
            question_17_layout.visibility = View.GONE
            question_18_layout.visibility = View.VISIBLE
        }



        yes_18.setOnClickListener {

//            number[17] = 100
            number[7] = 100
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }

        no_18.setOnClickListener {
//            number[17] = 0
            number[7] = 0
            question_18_layout.visibility = View.GONE
            question_19_layout.visibility = View.VISIBLE
        }



        yes_19.setOnClickListener {
//            number[18] = 100
            number[8] = 100
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
//            number[18] = 0
            number[8] = 0
            question_19_layout.visibility = View.GONE
            result_20_layout.visibility = View.VISIBLE

            algorithm()
            if(bool){
                algorithm2()
                if (bool){

                    mResult = true
                    mNumberofMonths = 0
                    mInfinity = false
                    mDateToDonate = ""
                    var test = Test(numberOfDonationsFinal, mResult, mNumberofMonths, mDateToDonate,mInfinity, userId)
                    sendTestInfo(test)
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


    protected fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else {
            false
        }
    }

    fun loadTestInfo(){

    }

    fun sendTestInfo(test : Test){


        var FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference = FirebaseDatabase.getReference("Data").child("Users").child(test.mUserId)

        var user = User(user!!.mName, user!!.mPhoneNumber, user!!.mBirthday, user!!.mWeight, user!!.mSexe, user!!.mHeroType, user!!.mPoints, user!!.mNumberOfTest + 1 , user!!.mNumberOfDonations, user!!.mUserId)
        reference.setValue(user)
        var FirebaseDatabase2 = com.google.firebase.database.FirebaseDatabase.getInstance()
        var reference2 = FirebaseDatabase2.getReference("Data").child("Users").child(test.mUserId).child("Test").child((user.mNumberOfTest - 1).toString())

        reference2.setValue(test)




    }


}





