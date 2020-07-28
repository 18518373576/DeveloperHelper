package com.example.developerandroidx.ui.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developerandroidx.App
import com.example.developerandroidx.base.BaseFragmentForKotlin
import com.example.developerandroidx.databinding.FragmentKotlinBinding

/**
 * 作者： zjf 7/26/20 9:44 PM
 * 参考：
 * 描述：
 */
class KotlinFragment : BaseFragmentForKotlin<FragmentKotlinBinding>() {

    override fun initView() {
        binding.tvKotlin.setOnClickListener {
          //  App.showNotify("hello kotlin")
        }
//        tvKotlin.setOnClickListener {
//            App.showNotify("hello kotlin")
//        }
    }
}