package com.example.developerandroidx.ui.java.thread.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/22/20 8:39 AM
 * 参考：
 * 描述：
 */
class ThreadDialog_06 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "线程控制"
            esv_content.addBody("当线程执行完run()方法后,它将自然终止运行.但在实际应用中,线程何时结束运行是不确定的,需要由外部程序决定." +
                    "Thread类提供了一些线程控制的方法:")
            esv_content.addBoldBody("(1)start():启动线程." + esv_content.tab +
                    "(2)suspend():使线程暂停." + esv_content.tab +
                    "(3)resume():使暂停的线程恢复运行." + esv_content.tab +
                    "(4)stop():终止线程.")
            esv_content.addBody("不过从JDK1.2开始出了start()方法,其他三个控制线程的方法都废弃了.")
            esv_content.addTitle_2("被废弃的suspend()和resume()方法")
            esv_content.addBody("Thread类有两个方法,suspend()和resume(),可以直接控制线程的暂停与恢复运行.suspend()使一个运行中线程放弃CPU," +
                    "暂停运行,而resume()方法使暂停的线程恢复运行.这样会导致以下风险:")
            esv_content.addBoldBody("(1)容易导致死锁." + esv_content.tab +
                    "(2)一个线程强制中断另一个线程的运行,会造成另一个线程操作的数据停留在逻辑不合理的状态.")
            esv_content.addBoldBody("被废弃的stop()方法")
            esv_content.addBody("Thread类的stop()方法可以强制终止一个线程.此方法有可能使操作的共享数据停留在不稳定的中间状态.")

        }
    }
}