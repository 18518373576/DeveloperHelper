package com.example.developerandroidx.adapter.quickAdapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.developerandroidx.R

/**
 * 作者： zjf 8/3/20 11:31 AM
 * 参考：
 * 描述：
 */
class KotlinAdapter(layoutResId: Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {

    //重写构造方法
    constructor() : this(R.layout.item_kotlin)

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_kotlin, item)
    }
}