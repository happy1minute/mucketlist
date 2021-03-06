package com.yam.core.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.yam.core.application.YApplication
import com.yam.core.util.RUtil

abstract class YFragment : Fragment() {
    lateinit var wrapper: View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wrapper.isClickable = true

        onInit()
    }

    abstract fun onInit()

    open fun moveToFragment(fragment: Fragment, anim: String) {
        var fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()

        if(!TextUtils.isEmpty(anim)) {
            when(anim){
                "top" -> fragmentTransaction.setCustomAnimations(RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_in_bottom"), RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_out_top"))
                "left" -> fragmentTransaction.setCustomAnimations(RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_in_right"), RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_out_left"))
                "right" -> fragmentTransaction.setCustomAnimations(RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_in_left"), RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_out_right"))
                "bottom" -> fragmentTransaction.setCustomAnimations(RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_in_top"), RUtil.getR(activity!!.applicationContext, "anim", "anim_slide_out_bottom"))
                "fade" -> fragmentTransaction.setCustomAnimations(RUtil.getR(activity!!.applicationContext, "anim", "anim_fade_in"), RUtil.getR(activity!!.applicationContext, "anim", "anim_fade_out"))
            }
        }

        var preFragment = YApplication.fragmentList.get(YApplication.fragmentList.size - 1)
        preFragment.onHiddenChanged(true)

        fragmentTransaction.add(
            RUtil.getIdR(activity!!.applicationContext, "contents"),
            fragment,
            "center"
        )
        fragmentTransaction.commitAllowingStateLoss()

        YApplication.addFragment(fragment)
    }
}