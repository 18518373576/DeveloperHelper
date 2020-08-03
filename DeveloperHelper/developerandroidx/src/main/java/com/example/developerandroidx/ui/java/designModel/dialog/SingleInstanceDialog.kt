package com.example.developerandroidx.ui.java.designModel.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/3/20 8:46 AM
 * 参考：
 * 描述：单例模式
 */
class SingleInstanceDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "单例模式"
            esv_content.addBody("单例模式的实现由单个类组成,为确保单例实例的唯一性,所有的单例构造方法都要声明为私有的,再通过声明静态方法" +
                    "实现全局访问获得该单例实例.")
            esv_content.addBoldBody("简单单例实现")
            esv_content.addCode(
                    "public class Singleton_01 {\n" +
                            "    private static Singleton_01 instance;\n" +
                            "\n" +
                            "    //私有化构造方法\n" +
                            "    private Singleton_01() {\n" +
                            "    }\n" +
                            "\n" +
                            "    //提供静态方法获取实例\n" +
                            "    public static Singleton_01 getInstance() {\n" +
                            "        if (instance == null) {\n" +
                            "            instance = new Singleton_01();\n" +
                            "        }\n" +
                            "        return instance;\n" +
                            "    }\n" +
                            "\n" +
                            "    public void doSomething() {\n" +
                            "        System.out.println(getClass().getSimpleName() + \":简单单例\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBody("注意:在多线程中使用这种模式,如果实例为空,可能存在两个线程同时调用getInstance()方法.从而破坏单例实例的唯一性." +
                    "要解决这个问题,我们需要创建同步代码块来避免实例被重复创建.")
            esv_content.addBoldBody("同步锁单例")
            esv_content.addCode(
                    "public class Singleton_02 {\n" +
                            "    private static Singleton_02 instance;\n" +
                            "\n" +
                            "    private Singleton_02() {\n" +
                            "    }\n" +
                            "\n" +
                            "    //添加同步锁\n" +
                            "    public static synchronized Singleton_02 getInstance() {\n" +
                            "        if (instance == null) {\n" +
                            "            instance = new Singleton_02();\n" +
                            "        }\n" +
                            "        return instance;\n" +
                            "    }\n" +
                            "\n" +
                            "    public void doSomething() {\n" +
                            "        System.out.println(getClass().getSimpleName() + \":带有同步锁的单例\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBody("上述模式实现了线程安全,但带来了延迟,其他线程只有等待持有同步锁的线程释放掉锁,才能依次进入同步代码块执行操作.所以对上述模式" +
                    "加以改进,只有在对象为null的时候才会执行同步代码块.")
            esv_content.addBoldBody("双重校验的同步锁单例")
            esv_content.addCode(
                    "public class Singleton_03 {\n" +
                            "    private static Singleton_03 instance;\n" +
                            "\n" +
                            "    private Singleton_03() {\n" +
                            "    }\n" +
                            "\n" +
                            "    public static Singleton_03 getInstance() {\n" +
                            "        if (instance == null) {\n" +
                            "            synchronized (Singleton_03.class) {\n" +
                            "                if (instance == null) {\n" +
                            "                    instance = new Singleton_03();\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return instance;\n" +
                            "    }\n" +
                            "\n" +
                            "    public void doSomething() {\n" +
                            "        System.out.println(getClass().getSimpleName() + \":带有双重校验的同步锁单例\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBody("还可以通过在声明时直接实例化静态成员方法的方式实现无锁的线程安全的单例.")
            esv_content.addBoldBody("无锁的线程安全单例")
            esv_content.addCode(
                    "public class Singleton_04 {\n" +
                            "    //静态成员变量只有一个实例保证线程安全\n" +
                            "    private static final Singleton_04 instance = new Singleton_04();\n" +
                            "\n" +
                            "    private Singleton_04() {\n" +
                            "    }\n" +
                            "\n" +
                            "    public static Singleton_04 getInstance() {\n" +
                            "        return instance;\n" +
                            "    }\n" +
                            "\n" +
                            "    public void doSomething() {\n" +
                            "        System.out.println(getClass().getSimpleName() + \":无锁的线程安全单例\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("使用枚举实现单例")
            esv_content.addCode(
                    "public enum Singleton_05 {\n" +
                            "    INSTANCE();\n" +
                            "\n" +
                            "    //枚举构造方法,执行初始化操作\n" +
                            "    Singleton_05() {\n" +
                            "\n" +
                            "    }\n" +
                            "\n" +
                            "    public void doSomething() {\n" +
                            "        System.out.println(getClass().getSimpleName() + \":枚举实现线程安全单例\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("测试一下")
            esv_content.addCode(
                    "public class Test {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Singleton_01.getInstance().doSomething();\n" +
                            "        Singleton_02.getInstance().doSomething();\n" +
                            "        Singleton_03.getInstance().doSomething();\n" +
                            "        Singleton_04.getInstance().doSomething();\n" +
                            "        Singleton_05.INSTANCE.doSomething();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:\n" +
                            "Singleton_01:简单单例\n" +
                            "Singleton_02:带有同步锁的单例\n" +
                            "Singleton_03:带有双重校验的同步锁单例\n" +
                            "Singleton_04:无锁的线程安全单例\n" +
                            "Singleton_05:枚举实现线程安全单例")
        }
    }
}