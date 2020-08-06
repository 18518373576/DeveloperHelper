package com.example.developerandroidx.ui.java.commonObj

import android.view.View
import com.example.developerandroidx.base.BaseActivityForKotlinWithViewBinding
import com.example.developerandroidx.databinding.ActivityCommonObjBinding
import com.example.developerandroidx.ui.java.commonObj.dialog.CommonObjDialog_01
import com.example.developerandroidx.ui.java.commonObj.dialog.CommonObjDialog_02

class CommonObjActivity : BaseActivityForKotlinWithViewBinding<ActivityCommonObjBinding>() {

    override fun initView() {
        setTitle("Java常用类")
        binding.tvCommonObj01.setOnClickListener(this)
        binding.tvCommonObj02.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v) {
            binding.tvCommonObj01 ->
                CommonObjDialog_01().show(context)
            binding.tvCommonObj02 ->
                CommonObjDialog_02().show(context)
        }
    }
}