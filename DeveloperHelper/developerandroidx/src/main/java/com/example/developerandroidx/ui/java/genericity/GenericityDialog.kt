package com.example.developerandroidx.ui.java.genericity

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/27/20 10:35 AM
 * 参考：
 * 描述：
 */
class GenericityDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "泛型"
            esv_content.addBoldBody("泛型允许在定义类和方法时声明类型参数(例如<T>),当程序访问类和方法时,可以提供明确地类型和参数" +
                    "(例<String>),泛型主要有两大作用:" + esv_content.tab +
                    "①编译器在编译时就能根据类型参数来检查各种赋值操作是否类型兼容,从而避免类型转换异常." + esv_content.tab +
                    "②简化程序代码,不必使用强制类型转换.")
            esv_content.addBody("在声明类型参数时,可以通过extends关键字来设定上限,例如<T extends Number>,表示T必须是" +
                    "Number类或其子类.也可以通过super关键字来设定下限,例如<T super ArrayList>,表示T必须是ArrayList类或其父类.")
            esv_content.addBody("如果定义了一个Set类型的变量s,它有可能引用Set<String>类型的实例,也有可能引用Set<Integer>" +
                    "类型的实例,可以使用通配符'?'定义为Set<?>.")
        }
    }
}