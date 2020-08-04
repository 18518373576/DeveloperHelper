package com.example.developerandroidx.ui.java.interfaceDesc;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/8 10:45 AM
 * 参考：
 * 描述：
 */
public class InterfaceDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("接口");
                esv_content.addTitle_2("接口的概念");
                esv_content.addBody("一种是指概念性的接口,即指系统对外提供的所有服务.类的所有能被外部使用者访问的方法构成了类的接口.");
                esv_content.addBody("一种是指interface修饰的接口,也称接口类型.它用于明确地描述系统对外提供的所有服务,它能够更加清晰的把系统的实现细节与接口分离.");
                esv_content.addBoldBody("接口对其成员变量和方法做了许多限制,特征归纳如下:");
                esv_content.addBody("1.接口中的成员变量默认都是public static final类型的,必须被显示初始化.");
                esv_content.addBody("2.接口中的方法默认都是public abstract类型的.");
                esv_content.addBody("3.在JDK8以前的版本中,接口只能包含抽象方法.从JDK8开始,为了提高代码的可重用性,允许在接口中定义" +
                        "默认方法和静态方法,默认方法用default关键字声明,拥有默认的实现.接口的实现类可以直接访问默认方法也可以覆盖它.");
                esv_content.addBody("接口中的静态方法只能在接口内部被访问,或者通过接口名称来访问,如果试图通过实现类访问静态方法,会导致编译错误.");
                esv_content.addBody("4.接口没有构造方法,不能被实例化,在接口中定义构造方法是非法的.");
                esv_content.addBody("5.一个接口不能实现另一个接口,但可以继承多个其他接口.");
                esv_content.addBody("6.与继承抽象父类类似,接口实现类必须实现接口中的所有抽象方法,或者把实现类定义为抽象类.");
                esv_content.addBody("7.不允许创建接口的实例,但允许定义接口类型的引用变量,该变量引用接口的实现类的实例.");
                esv_content.addBody("8.一个类只能继承一个直接父类,但能实现多个接口.");
                esv_content.addBody("抽象类和接口");
                esv_content.addBoldBody("相同点:");
                esv_content.addBody("1.代表系统抽象层.");
                esv_content.addBody("2.都不能被实例化.");
                esv_content.addBody("3.都能包含抽象方法.");
                esv_content.addBody("4.从JDK8开始,不仅抽象类能为某些方法提供默认实现,接口也可以.");
                esv_content.addBoldBody("不同点:");
                esv_content.addBody("1.接口中的成员变量只能是public static final类型的,而在抽象类中可以定义各种类型的成员变量和实例变量,这是抽象类的优势所在.");
                esv_content.addBody("2.一个类只能继承一个直接的父类,但可以实现多个接口,这是接口的优势所在.");
            }
        });
    }
}
