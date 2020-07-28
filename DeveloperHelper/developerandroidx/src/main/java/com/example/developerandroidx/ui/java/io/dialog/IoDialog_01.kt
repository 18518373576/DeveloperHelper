package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/27/20 4:00 PM
 * 参考：
 * 描述：
 */
class IoDialog_01 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "输入与输出(I/O)"
            esv_content.addBody("程序的主要任务是操作数据.在运行时这些数据必须位于内存中,并且属于特定的类型,程序才能操作它们." + esv_content.tab +
                    "在Java中,把一组有序的数据序列称为流.根据操作的类型,可以把流分为输入流和输出流两种.程序从输入流读取数向输出流写入数据." + esv_content.tab +
                    "Java I/O类库负责处理程序的输入和输出.它对各种常见的输入输出流进行了抽象.")
            esv_content.addBoldBody("如果数据流中最小的数据单元是字节,称为字节流;如果数据流中最小的数据单元是字符,称为字符流.在" +
                    "I/O类库中,java.io.InputStream和java.io.OutputStream分别表示字节输入流和字节输出流.java.io.Reader和java.io.Writer" +
                    "分别表示字符输入流和字符输出流.")
            esv_content.addBody("从JDK1.4版本开始引入了新I/O类库.它位于java.nio包中.新I/O类库利用通道很缓冲区等来提高I/O操作的效率.")
            esv_content.addTitle_2("输入流和输出流概述")
            esv_content.addBody("InputStream和OutputStream都是抽象类,不能被实例化.")
            esv_content.addBoldBody("InputStream提供了一系列和读取数据有关的方法:")
            esv_content.addBody("(1)int read():" + esv_content.tab +
                    "从输入流读取数据.它有三种重载形式:" + esv_content.tab +
                    "①int read():从输入流读取一个8位的字节,把它转换为0-255的整数,返回这一整数.结尾返回-1" + esv_content.tab +
                    "②int read(byte[] b):从输入流读取若干字节,把它们保存到参数b指定的字节数组中.返回的整数表示读取的字节数,结尾返回-1." + esv_content.tab +
                    "③int read(byte[] b , int off , int len):从输入流读取若干字节,把它们保存到参数b指定的数组中,参数off指定在字节数组中开始保存数据的" +
                    "起始下标,len指定读取的字节数目.")
            esv_content.addBody("(2)void close():" + esv_content.tab +
                    "关闭输入流.")
            esv_content.addBody("(3)int available():" + esv_content.tab +
                    "返回可以从字节输入流中读取的字节数目.")
            esv_content.addBoldBody("OutputStream类提供了一系列和写有关的方法:")
            esv_content.addBody("(1)void write(int b):" + esv_content.tab +
                    "向输出流写入数据,有三种重载形式,与输入流类似.")
            esv_content.addBody("(2)void close():" + esv_content.tab +
                    "关闭输出流.")
            esv_content.addBody("(3)void flush():" + esv_content.tab +
                    "flush()方法强制把缓冲区内的数据写到输出流中.")
        }
    }
}