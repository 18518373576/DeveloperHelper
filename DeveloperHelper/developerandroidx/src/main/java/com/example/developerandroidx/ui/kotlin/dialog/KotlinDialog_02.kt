package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/3/20 3:33 PM
 * 参考：
 * 描述：
 */
class KotlinDialog_02 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "数组"
            esv_content.addBody("在kotlin中声明并初始化一个数组的语句如下:")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    //基本数据类型数组声明\n" +
                            "    var int_array: IntArray = intArrayOf(1, 2, 3)\n" +
                            "    var long_array: LongArray = longArrayOf(1, 2, 3)\n" +
                            "    var char_array: CharArray = charArrayOf('a', 'b', 'c')\n" +
                            "    //其他类型数组声明\n" +
                            "    var string_array: Array<String> = arrayOf(\"a\", \"b\", \"c\")\n" +
                            "\n" +
                            "    //数组元素的操作\n" +
                            "    println(string_array.size)\n" +
                            "    println(string_array[0])\n" +
                            "    //kotlin可以使用重载操作符,这里+作用是扩大数组长度并增加一个元素\n" +
                            "    println((string_array + \"d\")[3])\n" +
                            "}")
        }
    }
}