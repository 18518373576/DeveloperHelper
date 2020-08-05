package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/5/20 9:37 AM
 * 参考：
 * 描述：
 */
class KotlinDialog_03 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "流程控制"
            esv_content.addTitle_2("if...else...")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    var i = 0\n" +
                            "    var j = 1\n\n" +
                            "    //if...else...\n" +
                            "    if (i == j) {\n" +
                            "        print(\"相等\")\n" +
                            "    } else {\n" +
                            "        print(\"不相等\")\n" +
                            "    }\n" +
                            "\n" +
                            "    //可以用以下写法替换jav中的?:\n" +
                            "    var str: String = if (i == j) \"相等\" else \"不相等\"\n" +
                            "}")
            esv_content.addTitle_2("when...else...")
            esv_content.addBody("kotlin中取消了switch...case...多路分支语句,取而代之的是when...else...," +
                    "else相当于java中的default,同样when...else...语句也可以返回字符串,换行自动执行break.")
            esv_content.addCode(
                    "var str_01 = when (i) {\n" +
                            "    0, 2 -> \"值为0或2\"\n" +
                            "    1 -> \"值为1\"\n" +
                            "    in 10..20 -> \"在10-20的范围内\"\n" +
                            "    is Int -> \"是一个整形数字\"\n" +
                            "    else -> \"以上条件都不满足\"\n" +
                            "}")
            esv_content.addTitle_2("循环")
            esv_content.addCode(
                    "    //循环\n" +
                            "    var list = mutableListOf<String>(\"a\", \"b\", \"c\", \"d\")\n" +
                            "\n" +
                            "    //获取元素\n" +
                            "    for (item in list) {\n" +
                            "        print(item)\n" +
                            "    }\n" +
                            "\n" +
                            "    //获取下标\n" +
                            "    for (i in list.indices) {\n" +
                            "        print(list[i])\n" +
                            "    }\n" +
                            "\n" +
                            "    //左闭右开区间,包含10不包含20\n" +
                            "    for (i in 10 until 20) {\n" +
                            "        print(i)\n" +
                            "    }\n" +
                            "\n" +
                            "    //包含10和20,每次递增2\n" +
                            "    for (i in 10..20 step 2) {\n" +
                            "        print(i)\n" +
                            "    }\n" +
                            "\n" +
                            "    //递减\n" +
                            "    for (i in 20 downTo 10){\n" +
                            "        print(i)\n" +
                            "    }")
            esv_content.addBody("kotlin中while语句与java中英法相同.continue表示忽略本次继续执行循环,break表示跳出循环.")
        }
    }
}