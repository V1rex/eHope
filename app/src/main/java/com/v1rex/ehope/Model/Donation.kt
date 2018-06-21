package com.v1rex.ehope.Model

import android.support.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
@Keep
class Donation constructor(var mId : Int = 0, var mDateTime : String = "", var mUid : String = "") {
}