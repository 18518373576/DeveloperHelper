package com.example.developerandroidx.ui.java.thread.dialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * 作者： zjf 7/20/20 6:54 PM
 * 参考：
 * 描述：
 */
public class ThreadDialog_03 implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
//        while (true) {
//
//        }
        DialogUtils.getInstance().showEsvDialog(context, (dialog, title, esv_content) -> {
            title.setText("线程调度");
            esv_content.addBody("计算机通常只有一个CPU,在任意时刻只能执行一条机器指令,每个线程只有获得CPU的使用权才能执行指令.所谓多线程的并发运行," +
                    "其实是指从宏观上看,各个线程轮流获得CPU的使用权,分别执行各自的任务.在可运行池中,会有多个处于就绪状态的线程等待CPU.Java虚拟机的一项" +
                    "任务就是负责线程的调度.线程的调度是指按照特定的机制为多个线程分配CPU的使用权,有两种调度模型:分时调度模型和抢占式调度模型.");
            esv_content.addBody("分时调度模型是指让所有线程轮流获得CPU的使用权,并且平均分配每个线程占用CPU的时间片." + esv_content.tab +
                    "Java虚拟机使用的是抢占式调度模型,它是指优先让可运行池中优先级高的线程占用CPU,如果可运行池中线程的优先级相同,那么就随机的" +
                    "选择一个线程,使其占用CPU.处于运行状态的线程会一直运行,直至他不得不放弃CPU.一个线程会因为一下原因而放弃CPU:");
            esv_content.addBody("(1)Java虚拟机让当前线程暂时放弃CPU,转到就绪状态,使其他线程获得运行机会.");
            esv_content.addBody("(2)线程因为某些原因而进入阻塞状态." + esv_content.tab +
                    "(3)线程结束运行.");
            esv_content.addBoldBody("值得注意的是,线程的调度不是跨平台的,它不仅仅取决于Java虚拟机,还依赖于操作系统.在某些操作系统中,只要运行中的" +
                    "线程没有阻塞,就不会放弃CPU.在某些操作系统中,即使运行中的线程没有被阻塞,也会在运行一段时间后放弃CPU,给其他线程运行的机会.");
            esv_content.addBody("Java线程调度不是分时的,同时启动多个线程后,不能保证各个线程轮流获得均等的CPU使用时间.");
            esv_content.addBody("如果希望明确地让一个线程给另外一个线程运行的机会,可以采取以下方法之一:");
            esv_content.addBody("(1)调整各个线程的优先级." + esv_content.tab +
                    "(2)让处于运行状态的线程执行Thread.sleep()方法." + esv_content.tab +
                    "(3)让处于运行状态的线程执行Thread.yield()方法." + esv_content.tab +
                    "(3)让处于运行状态的线程调用另一个线程的join()方法.");
            esv_content.addTitle_2("调整各个线程的优先级");
        });
    }
}
