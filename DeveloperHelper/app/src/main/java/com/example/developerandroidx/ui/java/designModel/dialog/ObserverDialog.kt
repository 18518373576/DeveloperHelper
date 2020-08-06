package com.example.developerandroidx.ui.java.designModel.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/2/20 1:09 PM
 * 参考：
 * 描述：观察者模式
 */
class ObserverDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "观察者模式"
            esv_content.addBody("观察者模式使得一个对象的状态改变时,已登记的其他对象能够观察到这一改变.")
            esv_content.addBody("下面的程序模拟一下老师布置作业的场景.")
            esv_content.addBoldBody("Observable(被观察者)")
            esv_content.addCode(
                    "/**\n" +
                            " * 被观察者\n" +
                            " */\n" +
                            "public interface Observable {\n" +
                            "    //注册观察者\n" +
                            "    void register(Observer observer);\n" +
                            "\n" +
                            "    //移除观察者\n" +
                            "    void remove(Observer observer);\n" +
                            "\n" +
                            "    //被观察者内容变化通知\n" +
                            "    void notify(String msg);\n" +
                            "}")
            esv_content.addBoldBody("Observer(观察者)")
            esv_content.addCode(
                    "/**\n" +
                            " * 观察者\n" +
                            " */\n" +
                            "public interface Observer {\n" +
                            "    //收到通知更新自己的内容\n" +
                            "    void update(String msg);\n" +
                            "}")
            esv_content.addBoldBody("Teacher")
            esv_content.addCode(
                    "/**\n" +
                            " * 实现被观察者\n" +
                            " */\n" +
                            "public class Teacher implements Observable {\n" +
                            "    //老师班级里的学生\n" +
                            "    private List<Observer> myStudents = new ArrayList();\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public void register(Observer observer) {\n" +
                            "        myStudents.add(observer);\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public void remove(Observer observer) {\n" +
                            "        myStudents.remove(observer);\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public void notify(String msg) {\n" +
                            "        for (Observer observer : myStudents) {\n" +
                            "            observer.update(msg);\n" +
                            "        }\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("Student")
            esv_content.addCode(
                    "/**\n" +
                            " * 实现观察者,观察老师布置作业的动作\n" +
                            " */\n" +
                            "public class Student implements Observer {\n" +
                            "    private String name;\n" +
                            "\n" +
                            "    public Student(String name) {\n" +
                            "        this.name = name;\n" +
                            "    }\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public void update(String msg) {\n" +
                            "        System.out.println(name + \"收到作业:\" + msg);\n" +
                            "    }\n" +
                            "}")
            esv_content.addBoldBody("测试一下")
            esv_content.addCode(
                    "public class Test {\n" +
                            "\n" +
                            "    private static class TeachClass implements Observer {\n" +
                            "        //开始上课\n" +
                            "        public void startClass() {\n" +
                            "            Student student_01 = new Student(\"张三\");\n" +
                            "            Student student_02 = new Student(\"李四\");\n" +
                            "            Student student_03 = new Student(\"王五\");\n" +
                            "            Student student_04 = new Student(\"赵六\");\n" +
                            "            Teacher teacher = new Teacher();\n" +
                            "            //课堂上有一个AI机器人,负责记录课堂内容\n" +
                            "            teacher.register(this);\n" +
                            "            teacher.register(student_01);\n" +
                            "            teacher.register(student_02);\n" +
                            "            teacher.register(student_03);\n" +
                            "            teacher.register(student_04);\n" +
                            "            //布置作业\n" +
                            "            teacher.notify(\"抄课文\");\n" +
                            "        }\n" +
                            "\n" +
                            "        @Override\n" +
                            "        public void update(String msg) {\n" +
                            "            System.out.println(\"本节课的作业是:\" + msg);\n" +
                            "        }\n" +
                            "    }\n" +
                            "\n" +
                            "    public static void main(String[] args) {\n" +
                            "        //开始一节新的课程\n" +
                            "        new TeachClass().startClass();\n" +
                            "    }\n" +
                            "}\n" +
                            "输出结果:\n" +
                            "本节课的作业是:抄课文\n" +
                            "张三收到作业:抄课文\n" +
                            "李四收到作业:抄课文\n" +
                            "王五收到作业:抄课文\n" +
                            "赵六收到作业:抄课文")
        }
    }
}