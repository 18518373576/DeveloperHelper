package com.example.developerandroidx.ui.kotlin

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.developerandroidx.adapter.quickAdapter.KotlinAdapter
import com.example.developerandroidx.base.BaseFragmentForKotlin
import com.example.developerandroidx.databinding.FragmentKotlinBinding
import com.example.developerandroidx.ui.kotlin.dialog.KotlinDialog_01
import com.example.developerandroidx.ui.kotlin.dialog.KotlinDialog_02

/**
 * 作者： zjf 7/26/20 9:44 PM
 * 参考：
 * 描述：kotlin基础
 */
class KotlinFragment : BaseFragmentForKotlin<FragmentKotlinBinding>(), OnItemClickListener {

    override fun initView() {
        binding.rcvKotlin.layoutManager = LinearLayoutManager(context)
    }

    override fun initData() {
        super.initData()
        var adapter = KotlinAdapter();
        binding.rcvKotlin.adapter = adapter
        adapter.setOnItemClickListener(this)
        var viewModel: KotlinViewModel = getViewModel(this, KotlinViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (adapter.data.get(position)) {
            "基本数据类型" ->
                KotlinDialog_01().show(binding.root.context)
            "数组和集合" ->
                KotlinDialog_02().show(binding.root.context)

        }
    }
}