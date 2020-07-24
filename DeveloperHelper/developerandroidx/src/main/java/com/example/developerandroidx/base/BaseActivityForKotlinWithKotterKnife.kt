package com.example.developerandroidx.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.developerandroidx.R
import kotterknife.bindView
import java.lang.Exception
import java.lang.RuntimeException

/**
 * 作者： zjf 7/23/20 11:07 AM
 * 参考：
 * 描述：
 */
abstract class BaseActivityForKotlinWithKotterKnife : BaseActivityForKotlin(), View.OnClickListener {

    private val ivBack: ImageView by bindView(R.id.iv_back)
    private val tvTitle: TextView by bindView(R.id.tv_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bindLayout())
        try {
            ivBack.setOnClickListener(this)
        } catch (e: Exception) {
            throw RuntimeException("布局文件必须 <include layout=\\\"@layout/title_bar\\\"/>,如无需使用titleBar请直接继承BaseActivity")
        }


        initView()

        initData()
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        tvTitle.text = title
    }

    /**
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract fun bindLayout(): Int

    /**
     *  点击事件
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back ->
                finish()
        }
    }
}