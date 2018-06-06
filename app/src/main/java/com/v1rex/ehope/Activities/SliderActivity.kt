package com.v1rex.ehope.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import com.github.paolorotolo.appintro.AppIntro2
import com.v1rex.ehope.Fragments.SLideOneFragment
import com.v1rex.ehope.Fragments.SlideFourFragment
import com.v1rex.ehope.Fragments.SlideThreeFragment
import com.v1rex.ehope.Fragments.SlideTwoFragment


class SliderActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        addSlide(SLideOneFragment.newInstance())
        addSlide(SlideTwoFragment.newInstance())
        addSlide(SlideThreeFragment.newInstance())
        addSlide(SlideFourFragment.newInstance())

        setFadeAnimation()
        setBarColor(Color.parseColor("#00FFFFFF"))



    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, LoginRegisterActivity::class.java)
        intent.putExtra("type","register")
        startActivity(intent)

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, LoginRegisterActivity::class.java)
        intent.putExtra("type","register")
        startActivity(intent)
    }


}
