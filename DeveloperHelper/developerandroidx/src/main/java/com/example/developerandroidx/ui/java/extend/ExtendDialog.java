package com.example.developerandroidx.ui.java.extend;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/5 12:04 PM
 * 参考：
 * 描述：
 */
public class ExtendDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("继承概述");
                esv_content.addTitle_2("继承的概念");
                esv_content.addBody("在Java语言中,使用extends关键字表示一个类继承了另一个类.那么,子类到底从父类继承了那些东西?这需要分为两种情况:");
                esv_content.addBody("1.当子类和父类在同一个包中时:子类继承父类中的public,protected,和默认访问级别的成员变量和成员方法");
                esv_content.addBody("2.当子类和父类不在同一个包中时:子类继承父类中的public,protected访问级别的成员变量和成员方法");
                esv_content.addBody("Java语言不支持多继承,即一个类只能直接继承一个类.但可以有多个间接的父类,即父类还可以继承别的类");
                esv_content.addBody("所有Java类都直接或间接的继承了Object类,Object类是所有java类的祖先,在这个类中定义了所有Java类都具有的相同行为.如果在定义一个类时," +
                        "没有使用extends关键字,那么这个类默认直接继承Object类.");
                esv_content.addTitle_2("方法重载(Overload)");
                esv_content.addBody("有时候,类的同一种功能有多种实现方式,到底采用哪种实现方式,取决于调用者给定的参数.对于类的方法,如果有两个方法的方法名相同," +
                        "但参数不一致,那么可以说,一个方法是另一个方法的重载方法.");
                esv_content.addTitle_2("方法的覆盖(Override)");
                esv_content.addBody("如果在子类中定义的一个方法,其名称,返回类型,参数签名都与父类中的某个方法相匹配,那么可以说子类的方法覆盖了父类的方法.需要注意的是:");
                esv_content.addBoldBody("1.子类方法不能缩小父类方法的访问权限.");
                esv_content.addBoldBody("2.子类方法不能抛出比父类方法更多的异常.");
                esv_content.addBoldBody("3.方法覆盖只存在于子类和父类之间,在同一个类中,方法只能重载,不能覆盖.");
                esv_content.addBoldBody("4.父类的静态方法不能被子类覆盖为非静态方法.");
                esv_content.addBoldBody("5.父类的抽象方法可以被子类覆盖为抽象方法");
                esv_content.addTitle_2("super关键字");
                esv_content.addBody("super和this都可以用来覆盖Java语言的默认作用域.使被屏蔽的方法或变量变为可见,在以下场合会出现方法或变量被屏蔽的现象:");
                esv_content.addBody("1.在一个方法内,当局部变量和类的成员变量重名,或者局部变量和父类的成员变量重名,按照变量的作用于规则,只有局部变量在方法内可见.");
                esv_content.addBody("2.当子类的某个方法覆盖了父类的方法时,在子类范围内父类的方法不可见.");
                esv_content.addBody("3.当子类中定义了和父类同名的成员变量时,在子类范围内,父类的成员变量不可见.");
                esv_content.addTitle_2("多态");
                esv_content.addBody("当系统A访问系统B的服务时,系统B可以通过多种实现方式提供服务,而这一切对系统A是透明的.");
                esv_content.addTitle_2("继承的利弊和使用原则");
                esv_content.addBody("继承是一种提高代码可重用性,以及提高系统可扩展性的有效手段.但是,如果继承树过于复杂,或者随便扩展本来不是专门为继承设计的类,反而" +
                        "会削弱系统的可扩展性和可维护性.");
                esv_content.addBody("1.继承树的层次不可太多");
                esv_content.addBody("继承树的层次应该尽量保持在2~3层,如果继承树的层次很多会导致一下弊端:1.对象模型结构太复杂,难以理解.2:影响系统的可扩展性.");
                esv_content.addBody("2.继承树的上层应为抽象层");
                esv_content.addBody("位于继承树上层的类具有以下作用:1.定义了下层子类都拥有的相同属性和方法,并尽可能多的为多数方法提供默认实现,提高程序的可重用性." +
                        "2.代表系统的接口,描述系统所能提供的服务.");
                esv_content.addTitle_2("继承关系最大的弱点:打破封装.");

            }
        });
    }
}
