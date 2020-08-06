package com.example.developerandroidx.ui.java.array

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/23/20 9:47 AM
 * 参考：
 * 描述：
 */
class ArrayDialog : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "数组"
            esv_content.addBody("数组是指一组数据的集合,数组中的每个数据称为元素.在Java中数据也是对象.数组中的元素可以是任意类型." +
                    "但同一个数组只能存放相同类型的元素.创建数组大致包括如下步奏:")
            esv_content.addBoldBody("(1)声明一个数组类型的引用变量,简称数组变量." + esv_content.tab +
                    "(2)用new语句构造数组的实例.new语句为数组分配内存,并且为数组中的每个元素赋予默认值." + esv_content.tab +
                    "(3)初始化,即为数组中的每个元素设置合适的初始值.")
            esv_content.addTitle_2("数组实用类:Arrays")
            esv_content.addBody("在java.util包中,有一个用于操作数组的实用类:Arrays.它提供了一些了静态方法:" + esv_content.tab +
                    "equals():比较两个数组是否相同." + esv_content.tab +
                    "fill():向数组中填充数据." + esv_content.tab +
                    "sort():把数组中的元素按升序排列." + esv_content.tab +
                    "parallelSort():开启多个线程,以并发的方式对数组中的元素进行排序,提高排序效率." + esv_content.tab +
                    "binarySearch():按照二分查找算法,查找数组中值与给定数据相同的元素,在使用该方法时,必须保证数组中的元素已经按照升序排列." + esv_content.tab +
                    "asList():把数组转换成一个List对象,将其返回." + esv_content.tab +
                    "toString():返回包含数组中所有元素信息的字符串.")
        }
    }
}