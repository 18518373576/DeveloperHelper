package com.example.developerandroidx.ui.java.designModel

import com.example.developerandroidx.base.BaseViewModel

/**
 * 作者： zjf 7/27/20 11:29 AM
 * 参考：
 * 描述：
 */
class DesignModelViewModel : BaseViewModel<MutableList<String>>() {

    override fun initData(vararg param: String?) {
        var list: MutableList<String> = mutableListOf()
        list.add("工厂模式")
        list.add("工厂模式")
        list.add("工厂模式")

        setData(list)
    }
}