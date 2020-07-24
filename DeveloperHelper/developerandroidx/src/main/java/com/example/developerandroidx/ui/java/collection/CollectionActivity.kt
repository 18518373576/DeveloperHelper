package com.example.developerandroidx.ui.java.collection

import android.view.View
import android.widget.TextView
import com.example.developerandroidx.R
import com.example.developerandroidx.base.BaseActivityForKotlinWithKotterKnife
import com.example.developerandroidx.ui.java.collection.dialog.CollectionDialog_01
import com.example.developerandroidx.ui.java.collection.dialog.CollectionDialog_02
import com.example.developerandroidx.ui.java.collection.dialog.CollectionDialog_03
import com.example.developerandroidx.ui.java.collection.dialog.CollectionDialog_04
import kotterknife.bindView

class CollectionActivity : BaseActivityForKotlinWithKotterKnife() {
    private val tvCollection04: TextView by bindView(R.id.tv_collection_04)
    private val tvCollection03: TextView by bindView(R.id.tv_collection_03)
    private val tvCollection02: TextView by bindView(R.id.tv_collection_02)
    private val tvCollection01: TextView by bindView(R.id.tv_collection_01)


    override fun bindLayout(): Int {
        return R.layout.activity_collection
    }


    override fun initView() {
        setTitle("集合")
        tvCollection01.setOnClickListener(this)
        tvCollection02.setOnClickListener(this)
        tvCollection03.setOnClickListener(this)
        tvCollection04.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.tv_collection_01 ->
                CollectionDialog_01().show(context)
            R.id.tv_collection_02 ->
                CollectionDialog_02().show(context)
            R.id.tv_collection_03 ->
                CollectionDialog_03().show(context)
            R.id.tv_collection_04 ->
                CollectionDialog_04().show(context)
        }
    }
}