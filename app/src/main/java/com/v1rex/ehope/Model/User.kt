package com.v1rex.ehope.Model

import android.support.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
@Keep
class User  {

    var mName : String = ""
    var mPhoneNumber : String = ""
    var mBirthday : String = ""
    var  mWeight: Int = 0
    var  mSexe : String = ""
    var mHeroType : String = ""
    var mPoints: Int = 0
    var mNumberOfTest: Int = 0
    var mNumberOfDonations : Int = 0
    var mPhotoExist : String = ""
    var mUserId : String? = ""

    constructor(){

    }



    constructor(mName: String, mPhoneNumber: String, mBirthday: String, mWeight: Int, mSexe: String, mHeroType: String, mPoints: Int, mNumberOfTest: Int, mNumberOfDonations: Int, mUserId: String?) {
        this.mName = mName
        this.mPhoneNumber = mPhoneNumber
        this.mBirthday = mBirthday
        this.mWeight = mWeight
        this.mSexe = mSexe
        this.mHeroType = mHeroType
        this.mPoints = mPoints
        this.mNumberOfTest = mNumberOfTest
        this.mNumberOfDonations = mNumberOfDonations
        this.mUserId = mUserId
    }

    constructor(mName: String, mPhoneNumber: String, mBirthday: String, mWeight: Int, mSexe: String, mHeroType: String, mPoints: Int, mNumberOfTest: Int, mNumberOfDonations: Int, mPhotoUrl: String, mUserId: String?) {
        this.mName = mName
        this.mPhoneNumber = mPhoneNumber
        this.mBirthday = mBirthday
        this.mWeight = mWeight
        this.mSexe = mSexe
        this.mHeroType = mHeroType
        this.mPoints = mPoints
        this.mNumberOfTest = mNumberOfTest
        this.mNumberOfDonations = mNumberOfDonations
        this.mPhotoExist = mPhotoUrl
        this.mUserId = mUserId
    }

}