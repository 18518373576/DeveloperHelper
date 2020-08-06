package com.example.developerandroidx.ui.java.thread.dialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * 作者： zjf 7/20/20 11:30 AM
 * 参考：
 * 描述：
 */
public class ThreadDialog_02 implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, (dialog, title, esv_content) -> {
            title.setText("线程的状态转换");
            esv_content.addTitle_2("新建状态");
            esv_content.addBody("用new语句创建的线程对象处于新建状态(New),此时它和其他的Java程序一样,仅仅在堆区中被分配了内存.");
            esv_content.addTitle_2("就绪状态");
            esv_content.addBody("当一个线程被创建后,其他线程调用它的start()方法,该线程就进入就绪状态(Runnable),Java虚拟机" +
                    "会为它创建方法调用栈和程序计数器.处于这个状态的线程位于可运行池中,等待CPU使用权.");
            esv_content.addTitle_2("运行状态");
            esv_content.addBody("处于运行状态(Running)的线程占用CPU,执行程序代码,在并发运行环境中,如果计算机只有一个CPU,那么任何时刻只会有一个线程" +
                    "处于运行状态.如果计算机有多个CPU,那么同一时刻可以让几个线程占用不同的CPU,使它们都处于运行状态.只有处于就绪状态的线程才有机会" +
                    "转到运行状态.");
            esv_content.addTitle_2("阻塞状态");
            esv_content.addBody("阻塞状态(Blocked)是指线程因为某些原因放弃CPU,暂时停止运行.当线程处于阻塞状态时,Java虚拟机不会给线程分配CPU," +
                    "直到线程重新进入就绪状态,它才有机会转到运行状态.");
            esv_content.addBoldBody("阻塞状态可分为三种:");
            esv_content.addBody("(1)位于对象等待池中的阻塞状态(Blocked in Object's wait pool):当线程处于运行状态时,如果执行了某个对象的wait方法," +
                    "Java虚拟机就会把线程方法这个对象的等待池中." + esv_content.tab +
                    "(2)位于对象锁池中的阻塞状态(Blocked in object's lock pool):当线程处于运行状态,试图获得某个对象的同步锁时,如果该对象的同步锁已经被其他" +
                    "对象占用,Java虚拟机就会把该线程放到这个对象的锁池中." + esv_content.tab +
                    "(3)其他阻塞状态(Otherwise Blocked):当前线程执行了sleep()方法,或者调用了其他线程的join()方法,或者发出了I/O请求时就会进入这个状态.");
            esv_content.addTitle_2("死亡状态");
            esv_content.addBody("当线程退出run()方法以后,就进入死亡状态(Dead),该线程结束生命周期.程序有可能使正常执行完run()方法而退出的." +
                    "也有可能时遇到异常退出的.不管线程是正常结束还是异常结束,都不会对其他线程造成影响.");
            esv_content.addLabelBody("Thread类的" + esv_content.getBoldLabel("isAlive()") + "方法用于判断一个线程是否还活着.当线程" +
                    "处于就绪状态和运行状态时,该方法返回true.");
        });
    }
}
