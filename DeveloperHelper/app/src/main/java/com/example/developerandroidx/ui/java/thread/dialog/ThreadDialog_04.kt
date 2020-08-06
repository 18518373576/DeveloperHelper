package com.example.developerandroidx.ui.java.thread.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/21/20 10:07 AM
 * 参考：
 * 描述：
 */
class ThreadDialog_04 : FunctionDialogInterface {

    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "线程的同步"
            esv_content.addBody("线程的职责就是执行一些操作,而多数操作涉及处理数据." + esv_content.tab +
                    "原子操作由业务逻辑上相关的一组操作完成.这些操作可能会操作与其他线程共享的资源.为了保证得到正确的结果,一个线程在执行原子操作期间,应采取措施使其他线程不能操作共享" +
                    "资源,例如:生产者消费者模式的例子.")
            esv_content.addBoldBody("同步代码块")
            esv_content.addBody("为了保证每个线程能正常执行原子操作,Java引入了同步机制,具体做法是在代表原子操作的代码前加上synchronized标记,每个Java对象都有且" +
                    "只有一个同步锁,在任何时刻,最多只允许一个线程拥有这把锁.当其他线程试图执行带有synchronized(this)标记的代码块时,该线程必须首先获得this关键字引用" +
                    "的Stack对象的锁.在以下两种情况下,该线程有着不同的命运:")
            esv_content.addBody("(1)假如这个锁已经被其他线程占用,Java虚拟机就会把请求执行的线程放到Stack对象的锁池中,该线程进入阻塞状态.在Stack对象的" +
                    "锁池中可能有很多等待锁的线程.等到其他线程释放了锁.Java虚拟会从锁池中随机地取出一个线程,使这个线程拥有锁,并且转到就绪状态." + esv_content.tab +
                    "(2)假如这个锁没有被其他线程占用,该线程就会获得这把锁,开始执行同步代码块.一般情况下,该线程只有执行完同步代码块,才会释放锁,当然也有一些例外情况.")
            esv_content.addBoldBody("如果一个方法中的所有代码都属于同步代码块,可以直接在方法前使用synchronized修饰.")
            esv_content.addTitle_2("线程同步的特征")
            esv_content.addBody("(1)如果一个同步代码块和非同步代码块操作共享资源,这仍会造成对共享资源的竞争.所谓线程之间的保持同步,是指不同的线程在执行" +
                    "同一个对象的同步代码块时,因为要获得这个对象的锁而互相牵制.")
            esv_content.addBody("(2)每个线程都有唯一的同步锁." + esv_content.tab +
                    "(3)在静态方法前边也可以使用synchronized修饰符" + esv_content.tab +
                    "(4)当一个线程开始执行同步代码块,并不意味着必须以不中断的方式执行.进入同步代码块的线程也可以执行sleep()和yield()方法.此时它并没有释放锁,只是把运行机会" +
                    "让给了其他线程." + esv_content.tab +
                    "(5)synchronized声明不会被继承.")

            esv_content.addTitle_2("同步与并发")
            esv_content.addBody("同步是解决共享资源竞争的有效手段,当一个线程已经在操作共享资源时,其他线程只能等待." + esv_content.tab +
                    "但是,多线程的同步与并发是一对此消彼长的矛盾.所以,为了提高并发性,应该使得同步代码块中包含尽可能少的操作,使得一个线程能尽快释放锁," +
                    "减少其他线程等待锁的时间.")
            esv_content.addTitle_2("线程的安全类")
            esv_content.addBody("一个线程安全的类应满足一下条件:" + esv_content.tab +
                    "(1)这个类的对象可以同时被多个线程安全的访问." + esv_content.tab +
                    "(2)每个线程都能正常的执行原子操作,得到正确的结果." + esv_content.tab +
                    "(3)每个线程的原子操作完成后,对象处于逻辑上的合理状态.")
            esv_content.addTitle_2("释放对象的锁")
            esv_content.addBody("由于等待一个锁的线程只有获得这把锁之后,才能恢复运行,所以让持有锁的线程在不需要锁的时候及时释放锁是很重要的." +
                    "在以下情况,持有锁的线程会释放锁:")
            esv_content.addBody("(1)执行完同步代码块就会释放锁." + esv_content.tab +
                    "在执行同步代码块的时候,遇到异常而导致线程终止,锁也会被释放." + esv_content.tab +
                    "(3)在执行同步代码块的时候,执行了锁所属对象的wait()方法,这个线程也会释放锁.")
            esv_content.addTitle_2("死锁")
            esv_content.addBody("当一个线程等待由另一个线程持有的锁,而后者正在等待已被第一个线程持有的锁时,就会出现死锁.")
        }
    }

}