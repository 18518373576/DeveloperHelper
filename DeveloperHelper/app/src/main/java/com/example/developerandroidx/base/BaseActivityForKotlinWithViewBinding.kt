package com.example.developerandroidx.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.example.developerandroidx.R
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import kotlin.RuntimeException

/**
 * 作者： zjf 7/23/20 11:07 AM
 * 参考：
 * 描述：
 */
abstract class BaseActivityForKotlinWithViewBinding<VB : ViewBinding> : BaseActivityForKotlin(), View.OnClickListener {

    lateinit var binding: VB
    lateinit var rootView: View
    lateinit var tvTitle: TextView
    lateinit var ivBack: ImageView
    lateinit var ivRight: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        //具有<>符号的变量是参数化类型
        if (type is ParameterizedType) {
            val clazz = type.actualTypeArguments[0] as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            binding = method.invoke(null, layoutInflater) as VB
            rootView = binding.root
            setContentView(rootView)

            try {
                tvTitle = rootView.findViewById(R.id.tv_title)
                ivBack = rootView.findViewById(R.id.iv_back)
                ivRight = rootView.findViewById(R.id.iv_right)
                ivBack.setOnClickListener(this)
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("布局文件必须 <include layout=\"@layout/title_bar\"/>,如无需使用titleBar请直接继承BaseActivityForKotlin")
            }

            initView()
            initData()
        } else {
            throw RuntimeException("请指定需要加载的layout,如不需要直接继承BaseActivityForKotlin")
        }
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        tvTitle.text = title
    }

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