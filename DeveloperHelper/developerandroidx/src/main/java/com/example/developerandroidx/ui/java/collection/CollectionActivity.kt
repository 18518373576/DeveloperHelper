package com.example.developerandroidx.ui.java.collection

import android.view.View
import com.example.developerandroidx.R
import com.example.developerandroidx.base.BaseActivityForKotlinWithViewBinding
import com.example.developerandroidx.databinding.ActivityCollectionBinding
import com.example.developerandroidx.ui.java.collection.dialog.*

class CollectionActivity : BaseActivityForKotlinWithViewBinding<ActivityCollectionBinding>() {

    override fun initView() {
        setTitle("集合")
        binding.tvCollection01.setOnClickListener(this)
        binding.tvCollection02.setOnClickListener(this)
        binding.tvCollection03.setOnClickListener(this)
        binding.tvCollection04.setOnClickListener(this)
        binding.tvCollection05.setOnClickListener(this)
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
            R.id.tv_collection_05 ->
                CollectionDialog_05().show(context)
        }
    }
}