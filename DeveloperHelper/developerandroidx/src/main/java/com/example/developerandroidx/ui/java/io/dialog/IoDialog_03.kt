package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/28/20 1:42 PM
 * 参考：
 * 描述：
 */
class IoDialog_03 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "过滤输入流"
            esv_content.addBody("FilterInputStream类是一种装饰器,它本身继承了InputStream类,用来装饰其他的输入流类." +
                    "FilterInputStream的构造方法是protected的,因此外部程序不能创建它的实例.FilterInputStream类和它的子类" +
                    "的构造方法都有一个InputStream类型的参数,该参数指定需要被修饰的输入流.")
            esv_content.addTitle_2("DataInputStream类")
            esv_content.addBody("DataInputStream实现了DataInput接口,用于读取基本数据类型")
        }
    }
}