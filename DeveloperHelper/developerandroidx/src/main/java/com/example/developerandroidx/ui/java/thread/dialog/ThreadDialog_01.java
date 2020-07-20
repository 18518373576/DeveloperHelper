package com.example.developerandroidx.ui.java.thread.dialog;

import android.content.Context;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * 作者： zjf 7/20/20 9:16 AM
 * 参考：
 * 描述：
 */
public class ThreadDialog_01 implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, (dialog, title, esv_content) -> {
            title.setText("线程的运行机制");
            esv_content.addTitle_2("进程和线程");
            esv_content.addBody("进程是指运行中的程序,每一个进程都有自己独立的内存空间,对一个应用可以同时启动多个进程.例如IE浏览器,每打开" +
                    "一个浏览器窗口就启动了一个浏览器进程.同样,执行JDK的java.exe程序,就启动了一个独立的Java虚拟机进程.该进程的任务" +
                    "是解析并执行Java程序代码" + esv_content.tab +
                    "线程是指进程中的一个执行流程.有时也称为执行情景.一个进程可以有多个线程组成,即在一个进程中可以同时运行多个不同的线程." +
                    "它们分别执行不同的任务.当进程内的多个形成同时运行时,这种运行方式称为并发运行." + esv_content.tab +
                    "线程与进程的主要区别在于:每个进程都需要操作系统为其分配独立的内存地址空间.而同一进程中的所有线程在同一块地址空间中工作.这些线程" +
                    "可以共享同一块内存和系统资源,比如共享一个对象或一个打开的文件");
            esv_content.addTitle_2("Java线程的运行机制");
            esv_content.addBody("在Java虚拟机进程中,执行程序代码的任务是由线程来完成的.每个线程都有一个独立的程序计数器和方法调用栈.");
            esv_content.addLabelBody(esv_content.getBoldLabel("程序计数器:") + "也称为PC寄存器,当程序执行一个方法时,程序计数器指向" +
                    "方法区中下一条要执行的字节码指令.");
            esv_content.addLabelBody(esv_content.getBoldLabel("方法调用栈:") + "简称方法栈,用来跟踪线程运行中一系列的方法调用过程" +
                    ".栈中的元素称为栈帧,每当线程调用一个方法时,就会向方法栈压入一个新帧,帧用来存储方法的参数,局部变量和运算过程中的临时数据.");
            esv_content.addBoldBody("栈帧有三部分组成:");
            esv_content.addLabelBody(esv_content.getBoldLabel("局部变量区:") + "存放局部变量和方法参数.");
            esv_content.addLabelBody(esv_content.getBoldLabel("操作数栈:") + "是线程的工作区,用来存放运算过程中生成的临时数据.");
            esv_content.addLabelBody(esv_content.getBoldLabel("栈数据区:") + "为线程执行指令提供相关的信息,包括:如何定位到位于堆区和" +
                    "方法区的特定数据,如何正常退出方法或者异常中断方法.");
            esv_content.addBody("每当启动一个Java虚拟机进程时,Java虚拟机就会创建一个主线程,该线程从程序入口main()方法开始执行.");
            esv_content.addImage(R.mipmap.image_thread_code, 350);
            esv_content.addBody("主线程从main()方法程序代码开始执行,当它开始执行method()方法的a++操作时,运行时数据区的如下图:");
            esv_content.addImage(R.mipmap.image_thread_flow, 200);
        });
    }
}
