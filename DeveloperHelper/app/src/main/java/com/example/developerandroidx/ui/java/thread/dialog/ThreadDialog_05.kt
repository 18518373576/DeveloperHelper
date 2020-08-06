package com.example.developerandroidx.ui.java.thread.dialog

import android.content.Context
import com.example.developerandroidx.R
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/21/20 1:40 PM
 * 参考：
 * 描述：
 */
class ThreadDialog_05 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "线程的通信"
            esv_content.addBody("不同的线程执行不同的任务,如果这些任务有某种联系,线程之间必须能够通信,协调完成工作.")
            esv_content.addBody("java.lang.Object类提供了两个用于线程通信的方法:" + esv_content.tab +
                    "(1)wait():执行该方法的线程释放对象的锁,Java虚拟机把该线程放到该对象的等待池中,该线程等待其他线程将它唤醒." + esv_content.tab +
                    "(2)notify():执行该方法的线程唤醒在对象等待池中等待的一个线程.Java虚拟机从对象的等待池中随机地选择一个线程,把它转到对象的锁池中.")
            esv_content.addBoldBody("假定线程1和线程2共同操作一个对象s,这两个线程可以通过对象s的wait()和notify()方法来进行通信,流程如下:")
            esv_content.addBoldBody("(1)当线程1执行对象s的一个同步代码块时,线程1持有对象的锁,线程2在对象s的锁池中等待." + esv_content.tab +
                    "(2)线程1在同步代码块中执行s.wait()方法,线程1释放对象s的锁,进入对象s的等待池." + esv_content.tab +
                    "(3)在对象的锁池中等待的线程2获得了对象s的锁,执行对象的另一个同步代码块." + esv_content.tab +
                    "(4)线程2在同步代码块中执行s.notify(),Java虚拟机把线程1从对象的等待池中移到对象的锁池中,在那里等待获得锁." + esv_content.tab +
                    "(5)线程2执行完同步代码块后,释放锁,线程1获得锁,继续执行同步代码块.")
            esv_content.addImage(R.mipmap.image_thread_communication, 200)
            esv_content.addBody("当一个线程执行了s.notify()方法后,如果对象s的等待池中有多个线程,那么Java虚拟机随机抽取一个线程,把它" +
                    "放到对象s的锁池中.如果对象s的等待池中没有线程,那么notify()方法什么也不做.")
            esv_content.addBody("Object类还有一个notifyAll()方法,该方法会把等待池中所有的线程都转到对象的锁池中.")
        }
    }
}