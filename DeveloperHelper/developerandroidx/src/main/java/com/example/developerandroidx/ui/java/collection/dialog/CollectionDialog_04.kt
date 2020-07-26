package com.example.developerandroidx.ui.java.collection.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/24/20 12:49 PM
 * 参考：
 * 描述：
 */
class CollectionDialog_04 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "队列"
            esv_content.addBody("从JDK5开始,用java.util.Queue接口表示队列,队列的特点是向末尾添加元素,从队列头部删除元素.队列中允许有重复元素.")
            esv_content.addBoldBody("Queue具有以下加入元素的方法:")
            esv_content.addBody("(1)boolean add(E e)" + esv_content.tab +
                    "(2)boolean offer(E e)")
            esv_content.addBody("以上两个方法都是向队列末尾添加元素,如果操作成功就返回true.如果队列已满add方法会抛出异常,而offer方法返回false." +
                    "大多数队列容量都不受限制,允许无限扩容,所以add元素一般不会发生异常.")
            esv_content.addBoldBody("Queue具有以下删除元素的方法:")
            esv_content.addBody("(1)E remove()" + esv_content.tab +
                    "(2)E poll()")
            esv_content.addBody("以上两个方法都会删除头部元素,如果队列为空remove()会抛出异常,而poll()方法返回null.")
            esv_content.addBoldBody("Queue具有以下方法获取元素")
            esv_content.addBody("(1)E element()" + esv_content.tab +
                    "(2)E peek()")
            esv_content.addBody("以上两个方法都会返回头部元素,但不删除它们.如果队列为空element()方法会抛出异常." +
                    "peek()方法返回null.")
            esv_content.addTitle_2("Deque(双向队列)")
            esv_content.addBody("Queue是单向队列,它有一个子接口Deque,表示双向队列.双向队列的特点是在头部和尾部都可以添加和删除元素.")
            esv_content.addTitle_2("PriorityQueue(优先级队列)")
            esv_content.addBody("优先级队列会按照排序的方式对队列中的元素进行排序和检索.因此加入到PriorityQueue的对象必须实现Comparable" +
                    "接口,提供元素排序时两个元素之间的比较规则.")

        }
    }
}