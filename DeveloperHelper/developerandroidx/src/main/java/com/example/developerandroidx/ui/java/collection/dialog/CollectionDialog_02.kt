package com.example.developerandroidx.ui.java.collection.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 7/24/20 9:32 AM
 * 参考：
 * 描述：
 */
class CollectionDialog_02 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.setText("Set集合")
            esv_content.addBody("Set是最简单的一种集合,集合中的对象不按特定方式排序,并且没有重复对象.Set接口主要有两个实现类:" +
                    "HashSet和TreeSet.HashSet类按照哈希算法来存取集合中的对象,存取速度比较快.HashSet类还有一个子类LinkedHashSet类,它不仅实现的" +
                    "哈希算法,而且实现了链表数据结构,链表数据结构能够提高插入和删除元素的性能.TreeSet实现了SortSet接口,具有排序功能.")
            esv_content.addTitle_2("HashSet类")
            esv_content.addBody("HashSet类按照哈希算法来存取集合中的对象.既有很好的存取和查找性能.当向集合中添加一个对象时,HashSet会调用对象的" +
                    "hashCode()方法获得哈希码,然后根据哈希码进一步计算出对象在集合中存放的位置.")
            esv_content.addBody("在Object类中定义了hashCode()和equals()方法,Object类的equals()方法按照对象的内存地址比较是否相等," +
                    "如果equals()返回true,那么对象的hashCode也肯定相同.")
            esv_content.addBody("如果用户定义的类覆盖了Object类的equals()方法,但是没有覆盖hashCode()方法,就会导致HashSet无法正常工作.")
            esv_content.addTitle_2("TreeSet类")
            esv_content.addBody("TreeSet类实现了SortSet接口,能够对集合中的对象进行排序." + esv_content.tab +
                    "当向TreeSet集合中添加一个对象时,会把它插入到有序的对象序列中,TreeSet支持两种排序方式:自然排序和客户化排序,默认TreeSet采用自然排序.")
            esv_content.addBoldBody("1.自然排序")
            esv_content.addBody("在JDK类库中,有一部分类实现了Comparable接口,Comparable接口有一个compareTo(Object o)方法,它返回整数类型," +
                    "对于表达式x.compareTo(y),如果返回0,表示x和y的值相等.如果返回值大于0,表示x大于y,如果返回值小于0,表示x小于y.")
            esv_content.addBody("使用自然排序时,只能向TreeSet集合中添加同类型的对象,并且这些对象的类必须实现了Comparable接口.")
            esv_content.addBoldBody("2.客户化排序")
            esv_content.addBody("在构造TreeSet的实例时,调用它的TreeSet(Comparator comparator)构造方法,把Comparator的实现类的对象作为参数" +
                    "赋进去.")
        }
    }
}