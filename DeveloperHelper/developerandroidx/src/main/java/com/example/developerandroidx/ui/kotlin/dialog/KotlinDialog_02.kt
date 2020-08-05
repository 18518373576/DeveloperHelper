package com.example.developerandroidx.ui.kotlin.dialog

import android.content.Context
import com.example.developerandroidx.projectInterface.FunctionDialogInterface
import com.example.developerandroidx.utils.DialogUtils

/**
 * 作者： zjf 8/3/20 3:33 PM
 * 参考：
 * 描述：
 */
class KotlinDialog_02 : FunctionDialogInterface {
    override fun show(context: Context) {
        DialogUtils.getInstance().showEsvDialog(context) { dialog, title, esv_content ->
            title.text = "数组和集合"
            esv_content.addTitle_2("声明数组")
            esv_content.addCode(
                    "fun main() {\n" +
                            "    //基本数据类型数组声明\n" +
                            "    var int_array: IntArray = intArrayOf(1, 2, 3)\n" +
                            "    var long_array: LongArray = longArrayOf(1, 2, 3)\n" +
                            "    var char_array: CharArray = charArrayOf('a', 'b', 'c')\n" +
                            "    //其他类型数组声明\n" +
                            "    var string_array: Array<String> = arrayOf(\"a\", \"b\", \"c\")\n" +
                            "\n" +
                            "    //数组元素的操作\n" +
                            "    println(string_array.size)\n" +
                            "    println(string_array[0])\n" +
                            "    //kotlin可以使用重载操作符,这里+作用是扩大数组长度并增加一个元素\n" +
                            "    println((string_array + \"d\")[3])\n" +
                            "}")
            esv_content.addTitle_2("集合")
            esv_content.addBody("Java中的集合类可以在kotlin中使用,不过,kotlin也有自己的集合类.与Java一样kotlin集合也包括三大类:${esv_content.tab}" +
                    "①Set${esv_content.tab}" +
                    "①List${esv_content.tab}" +
                    "①Map")
            esv_content.addCode(
                    "//在kotlin中java中的集合也可以使用\n" +
                            "var javaList = ArrayList<String>()\n" +
                            "var javaSet = HashSet<String>()\n" +
                            "var javaMap = HashMap<String, String>()")
            esv_content.addBody("kotlin对变量的修改操作很慎重,每个变量在定义时就必须指定能否修改.对于以上三种基本集合类,默认为只读,如果" +
                    "要修改的话需要加上Mutable前缀.")
            esv_content.addBoldBody("定义set集合")
            esv_content.addCode(
                    "//定义只读set\n" +
                            "var set = setOf(\"a\", \"b\")\n" +
                            "\n" +
                            "//定义可读写set,下面两个方法等价都返回LinkedHashSet()\n" +
                            "var mutableSet = mutableSetOf<String>()\n" +
                            "var linkedHashSet = linkedSetOf<String>()\n" +
                            "//返回HashSet(),在java中LinkedHashSet为HashSet子类\n" +
                            "var hashSet = hashSetOf<String>()\n" +
                            "\n" +
                            "//以上都是无序的set集合,kotlin中sortedSet为有序Set集合,等价于java的TreeSet\n" +
                            "var treeSet = sortedSetOf<String>()")
            esv_content.addBoldBody("定义List集合")
            esv_content.addCode(
                    "//listOf初始化只读集合,返回一个数组ArraysUtilJVM.asList(this)\n" +
                            "var kotlinList = listOf<String>(\"1\", \"2\")\n\n" +
                            "//可变集合的定义,下面两种但是等价,都返回ArrayList()\n" +
                            "var kotlinMutableList = mutableListOf<String>()\n" +
                            "var arrayList = arrayListOf<String>()")
            esv_content.addBoldBody("定义Map集合")
            esv_content.addCode(
                    "//定义只读map,使用to初始化\n" +
                            "var map = mapOf<String, String>(\"A\" to \"a\", \"B\" to \"b\")\n\n" +
                            "//定义可读写map,下面两个方法等价,都返回LinkedHashMap(),使用Pair初始化\n" +
                            "var mutableMap = mutableMapOf(Pair(\"A\", \"a\"))\n" +
                            "var linkedHashMap = linkedMapOf<String, String>()\n\n" +
                            "//定义hashMap\n" +
                            "var hashMap = hashMapOf<String, String>()\n\n" +
                            "//定义对key排序的map,等价于java的TreeMap\n" +
                            "var sortMap = sortedMapOf<String, String>()")

            esv_content.addTitle_2("集合的遍历")
            esv_content.addCode(
                    "//集合的遍历\n" +
                            "var list = mutableListOf<String>(\"a\", \"b\", \"c\", \"d\")\n" +
                            "\n" +
                            "println(\"使用for-in循环\")\n" +
                            "for (item in list) {\n" +
                            "    print(\"${'$'}item \")\n" +
                            "}\n" +
                            "\n" +
                            "println(\"\\n使用迭代器\")\n" +
                            "var it = list.iterator()\n" +
                            "while (it.hasNext()) {\n" +
                            "    print(\"${'$'}{it.next()} \")\n" +
                            "}\n\n" +
                            "println(\"\\n使用forEach遍历\")\n" +
                            "//使用匿名内部类实现Consumer接口\n" +
                            "list.forEach {\n" +
                            "    print(\"${'$'}it \")\n" +
                            "}")
        }
    }
}