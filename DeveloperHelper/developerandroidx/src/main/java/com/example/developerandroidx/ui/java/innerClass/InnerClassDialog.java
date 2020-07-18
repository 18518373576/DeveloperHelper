package com.example.developerandroidx.ui.java.innerClass;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 7/18/20 10:34 AM
 * 参考：
 * 描述：
 */
public class InnerClassDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("内部类");
                esv_content.addBody("在一个类的内部定义的类称为内部类.内部类允许把一些逻辑相关的类组织在一起,并且控制内部类代码的可视性.学会使用内部类,是掌握Java高级编程" +
                        "的一部分,它能够让程序结构变得更优雅.");
                esv_content.addBody("如下图所示,类A是顶层类,类B和类C都是内部类,并且类A是类B的外部类,类A是类B和类C的外部类,Tester类会访问类A及它的内部类" +
                        ",因此Tester类称为客户类.");
                esv_content.addImage(R.mipmap.image_inner_class_01, 200);
                esv_content.addTitle_2("内部类的基本语法");
                esv_content.addBoldBody("变量可按照作用域进行如下图的分类:");
                esv_content.addImage(R.mipmap.image_inner_class_02, 130);
                esv_content.addBoldBody("同样,内部类按照作用域可进行如下图所示的分类:");
                esv_content.addImage(R.mipmap.image_inner_class_03, 130);
                esv_content.addBody("顶层类只能处于public和默认访问级别,而成员内部类可处于public,protected,默认和private这4种访问级别.");
                esv_content.addTitle_2("实例内部类");
                esv_content.addBody("实例内部类是成员内部类的一种,没有static修饰的内部类就是实例内部类.实例内部类具有一下特点:");
                esv_content.addBody("(1)在创建实例内部类时,外部类的实例必须已经存在.例:" + esv_content.tab +
                        " A.B b = new A().new B();");
                esv_content.addBody("(2)实例内部类的实例自动持有外部类的实例引用,在内部类中,可以直接访问外部类的所有成员,包括成员变量和成员方法." + esv_content.tab +
                        "(3)外部类实例和内部类实例时一对多的关系,一个内部类实例只会引用一个外部类实例,而一个外部类实例对应零个或多个内部类实例.在外部类中" +
                        "不能直接访问内部类的成员,必须通过内部类的实例去访问." + esv_content.tab +
                        "(4)在实例内部类中不能定义静态成员,实例内部类中只能定义实例成员." + esv_content.tab +
                        "(5)如果实例内部类B与外部类A包含同名的成员,那么在类B中,this.v表示类B的成员,A.this.v表示类A的成员.");
                esv_content.addTitle_2("静态内部类");
                esv_content.addBody("静态内部类是成员内部类的一种,用static修饰.静态内部类具有以下特点:");
                esv_content.addBody("(1)静态内部类的实例不会自动持有外部类的特定实例的引用,在创建内部类的实例时,不必创建外部类的实例.例:" + esv_content.tab +
                        "A.B b = new A.B();");
                esv_content.addBody("(2)静态内部类可以直接访问外部类的静态成员,如果访问外部类的实例成员,必须通过外部类的实例去访问." + esv_content.tab +
                        "(3)在静态内部类中可以定义静态成员和实例成员." + esv_content.tab +
                        "(4)客户类可以通过完整的类名直接访问静态内部类的静态成员.");
                esv_content.addTitle_2("局部内部类");
                esv_content.addBody("局部内部类是在一个方法中定义的内部类,它的可见范围是当前方法.和局部变量一样,和局部变量一样,局部内部类不能用" +
                        "访问控制修饰符(public,protected,private)和static修饰.局部内部类具有以下特点:");
                esv_content.addBody("(1)局部内部类只能在当前方法中使用." + esv_content.tab +
                        "(2)局部内部类和实例内部类一样,不能包含静态成员." + esv_content.tab +
                        "(3)局部内部类中定义的内部类也不能被public,protected,private这些访问控制修饰符修饰." + esv_content.tab +
                        "(4)局部内部类和实例内部类一样,可以访问外部类的所有成员,此外,局部内部类还可以访问所在方法中的符合以下条件之一的参数和变量." +
                        "·最终变量或参数:用final修饰." + esv_content.tab +
                        "·实际上的最终变量或参数:虽没有用final修饰,但程序不会改变变量的值.");

                esv_content.addTitle_2("匿名类");
                esv_content.addBody("匿名类是一种特殊的内部类,这种类没有名字(例如Android中创建Handler).匿名内部类具有一下特点:");
                esv_content.addBody("(1)匿名类本身没有构造方法,但是会调用父类的构造方法." + esv_content.tab +
                        "(2)匿名类尽管没有构造方法,但可以在匿名类中提供一段实例初始化代码,Java虚拟机会在调用了父类的构造方法后,执行这段代码." + esv_content.tab +
                        "例:A a = new A(){{初始化匿名类} void method(){执行方法}}");
                esv_content.addBody("(3)除了可以在外部类的方法内定义匿内,还可以在声明一个成员变量时定义匿名类." + esv_content.tab +
                        "(4)匿名类除了可以继承还可以实现接口,例:" + esv_content.tab +
                        "new Thread(new Runnable(){})" + esv_content.tab + "以上匿名类实现了java.lang.Runnable接口" +
                        "并把匿名类的实例作为参数传递给Thread");
                esv_content.addBody("(5)匿名类和局部内部类一样,可以访问外部类的所有成员." + esv_content.tab +
                        "(6)局部内部类的名字在方法外是不可见的,因此与匿名类一样.可以起到封装类型名字的作用.");
                esv_content.addTitle_2("内部类的类文件");
                esv_content.addBody("成员内部类:外部类的名字$内部类的名字." + esv_content.tab +
                        "局部内部类:外部类的名字$数字和内部类的名字." + esv_content.tab +
                        "匿名类:外部类的名字$数字.");
            }
        });
    }
}
