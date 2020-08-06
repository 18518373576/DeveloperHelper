package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.R
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/30/20 12:41 PM
 * 参考：
 * 描述：
 */
class IoDialog_04 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "字符输入/输出流"
            esv_content.addBody("java.io包提供了Reader/Writer类,它们分别表示字符输入流和字符输出流." + esv_content.tab +
                    "在处理字符流时,最主要的问题是进行字符编码的转换.JAVA语言采用Unicode字符编码,对于每一个字符,Java虚拟机会为其分配两个字节的内存." + esv_content.tab +
                    "Reader类能够将输入流中采用其他编码类型的字符转换为Unicode字符.Writer类能够把Unicode字符转换为其他编码类型的字符.再写到输出流中.")
            esv_content.addTitle_2("Reader类")
            esv_content.addImage(R.mipmap.image_reader, 250)
            esv_content.addBoldBody("CharArrayReader")
            esv_content.addBody("适配器,从内存中的字符数组中读取字符,它的数据源是一个字符数组.")
            esv_content.addCode(
                    "public class CharArrayReaderTest {\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        char[] buff = new char[]{'你', '好', ',', 'w', 'o', 'r', 'l', 'd', '!'};\n" +
                            "        CharArrayReader reader = new CharArrayReader(buff);\n" +
                            "        int data;\n" +
                            "        //read()方法每次读一个字符,读到结尾返回-1\n" +
                            "        while ((data = reader.read()) != -1) {\n" +
                            "            System.out.print((char) data);\n" +
                            "        }\n" +
                            "        reader.close();\n" +
                            "    }\n" +
                            "}\n" +
                            "打印结果:你好,world!")
            esv_content.addBoldBody("BufferedReader")
            esv_content.addBody("装饰器,为其他Reader提供缓冲区,此外,它的readeLine()方法能够读入一行字符串.")
            esv_content.addBoldBody("LineNumberReader")
            esv_content.addBody("装饰器,为其他Reader提供缓冲区,并且可以跟踪字符输入流的行号.")
            esv_content.addBoldBody("StringReader")
            esv_content.addBody("适配器,把字符串转换为Reader,从字符串读取字符.")
            esv_content.addCode(
                    "public class StringReaderTest {\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        StringReader reader = new StringReader(\"你好,world!\");\n" +
                            "        int data;\n" +
                            "        //read()方法每次从流中读取一个字符,结尾返回-1\n" +
                            "        while ((data = reader.read()) != -1) {\n" +
                            "            System.out.print((char) data);\n" +
                            "        }\n" +
                            "        reader.close();\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("PipedReader")
            esv_content.addBody("管道流,连接一个PipedWriter.")
            esv_content.addBoldBody("FilterReader")
            esv_content.addBody("装饰器,扩展其他Reader功能.")
            esv_content.addBoldBody("PushBackReader")
            esv_content.addBody("装饰器,能够把读到的字符压回到缓冲区中.")
            esv_content.addBoldBody("InputStreamReader")
            esv_content.addBody("适配器,把InputStream转换为Reader,可以指定数据源的字符编码.")
            esv_content.addCode(
                    "public class InputStreamReaderTest {\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        //test.txt采用UTF-8编码\n" +
                            "        InputStreamReader reader = new InputStreamReader(\n" +
                            "                new FileInputStream(\"/Users/zhang/Documents/doc/test.txt\"), \"UTF-8\");\n" +
                            "        int data;\n" +
                            "        while ((data = reader.read()) != -1) {\n" +
                            "            System.out.print((char) data);\n" +
                            "        }\n" +
                            "        reader.close();\n" +
                            "    }\n" +
                            "}\n" +
                            "打印结果:你好,Java!你好,Java!")
            esv_content.addBoldBody("FileReader")
            esv_content.addBody("FileReader用于从文件中读取字符数据,该类只能按照本地平台的字符编码进行读取,用户你能指定其他类型的字符编码.")
            esv_content.addTitle_2("Writer类")
            esv_content.addImage(R.mipmap.image_writer, 280)
        }
    }
}