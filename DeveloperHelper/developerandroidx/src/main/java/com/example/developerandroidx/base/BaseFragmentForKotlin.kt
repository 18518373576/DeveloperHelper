package com.example.developerandroidx.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 作者： zjf 7/26/20 9:30 PM
 * 参考：
 * 描述：
 */
abstract class BaseFragmentForKotlin<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        //具有<>符号的变量是参数化类型,这里判断当前类是否带有类型参数
        if (type is ParameterizedType) {
            val clazz = type.actualTypeArguments[0] as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            _binding = method.invoke(null, layoutInflater, container, false) as VB
            return _binding!!.root
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    /**
     * 获取ViewModel
     *
     * @param owner      ViewModel库拥有者，可是是fragment或者activity
     * @param modelClass 自己定义的viewModel类
     * @return
     */
    open fun <VM : ViewModel> getViewModel(owner: ViewModelStoreOwner, modelClass: Class<VM>): VM {
        return ViewModelProvider(owner!!)[modelClass!!]
    }

    protected abstract fun initView()
    protected fun initData() {

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}