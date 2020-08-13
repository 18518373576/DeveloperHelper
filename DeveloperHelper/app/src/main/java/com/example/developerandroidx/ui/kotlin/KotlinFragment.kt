package com.example.developerandroidx.ui.kotlin

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.developerandroidx.adapter.quickAdapter.KotlinAdapter
import com.example.developerandroidx.base.BaseFragmentForKotlin
import com.example.developerandroidx.databinding.FragmentKotlinBinding
import com.example.developerandroidx.ui.kotlin.dialog.*

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
        val adapter = KotlinAdapter();
        binding.rcvKotlin.adapter = adapter
        adapter.setOnItemClickListener(this)
        val viewModel: KotlinViewModel = getViewModel(this, KotlinViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (adapter.data.get(position)) {
            "基本数据类型" ->
                KotlinDialog_01().show(binding.root.context)
            "数组和集合" ->
                KotlinDialog_02().show(binding.root.context)
            "流程控制" ->
                KotlinDialog_03().show(binding.root.context)
            "空安全" ->
                KotlinDialog_04().show(binding.root.context)
            "等式判断" ->
                KotlinDialog_05().show(binding.root.context)

        }
    }
}