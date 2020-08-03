package com.example.developerandroidx.ui.java.designModel.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/1/20 4:34 PM
 * 参考：
 * 描述：
 */
class BuilderModelDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "建造者模式"
            esv_content.addBody("在开发复杂的应用程序时,代码往往会变得非常复杂,类会封装更多的功能,类的结构也会变得更加复杂.")
            esv_content.addBody("当需要实例化一个复杂的类,以得到不同结构和不同内部状态的对象时,我们可以使用不同的类对" +
                    "它们的实例化操作逻辑分别进行封装,这些类就被称为建造者.")
            esv_content.addBoldBody("汽车抽象类")
            esv_content.addCode(
                    "public interface Car {\n" +
                            "    void run();\n" +
                            "}")
            esv_content.addBoldBody("建造者抽象类")
            esv_content.addCode(
                    "public interface CarBuilder {\n" +
                            "    //设置颜色\n" +
                            "    CarBuilder setColor(String color);\n" +
                            "\n" +
                            "    //设置车轮\n" +
                            "    CarBuilder setWheel(String wheel);\n" +
                            "\n" +
                            "    //设置引擎\n" +
                            "    CarBuilder setEngine(String engine);\n" +
                            "\n" +
                            "    //混合动力设置电池\n" +
                            "    CarBuilder setBatteries(String batteries);\n" +
                            "\n" +
                            "    Car build();\n" +
                            "}")
            esv_content.addBoldBody("汽车实体类")
            esv_content.addCode(
                    "public class WuLingCar implements Car {\n" +
                            "\n" +
                            "    private WuLingCar() {\n" +
                            "    }\n" +
                            "\n" +
                            "    public static class Builder implements CarBuilder {\n" +
                            "        private WuLingCar car;\n" +
                            "\n" +
                            "        public Builder() {\n" +
                            "            car = new WuLingCar();\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public CarBuilder setColor(String color) {\n" +
                            "            car.setColor(color);\n" +
                            "            return this;\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public CarBuilder setWheel(String wheel) {\n" +
                            "            car.setWheel(wheel);\n" +
                            "            return this;\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public CarBuilder setEngine(String engine) {\n" +
                            "            car.setEngine(engine);\n" +
                            "            return this;\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public CarBuilder setBatteries(String batteries) {\n" +
                            "            return this;\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public Car build() {\n" +
                            "            return car;\n" +
                            "        }\n" +
                            "    }\n" +
                            "\n" +
                            "    private void setEngine(String engine) {\n" +
                            "        System.out.println(\"引擎:\" + engine);\n" +
                            "    }\n" +
                            "\n" +
                            "    private void setWheel(String wheel) {\n" +
                            "        System.out.println(\"车轮:\" + wheel);\n" +
                            "    }\n" +
                            "\n" +
                            "    private void setColor(String color) {\n" +
                            "        System.out.println(\"颜色:\" + color);\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public void run() {\n" +
                            "        System.out.println(\"跑起来\");\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("测试一下")
            esv_content.addCode(
                    "public class Test {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        Car myCar = new WuLingCar.Builder()\n" +
                            "                .setColor(\"红色\")\n" +
                            "                .setEngine(\"1.0T\")\n" +
                            "                .setWheel(\"橡胶轮胎\")\n" +
                            "                .build();\n" +
                            "        myCar.run();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:\n" +
                            "颜色:红色\n" +
                            "引擎:1.0T\n" +
                            "车轮:橡胶轮胎\n" +
                            "跑起来")
            esv_content.addBody("一个车系有多个扩展的车型,每种车型功能都有些差别,使用不同的建造者来创建不同的车型进行扩展,这样" +
                    "就不需要修改原来已有的代码.")
        }
    }
}