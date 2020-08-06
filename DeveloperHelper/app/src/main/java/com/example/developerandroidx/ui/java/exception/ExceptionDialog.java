package com.example.developerandroidx.ui.java.exception;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.App;
import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/8 1:43 PM
 * 参考：
 * 描述：
 */
public class ExceptionDialog implements FunctionDialogInterface {

    private class NoWayException extends RuntimeException {
        public NoWayException(String msg) {
            super(msg);
        }
    }

    private void test() {
        throw new NoWayException("对方不想借钱给你,并对你抛出了一个异常...");
    }

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("异常处理");
                esv_content.addTitle_2("异常处理对性能的影响");
                esv_content.addBody("一般来说,在Java程序中使用try-catch语句不会对性能造成很大的影响.仅当异常发生时,Java虚拟机需要执行额外的操作,来定位" +
                        "处理异常的代码块,这时会对性能产生负面的影响.如果抛出和捕获异常在同一个方法中,影响就会小些.如果Java虚拟机必须搜索方法调用栈来寻找" +
                        "异常处理代码块,对性能的影响就比较大了.");
                esv_content.addBody("所以,应该确保仅仅在程序中可能出现异常的地方使用try-catch语句.如果当前方法具备自行处理异常的能力,就尽量自行处理,不要把自己" +
                        "可以处理的异常推给方法调用者去处理.");
                esv_content.addTitle_2("运用Java异常处理机制");
                esv_content.addBoldBody("1.try-catch语句:捕获异常");
                esv_content.addAlertBody("try{" +
                        esv_content.tabDouble + "可能出现异常的代码" +
                        esv_content.tab + "}catch (Exception e){" +
                        esv_content.tabDouble + "处理异常" +
                        esv_content.tab + "}", R.color.colorMain);
                esv_content.addBoldBody("2.finally语句:任何情况下必须执行的代码");
                esv_content.addBody("由于异常会强制中断流程,这会使得某些不管在任何情况下都必须执行的步骤被忽略,使用finally语句,不管try代码块" +
                        "是否发生异常,都会执行finally代码块.");
                esv_content.addBoldBody("3.throws子句:声明可能会出现的异常");
                esv_content.addBody("如果一个方法可能会出现异常,但没有能力处理这种异常,可以在方法声明处用throws子句来声明抛出异常.");
                esv_content.addBody("一个方法可能会出现中异常,throws子句允许抛出多个异常.");
                esv_content.addAlertBody("public void method() " +
                        esv_content.tabDouble + "throws IOException , SQLException {...}", R.color.colorMain);
                esv_content.addBoldBody("4.throw语句:抛出异常");
                esv_content.addAlertBody("if(...)" +
                        esv_content.tabDouble + "throw new Exception(...)", R.color.colorMain);
                esv_content.addBody("需要注意的是,由throw语句抛出的对象必须是Throwable类或其子类.");
                esv_content.addTitle_2("Java异常类");
                esv_content.addBody("在程序运行中,任何中断正常流程的因素都被认为是异常.按照面向对象的思想,Java语言用Java类来描述异常.所有异常类都继承自Throwable类." +
                        "它的实例表示具体的异常对象,可以通过throw语句抛出.Throwable类提供了访问异常信息的一些方法,常用的方法包括:");
                esv_content.addBody("1.getMessage():返回String类型的异常信息.");
                esv_content.addBody("2.printStackTrace():打印跟踪方法调用栈而获得详细异常信息.");
                esv_content.addBoldBody("Throwable有两个直接子类:");
                esv_content.addBody("1.Error类:表示靠程序本身无法回复的严重错误.比如内存不足,或者Java虚拟机的方法调用栈溢出.");
                esv_content.addBody("2.Exception类:表示程序本身可以处理的异常.当程序运行时出现这类异常,应尽可能的处理异常,并且使程序回复运行," +
                        "而不应随意终止程序.");
                esv_content.addImage(R.mipmap.image_exception, 250);
                esv_content.addBoldBody("下面对一些常用的异常做简单介绍:");
                esv_content.addBody("1.IOException:操作输入输出流时可能遇到异常.");
                esv_content.addBody("2.ArithmeticException:数学异常,把整数除以0就会出现这种异常.");
                esv_content.addBody("3.NullPointerException:空指针异常,当引用变量为null时,试图访问对象的属性或方法,就会出现这种异常.");
                esv_content.addBody("4.IndexOutOfBoundsException:下标越界异常.");
                esv_content.addBody("5.ClassCastException:类型转换异常.");
                esv_content.addBody("6.IllegalArgumentException:非法参数异常.");
                esv_content.addTitle_2("RuntimeException运行时异常");
                esv_content.addBody("RuntimeException类及其子类都称为运行时异常,这种异常的特点是Java编译器不会检查它,也就是说,当程序中可能出现" +
                        "这种异常时,即使没有使用try-catch捕获它,也没有用throws子句声明抛出它,也会编译通过.");
                esv_content.addTitle_2("受检查异常");
                esv_content.addBody("除了RuntimeException及其子类外,其他Exception及其子类都属于受检查异常.这种异常的特点是Java编译器会检查它.也就是说," +
                        "当程序中出现这种异常时,如果不捕获或者抛出编译通不过.");
                esv_content.addBodyWithClick("测试抛出RuntimeException", R.color.colorMain, new ExtensibleScrollView.OnBodyClickListener() {
                    @Override
                    public void onclick(View view) {
                        try {
                            test();
                        } catch (NoWayException e) {
                            App.showNotify(e.getMessage());
                        } finally {
                            App.showNotify("辛亏被我捕获到了,哈哈哈...");
                        }
                    }
                });
            }
        });
    }
}
