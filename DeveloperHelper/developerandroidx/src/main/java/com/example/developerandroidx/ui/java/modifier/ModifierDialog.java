package com.example.developerandroidx.ui.java.modifier;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/7 2:20 PM
 * 参考：
 * 描述：
 */
public class ModifierDialog implements FunctionDialogInterface {
//    static final int a;
//
//    public ModifierDialog() {
//        a = 10;
//    }

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("Java修饰符");
                esv_content.addTitle_2("访问控制修饰符");
                esv_content.addBody("public:公开级别,对外公开.");
                esv_content.addBody("protected:受保护级别,向子类和同一个包中的类公开.");
                esv_content.addBody("private:只有类本身可以访问,不对外公开.");
                esv_content.addBody("默认级别:没有访问修饰符即为默认级别,对同一个包中的类公开.");
                esv_content.addTitle_2("abstract");
                esv_content.addBody("abstract修饰符可以修饰类和成员方法.");
                esv_content.addBody("1.用abstract修饰的类表示抽象类.抽象类位于继承树的抽象层,抽象类不能被实例化,即不允许创建抽象类本身的实例." +
                        "没有用abstract修饰的类称为具体类,具体类可以被实例化.");
                esv_content.addBody("2.用abstract修饰的方法表示抽象方法,抽象方法没有具体的方法体.抽象方法用来描述系统具有什么功能," +
                        "但不提供具体的实现.没有用abstract修饰的方法称为具体方法,具体方法具有方法体.");
                esv_content.addBody("abstract修饰符语法规则:");
                esv_content.addBody("1.抽象类中可以没有抽象方法,但包含了抽象方法的类必须定义为抽象类.");
                esv_content.addBody("2.抽象方法不能用static修饰.");
                esv_content.addBody("3.抽象类不能被实例化,然而可以创建一个引用变量,并让它引用一个非抽象子类的实例.");
                esv_content.addBody("4.抽象类不能被final修饰符修饰.");
                esv_content.addBody("5.抽象方法不能被private修饰符修饰.");
                esv_content.addTitle_2("final");
                esv_content.addBody("final具有不可改变的含义,它可以修饰非抽象类,非抽象成员方法和变量.");
                esv_content.addBody("1.用final修饰的类不能被继承.");
                esv_content.addBody("2.用final修饰的方法不能被子类的方法覆盖.");
                esv_content.addBody("3.用final修饰的变量表示常量,只能被赋值一次.");
                esv_content.addBody("4.用final修饰的引用类型变量,始终只能引用一个对象,但可以改变对象的内容.");
                esv_content.addAlertBody("注意:对于fina类型的实例变量,必须显式初始化或在构造方法中进行初始化.对于final类型的静态变量," +
                        "可以在定义变量时进行初始化,或在静态代码块中初始化,在构造方法中初始化编译错误.");
                esv_content.addTitle_2("static");
                esv_content.addBody("static修饰符可以用来修饰类的成员变量,成员方法和代码块.");
                esv_content.addBody("用static修饰的成员变量表示静态变量,可以直接通过类名访问.");
                esv_content.addBody("用static修饰的成员方法表示静态方法,可以直接通过类名访问.");
                esv_content.addBody("用static修饰的程序代码块表示静态代码块,当Java虚拟机加载类时,就会执行该代码块.");
                esv_content.addBody("1.static变量");
                esv_content.addBody("静态变量在内存中只有一个备份,运行时Java虚拟机只为静态变量分配一次内存.在加载类的过程中完成静态变量的内存分配.");
                esv_content.addBody("静态变量能被类的所有实例共享,可作为实例之间进行交流的共享数据.");
                esv_content.addBody("如果类的所有实例都包含一个相同的常量属性,可以把这个属性定义为静态常量,从而节省内存空间.");
                esv_content.addBody("2.static方法");
                esv_content.addBody("因为静态方法不需要通过实例就能访问,因此静态方法中不能使用this关键字,也不能直接访问所属类的实例变量和实例方法.但是" +
                        ",可以访问所属类的静态变量和静态方法.");
                esv_content.addBody("静态方法必须被实现,静态方法用来表示某个类所特有的功能,这种功能的实现不依赖于类的具体实例," +
                        "也不依赖于它的子类.既然如此,当前类必须为静态方法提供实现.");
            }
        });
    }
}
