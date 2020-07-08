package com.example.developerandroidx.ui.java.exception;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/8 1:43 PM
 * 参考：
 * 描述：
 */
public class ExceptionDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
//                    Handler handler = new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//                            super.handleMessage(msg);
//                            LogUtils.e("handler", "handler机制测试");
//                        }
//                    };
//                    Thread.sleep(1000);
////                    handler.obtainMessage()
//                    handler.sendEmptyMessage(100);
//                    Looper.loop();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
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
            }
        });
    }
}
