package com.v1rex.ehope.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.v1rex.ehope.Model.Donation
import com.v1rex.ehope.Model.Test
import com.v1rex.ehope.Model.User
import com.v1rex.ehope.R
import kotlinx.android.synthetic.main.activity_earn_points.*
import java.text.SimpleDateFormat
import java.util.*


class EarnPointsActivity : AppCompatActivity() {
    var user: User? = User()
    var test: Test? = null
    var dateTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earn_points)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSupportActionBar(my_toolbar_earn)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        my_toolbar_earn.setNavigationOnClickListener {
            finish()
        }

        edit_time.setOnClickListener {
            time_picker_earn_layout.visibility = View.VISIBLE
        }

        finished_time_earn.setOnClickListener {
            time_picker_earn_layout.visibility = View.GONE
        }

        edit_date.setOnClickListener {
            date_picker_earn_layout.visibility = View.VISIBLE
        }

        finished_date_earn.setOnClickListener {
            date_picker_earn_layout.visibility = View.GONE
        }


        save_earn.setOnClickListener {
            val day = date_picker_earn.dayOfMonth
            val month = date_picker_earn.month
            val year = date_picker_earn.year
            val hour = time_picker_earn.hour
            val minute = time_picker_earn.minute

            val calendar = Calendar.getInstance()
            calendar.set(year, month, day, hour, minute)
            val calendarTimeToDonate = calendar
            calendarTimeToDonate.add(Calendar.MONTH, 1)

            val date = SimpleDateFormat("yyyy.MM.dd 'at' hh:mm")
            dateTime = date.format(calendar.getTime())

            val dateToPassTest = SimpleDateFormat("yyyy.MM.dd")
            val dateTimeToPassTest = date.format(calendarTimeToDonate.getTime())



            var numberTestIndex = (user!!.mNumberOfTest) - 1

            var mDatabase = FirebaseDatabase.getInstance()

            var mReference = mDatabase.getReference("Data").child("Users").child(user!!.mUserId.toString())
            var mReferenceTest = mDatabase.getReference("Data").child("Test").child(user!!.mUserId.toString()).child(numberTestIndex.toString())


            var user2 = user

            user2!!.mNumberOfDonations += 1
            user2!!.mPoints += 100
            user2!!.dateToPassTest = dateTimeToPassTest

            mReference.setValue(user2)



            var donation = Donation(user!!.mNumberOfTest - 1, dateTime, user!!.mUserId.toString())

            var mReferenceDonation = mDatabase.getReference("Data").child("Donations").child(user!!.mUserId.toString()).child((user2!!.mNumberOfTest - 1).toString())
            mReferenceDonation.setValue(donation)


            finish()
        }

        var isConnected = isOnline()

        if(isConnected){

            var mAuth = FirebaseAuth.getInstance().uid

            var mDatabase : FirebaseDatabase? = FirebaseDatabase.getInstance()
            var mRef = mDatabase!!.getReference("Data").child("Users").child(mAuth.toString())

            var valueEventListenerUser = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user = dataSnapshot.getValue(User::class.java)
                    var numberDonations = user!!.mNumberOfDonations
                    var numberTest = user!!.mNumberOfTest
                    var dateToPassTestString = user!!.dateToPassTest

                    val calendarToPassCal = Calendar.getInstance()
                    val dateToPassTest = SimpleDateFormat("yyyy.MM.dd")
                    calendarToPassCal.time = dateToPassTest.parse(dateToPassTestString)

                    if (numberDonations == 0 && numberTest == 0 && dateToPassTestString == ""){
                        message_layout_donations.visibility = View.VISIBLE

                        message_text_donations.setText("Sorry but you have test your abilities before add a donations ")
                    }
                    else if(numberDonations > 0 && numberTest > 0 && numberTest == numberDonations ){
                        message_layout_donations.visibility = View.VISIBLE

                        message_text_donations.setText("Sorry but you have to wait till to test your abilities before add a donations")
                    }
                    else if(numberTest > numberDonations){

                        var mRef2 = mDatabase.getReference("Data").child("Test").child(user!!.mUserId.toString()).child((user!!.mNumberOfTest - 1).toString())

                        var valueEventListenerTest = object : ValueEventListener {
                            override fun onDataChange(p0: DataSnapshot) {
                               test = p0.getValue(Test::class.java)
                                var dateToDonate = test!!.mDateToDonate
                                var infinityTest = test!!.mInfinity
                                var result = test!!.mResult


                                if(infinityTest == true){
                                    message_layout_donations.visibility = View.VISIBLE

                                    message_text_donations.setText("Sorry but you cannot donate your blood")
                                }
                                else{
                                    var date = Date()
                                    val currentCalendar = Calendar.getInstance()
                                    currentCalendar.time = date
                                    if(result == false){
                                        if (currentCalendar.after(calendarToPassCal)){
                                            message_layout_donations.visibility = View.VISIBLE
                                            message_text_donations.setText("Sorry but you have to wait till: $dateToPassTestString to re-pass the test and donate your blood ")
                                        }else if (currentCalendar.equals(calendarToPassCal)){
                                            message_layout_donations.visibility = View.GONE

                                            add_donations_layout.visibility = View.VISIBLE
                                        }else if (currentCalendar.before(calendarToPassCal)){
                                            message_layout_donations.visibility = View.GONE

                                            add_donations_layout.visibility = View.VISIBLE
                                        }

                                    }
                                    else if (result == true ){
                                        message_layout_donations.visibility = View.GONE

                                        add_donations_layout.visibility = View.VISIBLE
                                    }

                                    mRef2.removeEventListener(this)
                                }




                            }

                            override fun onCancelled(p0: DatabaseError) {

                            }

                        }



                    mRef2.addValueEventListener(valueEventListenerTest)

                    }

                }
                override fun onCancelled(p0: DatabaseError) {

                }
            }
            mRef.addValueEventListener(valueEventListenerUser)

        }
        else if(!isConnected){
            message_layout_donations.visibility = View.VISIBLE

            message_text_donations.setText("Sorry but you need to be connected to internet if you want to add donations")
        }

    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else {
            false
        }
    }
}
