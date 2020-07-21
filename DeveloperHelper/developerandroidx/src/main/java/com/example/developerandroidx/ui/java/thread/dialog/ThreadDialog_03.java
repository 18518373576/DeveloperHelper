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
            esv_content.addBody("所有处于就绪状态的线程根据优先级存放在可运行池中,优先级低的线程获得较少的运行机会,优先级高德线程获得较多的运行机会." +
                    "Thread类的setPriority(int)和getPriority()方法分别用来设置和读取优先级.优先级用整数表示,取值范围是1~10.");
            esv_content.addBody("值得注意的是,尽管Java提供了10个优先级,但它与多数操作系统都不能很好的进行线程优先级的映射.如果希望程序能移植到各个操作系统中,应该确保在设置" +
                    "优先级时,只使用MAX_PRIORITY,NORM_PRIORITY,MIN_PRIORITY这三个优先级,这样才能保证在不同的操作系统中,对同样优先级的线程采用同样的调度方式.");
            esv_content.addBoldBody("线程睡眠:Thread.sleep()方法");
            esv_content.addBody("当一个线程在运行中执行了sleep()方法,他就会放弃CPU.转到阻塞状态.值得注意的是,当线程结束睡眠时,首先转到就绪状态,在可运行池中等待获得CPU.");
            esv_content.addBoldBody("线程让步:Thread.yield()方法");
            esv_content.addBody("当线程执行了Thread.yield()方法时,如果此时具有相同或更高优先级的线程处于就绪状态,yield()方法将把当前运行的线程方法可运行池中," +
                    "并使另一个线程运行.如果没有相同或更高优先级的可运行线程,yield()方法什么都不做.");
            esv_content.addBoldBody("等待其他线程结束:join()");
            esv_content.addBody("当前的运行线程可以调用另一个线程的join()方法,当前线程的运行状态将转到阻塞状态,直到另一个线程运行结束,他才会恢复.");
        });
    }
}
