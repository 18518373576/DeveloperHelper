package com.example.developerandroidx.ui.java.collection

import android.view.View
import android.widget.TextView
import com.example.developerandroidx.App
import com.example.developerandroidx.R
import com.example.developerandroidx.base.BaseActivityForKotlinWithKotterKnife
import kotterknife.bindView

class CollectionActivity : BaseActivityForKotlinWithKotterKnife(), View.OnClickListener {

    private val tvCollection01: TextView by bindView(R.id.tv_collection_01)


    override fun bindLayout(): Int {
        return R.layout.activity_collection
    }


    override fun initView() {
        setTitle("集合")
        tvCollection01.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_collection_01 ->
                App.showNotify("提示消息")
        }
    }
}