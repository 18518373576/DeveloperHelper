package com.example.developerandroidx.ui.java.commonObj.dialog

import android.content.Context
import com.example.developerandroidx.R
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/2/20 9:35 AM
 * 参考：
 * 描述：
 */
class CommonObjDialog_01 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "正则表达式"
            esv_content.addBody("正则表达式用来描述特定的字符串模式,在正则表达式中,有些字符具有特殊的含义:")
            esv_content.addImage(R.mipmap.image_zheng_ze, 150)
            esv_content.addCode(
                    "public class RegularExpression {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        //判断一个18号段的手机号\n" +
                            "        Pattern pattern = Pattern.compile(\"18[35679]\\\\d{8}\");\n" +
                            "        Matcher m1 = pattern.matcher(\"18512345678\");\n" +
                            "        Matcher m2 = pattern.matcher(\"1851234567\");\n" +
                            "        Matcher m3 = pattern.matcher(\"18112345678\");\n" +
                            "        System.out.println(m1.matches());\n" +
                            "        System.out.println(m2.matches());\n" +
                            "        System.out.println(m3.matches());\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:\n" +
                            "true\n" +
                            "false\n" +
                            "false")
        }
    }
}