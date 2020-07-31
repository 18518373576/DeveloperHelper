package com.example.developerandroidx.ui.java.io.dialog

import android.content.Context
import com.example.developerandroidx.R
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/27/20 4:49 PM
 * 参考：
 * 描述：
 */
class IoDialog_02 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "字节输入流"
            esv_content.addBody("所有字节输入流都是InputStream的直接或间接子类.输入流的层次结构如下图:")
            esv_content.addImage(R.mipmap.image_input_stream, 300)
            esv_content.addTitle_2("字节数组输入流(ByteArrayInputStream)")
            esv_content.addBody("ByteArrayInputStream从内存中的字节数组中读取数据,因此它的数据源是一个字节数组.这个类的构造方法包括:")
            esv_content.addBoldBody("①ByteArrayInputStream(byte[] buf):参数buf指定字节数组类型的数据源." + esv_content.tab +
                    "②ByteArrayInputStream(byte[] buf, int offset, int length):参数buf指定字节数组类型的数据源,参数offset指定从数组中开始读数据" +
                    "的起始下标位置,length指定从数组中读取的字节数.")
            esv_content.goTo("查看示例", CodeViewActivity::class.java,
                    "public class ByteArrayInputStreamTest {\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        byte[] buff = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};\n" +
                            "        ByteArrayInputStream in = new ByteArrayInputStream(buff, 1, 4);\n" +
                            "        int data = in.read();\n" +
                            "        while (data != -1) {\n" +
                            "            System.out.print(data + \",\");\n" +
                            "            data = in.read();\n" +
                            "        }\n" +
                            "        in.close();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:2,3,4,5,")
            esv_content.addTitle_2("文件输入流(FileInputStream)")
            esv_content.addBody("FileInputStream类从文件中读取数据,它有以下构造方法:")
            esv_content.addBoldBody("①FileInputStream(File file):参数file指定文件数据源." + esv_content.tab +
                    "②FileInputStream(String name):参数name指定文件数据源.在参数name中包含文件路径信息.")
            esv_content.goTo("查看示例", CodeViewActivity::class.java,
                    "public class FileInputStreamTest {\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        //文本内容:测试ABCD\n" +
                            "        FileInputStream in = new FileInputStream(\"/Users/zhang/Documents/doc/test.txt\");\n" +
                            "        int data;\n" +
                            "        while ((data = in.read()) != -1) {\n" +
                            "            System.out.print(data + \",\");\n" +
                            "        }\n" +
                            "        in.close();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:230,181,139,232,175,149,65,66,67,68,")
            esv_content.addBody("utf-8编码汉字占3个字节.如果文件很大,为了提高效率可以使用read(byte[] buff)方法.")
            esv_content.addTitle_2("管道输入流:PipedInputStream")
            esv_content.addBody("管道输入流从一个管道输出流中读取数据.通常由一个线程向管道输出流写数据,由另一个线程从管道输入流中读数据." +
                    "两个线程可以用管道来通信.当线程A执行管道输入流的read方法时,如果暂时还没有数据,那么这个线程就会阻塞.只有当线程B向管道输出流写了新的" +
                    "数据时,线程A才会恢复.")
            esv_content.goTo("查看示例", CodeViewActivity::class.java,
                    "public class PipeInputStreamTest {\n" +
                            "    //管道输出流线程\n" +
                            "    private static class Sender extends Thread {\n" +
                            "        private PipedOutputStream out = new PipedOutputStream();\n" +
                            "        public PipedOutputStream getOutputStream() {\n" +
                            "            return out;\n" +
                            "        }\n" +
                            "        @Override\n" +
                            "        public void run() {\n" +
                            "            try {\n" +
                            "                for (char c : new Character[]{'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'}) {\n" +
                            "                    out.write(c);\n" +
                            "                }\n" +
                            "                out.close();\n" +
                            "            } catch (IOException e) {\n" +
                            "                e.printStackTrace();\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "    //管道输入流线程\n" +
                            "    private static class Receiver extends Thread {\n" +
                            "        private PipedInputStream in;\n" +
                            "        public Receiver(Sender sender) throws IOException {\n" +
                            "            in = new PipedInputStream(sender.getOutputStream());\n" +
                            "        }\n" +
                            "        @Override\n" +
                            "        public void run() {\n" +
                            "            try {\n" +
                            "                int data;\n" +
                            "                while ((data = in.read()) != -1) {\n" +
                            "                    System.out.print((char) data);\n" +
                            "                }\n" +
                            "                in.close();\n" +
                            "            } catch (IOException e) {\n" +
                            "                e.printStackTrace();\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "    //主函数\n" +
                            "    public static void main(String[] args) throws IOException {\n" +
                            "        Sender sender = new Sender();\n" +
                            "        Receiver receiver = new Receiver(sender);\n" +
                            "        sender.start();\n" +
                            "        receiver.start();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:hello world")
            esv_content.addTitle_2("顺序输入流(SequenceInputStream)")
            esv_content.addBody("SequenceInputStream类可以将几个输入流串联在一起,合并为一个输入流.当通过这个类读取数据时,它会一次从串联的" +
                    "输入流中读取数据.对程序来说就好像是对同一个流操作.SequenceInputStream类的构造方法为:")
            esv_content.addBoldBody("①SequenceInputStream(Enumeration e):在枚举参数类型e中包含若干需要被串联的输入流." + esv_content.tab +
                    "②SequenceInputStream(InputStream s1,InputStream s2):参数s1和s2代表两个需要被串联的输入流.顺序输入流先读取s1中的数据,再读取" +
                    "s2中的数据.")
            esv_content.addTitle_2("过滤输入流(FilterInputStream)")
            esv_content.addBody("FilterInputStream类是一种装饰器,它本身继承了InputStream类," +
                    "用来装饰其他的输入流类.FilterInputStream的构造方法是protected的,因此外部程序不能创建它的实例.FilterInputStream类和它的子类" +
                    "的构造方法都有一个InputStream类型的参数,该参数指定需要被修饰的输入流.")
            esv_content.addBoldBody("DataInputStream类")
            esv_content.addBody("DataInputStream实现了DataInput接口,用于读取基本数据类型.与DataOutputStream搭配使用,可以按照与平台无关的方式," +
                    "从流中读取基本数据类型.")
            esv_content.goTo("查看示例", CodeViewActivity::class.java,
                    "public class FilterInputStreamTest {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        try (\n" +
                            "                //定义输出流\n" +
                            "                OutputStream out = new FileOutputStream(\"/Users/zhang/Documents/doc/test.txt\");\n" +
                            "                //装饰输出流\n" +
                            "                DataOutputStream dataOutputStream = new DataOutputStream(out);\n" +
                            "                //定义输入流\n" +
                            "                InputStream in = new FileInputStream(\"/Users/zhang/Documents/doc/test.txt\");\n" +
                            "                //装饰输入流\n" +
                            "                DataInputStream dataInputStream = new DataInputStream(in)) {\n" +
                            "            //使用装饰类写一个字符串\n" +
                            "            dataOutputStream.writeUTF(\"你好,Java!\");\n" +
                            "            //注意:输出流和输入流,一一对应,写一次就需要读一次\n" +
                            "            dataOutputStream.writeUTF(\"你好,Java!\");\n" +
                            "            //读取输入流内容并打印\n" +
                            "            System.out.print(dataInputStream.readUTF());\n" +
                            "        } catch (IOException e) {\n" +
                            "            e.printStackTrace();\n" +
                            "        }\n" +
                            "    }\n" +
                            "}\n" +
                            "输入结果:你好,Java!")
            esv_content.addBoldBody("BufferedInputStream类")
            esv_content.addBody("BufferedInputStream类覆盖了被装饰的输入流的读数据行为,利用缓冲区来提高读数据的效率,BufferedInputStream" +
                    "类先把一批数据读入到缓冲区,接下来read方法只从缓冲区获取数据,这样能减少物理读取数据的次数.")
            esv_content.addBoldBody("PushbackInputStream类")
            esv_content.addBody("PushbackInputStream类有一个后推缓冲区.用于存放已经读入的当前字节.在需要根据当前读入的字节来判断该对下一个" +
                    "字节做什么操作时,这个类非常有用.比如编译器的语法分析器就需要这个类来扫描源文件.在程序中一般很少使用这个类.")
        }
    }
}