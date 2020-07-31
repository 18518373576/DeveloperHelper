package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.R
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
            title.text = "字节输出流(OutputStream)"
            esv_content.addBody("所有字节输出流都是OutputStream类的直接或间接子类.输出流的种类和输入流的种类大致对应.")
            esv_content.addImage(R.mipmap.image_output_stream, 200)
            esv_content.addTitle_2("字节数组输出流(ByteArrayOutputStream)")
            esv_content.addBody("ByteArrayOutputStream向内存中的字节数组写数据.类本身采用了适配器设计模式,把字节数组转换为输出流." +
                    "使得程序能够对字节数组进行操作.")
            esv_content.addTitle_2("文件输出流(FileOutputStream)")
            esv_content.addBody("FileOutputStream向文件写数据,具有以下构造方法:" + esv_content.tab +
                    "①FileOutputStream(File file)" + esv_content.tab +
                    "②FileOutputStream(String name)" + esv_content.tab +
                    "③FileOutputStream(String name,boolean append)")
            esv_content.addBody("在创建FileOutputStream实例时,如果相应的文件不存在,会自动创建一个空的文件.如果参数表示的文件路径存在," +
                    "但是代表一个文件目录,那么会抛出FileNotFoundException异常." + esv_content.tab +
                    "方法③append参数为true,将在文件末尾添加数据.")
            esv_content.addTitle_2("PrintStream类")
            esv_content.addBody("PrintStream和DataOutputStream一样,也能输出格式化的数据.区别在于,PrintStream采用系统默认的字符编码." +
                    "DataOutputStream采用适用于java的UTF-8编码,使用DataOutputStream的writeUTF()方法写的数据,只能用骑readUTF()方法读取,这样" +
                    "才能得到正确的结果.")
        }
    }
}