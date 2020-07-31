package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/31/20 9:36 AM
 * 参考：
 * 描述：
 */
class IoDialog_05 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "对象序列化"
            esv_content.addBody("对象的序列化是指把对象写到一个输出流中,对象的反序列化,是指从一个输入流中读取一个对象.Java语言要求,只有实现了" +
                    "java.io.Serializable接口的类才能被序列化.")
            esv_content.goTo("查看示例代码", CodeViewActivity::class.java,
                    "public class ObjectStreamTest {\n" +
                            "    public static void main(String[] args) throws IOException, ClassNotFoundException {\n" +
                            "        //对象序列化\n" +
                            "        ObjectOutputStream outputStream = new ObjectOutputStream(\n" +
                            "                new FileOutputStream(\"/Users/zhang/Documents/doc/object.obj\"));\n" +
                            "        outputStream.writeObject(\"hello\");\n" +
                            "        outputStream.writeObject(new Date());\n" +
                            "        outputStream.close();\n" +
                            "        //对象反序列化\n" +
                            "        ObjectInputStream inputStream = new ObjectInputStream(\n" +
                            "                new FileInputStream(\"/Users/zhang/Documents/doc/object.obj\"));\n" +
                            "        String str = (String) inputStream.readObject();\n" +
                            "        Date date = (Date) inputStream.readObject();\n" +
                            "        System.out.println(str);\n" +
                            "        System.out.println(date.getTime());\n" +
                            "        inputStream.close();\n" +
                            "    }\n" +
                            "}\n" +
                            "打印结果:\n" +
                            "hello\n" +
                            "1596160242060")
        }
    }
}