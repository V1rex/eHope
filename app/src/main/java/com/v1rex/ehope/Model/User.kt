package com.v1rex.ehope.Model

import android.support.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
@Keep
class User constructor(var mName : String = "", var mPhoneNumber : String = "", var mBirthday : String = "",var  mWeight: Int = 0,var  mSexe : String = "", var mHeroType : String = "" , var mPoints: Int = 0, var mNumberOfTest: Int = 0 ,var mNumberOfDonations : Int = 0,var mUserId : String? = "") {



}