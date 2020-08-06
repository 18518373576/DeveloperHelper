package com.example.developerandroidx.ui.kotlin

import com.example.developerandroidx.base.BaseViewModel

/**
 * 作者： zjf 8/3/20 11:25 AM
 * 参考：
 * 描述：
 */
class KotlinViewModel : BaseViewModel<MutableList<String>>() {

    override fun initData(vararg param: String?) {
        var list: MutableList<String> = mutableListOf()
        list.add("基本数据类型")
        list.add("数组和集合")
        list.add("流程控制")

        setData(list)
    }
}