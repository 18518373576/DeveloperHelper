package com.example.developerandroidx.ui.java.designModel.Dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/31/20 11:32 AM
 * 参考：
 * 描述：
 */
class FactoryModelDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "简单工厂模式"
            esv_content.addBoldBody("定义工厂抽象类")
            esv_content.addCode(
                    "public interface BaseCarFactory {\n" +
                            "    Car create(CarType type, CarColor carColor);\n" +
                            "}")
            esv_content.addBoldBody("定义汽车抽象类")
            esv_content.addCode(
                    "public abstract class Car {\n" +
                            "\n" +
                            "    //汽车必须会跑\n" +
                            "    public void run() {\n" +
                            "        System.out.println(\"跑一下,试试漏油不!\");\n" +
                            "    }\n" +
                            "\n" +
                            "    //消费者可以选择车漆颜色\n" +
                            "    abstract void Custom(CarColor carColor);\n" +
                            "}")
            esv_content.addBoldBody("定义汽车种类枚举")
            esv_content.addCode(
                    "public enum CarType {\n" +
                            "    SUV, MPV\n" +
                            "}")
            esv_content.addBoldBody("定义汽车颜色枚举")
            esv_content.addCode("public enum CarColor {\n" +
                    "    RED(\"红色\"), BLUE(\"蓝色\"), GRAY(\"灰色\");\n" +
                    "    String color;\n" +
                    "\n" +
                    "    CarColor(String s) {\n" +
                    "        this.color = s;\n" +
                    "    }\n" +
                    "}")
            esv_content.addBoldBody("定义汽车")
            esv_content.addCode(
                    "public class MPV_car extends Car {\n" +
                            "\n" +
                            "    public MPV_car() {\n" +
                            "        System.out.println(\"生产一辆MPV\");\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    void Custom(CarColor carColor) {\n" +
                            "        System.out.println(\"车漆颜色:\" + carColor.color);\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("")
            esv_content.addCode(
                    "public class SUV_car extends Car {\n" +
                            "    public SUV_car() {\n" +
                            "        System.out.println(\"生产一辆SUV\");\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    void Custom(CarColor carColor) {\n" +
                            "        System.out.println(\"车漆颜色:\" + carColor.color);\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("消费者")
            esv_content.addCode(
                    "public class Consumer {\n" +
                            "    public Car buyCar(CarType carType, CarColor carColor) {\n" +
                            "        return new CarFactory().create(carType, carColor);\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("测试以下看看效果")
            esv_content.addCode(
                    "public class Test {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Consumer consumer_01 = new Consumer();\n" +
                            "        consumer_01.buyCar(CarType.SUV, CarColor.RED).run();\n" +
                            "        Consumer consumer_02 = new Consumer();\n" +
                            "        consumer_02.buyCar(CarType.MPV, CarColor.BLUE).run();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出:\n" +
                            "生产一辆SUV\n" +
                            "车漆颜色:红色\n" +
                            "跑一下,试试漏油不!\n" +
                            "生产一辆MPV\n" +
                            "车漆颜色:蓝色\n" +
                            "跑一下,试试漏油不!")
        }
    }
}