package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/6/20 4:10 PM
 * 参考：
 * 描述：
 */
class KotlinDialog_05 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "等式判断"
            esv_content.addBody("在java中判断基本数据类型是否相等使用==,而在引用变量类型中使用==则为判断对象的地址是否相等." +
                    "java还提供了equals()方法,只能作用于引用数据类型,默认情况下作用与==相同,但是equals()方法可由类覆写,实现自己的需求.如在String类" +
                    "中则表示判断两个字符串的内容是否相同.")
            esv_content.addBody("kotlin则使用==判断对象数据结构是否相等,使用===判断对象引用地址是否相等(即为同一个对象).以字符串为例:")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    var str_01 = \"a\"\n" +
                            "    var str_02 = \"a\"\n" +
                            "    var str_03 = str_01\n" +
                            "    var str_04 = StringBuffer().append(\"a\").toString()\n" +
                            "\n" +
                            "\n" +
                            "    //判断内容是否相同\n" +
                            "    println(str_01 == str_02)//true\n" +
                            "    println(str_01 == str_04)//true\n" +
                            "    //判断是否为为同一个对象\n" +
                            "    //字符串特殊的地方:如果是以\"a\"的方式创建的\n" +
                            "    //字符串对象,则在内存中只会存在一个\"a\"对象.所以var str_02 = \"a\"\n" +
                            "    //等价于 var str_02 = str_01,所以引用判断地址值相等\n" +
                            "    println(str_01 === str_02)//true\n" +
                            "    println(str_01 === str_03)//true\n" +
                            "    println(str_01 === str_04)//false\n" +
                            "}")
        }
    }
}