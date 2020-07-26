package com.example.developerandroidx.ui.java.collection.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/25/20 11:28 AM
 * 参考：
 * 描述：
 */
class CollectionDialog_05 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "Map映射"
            esv_content.addBody("Map是一种把键对象和值对象进行映射的集合.它的每一个元素都包含一个键对象和值对象,而值对象可以是Map类型" +
                    ",依次类推,这样就形成了多级映射.")
            esv_content.addBody("Map集合中的键对象不允许重复,对于值对象则没有唯一性要求.")
            esv_content.addBody("Map的entrySet()返回一个Set集合,在这个集合中存放了Map.Entry类型的元素." +
                    "每个Map.Entry对象代表Map中的一个键值对.Map.Entry对象的getKey()方法返回键值对的键,getValue()方法" +
                    "返回键值对的值.")
            esv_content.addBoldBody("Map有两种比较常规的实现:HashMap和TreeMap.HashMap按照哈希值来存取键对象.有很好的的存取性.")
            esv_content.addBoldBody("TreeMap实现了SortedMap接口,能对键对象进行排序.")
        }
    }
}