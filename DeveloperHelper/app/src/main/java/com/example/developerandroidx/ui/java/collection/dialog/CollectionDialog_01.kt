package com.example.developerandroidx.ui.java.collection.dialog

import android.content.Context
import com.example.developerandroidx.R
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/24/20 8:45 AM
 * 参考：
 * 描述：
 */
class CollectionDialog_01 : FunctionDialogInterface {

    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.setText("集合概述")
            esv_content.addTitle_2("Java数组")
            esv_content.addBody("Java数组的长度是固定的,在同一个数组中只能存放相同类型的数据.数组可以存放基本数据类型," +
                    "也可以存放引用数据类型." + esv_content.tab +
                    "在创建Java数组时,必须明确指定数组的长度,数组一旦被创建,其长度就不能被改变.在许多应用场合,一组数据的数目不是固定的,为了使" +
                    "程序能方便的存储和操作数目 不固定的一组数据,JDK提供了Java集合.")
            esv_content.addTitle_2("Java集合")
            esv_content.addBody("所有的Java集合类都位移java.util包中.与Java数组不同,Java集合中不能存放基本类型数据,而只能存放对象的引用." +
                    "Java集合主要分为4中类型:")
            esv_content.addBoldBody("Set(集):集合中的对象不按特定方式排序,并且没有重复的对象.它的有些实现类能对集合中的对象按特定方式排序.")
            esv_content.addBoldBody("List(列表):集合中的对象按照索引位置排序.可以有重复对象,允许按照对象在集合中的索引位置" +
                    "检索对象,与数组有些相似.")
            esv_content.addBoldBody("Queue(队列):集合中的对象按照先进先出的规则来排序,在队列的尾部添加元素,在队列的头部删除元素,可以有" +
                    "重复对象.双向队列则允许在队列的头部和尾部添加和删除元素.")
            esv_content.addBoldBody("Map(映射):集合中的每个元素包含一对键值对,集合中没有重复的键对象,值对象可以重复.它的有些实现类" +
                    "能对集合中的键对象进行排序.")
            esv_content.addImage(R.mipmap.image_collection, 500)
        }
    }
}