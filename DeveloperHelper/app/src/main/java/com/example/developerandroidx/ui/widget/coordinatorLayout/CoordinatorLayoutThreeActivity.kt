package com.example.developerandroidx.ui.widget.coordinatorLayout

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developerandroidx.R
import com.example.developerandroidx.adapter.ArithmeticRcvAdapter
import com.example.developerandroidx.base.BaseActivityForKotlin
import com.example.developerandroidx.databinding.ActivityCoordinatorLayoutThreeBinding
import com.example.developerandroidx.ui.java.arithmetic.ArithmeticViewModel
import com.example.developerandroidx.utils.PixelTransformForAppUtil
import com.google.android.material.appbar.CollapsingToolbarLayout

class CoordinatorLayoutThreeActivity : BaseActivityForKotlin(), View.OnClickListener {
    lateinit var binding: ActivityCoordinatorLayoutThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorLayoutThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    override fun initStatusBar() {
        setNativeStatusBar(StateBarType.TRAN)
    }

    override fun initView() {
        binding.rcvCoordinator.layoutManager = LinearLayoutManager(context)
        binding.ivBack.setOnClickListener(this)
        setViewOffset()
    }

    override fun initData() {
        super.initData()

        var viewModel: ArithmeticViewModel = getViewModel(ArithmeticViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            binding.rcvCoordinator.adapter = ArithmeticRcvAdapter(it)
        })
    }

    private fun setViewOffset() {
        //设置toolbar的margin值,为系统状态栏的高度
        val params = CollapsingToolbarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.titleBarHeight))
        params.topMargin = PixelTransformForAppUtil.getStatusBarHeight()
        binding.toolbar.setLayoutParams(params)
        //返回按钮下移状态栏的高度
        binding.ivBack.setTranslationY(PixelTransformForAppUtil.getStatusBarHeight().toFloat())
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivBack -> finish()
        }
    }
}