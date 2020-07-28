package com.example.developerandroidx.ui.java.io

import android.view.View
import com.example.developerandroidx.base.BaseActivityForKotlinWithViewBinding
import com.example.developerandroidx.databinding.ActivityIoBinding
import com.example.developerandroidx.ui.java.io.dialog.IoDialog_01
import com.example.developerandroidx.ui.java.io.dialog.IoDialog_02
import com.example.developerandroidx.ui.java.io.dialog.IoDialog_03

class IoActivity : BaseActivityForKotlinWithViewBinding<ActivityIoBinding>() {

    override fun initView() {
        setTitle("I/O")
        binding.tvIo01.setOnClickListener(this)
        binding.tvIo02.setOnClickListener(this)
        binding.tvIo03.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v) {
            binding.tvIo01 ->
                IoDialog_01().show(context)
            binding.tvIo02 ->
                IoDialog_02().show(context)
            binding.tvIo03 ->
                IoDialog_03().show(context)
        }
    }
}