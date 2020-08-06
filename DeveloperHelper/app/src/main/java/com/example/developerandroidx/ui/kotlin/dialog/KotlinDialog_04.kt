package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/6/20 2:53 PM
 * 参考：
 * 描述：
 */
class KotlinDialog_04 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "空安全"
            esv_content.addBody("kotlin中引入了空安全概念,每个类型的变量都分作不可为空和可为空两种.正常声明的变量默认都是非空的,如:")
            esv_content.addAlertBody("var str: String = \"a\"")
            esv_content.addBody("非空变量要么在声明时就赋值,要么在方法调用前赋值.声明可为空变量时,可在声明时在类型后面加?,如:")
            esv_content.addAlertBody("var str_01: String? = null")
            esv_content.addBody("kotlin提供了校验字符串空值的几个方法:")
            esv_content.addCode(
                    "fun checkEmpty() {\n" +
                            "    //校验是否为null或长度为0,非空串和可空串均可调用\n" +
                            "    println(str_01.isNullOrEmpty())\n" +
                            "    //校验是否为null或长度为0或全为空格,非空串和可空串均可调用\n" +
                            "    println(str_01.isNullOrBlank())\n" +
                            "\n" +
                            "    //以下方法只有非空串可调用\n" +
                            "    println(str.isEmpty())\n" +
                            "    println(str.isBlank())\n" +
                            "    println(str.isNotEmpty())\n" +
                            "    println(str.isNotBlank())\n" +
                            "}")
            esv_content.addBody("校验空值得运算符:")
            esv_content.addCode(
                    "//如果str_01为空返回null,否则返回字符串的长度\n" +
                            "println(str_01?.length)\n" +
                            "//如果str_01为空返回-1,否则返回字符串的长度\n" +
                            "println(str_01?.length ?: -1)\n" +
                            "//如果确定已对str_01赋值,则可以使用!!,如果str_01为空会抛出运行时异常\n" +
                            "str_01 = \"a\"\n" +
                            "println(str_01!!.length)")
        }
    }
}