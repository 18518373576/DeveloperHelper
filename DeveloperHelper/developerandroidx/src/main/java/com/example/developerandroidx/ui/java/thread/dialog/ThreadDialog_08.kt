package com.example.developerandroidx.ui.java.thread.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/22/20 1:19 PM
 * 参考：
 * 描述：
 */
class ThreadDialog_08 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->

            title.text = "线程池"
            esv_content.addBody("在多线程环境中,不断的创建和销毁线程,既费时又消耗系统资源.为了提高程序的性能,java.util.concurrent并发包提供了" +
                    "用线程池来管理多线程的机制,它的原理是仅仅创建有限的线程,每个线程都会持续不断的执行各种任务.")
            esv_content.addBoldBody("Executor接口表示线程池,它的execute(Runnable command)方法用来提交Runnable类型参数command的run()" +
                    "方法所制定的任务.线程池会调度空闲的线程来执行该任务.至于何时执行该任务,这是由线程池在运行时动态的决定的.它的ExecutorService子接口" +
                    "具有管理线程池的一些方法:")
            esv_content.addBoldBody("(1)shutdown():预备关闭线程池,如果已经有任务开始执行,那么要等这些任务执行完毕后,才会真正的关闭线程池." +
                    "那些还没执行的任务则被忽略不再执行.")
            esv_content.addBoldBody("(2)shutdownNow():终止已经开始执行的任务,立即关闭线程池." + esv_content.tab +
                    "(3)awaitTermination():等待线程池关闭." + esv_content.tab +
                    "(4)isTerminated():判断线程池是否关闭.")
            esv_content.addBody("ExecutorService接口的submit(Callable<T> task)和submit(Runnable task)与Executor接口的execute(Runnable command)" +
                    "方法相似,都用于向线程池提交任务,区别在于两个submit支持异步运算,它们都会返回代表异步运算结果的Future对象.")
            esv_content.addBoldBody("Executors类包含一些静态方法,负责生成各种了类型的ExecutorService实例:")
            esv_content.addBoldBody("(1) Executors.newCachedThreadPool():创建拥有缓存的线程池,有任务时才创建新的线程,空闲的线程在缓存中保留60秒.")
            esv_content.addBoldBody("(2)Executors.newFixedThreadPool(int nThreads):创建拥有固定数目线程的线程池,空闲线程会一直保留.参数nThreads" +
                    "用于设置线程池中线程的数目.")
            esv_content.addBoldBody("(3) Executors.newSingleThreadExecutor():创建只有一个线程的线程池,这个线程会依次执行每个任务,如果这个线程因" +
                    "异常而终止,不会创建替代它的线程.newFixedThreadPool(1)方法尽管也只创建一个线程,但如果线程异常终止,线程池会创建一个新的线程来替代它" +
                    "继续执行任务.")
            esv_content.addBoldBody("(4)Executors.newScheduledThreadPool(int corePoolSize):线程会按时间计划来执行任务.允许用户设定计划执行任务的" +
                    "时间.参数corePoolSize设定线程池中线程的最小数目.当任务较多时,线程池可能会创建更多的线程来执行任务.")
            esv_content.addBoldBody("(4)Executors.newSingleThreadScheduledExecutor():创建只有一个线程的线程池.这个线程能按照时间计划来执行任务.")
        }
    }
}