package com.example.developerandroidx.ui.widget.waterDrop

import com.example.developerandroidx.R
import com.example.developerandroidx.base.BaseActivityWithButterKnife

class WaterDropActivity : BaseActivityWithButterKnife() {
    override fun bindLayout(): Int {
        return R.layout.activity_water_drop
    }

    override fun initView() {
        super.initView()
        setTitle("水滴")
    }

}