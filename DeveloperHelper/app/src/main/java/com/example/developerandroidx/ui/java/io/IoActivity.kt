package com.example.developerandroidx.ui.java.io

import android.view.View
import com.example.developerandroidx.base.BaseActivityForKotlinWithViewBinding
import com.example.developerandroidx.databinding.ActivityIoBinding
import com.example.developerandroidx.ui.java.io.dialog.*

class IoActivity : BaseActivityForKotlinWithViewBinding<ActivityIoBinding>() {

    override fun initView() {
        setTitle("I/O")
        binding.tvIo01.setOnClickListener(this)
        binding.tvIo02.setOnClickListener(this)
        binding.tvIo03.setOnClickListener(this)
        binding.tvIo04.setOnClickListener(this)
        binding.tvIo05.setOnClickListener(this)
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
            binding.tvIo04 ->
                IoDialog_04().show(context)
            binding.tvIo05 ->
                IoDialog_05().show(context)

        }
    }
}