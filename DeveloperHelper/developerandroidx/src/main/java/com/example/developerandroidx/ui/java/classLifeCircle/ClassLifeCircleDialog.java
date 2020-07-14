package com.example.developerandroidx.ui.java.classLifeCircle;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/13 12:00 PM
 * 参考：
 * 描述：
 */
public class ClassLifeCircleDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("类的生命周期");
                esv_content.addTitle_2("Java虚拟机及程序的生命周期");
                esv_content.addBody("当通过Java命令运行一个Java程序时,就启动了一个Java虚拟机进程.Java虚拟机进程从启动到终止的" +
                        "过程,称为Java虚拟机的生命周期.在以下情况下Java虚拟机将结束生命周期:");
                esv_content.addBody("1.程序正常执行结束.");
                esv_content.addBody("2.程序在执行过程中因为异常或错误而异常终止.");
                esv_content.addBody("3.执行了System.exit()方法.");
                esv_content.addBody("4.由于操作系统出现错误而导致Java虚拟机终止.");
                esv_content.addBody("当Java虚拟机处于生命周期中时,它的总任务就是运行Java程序.Java程序从开始运行到终止的过程称为程序的生命周期." +
                        "它和Java虚拟机的生命周期是一致的.");
                esv_content.addTitle_2("类的加载,连接和初始化");
                esv_content.addBody("当Java程序需要使用某个类时,Java虚拟机会确保这个类已经被加载,连接和初始化.其中连接过程包括验证,准备和解析" +
                        "三个步骤.");
                esv_content.addImage(R.mipmap.image_class_loader, 180);
                esv_content.addBoldBody("1.类的加载");
                esv_content.addBody("类的加载是指把类的.class文件中的二进制数据读入到内存中,把它存放在运行时数据区的方法区内," +
                        "然后在堆区创建一个java.lang.Class对象,用来封装类在方法区内的数据结构.");
                esv_content.addBody("类的加载的最终产品是位于运行时数据区的堆区的Class对象,Class对象封装了类在方法区中的数据结构," +
                        "并且向Java程序提供了访问类在方法区内的数据结构的接口.");
                esv_content.addImage(R.mipmap.image_class_loader_2, 120);
                esv_content.addBoldBody("2.类的验证");
                esv_content.addBody("当类被加载后,就进入连接阶段.连接就是把已经读入到内存的类的二进制数据合并到虚拟机的运行时环境中去." +
                        "连接的第一步是类的验证,保证被加载的类有正确的内部结构,并且与其他类协调一致.如果Java虚拟机检测到错误,就会抛出相应的Error对象.");
                esv_content.addBody("类的验证主要包括以下内容分:");
                esv_content.addBody("1.类文件的结构检查." + esv_content.tab +
                        "2.语义检查" + esv_content.tab +
                        "3.字节码验证" + esv_content.tab +
                        "4.二进制兼容验证");
                esv_content.addBoldBody("3.类的准备");
                esv_content.addBody("在准备阶段,Java虚拟机为类的静态变量分配内存,并设置默认的初始值.");
                esv_content.addBoldBody("4.类的解析");
                esv_content.addBody("在解析阶段,Java虚拟机会把类的二进制数据中的符号引用替换为直接引用.");
                esv_content.addBoldBody("5.类的初始化");
                esv_content.addBody("Java虚拟机初始化一个类包含以下步骤:");
                esv_content.addBody("(1)如果这个类还没有没加载和连接,那就先进行加载和连接." + esv_content.tab +
                        "(2)如果类存在直接的父类,并且这个父类还没被初始化,那就先初始化直接的父类." + esv_content.tab +
                        "(3)如果类中存在初始化语句,那么就依次执行这些初始化语句.");
                esv_content.addTitle_2("类的初始化的时机");
                esv_content.addLabelBody(Html.fromHtml("Java虚拟机只有在程序首次" +
                        esv_content.getBoldLabel("主动") + "使用一个类或接口时才会初始化它." +
                        "只有6种活动被看作是程序对类或接口的主动使用:"));
                esv_content.addBody("(1)创建类的实例." + esv_content.tab +
                        "(2)调用类的静态方法." + esv_content.tab +
                        "(3)访问某个类或接口的静态变量,或者对该静态变量赋值." + esv_content.tab +
                        "(4)调用Java API中的某些反射方法,比如调用Class.forName(\"...\"),如果类没有初始化,forName方法就会初始化该类." + esv_content.tab +
                        "(5)初始化一个类的子类." + esv_content.tab +
                        "(6)Java虚拟机启动时被标明为启动类的类.");
                esv_content.addTitle_2("类的加载器ClassLoader");
                esv_content.addBody("类加载器负责把类加载到Java虚拟机中,类的加载过程采用父亲委托机制,这种机制能更好的的保证Java平台的安全." +
                        esv_content.tab + "Java虚拟机自带以下几种加载器:");
                esv_content.addTitle_2("类的卸载");
            }
        });
    }
}
