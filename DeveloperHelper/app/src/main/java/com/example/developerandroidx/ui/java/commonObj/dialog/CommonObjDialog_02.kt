package com.example.developerandroidx.ui.java.commonObj.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/2/20 10:34 AM
 * 参考：
 * 描述：
 */
class CommonObjDialog_02 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "Math类"
            esv_content.addBody("java.lang.Math类提供了许多用于数学运算的静态方法:")
            esv_content.addBoldBody("abs():返回绝对值." + esv_content.tab +
                    "ceil():返回大于等于参数的最小整数." + esv_content.tab +
                    "floor():返回小于等于参数的最大整数." + esv_content.tab +
                    "max():返回两个参数的较大值." + esv_content.tab +
                    "min():返回两个参数的较小值." + esv_content.tab +
                    "random():返回0~1之间的double类型的随机数,包括0不包括1." + esv_content.tab +
                    "round():返回四舍五入的整数值." + esv_content.tab +
                    "sin():正弦函数." + esv_content.tab +
                    "cos():余弦函数." + esv_content.tab +
                    "tan():正切函数." + esv_content.tab +
                    "exp():返回自然对数的幂." + esv_content.tab +
                    "sqrt():平方根函数." + esv_content.tab +
                    "pow():幂运算.")
        }
    }
}