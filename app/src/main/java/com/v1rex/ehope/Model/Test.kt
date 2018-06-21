package com.v1rex.ehope.Model

import android.support.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
@Keep
class Test constructor(var mId : Int = 0,var mResult: Boolean = false, var mNumberOfMonths : Int = 0, var mDateToDonate : String = "", var mInfinity : Boolean = false, var mUserId : String = "") {
}