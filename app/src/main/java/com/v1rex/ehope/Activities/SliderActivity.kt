package com.v1rex.ehope.Activities

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.github.paolorotolo.appintro.AppIntro
import com.v1rex.ehope.Fragments.SLideOneFragment
import com.v1rex.ehope.Fragments.SlideFourFragment
import com.v1rex.ehope.Fragments.SlideThreeFragment
import com.v1rex.ehope.Fragments.SlideTwoFragment


class SliderActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        addSlide(SLideOneFragment.newInstance())
        addSlide(SlideTwoFragment.newInstance())
        addSlide(SlideThreeFragment.newInstance())
        addSlide(SlideFourFragment.newInstance())


        setBarColor(Color.parseColor("#00FFFFFF"))
        setSeparatorColor(Color.parseColor("#ffffff"))

    }


}
