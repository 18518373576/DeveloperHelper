package com.example.developerandroidx.ui.java.objectLifeCircle;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/15 1:12 PM
 * 参考：
 * 描述：
 */
public class ObjectLifeCircleDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("对象的生命周期");
                esv_content.addBody("在Java虚拟机管辖的运行时数据区,最活跃的就是位于堆区的对象.在Java虚拟机的生命周期中,一个个的" +
                        "对象被陆陆续续的创建,又一个个被销毁.在对象生命周期的开始阶段,需要为对象分配内存,并且初始化它的实例变量." +
                        "当程序不再使用某个对象时,那么他就会结束生命周期,它的内存可以被Java虚拟机的垃圾回收器回收");
                esv_content.addTitle_2("对象的创建方式");
                esv_content.addBody("在Java程序中,对象可以被显式地或隐含地创建,创建一个对象就是指构造类的一个实例,前提条件是这个类已经被初始化.");
                esv_content.addBody("有四种显示的创建对象的方式:" + esv_content.tab +
                        "(1)使用new语句创建." + esv_content.tab +
                        "(2)运用反射创建." + esv_content.tab +
                        "(3)调用对象的clone方法." + esv_content.tab +
                        "(4)运用反序列化手段.");
                esv_content.addTitle_2("构造方法");
                esv_content.addBody("多数情况下,初始化对象的最终步骤是去调用这个对象的构造方法." +
                        "构造方法负责对象的初始化工作,构造方法必须满足以下语法规则:");
                esv_content.addBody("(1)方法名必须和类名相同." + esv_content.tab +
                        "(2)不要声明返回类型." + esv_content.tab +
                        "(3)不能被static,final,synchronized,abstract和native修饰.");
                esv_content.addTitle_2("重载构造方法");
                esv_content.addBody("当通过new语句创建一个对象时,在不同的条件下,对象可能会有不同的初始化行为." +
                        "可通过重载构造方法来表达对象的多种初始化行为.");
                esv_content.addTitle_2("默认构造方法");
                esv_content.addBody("默认构造方法是没有参数的构造方法,可分为两种:" + esv_content.tab +
                        "(1)隐含的默认构造方法." + esv_content.tab +
                        "(2)程序显示定义的默认构造方法." + esv_content.tab +
                        "在Java语言中每个类都至少有一个构造方法.如果用户没有定义构造方法,Java语言会自动提供一个没有参数的隐含的默认构造方法." +
                        "如果类中显示的定义了一个或多个构造方法,并且所有的构造方法都带有参数,那么这个类就失去了默认的构造方法.");
                esv_content.addTitle_2("子类调用父类的构造方法");
                esv_content.addBody("父类的构造方法不能被子类继承,但可以使用super语句调用父类的构造方法.");
                esv_content.addBody("当子类的构造方法没有使用super语句显式调用父类的构造方法时,那么通过这个构造方法创建子类对象时,Java虚拟机会自动先调用" +
                        "父类的默认构造方法.如果父类没有提供默认构造方法,则编译错误");
                esv_content.addTitle_2("垃圾回收");
                esv_content.addBody("当对象被创建后,就会在Java虚拟机的堆区中拥有一块内存,在Java虚拟机的生命周期中,Java程序" +
                        "会陆陆续续创建无数的对象,假如所有对象都永久占有内存,那么内存有可能很快被消耗光,最后引发内存空间不足的错误." +
                        "因此必须采取一种措施来及时回收那些无用对象的内存,以保证内存可以被重复利用." + esv_content.tab +
                        "在一些传统的编程语言,如C语言中,回收内存的任务是由程序本身负责的.程序可以显式的为自己的变量分配一块内存空间." +
                        "当这些变量不再有用时,程序必须显式的释放变量所占用的内存." + esv_content.tab +
                        "在Java语言中,内存回收的任务由Java虚拟机承担.在程序的运行时环境中,Java虚拟机提供了一个系统级的垃圾回收器线程,它负责自动回收那些" +
                        "无用对象所占用的内存.");
                esv_content.addLabelBody(esv_content.getBoldLabel("垃圾回收具有一下特点:"));
                esv_content.addBody("(1)只有当对象不再被程序中的任何变量引用时,它的内存才可能被回收." + esv_content.tab +
                        "(2)程序无法迫使垃圾回收器立即执行垃圾回收操作." + esv_content.tab +
                        "(3)当垃圾回收器将要回收无用对象内存时,先调用该对象的finalize()方法,该方法有可能使对象复活,导致垃圾回收器取消回收该对象的内存.");
                esv_content.addTitle_2("对象的可触及性");
                esv_content.addBody("在Java虚拟机的垃圾回收器看来,堆区中的每个对象都可能处于以下3个状态之一:");
                esv_content.addBody("(1)可触及状态:当一个对象被创建后,只要程序中还有引用它的变量,那么它就始终处于可触及状态." + esv_content.tab +
                        "(2)可复活状态:当程序不再有任何变量引用对象时,那么它就进入可复活状态,在这个状态中,垃圾回收器会准备释放它的内存,在释放之前会调用" +
                        "它的finalize()方法." + esv_content.tab +
                        "(3)不可触及状态:当Java虚拟机执行完所有可复活对象的finalize()方法后,假如都没有使对象转到可触及状态,那么该对象就进入不可触及状态," +
                        "只有当对象处于不可触及状态时,垃圾回收器才会真正回收它的内存.");
                esv_content.addImage(R.mipmap.image_trash_recycling, 250);
                esv_content.addTitle_2("垃圾回收的时间");
                esv_content.addLabelBody("当一个对象处于可复活状态时,垃圾回收线程何时执行它的finalize()方法,何时使它转到不可触及状态,何时回收它的内存," +
                        "这对于程序来说都是透明的.程序只能决定一个对象何时不再被任何引用变量引用,使得它成为可以被回收的垃圾." + esv_content.tab +
                        "站在程序的角度,如果一个对象不处于可触及状态,就可以称它为无用对象,程序不会持有无用对象的引用.不会再使用它,这样的对象可以被垃圾回收器回收." +
                        esv_content.getBoldLabel("一个对象的生命周期从被创建开始,到不再被任何引用变量引用结束,这就是对象的生命周期."));
                esv_content.addBody("垃圾回收器作为低优先级线程独立运行.在任何时候,程序都无法迫使垃圾回收器立即执行垃圾回收操作.在程序中可以调用" +
                        "System.gc()或者Runtime.gc()方法提示垃圾回收器尽快执行垃圾回收操作.");
                esv_content.addTitle_2("对象的finalize()方法的特点:");
                esv_content.addBody("(1)垃圾回收器是否会执行此方法,以及何时执行该方法,都是不确定的." + esv_content.tab +
                        "(2)finalize()方法有可能使对象复活,使它恢复到可触及状态." + esv_content.tab +
                        "(3)垃圾回收器在执行finalize()方法时,如果出现异常,垃圾回收器不会报告异常,程序继续正常执行.");
                esv_content.addTitle_2("对象的强,软,弱,和虚引用");
                esv_content.addBody("(1)强引用:一般情况下的引用都是强引用,这是最普遍的引用,如果一个对象具有强引用,垃圾回收器" +
                        "绝不会回收它.当内存空间不足时Java虚拟机宁愿抛出OOM错误,使程序终止,也不会靠随意回收具有强引用的对象来解决内存不足的问题." +
                        esv_content.tab + "(2)如果一个对象只具有软引用(SoftReference),那就类似于可有可无的生活用品,如果内存足够,垃圾回收器就不会回收它." +
                        "软引用可以用来实现一些内存敏感的高速缓存." + esv_content.tab +
                        "(3)弱引用:如果一个对象只具有弱引用(WeakReference),在垃圾回收器线程扫描它所管辖的内存区域的过程中,一旦发现了只具有弱引用的对象," +
                        "不管当前内存空间是否足够,都会回收它的内存.不过由于垃圾回收器是一个优先级很低的线程,因此不一定会很快发现那些只具有弱引用的对象." + esv_content.tab +
                        "(4)顾名思义,虚引用(PhantomReference)就是形同虚设,与其他几种引用都不同,虚引用并不会决定对象的生命周期,如果一个对象仅持有虚引用,那么他就和" +
                        "没有持有任何引用一样.虚引用必须和引用队列(ReferenceQueue)联合使用.");
            }
        });
    }
}
