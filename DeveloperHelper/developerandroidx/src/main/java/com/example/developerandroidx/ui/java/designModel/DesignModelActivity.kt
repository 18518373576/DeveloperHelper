package com.example.developerandroidx.ui.java.designModel

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.developerandroidx.adapter.quickAdapter.DesignModelAdapter
import com.example.developerandroidx.base.BaseActivityForKotlinWithViewBinding
import com.example.developerandroidx.databinding.ActivityDesignModelBinding
import com.example.developerandroidx.ui.java.designModel.dialog.BuilderModelDialog
import com.example.developerandroidx.ui.java.designModel.dialog.FactoryModelDialog
import com.example.developerandroidx.ui.java.designModel.dialog.ObserverDialog

class DesignModelActivity : BaseActivityForKotlinWithViewBinding<ActivityDesignModelBinding>(), OnItemClickListener {

    lateinit var adapter: DesignModelAdapter
    override fun initView() {
        setTitle("设计模式")
        adapter = DesignModelAdapter()
        binding.rcvDesignModel.layoutManager = LinearLayoutManager(context)
        binding.rcvDesignModel.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    override fun initData() {
        super.initData()
        var viewModel: DesignModelViewModel = getViewModel(DesignModelViewModel::class.java)

        viewModel.getData().observe(this, Observer {
            adapter.setList(it)
        })
    }

    /**
     * item点击事件
     */
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (adapter.data[position] as String?) {
            "工厂模式" ->
                FactoryModelDialog().show(context)
            "建造者模式" ->
                BuilderModelDialog().show(context)
            "观察者模式" ->
                ObserverDialog().show(context)


        }

    }

}