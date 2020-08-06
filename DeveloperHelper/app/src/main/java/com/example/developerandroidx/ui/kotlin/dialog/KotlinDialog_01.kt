package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/3/20 2:11 PM
 * 参考：
 * 描述：
 */
class KotlinDialog_01 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "基本数据类型"
            esv_content.addTitle_2("基本数据类型的变量声明")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    //字节型\n" +
                            "    var b: Byte = 127\n" +
                            "    //短整形\n" +
                            "    var s: Short = 0\n" +
                            "    //整形\n" +
                            "    var i: Int = 0\n" +
                            "    //长整形\n" +
                            "    var l: Long = 0\n" +
                            "    //单精度浮点类型\n" +
                            "    var f: Float = 0f\n" +
                            "    //双精度浮点类型\n" +
                            "    var d: Double = 0.0\n" +
                            "    //布尔类型\n" +
                            "    var bool: Boolean = true\n" +
                            "    //字符型\n" +
                            "    var c: Char = '0'\n" +
                            "}\n" +
                            "Short取值范围:-32768~32767 占内存:16位2个字节\n" +
                            "Int取值范围:-2147483648~2147483647 占内存:32位4个字节\n" +
                            "Long取值范围:-9223372036854775808~9223372036854775807 占内存:64位8个字节\n" +
                            "Float取值范围:1.4E-45~3.4028235E38\n" +
                            "Double取值范围:4.9E-324~1.7976931348623157E308\n" +
                            "Char占内存:16位2个字节")
            esv_content.addBody("前面的var表示后面是一个变量声明语句,接着是'变量名:变量类型'的格式声明,如果变量声明完成直接回车换行" +
                    ",后面无需带分号,如果没有回车换行,而是添加其他语句,则需要带上分号.")
            esv_content.addTitle_2("简单变量之间的转换")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    var i: Int = 66\n" +
                            "    println(i.toByte())\n" +
                            "    println(i.toChar())\n" +
                            "    println(i.toDouble())\n" +
                            "    println(i.toFloat())\n" +
                            "    println(i.toLong())\n" +
                            "    println(i.toShort())\n" +
                            "    println(i.toString())\n" +
                            "}")
        }
    }
}