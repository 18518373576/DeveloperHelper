package com.example.developerandroidx.ui.java.objectLifeCircle;

import android.content.Context;
import android.widget.TextView;

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
            }
        });
    }
}
