package com.example.developerandroidx.adapter.quickAdapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.developerandroidx.R

/**
 * 作者： zjf 7/27/20 12:18 PM
 * 参考：
 * 描述：
 */
class DesignModelAdapter(layoutResId: Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    constructor() : this(R.layout.item_design_model)

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_design_model, item)
    }
}