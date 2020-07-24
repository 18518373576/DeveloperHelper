package com.example.developerandroidx.ui.java.collection.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/24/20 10:21 AM
 * 参考：
 * 描述：
 */
class CollectionDialog_03 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "List列表"
            esv_content.addBody("List的主要特征是其元素以线性方式存储,集合中允许存放重复对象,List接口的主要实现类包括:")
            esv_content.addBoldBody("(1)ArrayList:ArrayList代表长度可变的数组,允许对元素进行快速的随机访问.但是想ArrayList中插入和" +
                    "删除元素的速度较慢.")
            esv_content.addBoldBody("(2)LinkedList:在实现中采用链表结构,对顺序访问进行了优化,向List中插入和删除元素的速度较快,随机访问则相对较慢." +
                    "LinkedList单独具有addFirst(),addLast(),getFirst(),getLast(),removeFirst(),removeLast()方法,这些方法使得LinkedList可以作为" +
                    "堆栈,队列,和双向队列使用.")
            esv_content.addTitle_2("为列表排序")
            esv_content.addBody("List只能对集合中的对象按照索引位置排序,如果希望对List中的对象按照其他特定的方式排序,可以借助Comparator接口和" +
                    "Collections类.Collections类是Java集合中的辅助类.它提供了操作集合的各种静态方法,其中sort()方法用于对List中的对象进行排序:")
            esv_content.addBoldBody("(1)sort(List list):对List中的对象进行自然排序." + esv_content.tab +
                    "(2)sort(List list , Comparator comparator):对List中的对象进行客户化排序.")
            esv_content.addTitle_2("ListIterator类")
            esv_content.addBody("List的listIterator()方法返回一个ListIterator对象,ListIterator接口继承了Iterator接口,此外还提供了专门操作列表的" +
                    "方法:")
            esv_content.addBoldBody("(1)add():向列表添加一个元素." + esv_content.tab +
                    "(2)hasNext():判断列表中是否还有下一个元素." + esv_content.tab +
                    "(3)hasPrevious():判断列表中是否还有上一个元素." + esv_content.tab +
                    "(4)next():返回列表中的下一个元素." + esv_content.tab +
                    "(5)previous():返回列表中的上一个元素.")
            esv_content.addTitle_2("获得固定长度的List对象")
            esv_content.addBody("java.utils.Arrays类的asList()方法能够把一个Java数组包装为一个List对象,这个List对象代表一个固定长度的数组." +
                    "由于数组程度不能该表,因此使用add()或remove()方法时会抛出运行时异常.")
        }
    }
}